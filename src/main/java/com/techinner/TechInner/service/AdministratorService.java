package com.techinner.TechInner.service;

import com.github.dozermapper.core.Mapper;
import com.techinner.TechInner.Methods.Methods;
import com.techinner.TechInner.dto.request.AdministratorRequestDTO;
import com.techinner.TechInner.dto.response.AdministratorResponseDTO;
import com.techinner.TechInner.entity.Administrator;
import com.techinner.TechInner.exceptions.BadRequestException;
import com.techinner.TechInner.exceptions.ConflictException;
import com.techinner.TechInner.exceptions.NotFoundException;
import static com.techinner.TechInner.mapper.mapperNew.ObjectMapper.parseObject;
import static com.techinner.TechInner.mapper.mapperNew.ObjectMapper.parseListObjects;
import com.techinner.TechInner.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

          return parseObject(admin, AdministratorResponseDTO.class);
    }

    public List<AdministratorResponseDTO> findAll(){
        List<Administrator> administratorList = repository.findAll();

        if (administratorList.isEmpty()) {
            throw new NotFoundException("No administrators found");
        }
        //Percorre a lista de Admins um a um e aplica o método toResponse em cada para converter dados da Entidade em DTO
        return parseListObjects(administratorList, AdministratorResponseDTO.class);
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

        Administrator entity = parseObject(dto, Administrator.class);

        String hashed = passwordEncoder.encode(dto.getPassword());

        entity.setPassword(hashed);

        repository.save(entity);

        return parseObject(entity, AdministratorResponseDTO.class);
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

            Optional.ofNullable(dto.getName()).ifPresent(admExist::setName);
            Optional.ofNullable(dto.getCpf()).ifPresent(admExist::setCpf);


            admExist = parseObject(dto,Administrator.class);

            return parseObject(admExist,AdministratorResponseDTO.class);

        }


    }



