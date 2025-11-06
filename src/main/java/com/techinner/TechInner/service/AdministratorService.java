package com.techinner.TechInner.service;

import com.techinner.TechInner.entity.Administrator;
import com.techinner.TechInner.exceptions.BadRequestException;
import com.techinner.TechInner.exceptions.ConflictException;
import com.techinner.TechInner.exceptions.InternalServerErrorException;
import com.techinner.TechInner.exceptions.NotFoundException;
import com.techinner.TechInner.repository.AdministratorRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class AdministratorService {

//    ===============================
//    MÉTODOS PARA GERENCIAR MESAS
//    ===============================

    @Autowired
    private AdministratorRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Administrator findById(String id){

        try {
            if (id == null || id.trim().isEmpty()) {
                throw new BadRequestException("Id parameter is missing or empty");
            }

          Integer idParse = Integer.parseInt(id);

          return repository.findById(idParse).orElseThrow(
                    () -> new NotFoundException("Administrator not Found")
            );
        }catch (NumberFormatException e){
            throw new BadRequestException("ID must be a number");
        }

    }

    public List<Administrator> findAll(){

        List<Administrator> administratorList = repository.findAll();

        if (administratorList.isEmpty()) {
            throw new NotFoundException("No administrators found");
        }
        return administratorList;

    }

    public Administrator register(Administrator administrator){

        // Valida se as informações está nula ou vazia
        if (Stream.of(
                administrator.getCpf(),
                administrator.getName(),
                administrator.getPassword()
        ).anyMatch(v -> v == null || v.trim().isEmpty())){
            throw new BadRequestException("Insert the informations");
        }

        if (repository.existsBycpf(administrator.getCpf())){
            throw new ConflictException("Administrator already registered.");
        }

        String hashed = passwordEncoder.encode(administrator.getPassword());
        administrator.setPassword(hashed);

        return repository.save(administrator);
    }

    public void delete(String id){

       if (id == null || id.trim().isEmpty()){
           throw new BadRequestException("Id parameter is missing or empty");
       }
       try{
           Integer idParse = Integer.parseInt(id);

           Administrator adm = repository.findById(idParse).orElseThrow(()
                   -> new NotFoundException("Not administrator found")
           );

           repository.delete(adm);

       }
       catch (NumberFormatException e){
           throw new BadRequestException("ID must be a number.");
       }

    }

    public Administrator update(String id, Administrator request){

        if(id == null || id.trim().isEmpty()){
            throw new BadRequestException("Id parameter is missing or empty");
        }
        Administrator admExist;
        try {
            Integer intparse = Integer.parseInt(id);

            if (intparse <0) {
                throw new BadRequestException("ID must be greater than zero");
            }

            admExist = repository.findById(intparse).orElseThrow(
                    () -> new NotFoundException("Not administrator found")
            );
        }
           catch (NumberFormatException e){
                throw new BadRequestException("ID must be a number");
            }


            // Verifico se o CPF não é nulo, comparo o Cpf do banco com o do request, se for igual ao do banco sai da condição, se não for entra
            boolean cpfChanged = request.getCpf() != null &&
                    !admExist.getCpf().equals(request.getCpf());

            if (cpfChanged && repository.existsBycpf(request.getCpf())){
                throw new ConflictException("CPF already registred by another administrator");
            }


            Administrator administratorAtualizado = Administrator.builder()
                    .id(admExist.getId())
                    .name(request.getName() != null ? request.getName() : admExist.getName())
                    .cpf(request.getCpf() != null ? request.getCpf() : admExist.getCpf())
                    .password(request.getPassword() != null ? request.getPassword() : admExist.getPassword())
                    .build();

          return repository.save(administratorAtualizado);

        }



    }



