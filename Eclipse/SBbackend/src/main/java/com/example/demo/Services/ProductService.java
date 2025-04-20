package com.example.demo.Services;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Model.Products;
import com.example.demo.Repositories.ProductsRepository;

@Service
public class ProductService {
//	List<Products> pl = new ArrayList<>(Arrays.asList(
//			new Products(101,"vish kit",5), 
//			new Products(102,"mosammi",150)));
	
	@Autowired
	ProductsRepository H2Repo;
	
	public List getProducts(){
//		return pl;
		return H2Repo.findAll();
	}
	
	public Products getIdProduct(int x) {
//		return pl.stream().filter(p -> p.getId()==x).findFirst().orElse(new Products(000, "Nalla", 0));
		return H2Repo.findById(x).orElse(null);
	}
	
	public Products addProduct(Products pd, MultipartFile imagefile) throws IOException {
//		return pl.add(pd) ? "Success" : "Insertion Failed";
		System.out.println(pd.toString());
		pd.setImageName(imagefile.getOriginalFilename());
		pd.setImageType(imagefile.getContentType());
		pd.setImageData(imagefile.getBytes());
		return H2Repo.save(pd);
	}
	
	public Products updateProduct(Products pd, MultipartFile imagefile, int id) throws IOException {
		System.out.println(pd.toString());
		
		Products existingProduct = H2Repo.findById(id).orElse(null);

	    if (existingProduct == null) {
	        throw new RuntimeException("Product not found with id: " + id);
	    }
		
		if(imagefile!=null) {			
			pd.setImageName(imagefile.getOriginalFilename());
			pd.setImageType(imagefile.getContentType());
			pd.setImageData(imagefile.getBytes());
		} else {
	        pd.setImageName(existingProduct.getImageName());
	        pd.setImageType(existingProduct.getImageType());
	        pd.setImageData(existingProduct.getImageData());
	    }
		System.out.println(pd.toString());
		return H2Repo.save(pd);
	}
	
	public ResponseEntity<String> deleteProduct(int pid) {
		try {			
			H2Repo.deleteById(pid);
		}catch(Exception e) {
			return new ResponseEntity<>("Invalid Product",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("Product deleted successfully",HttpStatus.OK);
	}
	
	public ResponseEntity<?> searchProduct(String key){
		List<Products> pro = H2Repo.SearchProduct(key);
		if(pro!=null) {			
			return new ResponseEntity<>(pro, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("",HttpStatus.OK);
		}
	}
}
