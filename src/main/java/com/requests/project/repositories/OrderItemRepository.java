package com.requests.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.requests.project.entities.OrderItem;
import com.requests.project.entities.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}