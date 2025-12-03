package com.techinner.TechInner.dto.response;


public class AdministratorResponseDTO {

    //Usados para enviar dados de resposta a requisição. Envia apenas o necessário
    private String name;
    private String cpf;

    public AdministratorResponseDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
