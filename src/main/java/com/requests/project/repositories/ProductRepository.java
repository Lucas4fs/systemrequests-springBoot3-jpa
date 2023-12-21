package com.requests.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.requests.project.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query(value = "SELECT obj FROM Product obj JOIN FETCH obj.categoryProduct")
	List<Product> searchAll();
}