package com.techinner.TechInner.service;

import com.techinner.TechInner.Methods.Methods;
import com.techinner.TechInner.dto.request.OrderRequestDTO;
import com.techinner.TechInner.dto.request.OrderStatusRequestDTO;
import com.techinner.TechInner.dto.response.OrderStatusResponseDTO;
import com.techinner.TechInner.entity.OrderStatus;
import com.techinner.TechInner.exceptions.BadRequestException;
import com.techinner.TechInner.exceptions.ConflictException;
import com.techinner.TechInner.exceptions.NotFoundException;
import static com.techinner.TechInner.mapper.mapperNew.ObjectMapper.parseObject;
import static com.techinner.TechInner.mapper.mapperNew.ObjectMapper.parseListObjects;
import com.techinner.TechInner.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderStatusService {

    @Autowired
    private OrderStatusRepository repository;

    public OrderStatusResponseDTO findById(String id){

        Methods.Isnumber(id);

       return parseObject(repository.findById(Methods.ConvertToInt(id)).orElseThrow(
               () -> new NotFoundException("Status not found")
       ), OrderStatusResponseDTO.class);
    }

    public List<OrderStatusResponseDTO> findAll(){

        List<OrderStatus> orderStatusList = repository.findAll();

        if (orderStatusList.isEmpty()){
            throw new BadRequestException("Status not found");
        }

        return parseListObjects(orderStatusList, OrderStatusResponseDTO.class) ;
    }

    public OrderStatusResponseDTO register(OrderStatusRequestDTO dto){

        if (dto.getDescription() == null ||
                dto.getDescription().trim().isEmpty()){
            throw new BadRequestException("Insert the informations");
        }

        if (repository.existsByDescriptionIgnoreCase(dto.getDescription())){
            throw new ConflictException("Status with this name already registered.");
        }

        OrderStatus order = parseObject(dto, OrderStatus.class);

        return parseObject(repository.save(order), OrderStatusResponseDTO.class);

    }

    public void delete(String id){

       Methods.Isnumber(id);


            OrderStatus orderStatus = repository.findById(Methods.ConvertToInt(id)).orElseThrow(
                    () -> new NotFoundException("Not found Status")
            );

            repository.delete(orderStatus);

    }

    public OrderStatusResponseDTO update(String id, OrderStatusRequestDTO dto){

        Methods.Isnumber(id);

        OrderStatus statusExist = repository.findById(Methods.ConvertToInt(id)).orElseThrow(
                    () -> new NotFoundException("Status not found")
        );


        boolean nameChanged = dto.getDescription() != null &&
                !statusExist.getDescription().equalsIgnoreCase(dto.getDescription());

        if (nameChanged && repository.existsByDescriptionIgnoreCase(dto.getDescription())){
            throw new ConflictException("Name already registred by another status");
        }


        Optional.ofNullable(dto.getDescription())
                .ifPresent(statusExist::setDescription);


        return parseObject(repository.save(statusExist), OrderStatusResponseDTO.class);
    }

}
