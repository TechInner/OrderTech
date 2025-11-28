package com.techinner.TechInner.dto.request;

import lombok.*;

import java.util.List;


public class UpdateOrderItemsDTO {

    private List<OrderItemRequestDTO> items;

    public UpdateOrderItemsDTO() {
        ;
    }

    public List<OrderItemRequestDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequestDTO> items) {
        this.items = items;
    }
}
