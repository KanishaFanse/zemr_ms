package com.portal.common.service;

import java.util.List;

import com.portal.department.domain.Department;
import com.portal.employee.domain.Employee;
import com.portal.servicecenter.domain.ServiceCenter;
import com.portal.store.domain.Store;

public interface ICommonExcelUploadService {

	public List<Department> saveDepartment(String fileName);
	
	public List<ServiceCenter> saveServiceCenters(String fileName);

	List<Store> saveStores(String fileName);

	List<Employee> saveEmployee(String fileName);

}
