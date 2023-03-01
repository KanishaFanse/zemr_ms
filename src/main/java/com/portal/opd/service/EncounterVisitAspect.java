package com.portal.opd.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.portal.BeanUtils;
import com.portal.opd.domain.EncounterVisitStatus;
import com.portal.repo.IBaseRepository;


@Service
public class EncounterVisitAspect {

	private static final Logger logger = LoggerFactory.getLogger(EncounterVisitAspect.class);
	
	@Autowired
	private IBaseRepository repository;
	
	public void pushOpdPatients(JsonArray opArr, String empNo) {
		try {
			List<EncounterVisitStatus> visitStatusList = new ArrayList<>();
			Iterator<JsonElement> itr = opArr.iterator();
			while(itr.hasNext()) {
				JsonElement temp = itr.next();
				JsonObject obj = temp.getAsJsonObject();
				Long encounterId = obj.get("encounterId").getAsLong();
				List<Long> encList = repository.find("checkEncounterVisitExist.Q", new Object[] {encounterId});
				if(BeanUtils.isNullOrEmpty(encList)) {
					EncounterVisitStatus visitStatus = new EncounterVisitStatus();
					visitStatus.setBranchCode(obj.get("branchCode").getAsString());
					visitStatus.setConsultStatus(obj.get("consultationStatus").getAsString());
					visitStatus.setDepartmentName(obj.get("departmentName").getAsString());
					visitStatus.setEncounterId(obj.get("encounterId").getAsLong());
					visitStatus.setMrno(obj.get("mrn").getAsString());
					visitStatus.setqNo(obj.get("queueNo").getAsString());
					visitStatus.setVisitId(obj.get("visitId").getAsLong());
					visitStatus.setVisitNo(obj.get("visitNo").getAsString());
					visitStatus.setCreatedDatetime(new Date());
					visitStatus.setCreatedBy(1L);
					visitStatus.setEmpNo(empNo);
					visitStatusList.add(visitStatus);
				}
			}
			saveVisitStatus(visitStatusList);
		}catch(Exception e) {
			logger.error("ERROR WHILE PUSHING ENTRIES::::", e);
		}finally {
			
		}
	}
	
	private void saveVisitStatus(List<EncounterVisitStatus> visitStatusList) {
		for(EncounterVisitStatus status : visitStatusList) {
			repository.save(status);
		}
	}
}
