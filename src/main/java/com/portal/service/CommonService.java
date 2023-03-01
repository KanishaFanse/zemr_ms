package com.portal.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.repo.IBaseRepository;

@Service
public class CommonService implements ICommonService {

	private static final Logger logger = LoggerFactory.getLogger(CommonService.class);
	
	@Autowired
	private IBaseRepository repository;
	
	@Override
	public List find(String queryName,  Object[] object) {
		return repository.find(queryName, object);
	}
}
