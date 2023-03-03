package com.portal.ambulance.service;

import java.util.List;

import com.portal.ambulance.domain.AmbulanceMaster;
import com.portal.ambulance.domain.AmbulanceTxn;

public interface IAmbulanceRequestService {

	List find(String queryName, Object[] object);

	AmbulanceMaster saveAmbulanceMaster(AmbulanceMaster master);

	AmbulanceTxn saveAmbulanceTransaction(AmbulanceTxn transaction);

}
