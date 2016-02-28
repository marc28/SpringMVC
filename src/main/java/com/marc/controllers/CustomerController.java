package com.marc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.marc.domain.Customer;
import com.marc.services.CustomerService;

@RequestMapping("/customer")
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
	@RequestMapping({"/list","/"})
	public String showAllCustomers(Model model) {
		model.addAttribute("customersAll", customerService.showAllCustomers());
		return "customer/list"; //new folder structure
	}

	/**
	 * Get customer by ID
	 */
	@RequestMapping("/show/{id}")
	public String getCustomerById(@PathVariable Integer id, Model model) {
		model.addAttribute("customer", customerService.getCustomerById(id));
		return "customer/show";
	}

	/**
	 * Edit a Customer -> posts to saveOrUpdateCustomer(..)
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("customer", customerService.getCustomerById(id));
		return "customer/customerForm";
	}
	
	/**
	 * Create a Customer
	 */
	@RequestMapping("/new")
	public String createCustomer(Model model){
		model.addAttribute("customer", new Customer());
		return "customer/customerForm";
	}
	
	/**
	 * Method call when form submitted!!
	 * 
	 */
	// @RequestMapping(value="/customergo",method=RequestMethod.POST) OLD WAY: 
	// customerForm  Line: 21 --> th:action="@{/customergo}"
	@RequestMapping(method=RequestMethod.POST)
	public String saveOrUpdateCustomer(Customer customer){
		Customer cus = customerService.saveOrUpdateCustomer(customer);
		return "redirect:/customer/show/"+cus.getId();
	}
	
	/**
	 * Deleting a Product
	 */
	@RequestMapping("/delete/{id}")
	public String deleteCustomer(@PathVariable Integer id){
		customerService.deleteCustomer(id);
		return "redirect:/customer/list";
	}
	
} //Testing from refactor


















