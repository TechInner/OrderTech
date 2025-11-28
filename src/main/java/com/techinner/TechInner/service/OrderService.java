package com.techinner.TechInner.service;

import com.techinner.TechInner.Methods.Methods;
import com.techinner.TechInner.dto.request.OrderRequestDTO;
import com.techinner.TechInner.dto.request.UpdateOrderItemsDTO;
import com.techinner.TechInner.dto.response.OrderItemResponseDTO;
import com.techinner.TechInner.dto.response.OrderResponseDTO;
import com.techinner.TechInner.dto.request.OrderItemRequestDTO;
import com.techinner.TechInner.entity.*;
import com.techinner.TechInner.exceptions.BadRequestException;
import com.techinner.TechInner.exceptions.NotFoundException;
import  static com.techinner.TechInner.mapper.mapperNew.ObjectMapper.parseObeject;
import  static com.techinner.TechInner.mapper.mapperNew.ObjectMapper.parseListObejects;
import com.techinner.TechInner.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.techinner.TechInner.Methods.Methods.ConvertToInt;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private MenuRepository menuRepository;

    public OrderResponseDTO findById(String id) {

        Methods.Isnumber(id);

            Order order = repository.findById(ConvertToInt(id))
                    .orElseThrow(() -> new NotFoundException("Order not found")
            );

            return parseObeject(order, OrderResponseDTO.class);
    }

    public List<OrderResponseDTO> findAll() {

        List<Order> orderList = repository.findAll();

        if (orderList.isEmpty()) {
            throw new NotFoundException("Orders not found");
        }

        return parseListObejects(orderList, OrderResponseDTO.class);
    }

    //Cria os a solicitação de pedidos (Order)
    public OrderResponseDTO registerOrder(OrderRequestDTO dto) {

        //Verifica se o corpo da requisição não veio nulo
        if (dto.getIdTable() == null || dto.getIdStatus() == null) {
            throw new BadRequestException("Table and Status are required");
        }

        Table table = tableRepository.findById(dto.getIdTable())
                .orElseThrow(() -> new NotFoundException("Table not found"));

        OrderStatus status = orderStatusRepository.findById(1).orElseThrow(
                () -> new NotFoundException("Status not found")
        );

        Order order = new Order();
        order.setOrderStatus(status);
        order.setTable(table);

       return parseObeject(repository.save(order), OrderResponseDTO.class);


    }


    //Adiciona items a uma solicitação já criada
    public OrderItemResponseDTO addOrder(String idOrder, OrderItemRequestDTO dto){

        Methods.Isnumber(idOrder);

        Order order = repository.findById(ConvertToInt(idOrder)).orElseThrow(()
                -> new NotFoundException("Order not found"));

        Menu menu = menuRepository.findById(dto.getMenuId()).orElseThrow(()
                -> new NotFoundException("Not food found")
        );

        OrderItem item = OrderItem.builder()
                .price(menu.getPrice())
                .quantity(dto.getQuantity())
                .menu(menu)
                .order(order)
                .observation(dto.getObservation())
                .build();

        order.getOrderItems().add(item);
        repository.save(order);

        return parseObeject(item, OrderItemResponseDTO.class);
    }

    public void delete(String id) {

        Methods.Isnumber(id);

       Order order = repository.findById(ConvertToInt(id))
               .orElseThrow(() -> new NotFoundException("Order not found"));

       repository.delete(order);
    }

        public OrderResponseDTO updateStatusOrder(String id, Integer statusId) {

            Methods.Isnumber(id);

            Order order = repository.findById(ConvertToInt(id))
                    .orElseThrow(() -> new NotFoundException("Order not found"));

            OrderStatus status = orderStatusRepository.findById(statusId)
                    .orElseThrow(() -> new NotFoundException("Status not found"));

            order.setOrderStatus(status);


            return parseObeject(repository.save(order), OrderResponseDTO.class);
        }

    public OrderItemResponseDTO updateOrderItems(String id, OrderItemRequestDTO dto) {

        Methods.Isnumber(id);

        Order orderExist = repository.findById(ConvertToInt(id))
                .orElseThrow(() -> new NotFoundException("Order not found"));

        OrderStatus orderStatusOpen = orderStatusRepository.findById(1).orElseThrow(
                () -> new NotFoundException("OrderStatus not found")
        );

        Menu menuExist = menuRepository.findById(dto.getMenuId()).orElseThrow(
                () -> new NotFoundException("Food not found")
        );

        // Verifica se está aberto para edição
        if (orderExist.getOrderStatus() != orderStatusOpen) {
            throw new BadRequestException("Only 'Open' orders can be updated");
        }

//        //Verifica de mandou pelo meno 1 item
//        if (request.getOrder().getOrderItems() == null || request.getOrder().getOrderItems().isEmpty()){
//            throw new BadRequestException("At least one item must be provided");
//        }

        //Garante que a lista de itens exista
        if (orderExist.getOrderItems() == null) {
            orderExist.setOrderItems(new ArrayList<>());

        }



        // 👉 VERIFICA SE O ITEM JÁ EXISTE NO PEDIDO
        OrderItem existingItem = orderExist.getOrderItems().stream()
                .filter(i -> Objects.equals(i.getMenu().getId(), dto.getMenuId()))
                .findFirst()
                .orElse(null);

        OrderItem item;

        if (existingItem != null) {
            // 👉 ATUALIZA ITEM EXISTENTE
            Optional.ofNullable(dto.getQuantity()).ifPresent(existingItem::setQuantity);
            Optional.ofNullable(dto.getObservation()).ifPresent(existingItem::setObservation);
            existingItem.setPrice(menuExist.getPrice());

            item = existingItem;

        } else {
            // 👉 ADICIONA NOVO ITEM
            item = new OrderItem();
            item.setMenu(menuExist);

            Optional.ofNullable(dto.getQuantity()).ifPresent(item::setQuantity);
            Optional.ofNullable(dto.getObservation()).ifPresent(item::setObservation);

            item.setPrice(menuExist.getPrice());
            item.setOrder(orderExist);

            orderExist.getOrderItems().add(item);
        }

        repository.save(orderExist);

        return parseObeject(item, OrderItemResponseDTO.class);
    }

    public String closeOrder(String id, OrderItemRequestDTO dto){

        Methods.Isnumber(id);

        Order order = repository.findById(ConvertToInt(id)).orElseThrow(
                () -> new NotFoundException("Order not Found")
        );

        double total = order.getOrderItems()
                .stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        OrderStatus newOrderStatus = orderStatusRepository.findById(4).orElseThrow(
                () -> new NotFoundException("Id not found")
        );

        order.setOrderStatus(newOrderStatus);

        repository.save(order);

        return "Total: R$" + total;

    }

    }

