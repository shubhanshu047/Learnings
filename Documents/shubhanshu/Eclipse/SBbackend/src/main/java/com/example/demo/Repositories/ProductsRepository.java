package com.example.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Model.Products;

public interface ProductsRepository extends JpaRepository<Products,Integer> {

//	@Query("select p from Products p where LOWER(p.brand) like LOWER(CONCAT('%', :key ,'%')) or LOWER(p.name) like LOWER(CONCAT('%', :key ,'%')) or LOWER(p.description) like LOWER(CONCAT('%', :key ,'%')) or LOWER(p.category) like LOWER(CONCAT('%', :key ,'%')) or LOWER(p.price) like LOWER(CONCAT('%', :key ,'%'))")
	@Query("select p from Products p  where LOWER(p.brand) like LOWER(CONCAT('%', :key ,'%')) or LOWER(p.name) like LOWER(CONCAT('%', :key ,'%'))"+
			" or LOWER(p.description) like LOWER(CONCAT('%', :key ,'%')) or LOWER(p.category) like LOWER(CONCAT('%', :key ,'%'))")
	List<Products> SearchProduct(String key);
	
}
//
//INSERT INTO products (name, brand, description, price, category, stock_quantity, release_date, available, image_name, image_type, image_data) 
//VALUES 
//('Smartphone X', 'TechBrand', 'A high-performance smartphone with an AI-powered camera.', 59999.99, 'Electronics', 50, '2024-01-01', true, null, null, NULL),
//('Gaming Laptop Pro', 'GameTech', 'A powerful gaming laptop with a dedicated GPU.', 89999.99, 'Computers', 30, '2023-11-15', false, null, null, NULL),
//('Wireless Headphones', 'SoundWave', 'Noise-cancelling wireless headphones with deep bass.', 7999.99, 'Accessories', 100, '2023-12-05', true, null, null, NULL);

