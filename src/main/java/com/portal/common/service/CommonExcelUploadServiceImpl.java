package com.portal.common.service;

import java.io.FileReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.portal.BeanUtils;
import com.portal.department.domain.Department;
import com.portal.employee.domain.Employee;
import com.portal.opd.domain.EncounterVisitStatus;
import com.portal.repo.IBaseRepository;
import com.portal.servicecenter.domain.ServiceCenter;
import com.portal.store.domain.Store;

@Service
public class CommonExcelUploadServiceImpl implements ICommonExcelUploadService {

	private static final Logger logger = LoggerFactory.getLogger(CommonExcelUploadServiceImpl.class);
	
	@Autowired
	private IBaseRepository repository;
	
	@Override
	public List<Department> saveDepartment(String fileName) {
		String filePath = System.getProperty("java.io.tmpdir") + fileName;
		List<Department> visitStatusList = new ArrayList<>();
		try {
			Reader reader = new FileReader(filePath);
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader(
					"DEPARTMENT_CODE","DEPARTMENT_NAME","DESCRIPTION","DEPARTMENT_TYPE",
					"SITE_NAME").withDelimiter(',').parse(reader);

			Iterator<CSVRecord> iterator = records.iterator();
			Boolean isFirstRecord=Boolean.TRUE;
			Boolean isRecords=Boolean.FALSE;
			while(iterator.hasNext()){
				CSVRecord record = iterator.next();
				if(!isFirstRecord) {
					isRecords = Boolean.TRUE;
					visitStatusList.add(setDepartment(record));
				}else {
					isFirstRecord = Boolean.FALSE;
				}
			}
			if(!BeanUtils.isNullOrEmpty(visitStatusList)){
				/*
				 * SAVE THE DEPARTMENTS
				 */
				saveDepartments(visitStatusList);
			}
		}catch(Exception e) {
			logger.error("ERROR WHILE PUBLISHING DEPARTMENT MASTER:::", e);
		}
		return visitStatusList;
	}
	
	private Department setDepartment(CSVRecord record) {
		Department department = new Department();
		department.setDepartmentCode(record.get("DEPARTMENT_CODE"));
		department.setDepartmentName(record.get("DEPARTMENT_NAME"));
		department.setDescription(record.get("DESCRIPTION"));
		department.setDepartmentType(fetchLookupIdFromValue(record.get("DEPARTMENT_TYPE"), "DEPARTMENT_TYPE"));
		department.setBranchId(fetchSiteIdFromCode(record.get("SITE_NAME")));
		department.setCreatedBy(1L);
		department.setUpdatedBy(1L);
		department.setCreatedDatetime(new Date());
		department.setUpdatedDatetime(new Date());
		return department;
	}
	
	private Long fetchLookupIdFromValue(String lookupCode, String categoryName) {
		Long result = 0L;
		List<Long> lookupList = repository.find("fetchIdFromLookup.Q", new Object[] {categoryName ,lookupCode});
		if(!BeanUtils.isNullOrEmpty(lookupList)) {
			result = lookupList.get(0);
		}
		return result;
	}
	
	private Long fetchSiteIdFromCode(String siteName) {
		Long result = 0L;
		List<Long> lookupList = repository.find("fetchIdFromOrg.Q", new Object[] {siteName});
		if(!BeanUtils.isNullOrEmpty(lookupList)) {
			result = lookupList.get(0);
		}
		return result;
	}
	
	private void saveDepartments(List<Department> deptList) {
		for(Department dept: deptList) {
			repository.save(dept);
		}
	}
	
	@Override
	public List<ServiceCenter> saveServiceCenters(String fileName) {
		String filePath = System.getProperty("java.io.tmpdir") + fileName;
		List<ServiceCenter> visitStatusList = new ArrayList<>();
		try {
			Reader reader = new FileReader(filePath);
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader(
					"SERVICE_CENTER_CODE","SERVICE_CENTER_NAME","SERVICE_CENTER_TYPE","DEPARTMENT","SITE_NAME").withDelimiter(',').parse(reader);

			Iterator<CSVRecord> iterator = records.iterator();
			Boolean isFirstRecord=Boolean.TRUE;
			Boolean isRecords=Boolean.FALSE;
			while(iterator.hasNext()){
				CSVRecord record = iterator.next();
				if(!isFirstRecord) {
					isRecords = Boolean.TRUE;
					//visitStatusList.add(setVisitStatus(record));
					visitStatusList.add(saveServiceCenter(record));
				}else {
					isFirstRecord = Boolean.FALSE;
				}
			}
			if(!BeanUtils.isNullOrEmpty(visitStatusList)){
				/*
				 * SAVE THE SERVICECENTER
				 */
				saveServiceCenterList(visitStatusList);
			}
		}catch(Exception e) {
			logger.error("ERROR WHILE PUBLISHING DEPARTMENT MASTER:::", e);
		}
		return visitStatusList;
	}
	
	private ServiceCenter saveServiceCenter(CSVRecord record) {
		ServiceCenter center = new ServiceCenter();
		center.setCode(record.get("SERVICE_CENTER_CODE"));
		center.setName(record.get("SERVICE_CENTER_NAME"));
		center.setDescription(record.get("SERVICE_CENTER_NAME"));
		center.setServiceCenterType(fetchLookupIdFromValue(record.get("SERVICE_CENTER_TYPE"), "SC_TYPES"));
		center.setSiteId(fetchSiteIdFromCode(record.get("SITE_NAME")));
		center.setDepartmentId(fetchIdfromDeptName(center.getSiteId(), record.get("DEPARTMENT")));
		center.setStoreAttached("1");
		center.setActive("1");
		center.setCreatedBy(1L);
		center.setUpdatedBy(1L);
		center.setCreatedDatetime(new Date());
		center.setUpdatedDatetime(new Date());
		return center;
	}
	
	private Long fetchIdfromDeptName(Long siteId, String departmentName) {
		Long deptid = null;
		List<Long> deptList = repository.find("fetchDeptIdFromSite.Q", new Object[] {siteId, departmentName});
		if(!BeanUtils.isNullOrEmpty(deptList)) {
			deptid = deptList.get(0);
		}
		return deptid;
	}
	
	private void saveServiceCenterList(List<ServiceCenter> scList) {
		for(ServiceCenter sc: scList) {
			repository.save(sc);
		}
	}
	
	@Override
	public List<Store> saveStores(String fileName) {
		String filePath = System.getProperty("java.io.tmpdir") + fileName;
		List<Store> visitStatusList = new ArrayList<>();
		try {
			Reader reader = new FileReader(filePath);
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader(
					"STORE_CODE","STORE_NAME","SITE_NAME").withDelimiter(',').parse(reader);

			Iterator<CSVRecord> iterator = records.iterator();
			Boolean isFirstRecord=Boolean.TRUE;
			Boolean isRecords=Boolean.FALSE;
			while(iterator.hasNext()){
				CSVRecord record = iterator.next();
				if(!isFirstRecord) {
					isRecords = Boolean.TRUE;
					visitStatusList.add(setStore(record));
				}else {
					isFirstRecord = Boolean.FALSE;
				}
			}
			if(!BeanUtils.isNullOrEmpty(visitStatusList)){
				saveStoreList(visitStatusList);
			}
		}catch(Exception e) {
			logger.error("ERROR WHILE PUBLISHING DEPARTMENT MASTER:::", e);
		}
		return visitStatusList;
	}
	
	private Store setStore(CSVRecord record) {
		Store store = new Store();
		store.setCode(record.get("STORE_CODE"));
		store.setName(record.get("STORE_NAME"));
		System.out.println("record.get(\"SITE_NAME\")::::"+record.get("SITE_NAME"));
		store.setSiteId(fetchSiteIdFromCode(record.get("SITE_NAME")));
		store.setActive("1");
		store.setCreatedBy(1L);
		store.setCreatedDatetime(new Date());
		store.setUpdatedBy(1L);
		store.setUpdatedDatetime(new Date());
		return store;
	}
	
	private void saveStoreList(List<Store> storeList) {
		for(Store sc: storeList) {
			repository.save(sc);
		}
	}
	
	@Override
	public List<Employee> saveEmployee(String fileName) {
		String filePath = System.getProperty("java.io.tmpdir") + fileName;
		List<Employee> visitStatusList = new ArrayList<>();
		try {
			Reader reader = new FileReader(filePath);
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader(
					"PREFIX","FIRST_NAME","MIDDLE_NAME","LAST_NAME","DOB",
					"GENDER","EMAIL","MOBILE_NO","EMPLOYEE_NO","QUALIFICATION",
					"LICENSE_NO","EMP_DESC","AWARDS","DEPARTMENT","SITE_NAME",
					"ACHIEVEMENTS","USER_NAME").withDelimiter(',').parse(reader);

			Iterator<CSVRecord> iterator = records.iterator();
			Boolean isFirstRecord=Boolean.TRUE;
			Boolean isRecords=Boolean.FALSE;
			while(iterator.hasNext()){
				CSVRecord record = iterator.next();
				if(!isFirstRecord) {
					isRecords = Boolean.TRUE;
					visitStatusList.add(setEmployee(record));
				}else {
					isFirstRecord = Boolean.FALSE;
				}
			}
			if(!BeanUtils.isNullOrEmpty(visitStatusList)){
				saveEmployeeList(visitStatusList);
			}
		}catch(Exception e) {
			logger.error("ERROR WHILE PUBLISHING DEPARTMENT MASTER:::", e);
		}
		return visitStatusList;
	}
	
	private Employee setEmployee(CSVRecord record) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Employee employee = new Employee();
		employee.setPrefix(fetchLookupIdFromValue(record.get("PREFIX"), "PRE_FIX"));
		employee.setFirstName(record.get("FIRST_NAME") != null ? record.get("FIRST_NAME").toUpperCase() : "");
		employee.setMiddleName(record.get("MIDDLE_NAME") != null ? record.get("MIDDLE_NAME").toUpperCase() : "");
		employee.setLastName(record.get("LAST_NAME") != null ? record.get("LAST_NAME").toUpperCase() : "");
		employee.setFullName(setFullName(employee));
		try {
			employee.setDob(format.parse(record.get("DOB")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		employee.setGender(fetchLookupIdFromValue(record.get("GENDER"), "SEX"));
		employee.setEmail(record.get("EMAIL"));
		employee.setMobileNo(record.get("MOBILE_NO"));
		employee.setEmpNo(record.get("EMPLOYEE_NO"));
		employee.setQualification(record.get("QUALIFICATION"));
		employee.setLicenseNo(record.get("LICENSE_NO"));
		employee.setEmpDesc(record.get("EMP_DESC"));
		employee.setAchievements(record.get("AWARDS"));
		employee.setSiteId(fetchSiteIdFromCode(record.get("SITE_NAME")));
		employee.setDepartmentId(fetchIdfromDeptName(employee.getSiteId(), record.get("SITE_NAME")));
		employee.setCreatedBy(1L);
		employee.setUpdatedBy(1L);
		employee.setCreatedDatetime(new Date());
		employee.setUpdatedDatetime(new Date());
		return employee;
	}
	
	private void saveEmployeeList(List<Employee> employeeList) {
		for(Employee emp: employeeList) {
			repository.save(emp);
		}
	}
	
	private String setFullName(Employee emp) {
		String fullName = "";
		if(!BeanUtils.isNullOrEmpty(emp.getFirstName())) {
			fullName = emp.getFirstName();
		}
		if(!BeanUtils.isNullOrEmpty(emp.getMiddleName())) {
			fullName += " "+emp.getMiddleName();
		}
		if(!BeanUtils.isNullOrEmpty(emp.getLastName())) {
			fullName += " "+emp.getLastName();
		}
		return fullName;
	}
}
