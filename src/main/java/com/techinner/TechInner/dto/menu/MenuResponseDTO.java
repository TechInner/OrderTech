package com.techinner.TechInner.dto.menu;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuResponseDTO {

    private int id;
    private String name;
    private Double price;
    private String description;
}
