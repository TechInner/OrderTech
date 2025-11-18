package com.techinner.TechInner.dto.order;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDTO {

    private Integer idStatus;
    private Integer idTable;
}
