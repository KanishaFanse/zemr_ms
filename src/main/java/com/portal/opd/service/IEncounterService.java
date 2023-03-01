package com.portal.opd.service;

import java.util.List;

import com.google.gson.JsonArray;

public interface IEncounterService {

	void pushEncounteredPatients(JsonArray opArr, String empNo);

	List find(String queryName, Object[] object);

	int update(String queryKey, Object[] params);

}
