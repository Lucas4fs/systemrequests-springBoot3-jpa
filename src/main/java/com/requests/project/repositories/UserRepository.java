package com.requests.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.requests.project.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}