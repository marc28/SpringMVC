package com.marc.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.marc.domain.DomainObject;

public abstract class AbstractMapService {

	protected Map<Integer, DomainObject> domainMap;

	public AbstractMapService() {
		domainMap = new HashMap<>();
		loadDomainObjects();
	}

	public List<DomainObject> listAll() {
		return new ArrayList<>(domainMap.values());
	}

	public DomainObject getById(Integer id) {
		return domainMap.get(id);
	}

	public DomainObject saveOrUpdate(DomainObject domainObject) {
		if (domainObject != null) {
			if (domainObject.getId() == null) {
				domainObject.setId(getNextGen());
			}
			domainMap.put(domainObject.getId(), domainObject);
			return domainObject;
		}else{
			throw new RuntimeException("Domain Object can't be null");
		}
	}

	public void delete(Integer id){
		domainMap.remove(id);
	}
	private Integer getNextGen() {
		return Collections.max(domainMap.keySet()) + 1;
	}

	public abstract void loadDomainObjects();
}
