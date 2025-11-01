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

    @PostMapping("/register")
    public ResponseEntity<Administrator> registerAdministrator
            (@RequestBody Administrator administrator){
        return ResponseEntity.ok().body(service.registerAdministrator(administrator));
    }

    @GetMapping("/{id}")
    private ResponseEntity<Administrator> findByIdAdministrador(@PathVariable int id){

        return ResponseEntity.ok().body(service.findById(id));
    }




}
