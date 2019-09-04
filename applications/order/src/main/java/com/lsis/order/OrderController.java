package com.lsis.order;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    OrderRepository orderRepository;

    @Value("${customer.service.application.url}")
    private  String customerURL ;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderInfo> findById(@PathVariable Long id){
         return new ResponseEntity(getOrderInfo(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity list(){
        return new ResponseEntity(orderRepository.findAll(),HttpStatus.ACCEPTED);
    }

    @PostMapping
    public String save(@RequestBody Order order){
        try {
            orderRepository.save(order);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "Order Added";
    }


    private OrderInfo getOrderInfo(Long id) {
        OrderInfo info = new OrderInfo();
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            Customer customer = new RestTemplate().getForObject(customerURL+"/"+id,Customer.class);
            info.setOrder(order.get());
            info.setCustomer(customer);
        }
        return info;
    }
}
