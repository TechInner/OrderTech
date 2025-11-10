package com.techinner.TechInner.service;

import com.techinner.TechInner.Methods.Methods;
import com.techinner.TechInner.dto.administrator.AdministratorRequestDTO;
import com.techinner.TechInner.dto.administrator.AdministratorResponseDTO;
import com.techinner.TechInner.entity.Administrator;
import com.techinner.TechInner.entity.Table;
import com.techinner.TechInner.exceptions.BadRequestException;
import com.techinner.TechInner.exceptions.ConflictException;
import com.techinner.TechInner.exceptions.InternalServerErrorException;
import com.techinner.TechInner.exceptions.NotFoundException;
import com.techinner.TechInner.mapper.AdministratorMapper;
import com.techinner.TechInner.repository.AdministratorRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.techinner.TechInner.Methods.Methods.ConvertToInt;

@Service
public class AdministratorService {


    @Autowired
    private AdministratorRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdministratorResponseDTO findById(String id){
            Methods.Isnumber(id);

          Administrator admin = repository.findById(ConvertToInt(id))
                  .orElseThrow(() -> new NotFoundException("Administrator not Found"));

          return AdministratorMapper.toResponse(admin);
    }

    public List<AdministratorResponseDTO> findAll(){
        List<Administrator> administratorList = repository.findAll();

        if (administratorList.isEmpty()) {
            throw new NotFoundException("No administrators found");
        }
        return administratorList.stream()
                .map(AdministratorMapper::toResponse)
                .collect(Collectors.toList());
    }

    public AdministratorResponseDTO register(AdministratorRequestDTO dto){

        // Valida se as informações está nula ou vazia
        if (Stream.of(
                dto.getCpf(),
                dto.getName(),
                dto.getPassword()
        ).anyMatch(v -> v == null || v.trim().isEmpty())){
            throw new BadRequestException("Insert the informations");
        }

        if (repository.existsBycpf(dto.getCpf())){
            throw new ConflictException("Administrator already registered.");
        }

        Administrator admin = AdministratorMapper.toEntity(dto);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        return AdministratorMapper.toResponse(repository.save(admin));
    }

    public void delete(String id){

       Methods.Isnumber(id);

           Administrator adm = repository.findById(ConvertToInt(id)).orElseThrow(()
                   -> new NotFoundException("Not administrator found")
           );

           repository.delete(adm);

    }

    public AdministratorResponseDTO update(String id, AdministratorRequestDTO dto){

       Methods.Isnumber(id);

           Administrator admExist = repository.findById(ConvertToInt(id)).orElseThrow(
                    () -> new NotFoundException("Not administrator found")
            );


            // Verifico se o CPF não é nulo, comparo o Cpf do banco com o do request, se for igual ao do banco sai da condição, se não for entra
            boolean cpfChanged = dto.getCpf() != null &&
                    !admExist.getCpf().equals(dto.getCpf());

            if (cpfChanged && repository.existsBycpf(dto.getCpf())){
                throw new ConflictException("CPF already registred by another administrator");
            }


            AdministratorMapper.updateEntityFromRequest(dto, admExist);

            return AdministratorMapper.toResponse(repository.save(admExist));

        }


    }



