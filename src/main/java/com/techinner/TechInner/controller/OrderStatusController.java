//package com.techinner.TechInner.controller;
//
//import com.techinner.TechInner.entity.OrderStatus;
//import com.techinner.TechInner.service.OrderStatusService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/order-status")
//public class OrderStatusController {
//
//    @Autowired
//    private OrderStatusService service;
//
//    @GetMapping("/find-id/{id}")
//    public ResponseEntity<OrderStatus> findById(@PathVariable String id){
//        return ResponseEntity.ok().body(service.findById(id));
//    }
//
//    @GetMapping("/find-all")
//    public ResponseEntity<List<OrderStatus>> findAll(){
//        return ResponseEntity.ok().body(service.findAll());
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<OrderStatus> register(@RequestBody OrderStatus orderStatus){
//        return ResponseEntity.ok().body(service.register(orderStatus));
//    }
//
//    @DeleteMapping("delete/{id}")
//    public ResponseEntity<?> delete(@PathVariable String id){
//        service.delete(id);
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping("update/{id}")
//    public ResponseEntity<OrderStatus> update(@PathVariable String id, @RequestBody OrderStatus orderStatus){
//        return ResponseEntity.ok().body(service.update(id, orderStatus));
//    }
//
//
//
//}
