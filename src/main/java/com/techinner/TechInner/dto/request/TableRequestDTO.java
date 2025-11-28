package com.techinner.TechInner.dto.request;

public class TableRequestDTO
{
   private String username;
   private String password;

   public TableRequestDTO(){

   }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
