package own.api.sales_tracker.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import own.api.sales_tracker.dto.Sales;
import own.api.sales_tracker.repository.SalesRepository;

@Service
public class SalesService {

		@Autowired
		SalesRepository repository;
	public ResponseEntity<Object> add(Sales sales) {
		// TODO Auto-generated method stub
			if (!repository.existsByPname(sales.getPname())) {
				repository.save(sales);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("message", "Data Created Success");
				map.put("statusCode", 201);
				map.put("data", sales);
				return new ResponseEntity<Object>(map,HttpStatus.CREATED);
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "product already already exists");
				return new ResponseEntity<Object>(map,HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}
	
		public ResponseEntity<Object> fetch() {
		List<Sales> sales=repository.findAll();
		if(sales.isEmpty()) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("error", "Not Data Present in Database");
			return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
		}else {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("message", "Record Found Success");
			map.put("data", sales);
			return new ResponseEntity<Object>(map,HttpStatus.OK);
		}
	}
	
		public ResponseEntity<Object> fetchById(int id) {
		Optional<Sales> optional = repository.findById(id);
		if(optional.isEmpty()) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("error", "No Record Found with Id: "+id);
			return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
		}else {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("message", "Record Found Success");
			map.put("data", optional.get());
			return new ResponseEntity<Object>(map,HttpStatus.OK);
		}
	}


		

		public ResponseEntity<Object> findByCategory(String category) {
			List<Sales> sales=repository.findByCategory(category);
			if(sales.isEmpty()) {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("error", "Not Data Present in the category: "+category);
				return new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
			}else {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("message", "Record Found Success");
				map.put("data", sales);
				return new ResponseEntity<Object>(map,HttpStatus.OK);
			}
		}

		public ResponseEntity<Object> delete(int id) {
			if (repository.findById(id).isEmpty()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "No Data Present with Id: " + id);
				return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
			} else {
				repository.deleteById(id);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("message", "Record Deleted Success");
				return new ResponseEntity<Object>(map, HttpStatus.OK);
			}
		}

		public ResponseEntity<Object> update(Sales sales) {
			repository.save(sales);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "Record Updated Success");
			map.put("data", sales);
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}

		public ResponseEntity<Object> update(Sales sales, int id) {
			if (repository.findById(id).isEmpty()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("error", "No Data Present with Id: " + id);
				return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
			} else {
				Sales s=repository.findById(id).get();
				if(sales.getPname()!=null)
					sales.setPname(s.getPname());
				if(sales.getPrice()!=0)
					sales.setPrice(s.getPrice());
				if(sales.getCategory()!=null)
					sales.setCategory(s.getCategory());
				if(sales.getPid()!=0)
					sales.setPid(s.getPid());
				repository.save(s);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("message", "Record Updated Success");
				map.put("data", s);
				return new ResponseEntity<Object>(map, HttpStatus.OK);
			}
		}

		
	 	
}


