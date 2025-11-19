package com.techinner.TechInner.mapper;

import com.techinner.TechInner.dto.administrator.AdministratorRequestDTO;
import com.techinner.TechInner.dto.administrator.AdministratorResponseDTO;
import com.techinner.TechInner.entity.Administrator;

//Responsável por converter entre Entity e DTO
public class AdministratorMapper {

    //Recebe os dados via AdministratorRequestDTO e os transforma. Depois armazena na entidade
    public static Administrator toEntity(AdministratorRequestDTO dto){
        if (dto == null) return null;
        return Administrator.builder()
                .name(dto.getName())
                .cpf(dto.getCpf())
                .password(dto.getPassword())
                .build();
    }

    //Dados da entidade Administrator são transformados e trafegados pela AdministratorResponseDTO para gerar resposta às requisições
    public static AdministratorResponseDTO toResponse(Administrator admin){
        if (admin == null) return null;
        return AdministratorResponseDTO.builder()
                .name(admin.getName())
                .cpf(admin.getCpf())
                .build();
    }

    //Responsável por atualizar um Administrador com os novos valores recebidos na DTO
    public static void updateEntityFromRequest(AdministratorRequestDTO dto, Administrator existing){ //Recebe a requisição (dto) e o objeto já salvo no banco
        if (dto.getName() != null) existing.setName(dto.getName());
        if (dto.getCpf() != null) existing.setCpf(dto.getCpf());
        if (dto.getPassword() != null) existing.setPassword(dto.getPassword());
    }
}
