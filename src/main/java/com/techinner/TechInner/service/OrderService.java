package com.techinner.TechInner.service;

import com.techinner.TechInner.Methods.Methods;
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

import static com.techinner.TechInner.Methods.Methods.ConvertToInt;

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

        //Verifica se o corpo da requisição não veio nulo
        if (order == null) {
            throw new BadRequestException("Order body is missing");
        }

        //Verifica se há uma mesa associada ao pedido
        if (order.getTable() == null || order.getTable().getUsername().trim().isEmpty()) {
            throw new BadRequestException("Table reference is required");
        }

        //Caso não haja data registrada, considera a data atual
        if (order.getDtOrder() == null) {
            order.setDtOrder(LocalDate.now());
        }

        //Percorre cada item do pedido
        for (OrderItem item : order.getOrderItems()) {

            //Verifica existência do item
            if (item == null) {
                throw new BadRequestException("Order item cannot be null");
            }

            //Verifica se o item está associado a um prato
            if (item.getMenu() == null) {
                throw new BadRequestException("Each item must reference a valid Menu (food)");
            }

            //Verifica se o nome do prato não está como nulo ou vazio
            if (item.getMenu().getName() == null || item.getMenu().getName().trim().isEmpty()) {
                throw new BadRequestException("Menu item name cannot be empty");
            }

            //Não permite que a quantidade seja <= 0
            if (item.getQuantity() <= 0) {
                throw new BadRequestException("Item quantity must be greater than 0");
            }

            //Não permite que o preço seja nulo ou <= 0
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

            //Valida o id
            if (id == null || id.trim().isEmpty()) {
                throw new BadRequestException("Id parameter is missing or empty");
            }

            Methods.Isnumber(id);
            Order orderExist;

            orderExist = repository.findById(ConvertToInt(id)).orElseThrow(
                        () -> new NotFoundException("Order not found"));


            Order orderChanged = new Order();
            orderChanged.setId(orderExist.getId());
            orderChanged.setTable(request.getTable() != null ? request.getTable() : orderExist.getTable());
            orderChanged.setOrderStatus(request.getOrderStatus() != null ? request.getOrderStatus() : orderExist.getOrderStatus());
            orderChanged.setDtOrder(orderExist.getDtOrder());
            orderChanged.setOrderItems(orderExist.getOrderItems());

            return repository.save(orderChanged);

        }

        public Order updateOrderItems(String id, Order request){

        if (id == null || id.trim().isEmpty()){
            throw new BadRequestException("Id parameter is missing or empty");
        }

        Methods.Isnumber(id);
        Order orderExist;


            orderExist = repository.findById(ConvertToInt(id)).orElseThrow(
                    () -> new NotFoundException("Order not found")
            );

        //Verifica se o Status do Pedido está "Em aberto"
        if (!orderExist.getOrderStatus().getDescription().equalsIgnoreCase("Open")){
            throw new BadRequestException("Only orders with status 'Open' can be updated");
        }

        if (request.getOrderStatus() != null && !request.getOrderItems().isEmpty()){
            for (OrderItem newItem : request.getOrderItems()){

                //Verifica se o item novo já existe no pedido
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

