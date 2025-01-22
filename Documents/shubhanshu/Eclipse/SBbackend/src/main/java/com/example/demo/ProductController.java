package com.example.demo;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	@GetMapping("/product/{productid}/image")
	public ResponseEntity<byte[]> ImageById(@PathVariable int productid) {
//		return ps.getIdProduct(productid);
		Products p = ps.getIdProduct(productid);
		byte[] image= p.getImageData();
		if(image!=null) {			
			return ResponseEntity.ok().contentType(MediaType.valueOf(p.getImageType())).body(image);
		}else {
			return ResponseEntity.notFound().build();				// build() is finalizing the the response without needing to add a body
		}
	}

	
	@PostMapping("/addproduct")
	public ResponseEntity<?> addkaro(@RequestPart Products product, @RequestPart MultipartFile imagefile) throws IOException {
//		return ps.addProduct(pd);
		Products p = ps.addProduct(product, imagefile);
		if(p!=null) {			
			return new ResponseEntity<>(p,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<String> updatekaro(@RequestPart Products product, @RequestPart (required = false) MultipartFile imagefile, @PathVariable int id) {
//		return ps.updateProduct(pd);
		Products p=null;
		try {
			p = ps.updateProduct(product, imagefile, id );
		} catch (IOException e) {
			return new ResponseEntity<>("Update Failed",HttpStatus.NOT_FOUND);
		}
		if(p!=null) {			
			return new ResponseEntity<>("Update successful",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Update Failed",HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/product/{productid}")
	public ResponseEntity<String> deletekaro(@PathVariable int productid) {
		return ps.deleteProduct(productid);
	}
}
