package com.techinner.TechInner.service;

import com.techinner.TechInner.entity.Table;
import com.techinner.TechInner.repository.TableRepository;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {

    @Autowired
    private TableRepository repository;

//    SaveTable
    public Table saveTable (Table table){
        return repository.save(table);
    }

//    GetAllTable
    public List<Table> getAllTable(){

        return repository.findAll();

    }

//    GetSingleTable
    public Table getSingleTable(int id){
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException("Table not found."));
    }

//    DeleteTable
    public String deleteTable(int id){
        repository.findById(id).orElseThrow(() ->
                new RuntimeException("Table not found."));
        repository.deleteById(id);

        return "Table deleted successfully.";
    }

// ResetPassword
    public Table resetPassword(String username, String newPassword){
        Table table = repository.findByUsername(username).orElseThrow(() ->
                new RuntimeException("Table not found."));
        table.setPassword(newPassword);
        return repository.save(table);
    }


}
