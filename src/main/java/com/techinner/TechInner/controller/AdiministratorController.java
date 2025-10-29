package com.techinner.TechInner.controller;

import com.techinner.TechInner.entity.Table;
import com.techinner.TechInner.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrator")
public class AdiministratorController {

    @Autowired
    private AdministratorService service;

    @PostMapping("/saveTable")
    public ResponseEntity<Table> saveTable
            (@RequestBody Table table){
        return ResponseEntity.ok().body(service.saveTable(table));
    }

    @GetMapping("/getalltable")
    public ResponseEntity<List<Table>> getAllTablhe(){
        return ResponseEntity.ok().body(service.getAllTable());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Table> getSingleTable
            (@PathVariable int id){
        return ResponseEntity.ok().body(service.getSingleTable(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTable
            (@PathVariable int id){
        return ResponseEntity.ok().body(service.deleteTable(id));
    }

    @PutMapping("/{username}/{newPassword}")
    public ResponseEntity<Table> resetPassword
            (@PathVariable String username, @PathVariable String newPassword){
        return ResponseEntity.ok().body(service.resetPassword(username, newPassword));
    }

}
