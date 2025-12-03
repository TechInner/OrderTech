package com.techinner.TechInner.controller;

import com.techinner.TechInner.dto.request.AdministratorRequestDTO;
import com.techinner.TechInner.dto.response.AdministratorResponseDTO;
import com.techinner.TechInner.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
