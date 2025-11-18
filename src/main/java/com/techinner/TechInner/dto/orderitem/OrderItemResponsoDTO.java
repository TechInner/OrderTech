package com.techinner.TechInner.dto.orderitem;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponsoDTO {

    private int id;
    private String menuName;
    private int quantity;
    private Double unitPrice;
}
