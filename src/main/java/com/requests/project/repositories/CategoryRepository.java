package com.requests.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.requests.project.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
