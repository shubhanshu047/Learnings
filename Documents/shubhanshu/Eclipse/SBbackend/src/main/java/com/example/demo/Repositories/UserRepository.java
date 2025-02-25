package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Model.Users;

public interface UserRepository extends JpaRepository<Users,Integer> {
	
	@Query("select x from Users x where x.username= :user")
	Users getUserByUsername(String user);
	
}
