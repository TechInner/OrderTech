package com.techinner.TechInner.controller;

import com.techinner.TechInner.dto.request.OrderRequestDTO;
import com.techinner.TechInner.dto.response.OrderItemResponseDTO;
import com.techinner.TechInner.dto.response.OrderResponseDTO;
import com.techinner.TechInner.dto.request.OrderItemRequestDTO;
import com.techinner.TechInner.dto.request.UpdateOrderItemsDTO;
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
    public ResponseEntity<OrderItemResponseDTO> updateOrderItems
           (@PathVariable String id, @RequestBody OrderItemRequestDTO dto){
        return ResponseEntity.ok().body(service.updateOrderItems(id, dto));
   }

   @GetMapping("/{id}/close_order")
    public ResponseEntity<String> closeOrder(
            @PathVariable String id,
            @RequestBody OrderItemRequestDTO dto
   ){
        return ResponseEntity.ok().body(service.closeOrder(id,dto));
   }

}
