package com.portal.controller;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.portal.BeanUtils;
import com.portal.PortDecider;
import com.portal.repo.IBaseRepository;

@Controller
public class TestController {

private static Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	private IBaseRepository repository;
	
	@GetMapping(value="/test")
	public @ResponseBody String test() {
		Gson gson = new GsonBuilder().create();
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			List<Object[]> prescriptionHeaders = repository.find("patient.pharma.header", new Object[] {0});
			System.out.println(prescriptionHeaders);
			for(Object[] obj : prescriptionHeaders) {
				StringBuilder append = new StringBuilder();
				try {
					String url = env.getProperty("pinkpharma.base.url")+
							PortDecider.getPortNo(obj[0].toString())
							+"/ws_egservice_omic_create_sord?cIndex=create";
					append.append("&brCode="+obj[0].toString());
					append.append("&order_no="+obj[1].toString());
					append.append("&order_date="+obj[2].toString());
					append.append("&act_code="+obj[0].toString());
					append.append("&customer_name="+obj[3].toString());
					if(!BeanUtils.isNull(obj[4])) {
						append.append("&email="+obj[4].toString());
					}
					append.append("&doctor_name="+obj[7].toString());
					String patId =obj[5].toString();
					if(patId.contains("KJSS")) {
						patId = patId.replace("KJSS", "");
					}else if(patId.contains("KJSG")) {
						patId = patId.replace("KJS", "");
					}else if(patId.contains("KJSC")) {
						patId = patId.replace("UH", "");
					}
					append.append("&customer_id="+patId);
					append.append("&customer_mobile="+obj[6].toString());
					append.append("&shipping_name="+obj[3].toString());
					if(!BeanUtils.isNull(obj[8])) {
						String s = obj[8].toString();
						String address1  = s;
						address1 = address1.substring(0, Math.min(address1.length(), 11));
						append.append("&shipping_add1=");
						append.append("&shipping_add2=");
						
					}
					append.append("&shipping_city="+obj[9].toString());
					append.append("&shipping_pin="+obj[10].toString());
					append.append("&shipping_state="+obj[11].toString());
					append.append("&shipping_mobile_no="+obj[6].toString());
					append.append("&billing_name="+obj[3].toString());
					if(!BeanUtils.isNull(obj[8])) {
						String s = obj[8].toString();
						String address1  = s;
						address1 = address1.substring(0, Math.min(address1.length(), 11));
						append.append("&billing_add1=");
						append.append("&billing_add2=");
						
					}
					append.append("&billing_city="+obj[9].toString());
					append.append("&billing_pin="+obj[10].toString());
					append.append("&billing_state="+obj[11].toString());
					append.append("&billing_mobile_no="+obj[6].toString()+"&discount_per=0&urgent=0&conversion=0");
					List<Object[]> prescriptionItemList = repository.find("patient.pharma.lineitems", 
							new Object[] {obj[0].toString(), obj[1].toString()});
					for(Object[] item : prescriptionItemList) {
						append.append("&item[]['item_id']="+item[0]+"&item[]"
								+ "['item_branch_id']="+obj[0]+"&item[]['item_qty']="+item[1]+
								"&item[]['item_price']=&item[]['item_discount']=0&item[]['disc_per']=0");
					}
					append.append("&inter_sale=0&disc_rs=0&order_value=0&payment method=3&post=1&orderToDc=0");
					String fullUrl = url+append.toString();
					logger.error("FULL URL:::"+fullUrl);
					RestTemplate restTemplate = new RestTemplate();
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_JSON);
					ResponseEntity<String> countResp = 
							restTemplate.getForEntity(fullUrl, String.class);
					logger.error("SALES ORDER RESPONSE::"+countResp.getBody());
					Type types = new TypeToken<List<Map<String, String>>>() {
			        }.getType();
					List<Map<String, String>> tempOuter = gson.fromJson(countResp.getBody(), types);
					System.out.println(tempOuter);
					for(Map<String, String> tempMap : tempOuter) {
						System.out.println(tempMap.get("status"));
						if(tempMap.get("status").equalsIgnoreCase("Sales Order Created Successfully")) {
							String orderNo = tempMap.get("cmessage");
							orderNo = orderNo.replace("\\/", "/");
							repository.update("salesOrder.UpdateQ", new Object[] {orderNo, obj[1].toString()});
						}
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return gson.toJson(responseMap);
	}
	
	@GetMapping(value="/dummy")
	public @ResponseBody String dummy() {
		try {
			repository.update("salesOrder.UpdateQ", new Object[] {"026/22/6/4", "CKJSGH2204000002"});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
