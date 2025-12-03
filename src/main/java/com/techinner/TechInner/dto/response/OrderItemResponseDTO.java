package com.techinner.TechInner.dto.response;

public class OrderItemResponseDTO {

       private String menuName;
       private int quantity;
       private Double unitPrice;

       public OrderItemResponseDTO(){

       }


    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
