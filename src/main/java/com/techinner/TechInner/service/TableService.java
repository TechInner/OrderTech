package com.techinner.TechInner.service;

import com.techinner.TechInner.Methods.Methods;
import com.techinner.TechInner.entity.Administrator;
import com.techinner.TechInner.entity.Table;
import com.techinner.TechInner.exceptions.BadRequestException;
import com.techinner.TechInner.exceptions.ConflictException;
import com.techinner.TechInner.exceptions.NotFoundException;
import com.techinner.TechInner.repository.TableRepository;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class TableService {

    @Autowired
    private TableRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    SaveTable


//    GetAllTable
    public List<Table> findAll(){

        List<Table> tableList = repository.findAll();

        if (tableList.isEmpty()){
            throw new NotFoundException("Not tables found");
        }

        return tableList;

    }


    public Table findById(String id){
        Methods.Isnumber(id);

            return repository.findById(Methods.ConvertToInt(id))
                    .orElseThrow(() ->
                    new RuntimeException("Table not found."));


    }


    public void delete(String id){

        Methods.Isnumber(id);

            Table table = repository.findById(Methods.ConvertToInt(id))
                    .orElseThrow(() ->
                    new RuntimeException("Table not found."));

            repository.delete(table);
    }

    public Table register(Table table){

        if (Stream.of(
                table.getUsername(),
                table.getPassword()
        ).anyMatch(v -> v == null || v.trim().isEmpty()))
        {
            throw new BadRequestException("Insert de fields");
        }

        if (repository.existsByUsernameIgnoreCase(table.getUsername())){
            throw new ConflictException("Table already registered");
        }

        String hashed = passwordEncoder.encode(table.getPassword());

        table.setPassword(hashed);

        return repository.save(table);

    }

    public Table update(String id, Table request){
        Methods.Isnumber(id);

        Table tableEntity = repository.findById(Methods.ConvertToInt(id))
                .orElseThrow(
                () -> new NotFoundException("Table not found")
        );

        boolean usernameChanged = request.getUsername() != null &&
                !tableEntity.getUsername().equalsIgnoreCase(request.getUsername());

        if (usernameChanged && repository.existsByUsername(request.getUsername())){
            throw new ConflictException("Username already registred by another table");
        }

        Table tableAtualizado = Table.builder()
                .id(tableEntity.getId())
                .username(request.getUsername() != null ? request.getUsername() : tableEntity.getUsername())
                .password(tableEntity.getPassword())
                .build();

        return repository.save(tableAtualizado);

    }


    public Table resetPassword(String username, String newPassword){
        Table table = repository.findByUsername(username).orElseThrow(() ->
                new RuntimeException("Table not found."));
        table.setPassword(newPassword);
        return repository.save(table);
    }





}
