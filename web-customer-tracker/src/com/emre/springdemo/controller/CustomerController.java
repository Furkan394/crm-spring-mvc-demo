package com.emre.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emre.springdemo.dao.CustomerDAO;
import com.emre.springdemo.entity.Customer;
import com.emre.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model model) {
		
		List<Customer> customers = customerService.getCustomers();

		model.addAttribute("customers", customers);
		
		return "list-customers";
	}

	@GetMapping("/show-form-for-add")
	public String showFormForAdd(Model model) {
		
		Customer customer = new Customer();
		
		model.addAttribute("customer", customer);
		
		return "customer-form";
	}
	
	@GetMapping("/show-form-for-update")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {
		
		Customer customer = customerService.getCustomer(id);
		
		model.addAttribute("customer", customer);
		
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int id) {
		
		customerService.deleteCustomer(id);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
                                    Model theModel) {
        // search customers from the service
        List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
                
        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);
        return "list-customers";        
    }
	
	@PostMapping("/save-customer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	
}
