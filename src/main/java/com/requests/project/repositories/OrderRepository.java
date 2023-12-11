package com.requests.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.requests.project.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	 @Query(value = "SELECT obj FROM Order obj JOIN FETCH obj.client")
	 List<Order>searchAll();
	
}