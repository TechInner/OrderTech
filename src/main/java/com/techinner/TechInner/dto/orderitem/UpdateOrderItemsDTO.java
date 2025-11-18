package com.techinner.TechInner.dto.orderitem;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateOrderItemsDTO {
    private List<OrderItemRequestDTO> items;
}
