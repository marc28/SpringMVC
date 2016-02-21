package com.marc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.marc.domain.Customer;
import com.marc.services.CustomerService;

@Controller
public class CustomerController {

	private CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	/**
	 * Show all customers
	 */
	@RequestMapping("/customers")
	public String showAllCustomers(Model model) {
		model.addAttribute("customersAll", customerService.showAllCustomers());
		return "customers";
	}

	/**
	 * Get customer by ID
	 */
	@RequestMapping("/customer/{id}")
	public String getCustomerById(@PathVariable Integer id, Model model) {
		model.addAttribute("customer", customerService.getCustomerById(id));
		return "customer";
	}

	/**
	 * Edit a Customer -> posts to saveOrUpdateCustomer(..)
	 */
	@RequestMapping("/customer/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("customer", customerService.getCustomerById(id));
		return "customerForm";
	}
	
	/**
	 * Create a Customer
	 */
	@RequestMapping("/customer/new")
	public String createCustomer(Model model){
		model.addAttribute("customer", new Customer());
		return "customerForm";
	}
	
	/**
	 * Method call when form submitted!!
	 * 
	 */
	@RequestMapping(value="/customergo",method=RequestMethod.POST)
	public String saveOrUpdateCustomer(Customer customer){
		Customer cus = customerService.saveOrUpdateCustomer(customer);
		return "redirect:/customer/"+cus.getId();
	}
	
	/**
	 * Deleting a Product
	 */
	@RequestMapping("customer/delete/{id}")
	public String deleteCustomer(@PathVariable Integer id){
		customerService.deleteCustomer(id);
		return "redirect:/customers";
	}
	
}


















