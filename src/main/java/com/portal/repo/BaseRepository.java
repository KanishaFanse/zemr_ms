package com.portal.repo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class BaseRepository implements IBaseRepository {
	private static Logger logger = LoggerFactory.getLogger(BaseRepository.class);
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private Environment env;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	@Transactional
	public void save(Object obj) {
		entityManager.persist(obj);
	}
	
	@Override
	public List<Object[]> getData(String key,Map<String, Object> paramList){
		List<Object[]> resultList =  null;
		try{
			resultList = template.queryForList(env.getProperty(key),paramList).stream().
					map(row -> row.values().toArray(new String[row.size()])).collect(Collectors.toList());
			logger.error("RESULT DATA---"+resultList);
		}catch(Exception e){
			logger.error("ERROR---"+e);
			e.printStackTrace();
		}
		return resultList;
	}
	
	@Override
	public String getSingleData(String key,Map<String, Object> paramList){
		String str =null;
		try{
			str =  template.queryForObject(env.getProperty(key), paramList,String.class);
		}catch(Exception e){
			logger.error("ERROR---"+e);
			e.printStackTrace();
		}
		return str;
	}
	
	@Override
	public List load(String query, Map<String, Object> paramList){
    	return template.queryForList(env.getProperty(query), paramList);
	}
	
	@Override
	public Integer updateData(String query, List<String> stringList) {
		Integer i = 0;
		try {
			SqlParameterSource sqlSource = new MapSqlParameterSource().addValue("inputs", stringList);
			i = template.update(env.getProperty(query), sqlSource);
		}catch(Exception e) {
			logger.error("ERROR---", e);
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public List find(String queryName,  Object[] object) {
		List list = null;
		Query query = entityManager.createNamedQuery(queryName);
		if (object != null && object.length > 0) {
			for (int i = 0; i < object.length; i++) {
				query.setParameter(i + 1, object[i]);
			}
		}
		list = query.getResultList();
		return list;
	}
	
	@Override
	@Transactional
	public int update(final String queryKey, final Object[] params) {
		if (queryKey == null || params == null) {
			throw new IllegalArgumentException(
					"Query Key or Parameters should not be null");
		}
		logger.debug("Executing Query name is------" + queryKey);
		Query query = entityManager.createNamedQuery(queryKey);
		bindParamtersToQuery(params, query);
		int rowsEffected = query.executeUpdate();
		logger.info(" No of rows effected for current update.................."
				+ rowsEffected);
		return rowsEffected;
	}
	
	protected void bindParamtersToQuery(final Object[] parameters, Query query) {
		if (parameters != null) {
			int i = 0;
			while (i < parameters.length) {
				query.setParameter(i + 1, parameters[i++]);
			}
		}
	}
}
