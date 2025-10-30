package com.techinner.TechInner.controller;

import com.techinner.TechInner.dto.ResetPasswordRequest;
import com.techinner.TechInner.entity.Administrator;
import com.techinner.TechInner.entity.Table;
import com.techinner.TechInner.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {

    @Autowired
    private AdministratorService service;

//    ===============================
//    MÉTODOS PARA GERENCIAR MESAS
//    ===============================

    @PostMapping("/tables")
    public ResponseEntity<Table> saveTable
            (@RequestBody Table table){
        return ResponseEntity.ok().body(service.saveTable(table));
    }

    @GetMapping("/tables")
    public ResponseEntity<List<Table>> getAllTable(){
        return ResponseEntity.ok().body(service.getAllTable());
    }

    @GetMapping("/tables/{id}")
    public ResponseEntity<Table> getSingleTable
            (@PathVariable int id){
        return ResponseEntity.ok().body(service.getSingleTable(id));
    }

    @DeleteMapping("/tables/{id}")
    public ResponseEntity<String> deleteTable
            (@PathVariable int id){
        return ResponseEntity.ok().body(service.deleteTable(id));
    }

//    @PutMapping("/tables/resetPassword")
//    public ResponseEntity<Table> resetPassword
//            (@RequestBody ResetPasswordRequest request){
//        return ResponseEntity.ok(service.resetPassword(request.getUsername(), request.getNewPassword()));
//    }

//    =======================================
// Método atualizado. Usando DTO para maior segurança dos dados setados no método
//    MÉTODOS PARA GERENCIAR ADMINISTRADORES
//    =======================================

    @PostMapping("/admin")
    public ResponseEntity<Administrator> registerAdministrator
            (@RequestBody Administrator administrator){
        return ResponseEntity.ok().body(service.registerAdministrator(administrator));
    }




}
