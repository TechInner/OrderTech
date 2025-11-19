package com.techinner.TechInner.dto.administrator;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdministratorResponseDTO {

    //Usados para enviar dados de resposta a requisição. Envia apenas o necessário
    private String name;
    private String cpf;
}
