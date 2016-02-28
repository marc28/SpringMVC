package com.marc.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.marc.domain.Customer;
import com.marc.domain.DomainObject;

@Service
public class CustomerServiceImple extends AbstractMapService implements CustomerService {

	@Override
	public List<DomainObject> listAll() {
		return super.listAll();
	}

	@Override
	public Customer getById(Integer id) {
		return (Customer) super.getById(id);
	}

	@Override
	public Customer saveOrUpdate(Customer domainObject) {
		return (Customer) super.saveOrUpdate(domainObject);
	}

	@Override
	public void delete(Integer id) {
		super.delete(id);
	}


	public void loadDomainObjects() {
		domainMap = new HashMap<>();
		Customer cus = new Customer();
		cus.setId(1);
		cus.setFirstName("Marc");
		cus.setLastName("Grogan");
		cus.setEmail("marc@mail.com");
		cus.setPhoneNo("123-456");
		cus.setAddressOne("clonwood");
		cus.setAddressTwo("Clane");
		cus.setCity("kildare");
		cus.setState("Leinster");
		cus.setZip("1H-2FG");
		domainMap.put(cus.getId(), cus);

	}



}
