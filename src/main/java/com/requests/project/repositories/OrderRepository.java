package com.requests.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.requests.project.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	/*NAO SEI SE PRECISO USAR
	 * @Query(value = "SELECT obj FROM Order obj JOIN FETCH obj.client")
	 * List<Order>searchAll();
	 */
}