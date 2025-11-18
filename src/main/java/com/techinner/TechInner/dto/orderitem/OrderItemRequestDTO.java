package com.techinner.TechInner.dto.orderitem;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemRequestDTO {

    private Integer menuId;
    private Integer quantity;
    private String observation;
}
