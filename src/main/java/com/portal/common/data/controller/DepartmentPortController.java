package com.portal.common.data.controller;

import java.io.FileOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
import com.portal.common.service.ICommonExcelUploadService;
import com.portal.department.domain.Department;
import com.portal.employee.domain.Employee;
import com.portal.opd.domain.EncounterVisitStatus;
import com.portal.servicecenter.domain.ServiceCenter;
import com.portal.store.domain.Store;

@Controller
@RequestMapping(value = "/department")
public class DepartmentPortController {
	
	private static final Logger logger = LoggerFactory.getLogger(DepartmentPortController.class);
	
	@Autowired
	private ICommonExcelUploadService uploadService;
	
	
	@PostMapping(value = "/portdata")
	@ResponseBody public String portMasterData(@RequestBody String json, HttpServletResponse resp) {
		String response = "";
		Gson gson = new GsonBuilder().create();
		Map<String, Object> responseMap = new HashMap<String, Object>();
		FileOutputStream fos = null;
		try {
			Map<String, Object> bodyMap = gson.fromJson(json, Map.class);
			if(!BeanUtils.isNull(bodyMap.get("type")) && !BeanUtils.isNull(bodyMap.get("excelString"))) {
				String registerPath = System.getProperty("java.io.tmpdir");
				String fileName = System.currentTimeMillis()+".csv";
				System.out.println(registerPath+fileName);
				fos = new FileOutputStream(registerPath+fileName);
				fos.write(Base64.getDecoder().decode(bodyMap.get("excelString").toString()));
				fos.close();
				resp.setHeader("Content-Disposition",
						"attachment;filename="+fileName);
				resp.setContentType("text/csv");
				if(bodyMap.get("type").equals("DEPARTMENT")) {
					List<Department> statusList = uploadService.saveDepartment(fileName);
					if(!BeanUtils.isNullOrEmpty(statusList)) {
						responseMap.put("status", "success");
						responseMap.put("data", statusList);
					}
				}else if(bodyMap.get("type").equals("SERVICECENTER")) {
					List<ServiceCenter> serviceCenterList = uploadService.saveServiceCenters(fileName);
					if(!BeanUtils.isNullOrEmpty(serviceCenterList)) {
						responseMap.put("status", "success");
						responseMap.put("data", serviceCenterList);
					}
				}else if(bodyMap.get("type").equals("STORE")) {
					List<Store> storeList = uploadService.saveStores(fileName);
					if(!BeanUtils.isNullOrEmpty(storeList)) {
						responseMap.put("status", "success");
						responseMap.put("data", storeList);
					}
				}else if(bodyMap.get("type").equals("EMPLOYEE")) {
					List<Employee> empList = uploadService.saveEmployee(fileName);
					if(!BeanUtils.isNullOrEmpty(empList)) {
						responseMap.put("status", "success");
						responseMap.put("data", empList);
					}
				}
			}else {
				responseMap.put("status", "failure");
				responseMap.put("message", "type and excelString are mandatory");
			}
		}catch(Exception e) {
			responseMap.put("status", "failure");
			responseMap.put("message", e.getMessage());
			logger.error("ERROR WHILE PORTING MASTER DATA:::::", e);
		}finally {
			response = gson.toJson(responseMap);
			gson = null;
			responseMap = null;
		}
		return response;
	}

}
