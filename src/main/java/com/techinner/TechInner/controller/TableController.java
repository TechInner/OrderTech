package com.techinner.TechInner.controller;

import com.techinner.TechInner.entity.Table;
import com.techinner.TechInner.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    private TableService service;

    @GetMapping("/find-id/{id}")
    public ResponseEntity<Table> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Table>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping("/register")
    public ResponseEntity<Table> register(@RequestBody Table table) {
        return ResponseEntity.ok().body(service.register(table));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Table> update(@PathVariable String id, @RequestBody Table table) {
        return ResponseEntity.ok().body(service.update(id, table));
    }
}
