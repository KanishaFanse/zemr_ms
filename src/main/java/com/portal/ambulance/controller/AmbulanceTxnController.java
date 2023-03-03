package com.portal.ambulance.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.portal.ambulance.domain.AmbulanceMaster;
import com.portal.ambulance.domain.AmbulanceTxn;
import com.portal.ambulance.service.IAmbulanceRequestService;
import com.portal.service.ICommonService;

@Controller
@RequestMapping(value = "/ambulance")
public class AmbulanceTxnController {
private static final Logger logger = LoggerFactory.getLogger(AmbulanceTxnController.class);
	
	@Autowired
	private IAmbulanceRequestService ambulanceService;
	@Autowired
	private ICommonService service;
	
	@PostMapping(value = "/txn/save")
	@ResponseBody public String saveAmbulanceTxn(@RequestBody String json) {
		Gson gson = new GsonBuilder().create();
		Map<String, Object> responseMap = new HashMap<String, Object>();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		try {
			AmbulanceTxn master = gson.fromJson(json, AmbulanceTxn.class);
			if(validateData(master, responseMap)) {
				master.setExpTime(format.parse(master.getExpectedTime()));
				master.setCreatedDatetime(new Date());
				ambulanceService.saveAmbulanceTransaction(master);
				responseMap.put("status", "success");
				responseMap.put("message", "Data Saved Successfully");
			}
		}catch(Exception e) {
			responseMap.put("status", "failure");
			responseMap.put("message", e.getMessage());
			logger.error("ERROR WHILE SAVING AMBULANCE TRANSACTION DETAILS", e);
		}finally {
			
		}
		return gson.toJson(responseMap);
	}
	
	
	@GetMapping(value = "/txn/getAmb")
	@ResponseBody public String fetchAmbulances(@RequestParam String fromDate, @RequestParam String toDate) {
		String response = "";
		Gson gson = new GsonBuilder().create();
		Map<String, Object> responseMap = new LinkedHashMap<>();
		List<Map<String, Object>> mapList = new LinkedList<>();
		List<Object[]> deptList = null;
		try {
			deptList = service.find("getambulances.Q", new Object[] {fromDate,toDate});
			for(Object[] obj : deptList) {
				Map<String, Object> map = new HashMap<>();
				map.put("address", obj[0]);
				map.put("attendent", obj[1]);
				map.put("cancel_remark", obj[2]);
				map.put("chest_pain", obj[3]);
				mapList.add(map);
			}
			responseMap.put("status", "success");
			responseMap.put("data", mapList);
		}catch(Exception e) {
			responseMap.put("status", "failure");
			responseMap.put("message", e.getMessage());
			logger.error("ERROR WHILE FETCHING DETAILS:::", e);
		}finally {
			response = gson.toJson(responseMap);
			gson = null;
			responseMap = null;
			mapList = null;
		}
		return response;
	}
	
	private boolean validateData(AmbulanceTxn master, Map<String, Object> response) {
		// TODO Auto-generated method stub
		return true;
	}
}
