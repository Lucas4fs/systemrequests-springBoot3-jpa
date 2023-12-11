package com.requests.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.requests.project.entities.Order;
import com.requests.project.repositories.OrderRepository;
import com.requests.project.services.exceptions.DatabaseException;
import com.requests.project.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class OrderService{

	@Autowired
	private OrderRepository repository;

	public List<Order> searchAll() { 
		return repository.searchAll(); 
	}
	 
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Order insert(Order obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
	    try {
	        Order order = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	        repository.delete(order);
	    } catch (DataIntegrityViolationException e) {
	        throw new DatabaseException(e.getMessage());
	    }
	}

	public Order update(Long id, Order obj) {
		try {
			Order entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
	}

	private void updateData(Order entity, Order obj) {
		entity.setMoment(obj.getMoment());
		entity.setOrderStatus(obj.getOrderStatus());
		entity.setClient(obj.getClient());
	}
}