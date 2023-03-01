package com.portal.drug.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
public class DrugOrderController {

	private static final Logger logger = LoggerFactory.getLogger(DrugOrderController.class);
	
	@PostMapping(value = "/savedrugorder")
	@ResponseBody public String saveDrugOrder(@RequestBody String json) {
		String response = "";
		Map<String, Object> exceptionMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().create();
		try {
			
		}catch(Exception e) {
			exceptionMap.put("status", "failure");
			exceptionMap.put("message", e.getMessage());
			response = gson.toJson(exceptionMap);
			logger.error("ERROR WHILE SAVING DRUG ORDER:::", e);
		}finally {
			gson = null;
			exceptionMap = null;
		}
		return response;
	}
}
