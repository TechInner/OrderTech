package com.techinner.TechInner.service;

import com.techinner.TechInner.Methods.Methods;
import com.techinner.TechInner.dto.request.ResetPasswordRequest;
import com.techinner.TechInner.dto.request.TableRequestDTO;
import com.techinner.TechInner.dto.response.TableResponseDTO;
import com.techinner.TechInner.entity.Table;
import static com.techinner.TechInner.mapper.mapperNew.ObjectMapper.parseObject;
import static com.techinner.TechInner.mapper.mapperNew.ObjectMapper.parseListObjects;
import com.techinner.TechInner.exceptions.BadRequestException;
import com.techinner.TechInner.exceptions.ConflictException;
import com.techinner.TechInner.exceptions.NotFoundException;
import com.techinner.TechInner.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class TableService {

    @Autowired
    private TableRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    SaveTable


    public TableResponseDTO findById(String id){

        Methods.Isnumber(id);

            return parseObject(repository.findById(Methods.ConvertToInt(id))
                    .orElseThrow(() ->
                            new RuntimeException("Table not found.")), TableResponseDTO.class);


    }

//    GetAllTable
    public List<TableResponseDTO> findAll(){

        List<Table> tableList = repository.findAll();

        if (tableList.isEmpty()){
            throw new NotFoundException("Not tables found");
        }

        return parseListObjects(tableList, TableResponseDTO.class) ;

    }


    public void delete(String id){

        Methods.Isnumber(id);

            Table table = repository.findById(Methods.ConvertToInt(id))
                    .orElseThrow(() ->
                    new RuntimeException("Table not found."));

            repository.delete(table);
    }

    public TableResponseDTO register(TableRequestDTO dto){

        if (Stream.of(
                dto.getUsername(),
                dto.getPassword()
        ).anyMatch(v -> v == null || v.trim().isEmpty()))
        {
            throw new BadRequestException("Insert de fields");
        }

        if (repository.existsByUsernameIgnoreCase(dto.getUsername())){
            throw new ConflictException("Table already registered");
        }

        Table entity = parseObject(dto, Table.class);

        String hashed = passwordEncoder.encode(dto.getPassword());

        entity.setPassword(hashed);

        return parseObject(repository.save(entity), TableResponseDTO.class);

    }

    public TableResponseDTO update(String id, TableRequestDTO dto){
        Methods.Isnumber(id);

        Table tableEntity = repository.findById(Methods.ConvertToInt(id))
                .orElseThrow(
                () -> new NotFoundException("Table not found")
        );

        boolean usernameChanged = dto.getUsername() != null &&
                !tableEntity.getUsername().equalsIgnoreCase(dto.getUsername());

        if (usernameChanged && repository.existsByUsername(dto.getUsername())){
            throw new ConflictException("Username already registred by another table");
        }

//        Table tableAtualizado = Table.builder()
//                .id(tableEntity.getId())
//                .username(request.getUsername() != null ? request.getUsername() : tableEntity.getUsername())
//                .password(tableEntity.getPassword())
//                .build();

        Optional.ofNullable(dto.getUsername()).ifPresent(tableEntity::setUsername);



        return parseObject(repository.save(tableEntity), TableResponseDTO.class);

    }


    public String resetPassword(String id, ResetPasswordRequest dto){

        Methods.Isnumber(id);

        Table table = repository.findById(Methods.ConvertToInt(id)).orElseThrow(() ->
                new NotFoundException("Table not found."));

        String hashed = passwordEncoder.encode(dto.getPassword());

        table.setPassword(hashed);

        repository.save(table);

        return "successfully changed\n";
    }





}
