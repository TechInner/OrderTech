package com.techinner.TechInner.dto.administrator;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdministratorRequestDTO {

    //Representa o que será enviado pelo usuário na requisição
    private String name;
    private String cpf;
    private String password;
}
