package com.techinner.TechInner.service;

import com.techinner.TechInner.entity.Administrator;
import com.techinner.TechInner.entity.Table;
import com.techinner.TechInner.exceptions.administrator.AdministratorNotFound;
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

    public Administrator findById(int id){
      return repository.findById(id).orElseThrow(
                () -> new AdministratorNotFound("Administrator not Found")
        );
    }

    public Administrator registerAdministrator(Administrator administrator){
        if (repository.existsBycpf(administrator.getCpf())){
            throw new RuntimeException("Administrator cadastred.");
        }
        return repository.save(administrator);
    }


}
