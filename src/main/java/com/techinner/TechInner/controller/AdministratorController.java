package com.techinner.TechInner.controller;

import com.techinner.TechInner.dto.ResetPasswordRequest;
import com.techinner.TechInner.dto.administrator.AdministratorRequestDTO;
import com.techinner.TechInner.dto.administrator.AdministratorResponseDTO;
import com.techinner.TechInner.entity.Administrator;
import com.techinner.TechInner.entity.Table;
import com.techinner.TechInner.mapper.AdministratorMapper;
import com.techinner.TechInner.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {

    @Autowired
    private AdministratorService service;

    @GetMapping("/find-id/{id}")
    private ResponseEntity<AdministratorResponseDTO> findById(@PathVariable String id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<AdministratorResponseDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/register")
    public ResponseEntity<AdministratorResponseDTO> register
            (@RequestBody AdministratorRequestDTO dto){
        return ResponseEntity.ok(service.register(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AdministratorResponseDTO> update(@PathVariable String id, @RequestBody AdministratorRequestDTO dto){
               return ResponseEntity.ok(service.update(id, dto));
    }





}
