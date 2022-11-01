package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api") 
public class CustomerRestController {

	
	// autowire CustomerService, inject dependency
	@Autowired
	private CustomerService customerService;
	
	
	
	// add mapping for GET /customers
	@GetMapping("/customers") 
	public List<Customer> getCustomers(){
		
		return customerService.getCustomers();
	}
	
	
	// lets get a single customer
	@GetMapping("/customers/{customerId}") // => spring-crm-rest/api/customers/{customerId}
	public Customer getCustomer(@PathVariable int customerId) {
		
		Customer singleCustomer = customerService.getCustomer(customerId);
		
		if(singleCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found - " +customerId);
		}
		
		return singleCustomer;
		
	}
	
	
	// add a new customer to DB
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		
		theCustomer.setId(0);
		
		customerService.saveCustomer(theCustomer); // bare in mind that Hibernate has saveOrUpdate
		
		return theCustomer;
	}
	
	
	// lets update an existing Customer
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer); // bare in mind that Hibernate has saveOrUpdate
		
		return theCustomer;
	}
	
	
	
	// lets delete a Customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		
		Customer singleCustomer = customerService.getCustomer(customerId);
		
		if(singleCustomer == null) {
			
			throw new CustomerNotFoundException("Customer id not found - " +customerId);
		}
		
		customerService.deleteCustomer(customerId);
		
		return "Deleted customer id - " +customerId;
	}
	
	
	
	
	
	
	
	
}
