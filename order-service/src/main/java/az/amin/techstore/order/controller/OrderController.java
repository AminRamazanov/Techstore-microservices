package az.amin.techstore.order.controller;

import lombok.RequiredArgsConstructor;
import org.example.orderms.model.request.command.CartCheckedOutEvent;
import org.example.orderms.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public void getById(@PathVariable Long id){
        orderService.getOrder(id);
    }

    @PostMapping("/create")
    public void create(@RequestBody CartCheckedOutEvent cartCheckedOutEvent){
        orderService.createOrder(cartCheckedOutEvent);
    }
}
