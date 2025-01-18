package own.api.sales_tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import own.api.sales_tracker.dto.Sales;
import own.api.sales_tracker.service.SalesService;



@RestController
@OpenAPIDefinition(info= @Info(title="Sales Management" ,version = "1.0.0", description = "sales rest API"))
public class SalesController {
	
	@Autowired
	SalesService service;
	
		@GetMapping("/")
	    public String redirectToSwagger() {
	        return "redirect:/swagger-ui/index.html";
	    }
	
	 	@PostMapping("/sales")
	    public ResponseEntity<Object> addProduct(@RequestBody Sales sales) {
	      return service.add(sales);
	    }
	 	@GetMapping("/sales")
	    public ResponseEntity<Object> fetchProduct() {
	      return service.fetch();
	    }
	 	@GetMapping("/sales/{id}")
		public ResponseEntity<Object> fetchById(@PathVariable int id){
			return service.fetchById(id);
		}
		
		@GetMapping("/sales/category/{category}")
		public ResponseEntity<Object> fetchByName(@PathVariable String category){
			return service.findByCategory(category);
		}
		@DeleteMapping("/sales/{id}")
		public ResponseEntity<Object> delete(@PathVariable int id) {
			return service.delete(id);
		}

		@PutMapping("/sales")
		public ResponseEntity<Object> updateProduct(@RequestBody Sales sales) {
			return service.update(sales);
		}

		@PatchMapping("/sales/{id}")
		public ResponseEntity<Object> update(@PathVariable int id,@RequestBody Sales sales){
			return service.update(sales,id);
		}
}
