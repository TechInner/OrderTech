package com.techinner.TechInner.controller;

import com.techinner.TechInner.dto.menu.MenuRequestDTO;
import com.techinner.TechInner.dto.menu.MenuResponseDTO;
import com.techinner.TechInner.entity.Menu;
import com.techinner.TechInner.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService service;

    @GetMapping("/find-id/{id}")
    public ResponseEntity<MenuResponseDTO> findById(@PathVariable String id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<MenuResponseDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/register")
    public ResponseEntity<MenuResponseDTO> register
            (@RequestBody MenuRequestDTO dto){
        return ResponseEntity.ok(service.register(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<MenuResponseDTO> update(@PathVariable String id, @RequestBody MenuRequestDTO dto){
        return ResponseEntity.ok(service.update(id, dto));
    }
}
