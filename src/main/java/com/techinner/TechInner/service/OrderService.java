package com.techinner.TechInner.service;

import com.techinner.TechInner.entity.Order;
import com.techinner.TechInner.entity.OrderItem;
import com.techinner.TechInner.exceptions.BadRequestException;
import com.techinner.TechInner.exceptions.NotFoundException;
import com.techinner.TechInner.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public Order findById(String id) {

        if (id == null || id.trim().isEmpty()) {
            throw new BadRequestException("Id parameter is missing or empty");
        }
        try {
            Integer idParse = Integer.parseInt(id);

            return repository.findById(idParse).orElseThrow(
                    () -> new NotFoundException("Order not found")
            );
        } catch (NumberFormatException e) {
            throw new BadRequestException("ID must be a number");
        }
    }

    public List<Order> findAll() {

        List<Order> orderList = repository.findAll();

        if (orderList.isEmpty()) {
            throw new NotFoundException("Orders not found");
        }

        return orderList;
    }

    public Order register(Order order) {

        if (order == null) {
            throw new BadRequestException("Order body is missing");
        }

        if (order.getTable() == null || order.getTable().getUsername().trim().isEmpty()) {
            throw new BadRequestException("Table reference is required");
        }

        if (order.getDtOrder() == null) {
            order.setDtOrder(LocalDate.now());
        }

        for (OrderItem item : order.getOrderItems()) {

            if (item == null) {
                throw new BadRequestException("Order item cannot be null");
            }

            if (item.getMenu() == null) {
                throw new BadRequestException("Each item must reference a valid Menu (food)");
            }

            if (item.getMenu().getName() == null || item.getMenu().getName().trim().isEmpty()) {
                throw new BadRequestException("Menu item name cannot be empty");
            }

            if (item.getQuantity() <= 0) {
                throw new BadRequestException("Item quantity must be greater than 0");
            }

            if (item.getPrice() == null || item.getPrice() <= 0) {
                throw new BadRequestException("Item price must be greater than 0");
            }

            //Atribui cada item a seu pedido
            item.setOrder(order);

        }

        //Transforma (mapeia) cada elemento do stream (cada OrderItem) em um valor numérico double e calcula
        double total = order.getOrderItems().stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        return repository.save(order);

    }

    public void delete(String id) {

        if (id == null || id.isEmpty())
            throw new BadRequestException("Id parameter is missing or empty");

        try {
            Integer parseId = Integer.parseInt(id);

            Order order = repository.findById(parseId).orElseThrow(
                        () -> new NotFoundException("Id not found")
            );

            repository.delete(order);

            } catch (NumberFormatException e) {
                throw new BadRequestException("Id must be a number");

            }
        }

        public Order updateOrder(String id, Order request) {

            if (id == null || id.trim().isEmpty()) {
                throw new BadRequestException("Id parameter is missing or empty");
            }

            Order orderExist;
            try {
                Integer idParse = Integer.parseInt(id);
                orderExist = repository.findById(idParse).orElseThrow(
                        () -> new NotFoundException("Order not found"));
            } catch (NumberFormatException e) {
                throw new BadRequestException("Id must be a number");
            }

            Order orderAtualizado = new Order();
            orderAtualizado.setId(orderExist.getId());
            orderAtualizado.setTable(request.getTable() != null ? request.getTable() : orderExist.getTable());
            orderAtualizado.setOrderStatus(request.getOrderStatus() != null ? request.getOrderStatus() : orderExist.getOrderStatus());
            orderAtualizado.setDtOrder(orderExist.getDtOrder());
            orderAtualizado.setOrderItems(orderExist.getOrderItems());

            return repository.save(orderAtualizado);

        }

        public Order updateOrderItems(String id, Order request){

        if (id == null || id.trim().isEmpty()){
            throw new BadRequestException("Id parameter is missing or empty");
        }

        Order orderExist;
        try {
            Integer idParse = Integer.parseInt(id);
            orderExist = repository.findById(idParse).orElseThrow(
                    () -> new NotFoundException("Order not found")
            );
        } catch (NumberFormatException e){
            throw new BadRequestException("Id must be a number");
        }

        if (!orderExist.getOrderStatus().getDescription().equalsIgnoreCase("Open")){
            throw new BadRequestException("Only orders with status 'Open' can be updated");
        }

        if (request.getOrderStatus() != null){
            orderExist.setOrderStatus(request.getOrderStatus());
        }

        if (request.getOrderStatus() != null && !request.getOrderItems().isEmpty()){
            for (OrderItem newItem : request.getOrderItems()){

                Optional<OrderItem> existingItemOpt = orderExist.getOrderItems()
                        .stream()
                        .filter(i -> i.getId() == newItem.getId())
                        .findFirst();

                if (existingItemOpt.isPresent()) {

                    OrderItem existingItem = existingItemOpt.get();
                    existingItem.setMenu(newItem.getMenu() != null ? newItem.getMenu() : existingItem.getMenu());
                    existingItem.setQuantity(newItem.getQuantity() > 0 ? newItem.getQuantity() : existingItem.getQuantity());
                    existingItem.setPrice(newItem.getPrice() != null ? newItem.getPrice() : existingItem.getPrice());
                    existingItem.setObservation(newItem.getObservation());
                }else {
                    newItem.setOrder(orderExist);
                    orderExist.getOrderItems().add(newItem);
                }
            }
        }
                return repository.save(orderExist);
        }

    }

