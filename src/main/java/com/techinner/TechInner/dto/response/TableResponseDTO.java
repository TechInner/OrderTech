package com.techinner.TechInner.dto.response;

public class TableResponseDTO {

    private String username;

    public TableResponseDTO() {
    }

    public TableResponseDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
