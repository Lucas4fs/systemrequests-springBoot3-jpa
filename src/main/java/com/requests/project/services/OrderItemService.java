package com.requests.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.requests.project.dto.InterfaceOrderItem;
import com.requests.project.entities.OrderItem;
import com.requests.project.repositories.OrderItemRepository;
import com.requests.project.services.exceptions.DatabaseException;
import com.requests.project.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository repository;
	
	public List<InterfaceOrderItem> searchAll() {
		return repository.searchAll();
	}
 
	public OrderItem findById(Long id) {
		Optional<OrderItem> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public OrderItem insert(OrderItem obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		try {
			OrderItem orderItem = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
			repository.delete(orderItem);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public OrderItem update(Long id, OrderItem obj) {
		try {
			OrderItem entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(OrderItem entity, OrderItem obj) {
		entity.setQuantity(obj.getQuantity());
		entity.setPrice(obj.getPrice());
		entity.setProductId(obj.getProductId());
		entity.setOrderId(obj.getOrderId());
	}
}