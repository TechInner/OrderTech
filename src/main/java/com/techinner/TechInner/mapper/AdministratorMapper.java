package com.techinner.TechInner.mapper;

import com.techinner.TechInner.dto.administrator.AdministratorRequestDTO;
import com.techinner.TechInner.dto.administrator.AdministratorResponseDTO;
import com.techinner.TechInner.entity.Administrator;

public class AdministratorMapper {

    public static Administrator toEntity(AdministratorRequestDTO dto){
        if (dto == null) return null;
        return Administrator.builder()
                .name(dto.getName())
                .cpf(dto.getCpf())
                .password(dto.getPassword())
                .build();
    }

    public static AdministratorResponseDTO toResponse(Administrator admin){
        if (admin == null) return null;
        return AdministratorResponseDTO.builder()
                .id(admin.getId())
                .name(admin.getName())
                .cpf(admin.getCpf())
                .build();
    }

    public static void updateEntityFromRequest(AdministratorRequestDTO dto, Administrator existing){
        if (dto.getName() != null) existing.setName(dto.getName());
        if (dto.getCpf() != null) existing.setCpf(dto.getCpf());
        if (dto.getPassword() != null) existing.setPassword(dto.getPassword());
    }
}
