package com.techinner.TechInner.controller;

import com.techinner.TechInner.dto.request.OrderStatusRequestDTO;
import com.techinner.TechInner.dto.response.OrderStatusResponseDTO;
import com.techinner.TechInner.entity.OrderStatus;
import com.techinner.TechInner.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-status")
public class OrderStatusController {

    @Autowired
    private OrderStatusService service;

    @GetMapping("/find-id/{id}")
    public ResponseEntity<OrderStatusResponseDTO> findById(@PathVariable String id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<OrderStatusResponseDTO>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping("/register")
    public ResponseEntity<OrderStatusResponseDTO> register(@RequestBody OrderStatusRequestDTO orderStatus){
        return ResponseEntity.ok().body(service.register(orderStatus));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<OrderStatusResponseDTO> update(@PathVariable String id, @RequestBody OrderStatusRequestDTO orderStatus){
        return ResponseEntity.ok().body(service.update(id, orderStatus));
    }



}
