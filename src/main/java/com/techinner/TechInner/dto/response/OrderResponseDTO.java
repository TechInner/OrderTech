package com.techinner.TechInner.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDTO {

    private int id;
    private String status;
    private LocalDate dtOrder;
    private String tableNumber;
    private List<OrderItemResponseDTO> items;

}
