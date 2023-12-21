package com.requests.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.requests.project.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	 @Query(value = "SELECT obj FROM Category obj")
	 List<Category>searchAll();
}