package com.techinner.TechInner.dto.request;

public class OrderStatusRequestDTO {

    private String description;

    public OrderStatusRequestDTO() {
    }

    public OrderStatusRequestDTO(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
