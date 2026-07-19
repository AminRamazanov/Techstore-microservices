package az.amin.techstore.order.service;

import org.example.orderms.dao.OutboxEvent;
import org.example.orderms.model.OrderDto;
import org.example.orderms.model.request.command.CartCheckedOutEvent;
import org.example.orderms.model.event.OrderStatusEvent;

public interface OrderService {
    void createOrder(CartCheckedOutEvent cartCheckedOutEvent);

    void updateStatus(OrderStatusEvent orderStatusEvent);

    OrderDto getOrder(Long id);
}
