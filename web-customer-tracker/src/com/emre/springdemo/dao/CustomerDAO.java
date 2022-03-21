package com.emre.springdemo.dao;

import java.util.List;

import com.emre.springdemo.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int id);

	public void deleteCustomer(int id);

	public List<Customer> searchCustomers(String theSearchName);
	

}
