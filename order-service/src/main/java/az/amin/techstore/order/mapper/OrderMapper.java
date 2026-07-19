package az.amin.techstore.order.mapper;

import org.example.orderms.dao.OrderEntity;
import org.example.orderms.model.OrderDto;
import org.example.orderms.model.request.command.CartCheckedOutEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderEntity toEntity(OrderDto orderDto);

    OrderEntity toEntityFromCreate(CartCheckedOutEvent cartCheckedOutEvent);

    OrderDto toDto(OrderEntity orderEntity);
}
