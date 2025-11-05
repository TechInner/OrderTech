package com.techinner.TechInner.service;

import com.techinner.TechInner.entity.Menu;
import com.techinner.TechInner.exceptions.BadRequestException;
import com.techinner.TechInner.exceptions.ConflictException;
import com.techinner.TechInner.exceptions.NotFoundException;
import com.techinner.TechInner.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class MenuService {

    @Autowired
    private MenuRepository repository;

    public Menu findById(String id){

        if (id == null || id.trim().isEmpty()) {
            throw new BadRequestException("Id parameter is missing or empty");
        }

        try {
            Integer parseId = Integer.parseInt(id);

            return repository.findById(parseId).orElseThrow(
                    () -> new  NotFoundException("Food not found")
            );
        }
        catch (NumberFormatException e){
            throw new BadRequestException("ID must be a number");
        }

    }

    public List<Menu> findAll(){

        List<Menu> menuList = repository.findAll();

        if (menuList.isEmpty()){
            throw new NotFoundException("Food not found");
        }

        return menuList;

    }

    public Menu register(Menu menu){

        //Só faz a verificação de Tipo String
        if (Stream.of(
                menu.getDescription(),
                menu.getName()
        ).anyMatch(v -> v == null || v.trim().isEmpty())){
            throw new BadRequestException("Insert the informations");
        }

        if (menu.getPrice() == null || menu.getPrice() <= 1){
            throw new BadRequestException("The price must be greater than or equal to 1");
        }
        // Verifica a existência do nome não fazendo distinção de maiusculas e minusculas
        if (repository.existsByNameIgnoreCase(menu.getName())){
            throw new ConflictException("Food with this name already registered.");
        }

        return repository.save(menu);

    }

    public void delete(String id){

        if (id == null || id.isEmpty())
            throw new BadRequestException("Id parameter is missing or empty");

        try {
            Integer parseId = Integer.parseInt(id);

            Menu menu = repository.findById(parseId).orElseThrow(
                    () -> new NotFoundException("Id not found")
            );

            repository.delete(menu);

        }
        catch (NumberFormatException e){
            throw new BadRequestException("ID must be a number");
        }

    }

    public Menu update(String id, Menu request){

        if (id == null || id.trim().isEmpty()){
            throw new BadRequestException("Id parameter is missing or empty");
        }
        Menu menuExist;
            try{
                Integer idParse = Integer.parseInt(id);

               menuExist = repository.findById(idParse).orElseThrow(
                        () -> new NotFoundException("Id not found")
                );
            }
            catch (NumberFormatException e){
                throw new BadRequestException("ID must be a number");
            }

        // Verifica se houve mudança no nome e se o nome passado pelo usuário não é nulo
        boolean nameChanged = request.getName() != null &&
                !menuExist.getName().equalsIgnoreCase(request.getName());

        // Se o nome mudou e existir o nome cadastrado cai no IF
        if (nameChanged && repository.existsByNameIgnoreCase(request.getName())){
            throw new ConflictException("Name already registred by another food");
        }

        Menu menuAtualizado = Menu.builder()
                .id(menuExist.getId())
                .name(request.getName() != null ? request.getName() : menuExist.getName())
                .price(request.getPrice() != null ? request.getPrice() : menuExist.getPrice())
                .description(request.getDescription() != null ? request.getDescription() : menuExist.getDescription())
                .build();

        return repository.save(menuAtualizado);


    }


}
