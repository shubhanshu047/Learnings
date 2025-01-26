package com.example.demo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	public Products addProduct(Products pd, MultipartFile imagefile) throws IOException {
//		return pl.add(pd) ? "Success" : "Insertion Failed";
		System.out.println(pd.toString());
		pd.setImageName(imagefile.getOriginalFilename());
		pd.setImageType(imagefile.getContentType());
		pd.setImageData(imagefile.getBytes());
		return H2Repo.save(pd);
	}
	
	public Products updateProduct(Products pd, MultipartFile imagefile, int id) throws IOException {
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
		System.out.println(pd.toString());
		if(imagefile!=null) {			
			pd.setImageName(imagefile.getOriginalFilename());
			pd.setImageType(imagefile.getContentType());
			pd.setImageData(imagefile.getBytes());
		}
		pd.setId(1);
		System.out.println("-----------------");
		System.out.println(pd.toString());
		return H2Repo.save(pd);
	}
	
	public ResponseEntity<String> deleteProduct(int pid) {
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
