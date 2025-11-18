package com.techinner.TechInner.dto.order;

import com.techinner.TechInner.dto.orderitem.OrderItemResponsoDTO;
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
    private List<OrderItemResponsoDTO> items;

}
