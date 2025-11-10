package com.techinner.TechInner.dto.administrator;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdministratorRequestDTO {

    private String name;
    private String cpf;
    private String password;
}
