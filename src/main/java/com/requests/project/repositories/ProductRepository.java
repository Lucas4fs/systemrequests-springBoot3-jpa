package com.requests.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.requests.project.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
