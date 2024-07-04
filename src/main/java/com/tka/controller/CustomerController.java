package com.tka.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tka.entity.Customer;
import com.tka.service.CustomerService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin("*")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	
	@PostMapping("/customer")
	public Customer addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
		
	}
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		return customerService.getAllCustomers();
	}
	
	@DeleteMapping("/delete-customer/{id}")
	public String deleteCustomer(@PathVariable Long id) {
		return customerService.deleteCustomer(id);
	}
		
	@GetMapping("/get-customer/{id}")
	public ResponseEntity<Customer> updateCustomerById(@PathVariable Long id) {
		Customer customer = customerService.getCustomerById(id);
		if(customer==null) 
			return ResponseEntity.notFound().build();
			return ResponseEntity.ok(customer);	
		
	}
	
	@PutMapping("/update-customer/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id,@RequestBody Customer customer){
		 Customer updatedcustomer= customerService.updateCustomer(id, customer);
		 if(updatedcustomer==null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		 return ResponseEntity.ok(updatedcustomer);
	}
	

}
