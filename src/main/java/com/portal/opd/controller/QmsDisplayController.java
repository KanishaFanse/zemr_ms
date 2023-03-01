package com.portal.opd.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.portal.BeanUtils;
import com.portal.opd.service.IEncounterService;

@CrossOrigin(maxAge = 3600)
@Controller
@RequestMapping(value = "/qms")
public class QmsDisplayController {

	private static final Logger logger = LoggerFactory.getLogger(QmsDisplayController.class);
	
	@Autowired
	private IEncounterService encounterService;
	
	@Value("${hinai.server.url}")
	private String hinaiUrl;
	
	/*
	 * HERE ALL THE PATIENT WILL LIST BASED ON ENCOUNTER DATE AND DOCTOR 
	 */
	@GetMapping(value = "/displayall")
	@ResponseBody public String displayAllPatients(@RequestParam String empNo, @RequestParam String date) {
		String response = "";
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().create();
		try {
			String url = hinaiUrl+"doctor/dashboard?emp_no="+empNo+"&date="+date;
			System.out.println("URL::::"+url);
			RestTemplate restTemplate = new RestTemplate();
			response = restTemplate.getForObject(url, String.class);
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonObject = (JsonObject) jsonParser.parse(response);
			JsonElement element = jsonObject.get("opMap");
			if(!BeanUtils.isNull(element)) {
				JsonArray opArr = element.getAsJsonArray();
				Iterator<JsonElement> itr = opArr.iterator();
				while(itr.hasNext()) {
					JsonElement temp = itr.next();
					JsonObject obj = temp.getAsJsonObject();
					Long encounterId = obj.get("encounterId").getAsLong();
					List<String> currentStatus = encounterService.find("checkEncounterStatus.Q", 
							new Object[] {encounterId});
					if(!BeanUtils.isNullOrEmpty(currentStatus)) {
						obj.addProperty("consultationStatus", currentStatus.get(0));
					}
					obj.addProperty("vitalAdded", Boolean.FALSE);
					List<Object[]> vitalList = encounterService.find("checkExistingVitals.Q", new Object[] {encounterId});
					if(!BeanUtils.isNullOrEmpty(vitalList)) {
						obj.addProperty("vitalAdded", Boolean.TRUE);
					}
				}
				response = gson.toJson(element);
				encounterService.pushEncounteredPatients(opArr, empNo);
			}
		}catch(Exception e) {
			responseMap.put("status", "failure");
			responseMap.put("messaeg", e.getMessage());
			response = gson.toJson(responseMap);
			logger.error("ERROR WHILE FETCHING DETAILS::::", e);
		}finally {
			responseMap = null;
			gson = null;
		}
		return response;
	}
	
	/*
	 * BY MARKING INPROGRESS/ COMPLETE OF CONSULTATION THIS API NEED TO CALL
	 */
	@PutMapping(value = "/updateEvent")
	@ResponseBody public String updateEvent(@RequestParam String encounterId,
			@RequestParam String status) {
		Gson gson = new GsonBuilder().create();
		Map<String, Object> responseMap = new HashMap<>();
		String response = "";
		try {
			if(status.equals("INPROGRESS") || 
					status.equals("CHECKEDIN") ||
					status.equals("COMPLETED")) {
				encounterService.update("updateEventStatus.Q", new Object[] {status, Long.parseLong(encounterId)});
				responseMap.put("status", "success");
				responseMap.put("message", "event updated");
			}else {
				responseMap.put("status", "failure");
				responseMap.put("message", "Invalid status");
				return gson.toJson(responseMap);
			}
		}catch(Exception e) {
			responseMap.put("status", "failure");
			responseMap.put("message", e.getMessage());
			logger.error("ERROR WHILE UPDATING DETAILS::::", e);
		}finally {
			response = gson.toJson(responseMap);
			gson = null;
			responseMap = null;
		}
		return response;
	}
	
}
