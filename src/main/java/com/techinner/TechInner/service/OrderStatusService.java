package com.techinner.TechInner.service;

import com.techinner.TechInner.entity.OrderStatus;
import com.techinner.TechInner.exceptions.BadRequestException;
import com.techinner.TechInner.exceptions.ConflictException;
import com.techinner.TechInner.exceptions.InternalServerErrorException;
import com.techinner.TechInner.exceptions.NotFoundException;
import com.techinner.TechInner.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class OrderStatusService {

    @Autowired
    private OrderStatusRepository repository;

    public OrderStatus findById(String id){
        if (id == null || id.trim().isEmpty()){
            throw new BadRequestException("Id parameter is missing or empty");
        }
        try{
            Integer idParse = Integer.parseInt(id);

            return repository.findById(idParse).orElseThrow(
                    () -> new NotFoundException("Not status found")
            );
        }
        catch (NumberFormatException e){
            throw new BadRequestException("Id must be a number");
        }
    }

    public List<OrderStatus> findAll(){

        List<OrderStatus> orderStatusList = repository.findAll();

        if (orderStatusList.isEmpty()){
            throw new BadRequestException("Status not found");
        }

        return orderStatusList;
    }

    public OrderStatus register(OrderStatus orderStatus){

        if (orderStatus.getDescription() == null ||
                orderStatus.getDescription().trim().isEmpty()){
            throw new BadRequestException("Insert the informations");
        }

        if (repository.existsByDescriptionIgnoreCase(orderStatus.getDescription())){
            throw new ConflictException("Food with this name already registered.");
        }

        return repository.save(orderStatus);

    }

    public void delete(String id){

        if (id == null || id.trim().isEmpty()){
            throw new BadRequestException("Insert the Id");
        }

        try{
            Integer idParse = Integer.parseInt(id);

            OrderStatus orderStatus = repository.findById(idParse).orElseThrow(
                    () -> new NotFoundException("Not found Status")
            );

            repository.delete(orderStatus);
        }
        catch (NumberFormatException e){
            throw new BadRequestException("Id must be a number");
        }

    }

    public OrderStatus update(String id,OrderStatus request){
        if (id == null || id.trim().isEmpty()){
            throw new BadRequestException("Insert the Id");
        }
        OrderStatus statusExist;
        try {
            Integer idParse = Integer.parseInt(id);

            statusExist = repository.findById(idParse).orElseThrow(
                    () -> new NotFoundException("Status not found")
            );
        } catch (NumberFormatException e) {
            throw new BadRequestException("Id must be a number");
        }

        boolean nameChanged = request.getDescription() != null &&
                !statusExist.getDescription().equalsIgnoreCase(request.getDescription());

        if (nameChanged && repository.existsByDescriptionIgnoreCase(request.getDescription())){
            throw new ConflictException("Name already registred by another status");
        }

        OrderStatus orderStatusAtualizado = OrderStatus.builder()
                .id(statusExist.getId())
                .description(request.getDescription() != null ? request.getDescription() : statusExist.getDescription())
                .build();

        return repository.save(orderStatusAtualizado);
    }

}
