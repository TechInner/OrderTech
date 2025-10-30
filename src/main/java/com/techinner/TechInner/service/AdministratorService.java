package com.techinner.TechInner.service;

import com.techinner.TechInner.entity.Administrator;
import com.techinner.TechInner.entity.Table;
import com.techinner.TechInner.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AdministratorService {

//    ===============================
//    MÉTODOS PARA GERENCIAR MESAS
//    ===============================

    @Autowired
    private AdministratorRepository repository;

    private final TableService tableService;

    public AdministratorService(TableService tableService) {
        this.tableService = tableService;
    }

    public Table saveTable(Table table){
        return tableService.saveTable(table);
    }

    public List<Table> getAllTable(){
        return tableService.getAllTable();
    }

    public Table getSingleTable(int id){
        return tableService.getSingleTable(id);
    }

    public String deleteTable(int id){
        return tableService.deleteTable(id);
    }

    public Table resetPassword(String username, String newPassword){
        return tableService.resetPassword(username, newPassword);
    }

//    =======================================
//    MÉTODOS PARA GERENCIAR ADMINISTRADORES
//    =======================================

    public Administrator registerAdministrator(Administrator administrator){
        if (repository.existsBycpf(administrator.getCpf())){
            throw new RuntimeException("Administrator cadastred.");
        }
        return repository.save(administrator);
    }


}
