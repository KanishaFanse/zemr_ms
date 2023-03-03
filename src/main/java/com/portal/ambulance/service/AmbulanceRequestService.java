package com.portal.ambulance.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.ambulance.domain.AmbulanceMaster;
import com.portal.ambulance.domain.AmbulanceTxn;
import com.portal.repo.IBaseRepository;

@Service
public class AmbulanceRequestService implements IAmbulanceRequestService {

	private static final Logger logger = LoggerFactory.getLogger(AmbulanceRequestService.class);
	
	@Autowired
	private IBaseRepository repository;
	
	@Override
	public List find(String queryName, Object[] object){
		return repository.find(queryName, object);
	}
	
	@Override
	public AmbulanceMaster saveAmbulanceMaster(AmbulanceMaster master) {
		repository.save(master);
		return master;
	}
	
	@Override
	public AmbulanceTxn saveAmbulanceTransaction(AmbulanceTxn transaction) {
		repository.save(transaction);
		return transaction;
	}
	
}
