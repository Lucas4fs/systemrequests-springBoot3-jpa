package com.requests.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.requests.project.dto.InterfaceOrderItem;
import com.requests.project.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	@Query(value = "SELECT id AS id,"
		     + "(price * quantity) AS orderItemTotal,"
		     + "price AS productPrice,"
		     + " quantity AS orderItemQuantity,"
		     + " order_id AS orderId,"
		     + " product_id AS productId"
		     + " FROM tb_order_item"
		     , nativeQuery = true)
	List<InterfaceOrderItem> searchAll();
}
