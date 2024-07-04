package com.tka.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.tka.entity.Customer;
import com.tka.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
	@Autowired
	private CustomerRepository repo;
	
	public Customer addCustomer(Customer customer) {
		return repo.save(customer);
		
	}
	
	public List<Customer> getAllCustomers(){
		return repo.findAll();
		
	}
	
	public String deleteCustomer(@PathVariable Long id) {
		Customer customer =repo.findById(id).get();
		 if(customer!=null) {
			 repo.deleteById(id);
			 return "Cusomer deleted Successfully...";
		 }
		return "Cusomer Not Found with ID";
	}
	
	public Customer getCustomerById(Long id) {
		return repo.findById(id).orElse(null);
		
	}
	
	public Customer updateCustomer(Long id,Customer customer) {
		Optional<Customer> Optionalcustomer = repo.findById(id);
		if(Optionalcustomer.isPresent()) {
			Customer exCustomer= Optionalcustomer.get();
			exCustomer.setName(customer.getName());
			exCustomer.setEmail(customer.getEmail());
			exCustomer.setPhone(customer.getPhone());
			return repo.save(exCustomer);
		}
		return null;
		
	}

}
