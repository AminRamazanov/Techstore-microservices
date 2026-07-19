package az.amin.techstore.payment.mapper;


import org.example.paymentms.dao.PaymentEntity;
import org.example.paymentms.model.events.OrderCreatedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "status", ignore = true)
    PaymentEntity fromOrderCreated(OrderCreatedEvent orderCreatedEvent);
}
