package com.portal.department.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.portal.service.ICommonService;

@Controller
@RequestMapping(value = "/department")
public class DepartmentMasterController {
	
	private static final Logger logger = LoggerFactory.getLogger(DepartmentMasterController.class);
	
	@Autowired
	private ICommonService service;
	
	@GetMapping(value = "/clinical/list")
	@ResponseBody public String fetchDepartments(@RequestParam String siteId) {
		String response = "";
		Gson gson = new GsonBuilder().create();
		Map<String, Object> responseMap = new LinkedHashMap<>();
		List<Map<String, Object>> mapList = new LinkedList<>();
		List<Object[]> deptList = null;
		try {
			deptList = service.find("clinicalDepartmentList.Q", new Object[] {Long.parseLong(siteId), "CLINICAL"});
			for(Object[] obj : deptList) {
				Map<String, Object> map = new HashMap<>();
				map.put("departmentId", obj[0]);
				map.put("departmentCode", obj[1]);
				map.put("departmentName", obj[2]);
				map.put("description", obj[3]);
				map.put("departmentType", obj[4]);
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

}
