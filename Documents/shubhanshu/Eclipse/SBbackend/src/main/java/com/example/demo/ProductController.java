package com.example.demo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ProductController {

	@Autowired
	ProductService ps;
	
	@GetMapping("/products")
	public  ResponseEntity<List<Products>> Products() {
		return new ResponseEntity<>(ps.getProducts(),HttpStatus.OK);
//		 return ps.getProducts(); 
	}
	
	@GetMapping("/product/{productid}")
	public ResponseEntity<Products> ById(@PathVariable int productid) {
//		return ps.getIdProduct(productid);
		Products p = ps.getIdProduct(productid);
		if(p!=null) {			
			return new ResponseEntity<>(p,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/addproduct")
	public ResponseEntity<Products> addkaro(@RequestBody Products pd) {
//		return ps.addProduct(pd);
		Products p = ps.addProduct(pd);
		if(p!=null) {			
			return new ResponseEntity<>(p,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/updateproduct")
	public ResponseEntity<Products> updatekaro(@RequestBody Products pd) {
//		return ps.updateProduct(pd);
		Products p = ps.updateProduct(pd);
		if(p!=null) {			
			return new ResponseEntity<>(p,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/deleteproduct/{productid}")
	public ResponseEntity<HttpStatus> deletekaro(@PathVariable int productid) {
		return ps.deleteProduct(productid);
	}
}
