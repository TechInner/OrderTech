package com.techinner.TechInner.controller;

import com.techinner.TechInner.entity.Order;
import com.techinner.TechInner.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/find-id/{id}")
    public ResponseEntity<Order> findById(@PathVariable String id){
        return ResponseEntity.ok().body(service.findById(id));
    }

   @GetMapping("/find-all")
    public ResponseEntity<List<Order>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
   }

   @PostMapping("/register")
    public ResponseEntity<Order> register(@RequestBody Order order){
        return ResponseEntity.ok().body(service.register(order));
   }

   @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
   }

   @PutMapping("updateOrder/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable String id, @RequestBody Order order){
        return ResponseEntity.ok().body(service.updateOrder(id, order));
   }

   @PutMapping("updateOrderItems/{id}")
    public ResponseEntity<Order> updateOrderItems(@PathVariable String id, @RequestBody Order order){
        return ResponseEntity.ok().body(service.updateOrderItems(id, order));
   }

}
