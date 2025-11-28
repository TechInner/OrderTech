//package com.techinner.TechInner.mapper.implementation_old;
//
//import com.techinner.TechInner.dto.response.OrderItemResponsoDTO;
//import com.techinner.TechInner.dto.request.OrderRequestDTO;
//import com.techinner.TechInner.dto.response.OrderResponseDTO;
//import com.techinner.TechInner.entity.Order;
//import com.techinner.TechInner.entity.OrderItem;
//import com.techinner.TechInner.entity.OrderStatus;
//import com.techinner.TechInner.entity.Table;
//
//import java.util.stream.Collectors;
//
//public class OrderMapper {
//
//    public static Order toEntity(OrderRequestDTO dto, OrderStatus status, Table table){
//        Order order = new Order();
//        order.setOrderStatus(status);
//        order.setTable(table);
//        return order;
//    }
//
//    public static OrderResponseDTO toResponse(Order order){
//        return OrderResponseDTO.builder()
//                .id(order.getId())
//                .status(order.getOrderStatus().getDescription())
//                .dtOrder(order.getDtOrder())
//                .tableNumber(order.getTable().getUsername())
//                .items(order.getOrderItems() != null ?
//                        order.getOrderItems().stream()
//                                .map(OrderMapper::toItemResponse)
//                                .collect(Collectors.toList())
//                        :null)
//                .build();
//    }
//
//    private static OrderItemResponsoDTO toItemResponse(OrderItem item){
//        return OrderItemResponsoDTO.builder()
//                .id(item.getId())
//                .menuName(item.getMenu().getName())
//                .quantity(item.getQuantity())
//                .unitPrice(item.getPrice())
//                .build();
//    }
//
//
//
//}
