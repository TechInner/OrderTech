package com.techinner.TechInner.controller;

import com.techinner.TechInner.dto.request.ResetPasswordRequest;
import com.techinner.TechInner.dto.request.TableRequestDTO;
import com.techinner.TechInner.dto.response.TableResponseDTO;
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
    public ResponseEntity<TableResponseDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<TableResponseDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping("/register")
    public ResponseEntity<TableResponseDTO> register(@RequestBody TableRequestDTO dto) {
        return ResponseEntity.ok().body(service.register(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<TableResponseDTO> update(@PathVariable String id,
                                                   @RequestBody TableRequestDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @PatchMapping("reset-password/{id}")
    public ResponseEntity<String> restPassword(@PathVariable String id,
                                                             @RequestBody ResetPasswordRequest dto)
    {
      return  ResponseEntity.ok().body(service.resetPassword(id, dto));

    }
}
