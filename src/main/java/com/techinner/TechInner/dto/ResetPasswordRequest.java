package com.techinner.TechInner.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

//DTO criada para não passar senha da Mesa na URL do navegador e usar RequestBody para acessar o método resetPassword
public class ResetPasswordRequest {
    private String username;
    private String newPassword;

}
