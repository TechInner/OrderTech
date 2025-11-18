package com.techinner.TechInner.dto.menu;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuRequestDTO {

    private String name;
    private Double price;
    private String description;
}
