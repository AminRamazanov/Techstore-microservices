package az.amin.techstore.order.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.orderms.dao.InboxEvent;
import org.example.orderms.dao.OrderEntity;
import org.example.orderms.dao.OrderItemEntity;
import org.example.orderms.dao.OutboxEvent;
import org.example.orderms.dao.repository.InboxEventRepository;
import org.example.orderms.dao.repository.OrderRepository;
import org.example.orderms.dao.repository.OutboxRepository;
import org.example.orderms.enums.EventType;
import org.example.orderms.mapper.OrderMapper;
import org.example.orderms.model.OrderDto;
import org.example.orderms.model.request.command.CartCheckedOutEvent;
import org.example.orderms.model.request.command.CartCheckoutItem;
import org.example.orderms.model.event.OrderCreatedEvent;
import org.example.orderms.model.event.OrderStatusEvent;
import org.example.orderms.properties.RabbitPublisherProperties;
import org.example.orderms.service.OrderService;
import org.example.orderms.util.JsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InboxEventRepository inboxEventRepository;
    private final OutboxRepository outboxRepository;
    private final JsonUtil util;
    private final RabbitPublisherProperties rabbitPublisherProperties;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createOrder(CartCheckedOutEvent cartCheckedOutEvent) {
        log.info("Action.log.createOrder started");

        InboxEvent inboxEvent = new InboxEvent();

        if (inboxEventRepository.existsByEventId(cartCheckedOutEvent.getEventId())) {
            log.info("Duplicate command, skipping...");
            return;
        } else {
            inboxEvent.setEventId(cartCheckedOutEvent.getEventId());
            inboxEvent.setType(EventType.ORDER_CREATED);
            inboxEvent.setProcessed(false);
            inboxEventRepository.save(inboxEvent);
        }

        OrderEntity orderEntity = orderMapper.toEntityFromCreate(cartCheckedOutEvent);

        createOrderWithItems(cartCheckedOutEvent, orderEntity);

        inboxEvent.setProcessed(true);
        inboxEvent.setProcessedAt(LocalDateTime.now());

        log.info("Action.log.createOrder finished");
    }

    public void createOrderWithItems(CartCheckedOutEvent cartCheckedOutEvent, OrderEntity orderEntity){
        List<OrderItemEntity> items = new ArrayList<>();

        for (CartCheckoutItem item : cartCheckedOutEvent.getItems()) {

            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setPrice(item.getPrice());
            orderItem.setQuantity(item.getQuantity());

            orderItem.setTotal(
                    item.getPrice()
                            .multiply(item.getQuantity())
            );

            orderItem.setProductId(item.getProductId());
            orderItem.setProductName(item.getName());
            orderItem.setOrder(orderEntity);

            items.add(orderItem);
        }

        orderEntity.setItems(items);
        orderRepository.save(orderEntity);

        createOrderCreatedEvent(orderEntity, cartCheckedOutEvent);
    }

    public void createOrderCreatedEvent(OrderEntity orderEntity, CartCheckedOutEvent cartCheckedOutEvent){
        OrderCreatedEvent event = new OrderCreatedEvent();
        event.setAggregateId(orderEntity.getId());
        event.setAmount(orderEntity.getTotalAmount());
        event.setOrderId(orderEntity.getId());
        event.setCorrelationId(cartCheckedOutEvent.getCorrelationId());

        createOutbox(event, orderEntity);
    }

    public void createOutbox(OrderCreatedEvent event, OrderEntity orderEntity){
        OutboxEvent outbox = new OutboxEvent();
        outbox.setEventId(event.getEventId());
        outbox.setCorrelationId(event.getCorrelationId());
        outbox.setAggregateId(orderEntity.getId());
        outbox.setRoutingKey(rabbitPublisherProperties.getOrderCreatedKey());
        outbox.setPayload(util.toJson(event));
        outboxRepository.save(outbox);
    }

    @Override
    public void updateStatus(OrderStatusEvent orderStatusEvent) {
//        if (inboxEventRepository.existsByEventId(orderStatusEvent.get))
    }

    @Override
    public OrderDto getOrder(Long id) {
        return null;
    }
}
