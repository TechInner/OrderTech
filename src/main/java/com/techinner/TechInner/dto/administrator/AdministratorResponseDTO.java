package com.techinner.TechInner.dto.administrator;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdministratorResponseDTO {

    private int id;
    private String name;
    private String cpf;
}
