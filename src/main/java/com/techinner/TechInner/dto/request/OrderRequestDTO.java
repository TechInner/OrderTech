package com.techinner.TechInner.dto.request;


import java.util.List;

public class OrderRequestDTO {

   private Integer idStatus;
   private Integer idTable;
   private List<OrderItemRequestDTO> items;

   public OrderRequestDTO(){

   }

    public List<OrderItemRequestDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequestDTO> items) {
        this.items = items;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public Integer getIdTable() {
        return idTable;
    }

    public void setIdTable(Integer idTable) {
        this.idTable = idTable;
    }
}
