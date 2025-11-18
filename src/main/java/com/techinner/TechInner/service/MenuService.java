package com.techinner.TechInner.service;

import com.techinner.TechInner.Methods.Methods;
import com.techinner.TechInner.dto.menu.MenuRequestDTO;
import com.techinner.TechInner.dto.menu.MenuResponseDTO;
import com.techinner.TechInner.entity.Menu;
import com.techinner.TechInner.exceptions.BadRequestException;
import com.techinner.TechInner.exceptions.ConflictException;
import com.techinner.TechInner.exceptions.NotFoundException;
import com.techinner.TechInner.mapper.MenuMapper;
import com.techinner.TechInner.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.techinner.TechInner.Methods.Methods.ConvertToInt;

@Service
public class MenuService {

    @Autowired
    private MenuRepository repository;

    public MenuResponseDTO findById(String id) {
        Methods.Isnumber(id);

        Menu menu = repository.findById(ConvertToInt(id))
                .orElseThrow(() -> new NotFoundException("Menu not found"));

        return MenuMapper.toResponse(menu);

    }

    public List<MenuResponseDTO> findAll() {
        List<Menu> menuList = repository.findAll();

        if (menuList.isEmpty()) {
            throw new NotFoundException("Food not found");
        }

        return menuList.stream()
                .map(MenuMapper::toResponse)
                .collect(Collectors.toList());

    }

    public MenuResponseDTO register(MenuRequestDTO dto) {

        //Só faz a verificação de Tipo String
        if (Stream.of(
                dto.getDescription(),
                dto.getName()
        ).anyMatch(v -> v == null || v.trim().isEmpty())) {
            throw new BadRequestException("Insert the informations");
        }

        if (dto.getPrice() == null || dto.getPrice() <= 1) {
            throw new BadRequestException("The price must be greater than or equal to 1");
        }
        // Verifica a existência do nome não fazendo distinção de maiusculas e minusculas
        if (repository.existsByNameIgnoreCase(dto.getName())) {
            throw new ConflictException("Food with this name already registered.");
        }

        Menu menu = MenuMapper.toEntity(dto);
        return MenuMapper.toResponse(repository.save(menu));

    }

    public void delete(String id) {

        Methods.Isnumber(id);

        Menu menu = repository.findById(ConvertToInt(id))
                .orElseThrow(() -> new NotFoundException("Not menu found"));

        repository.delete(menu);

    }

    public MenuResponseDTO update(String id, MenuRequestDTO dto) {

        Methods.Isnumber(id);


        Menu menuExist = repository.findById(ConvertToInt(id)).orElseThrow(
                () -> new NotFoundException("Id not found")
        );

        // Verifica se houve mudança no nome e se o nome passado pelo usuário não é nulo
        boolean nameChanged = dto.getName() != null &&
                !menuExist.getName().equalsIgnoreCase(dto.getName());

        // Se o nome mudou e existir o nome cadastrado cai no IF
        if (nameChanged && repository.existsByNameIgnoreCase(dto.getName())) {
            throw new ConflictException("Name already registred by another food");
        }

        MenuMapper.updateEntityFromRequest(dto, menuExist);

        return MenuMapper.toResponse(repository.save(menuExist));


    }
}



