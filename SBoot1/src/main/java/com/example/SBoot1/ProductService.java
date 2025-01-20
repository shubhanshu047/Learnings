package com.example.SBoot1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductService {
//	List<Products> pl = new ArrayList<>(Arrays.asList(
//			new Products(101,"vish kit",5), 
//			new Products(102,"mosammi",150)));
	
	@Autowired
	H2Repository H2Repo;
	
	public List getProducts(){
//		return pl;
		return H2Repo.findAll();
	}
	
	public Products getIdProduct(int x) {
//		return pl.stream().filter(p -> p.getId()==x).findFirst().orElse(new Products(000, "Nalla", 0));
		return H2Repo.findById(x).orElse(null);
	}
	
	public ResponseEntity<Products> addProduct(Products pd) {
//		return pl.add(pd) ? "Success" : "Insertion Failed";
		
		Products p = H2Repo.findById(pd.id).orElse(null);
		if(p==null) {
			return new ResponseEntity<>(H2Repo.save(pd),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
	}
	
	public Products updateProduct(Products pd) {
//		int i=0;
//		for(i=0;i<pl.size();i++) {
//			if(pd.id == pl.get(i).id) {
//				break;
//			}
//		}	
//		Products success=null;
//		if(i==pl.size()) {
//			pl.add(pd);
//		}else {
//			success=pl.set(i, pd);
//		}
//		return success != null ? "Update successful" : "Added new";
		return H2Repo.save(pd);
	}
	
	public ResponseEntity<HttpStatus> deleteProduct(int pid) {
//		int i=0;
//		for(i=0;i<pl.size();i++) {
//			if(pid == pl.get(i).id) {
//				break;
//			}
//		}
//		if(i<pl.size()) {
//			pl.remove(i);
//			return "Deleted successfuly";
//		}else {
//			return "Data not found";
//		}
		try {			
			H2Repo.deleteById(pid);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
