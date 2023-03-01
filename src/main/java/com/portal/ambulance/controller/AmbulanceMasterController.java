package com.portal.ambulance.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.portal.BeanUtils;
import com.portal.ambulance.domain.AmbulanceMaster;
import com.portal.ambulance.service.IAmbulanceRequestService;

@Controller
@RequestMapping(value = "/ambulance")
public class AmbulanceMasterController {

	private static final Logger logger = LoggerFactory.getLogger(AmbulanceMasterController.class);
	
	@Autowired
	private IAmbulanceRequestService ambulanceService;
	
	@PostMapping(value = "/master/save")
	@ResponseBody public String saveAmbulanceMaster(@RequestBody String json) {
		Gson gson = new GsonBuilder().create();
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			AmbulanceMaster master = gson.fromJson(json, AmbulanceMaster.class);
			if(validateData(master, responseMap)) {
				master.setCreatedDatetime(new Date());
				ambulanceService.saveAmbulanceMaster(master);
				responseMap.put("status", "success");
				responseMap.put("message", "Data Saved Successfully");
			}
		}catch(Exception e) {
			responseMap.put("status", "failure");
			responseMap.put("message", e.getMessage());
			logger.error("ERROR WHILE SAVINF AMBULANCE DETAILS", e);
		}finally {
			
		}
		return gson.toJson(responseMap);
	}
	
	private Boolean validateData(AmbulanceMaster master, Map<String, Object> response) {
		Boolean flag = Boolean.TRUE;
		if(BeanUtils.isNullOrZero(master.getCreatedBy())) {
			response.put("message", "createdBy is required");
			flag = Boolean.FALSE;
			return flag;
		}
		if(BeanUtils.isNullOrEmpty(master.getAmbulanceNo())) {
			response.put("message", "ambulanceNo is required");
			flag = Boolean.FALSE;
			return flag;
		}
		if(BeanUtils.isNullOrEmpty(master.getAmbulanceType())) {
			response.put("message", "ambulanceType is required");
			flag = Boolean.FALSE;
			return flag;
		}
		if(BeanUtils.isNullOrZero(master.getDoctorId())) {
			response.put("message", "doctorId is required");
			flag = Boolean.FALSE;
			return flag;
		}
		if(BeanUtils.isNullOrZero(master.getNurseId())) {
			response.put("message", "nurseId is required");
			flag = Boolean.FALSE;
			return flag;
		}
		return flag;
	}
}
