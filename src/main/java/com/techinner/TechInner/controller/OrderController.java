package com.techinner.TechInner.controller;

import com.techinner.TechInner.dto.order.OrderRequestDTO;
import com.techinner.TechInner.dto.order.OrderResponseDTO;
import com.techinner.TechInner.dto.orderitem.OrderItemRequestDTO;
import com.techinner.TechInner.dto.orderitem.UpdateOrderItemsDTO;
import com.techinner.TechInner.entity.Order;
import com.techinner.TechInner.service.OrderService;
import jakarta.persistence.criteria.CriteriaBuilder;
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
    public ResponseEntity<OrderResponseDTO> findById(@PathVariable String id){
        return ResponseEntity.ok(service.findById(id));
    }

   @GetMapping("/find-all")
    public ResponseEntity<List<OrderResponseDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
   }

   @PostMapping("/register")
    public ResponseEntity<OrderResponseDTO> register(@RequestBody OrderRequestDTO dto){
        return ResponseEntity.ok(service.registerOrder(dto));
   }

   @PostMapping("/{idOrder}/items")
   public void addItem(
           @PathVariable String idOrder,
           @RequestBody OrderItemRequestDTO dto
   ){
        service.addOrder(idOrder,dto);
   }

   @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
   }

   @PatchMapping("/{id}/status/{statusId}")
    public ResponseEntity<OrderResponseDTO> updateStatusOrder
           (@PathVariable String id, @PathVariable Integer statusId){
        return ResponseEntity.ok(service.updateStatusOrder(id, statusId));
   }

   @PutMapping("{id}/items")
    public ResponseEntity<OrderResponseDTO> updateOrderItems
           (@PathVariable String id, @RequestBody UpdateOrderItemsDTO dto){
        return ResponseEntity.ok(service.updateOrderItems(id, dto));
   }

}
