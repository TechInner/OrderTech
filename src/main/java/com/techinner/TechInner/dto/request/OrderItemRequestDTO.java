package com.techinner.TechInner.dto.request;

import com.techinner.TechInner.entity.Order;
import lombok.*;

import java.util.List;


public class OrderItemRequestDTO {

    private Integer menuId;
    private Integer quantity;
    private String observation;




    public OrderItemRequestDTO(){

    }



    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
