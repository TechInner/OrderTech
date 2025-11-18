package com.techinner.TechInner.service;

import com.techinner.TechInner.Methods.Methods;
import com.techinner.TechInner.dto.order.OrderRequestDTO;
import com.techinner.TechInner.dto.order.OrderResponseDTO;
import com.techinner.TechInner.dto.orderitem.OrderItemRequestDTO;
import com.techinner.TechInner.dto.orderitem.UpdateOrderItemsDTO;
import com.techinner.TechInner.entity.*;
import com.techinner.TechInner.exceptions.BadRequestException;
import com.techinner.TechInner.exceptions.NotFoundException;
import com.techinner.TechInner.mapper.OrderMapper;
import com.techinner.TechInner.repository.MenuRepository;
import com.techinner.TechInner.repository.OrderRepository;
import com.techinner.TechInner.repository.OrderStatusRepository;
import com.techinner.TechInner.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private MenuRepository menuRepository;

    public OrderResponseDTO findById(String id) {

        Methods.Isnumber(id);

            Order order = repository.findById(ConvertToInt(id))
                    .orElseThrow(() -> new NotFoundException("Order not found")
            );

            return OrderMapper.toResponse(order);
    }

    public List<OrderResponseDTO> findAll() {

        List<Order> orderList = repository.findAll();

        if (orderList.isEmpty()) {
            throw new NotFoundException("Orders not found");
        }

        return orderList.stream()
                .map(OrderMapper::toResponse)
                .collect(Collectors.toList());
    }

    public OrderResponseDTO register(OrderRequestDTO dto) {

        //Verifica se o corpo da requisição não veio nulo
        if (dto.getIdTable() == null || dto.getIdStatus() == null) {
            throw new BadRequestException("Table and Status are required");
        }

        Table table = tableRepository.findById(dto.getIdTable())
                .orElseThrow(() -> new NotFoundException("Table not found"));

        OrderStatus status = orderStatusRepository.findById(dto.getIdStatus())
                .orElseThrow(() -> new NotFoundException("Order status not found"));

        Order order = OrderMapper.toEntity(dto, status, table);
        order.setDtOrder(LocalDate.now());

        return OrderMapper.toResponse(repository.save(order));
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


            return OrderMapper.toResponse(repository.save(order));
        }

    public OrderResponseDTO updateOrderItems(String id, UpdateOrderItemsDTO request) {

        Methods.Isnumber(id);

        Order orderExist = repository.findById(ConvertToInt(id))
                .orElseThrow(() -> new NotFoundException("Order not found"));

        // Verifica se está aberto para edição
        if (!orderExist.getOrderStatus().getDescription().equalsIgnoreCase("Open")) {
            throw new BadRequestException("Only 'Open' orders can be updated");
        }

        //Verifica de mandou pelo meno 1 item
        if (request.getItems() == null || request.getItems().isEmpty()){
            throw new BadRequestException("At least one item must be provided");
        }

        //Garante que a lista de itens exista
        if (orderExist.getOrderItems() == null) {
            orderExist.setOrderItems(new ArrayList<>());

        }

        for (OrderItemRequestDTO itemDTO : request.getItems()){

            Menu menu = menuRepository.findById(itemDTO.getMenuId())
                    .orElseThrow(() -> new NotFoundException("Menu not found"));

            if (itemDTO.getQuantity() == null || itemDTO.getQuantity() <= 0) {
                throw new BadRequestException("Quantity must be greater than 0");
            }

            // Verifica se o item já existe no pedido
            Optional<OrderItem> exist =
                    orderExist.getOrderItems().stream()
                            .filter(i -> i.getMenu().getId() == itemDTO.getMenuId())
                            .findFirst();

            if (exist.isPresent()) {
                //Atualiza item existente
                OrderItem existingItem = exist.get();
                existingItem.setQuantity(itemDTO.getQuantity());
                existingItem.setObservation(itemDTO.getObservation());
                existingItem.setPrice(menu.getPrice());
            } else {
                OrderItem newItem = new OrderItem();
                newItem.setMenu(menu);
                newItem.setQuantity(itemDTO.getQuantity());
                newItem.setObservation(itemDTO.getObservation());
                newItem.setUnitPrice(menu.getPrice());
                newItem.setOrder(orderExist);

                orderExist.getOrderItems().add(newItem);
            }
        }

        return OrderMapper.toResponse(repository.save(orderExist));
    }

    }

