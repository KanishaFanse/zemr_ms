package com.portal.repo;

import java.util.List;
import java.util.Map;

public interface IBaseRepository {

	public void save(Object obj);
	public List<Object[]> getData(String key,Map<String, Object> paramList);
	String getSingleData(String key, Map<String, Object> paramList);
	List load(String query, Map<String, Object> paramList);
	Integer updateData(String query, List<String> stringList);
	public List find(String queryName,  Object[] object);
	int update(String queryKey, Object[] params);
}
