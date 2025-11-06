package com.techinner.TechInner.service;

import com.techinner.TechInner.entity.Order;
import com.techinner.TechInner.entity.OrderItem;
import com.techinner.TechInner.exceptions.BadRequestException;
import com.techinner.TechInner.exceptions.NotFoundException;
import com.techinner.TechInner.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public Order findById(String id){

        if (id == null || id.trim().isEmpty()){
            throw new BadRequestException("Id parameter is missing or empty");
        }
        try {
            Integer idParse = Integer.parseInt(id);

            return repository.findById(idParse).orElseThrow(
                    () -> new NotFoundException("Order not found")
            );
        }
        catch (NumberFormatException e){
            throw new BadRequestException("ID must be a number");
        }
    }

    public List<Order> findAll(){

        List<Order> orderList = repository.findAll();

        if (orderList.isEmpty()){
            throw new NotFoundException("Orders not found");
        }

        return orderList;
    }



}
