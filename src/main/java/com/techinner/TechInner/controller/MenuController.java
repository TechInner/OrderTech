package com.techinner.TechInner.controller;

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
    public ResponseEntity<Menu> findById(@PathVariable String id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/find-all")
    private ResponseEntity<List<Menu>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping("/register")
    public ResponseEntity<Menu> register(@RequestBody Menu menu){
        return ResponseEntity.ok().body(service.register(menu));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Menu> update(@PathVariable String id, @RequestBody Menu menu){
        return ResponseEntity.ok().body(service.update(id, menu));
    }
}
