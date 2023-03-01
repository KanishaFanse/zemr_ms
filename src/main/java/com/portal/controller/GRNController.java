package com.portal.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.portal.BeanUtils;
import com.portal.PortDecider;
import com.portal.domain.GoodsReceiptNotes;
import com.portal.domain.GoodsReceiptNotesLineitems;
import com.portal.repo.IBaseRepository;

@RestController
public class GRNController {
	
	@Autowired
	private IBaseRepository repository;
	
	@Autowired
	private Environment env;
	
	@PostMapping(value="/fetchGRN")
	public @ResponseBody String test(@RequestParam String storeCode, @RequestBody String json) {
		String response  = "";
		Gson gson = new GsonBuilder().create();
		try {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};
			HashMap<String,Object> o = mapper.readValue(json, typeRef); 
			if(!BeanUtils.isNull(o) && !BeanUtils.isNull(o.get("code")) && "200".equals(o.get("code"))) {
				List<GoodsReceiptNotes> grnList = new ArrayList<>();
				ArrayList<Map<String, Object>> arrayList = (ArrayList<Map<String, Object>>)o.get("grnDetails");
				for(Map<String, Object> map : arrayList) {
					Map<String, Object> criteria = new HashMap<>();
					criteria.put("urlId", map.get("billNo").toString());
					List<Object[]> transactionNo = repository.getData("grn.existing.check", criteria);
					System.out.println("transactionNo****"+transactionNo);
					if(BeanUtils.isNullOrEmpty(transactionNo)) {
						String transNo = map.get("tranNo").toString();
						String[] strArr = transNo.split("/");
						GoodsReceiptNotes notes = new GoodsReceiptNotes();
						notes.setTranNo(map.get("tranNo").toString());
						notes.setBillNo(map.get("billNo").toString());
						notes.setDate(map.get("date").toString());
						notes.setRefNo(map.get("refNo").toString());
						notes.setRefDate(map.get("refDate").toString());
						notes.setDocTotal((Double)map.get("docTotal"));
						notes.setFromGstNo(map.get("fromGstNo").toString());
						notes.setToGstNo(map.get("toGstNo").toString());
						notes.setPost(map.get("post").toString());
						notes.setCgstAmt(Double.valueOf(map.get("cgstAmt").toString()));
						notes.setSgstAmt(Double.valueOf(map.get("sgstAmt").toString()));
						notes.setIgstAmt(Double.valueOf(map.get("igstAmt").toString()));
						notes.setCessAmt(Double.valueOf(map.get("cessAmt").toString()));
						notes.setStoreCode(storeCode);
						notes.setCreatedDate(new Date());
						ArrayList<Map<String, Object>> innerList = 
								(ArrayList<Map<String, Object>>)map.get("itemInfo");
						for(Map<String, Object> innMap : innerList) {
							GoodsReceiptNotesLineitems lineItem = new GoodsReceiptNotesLineitems();
							lineItem.setProductId(innMap.get("productId").toString());
							lineItem.setProductName(innMap.get("productName").toString());
							lineItem.setGstCode(innMap.get("hsnCode").toString());
							lineItem.setQtyPerBox(Float.valueOf(innMap.get("qtyPerBox").toString()));
							lineItem.setBatch(innMap.get("batch").toString());
							lineItem.setQty(Float.valueOf(innMap.get("qty").toString()));
							lineItem.setExpiryDate(innMap.get("expiryDate").toString());
							lineItem.setMrp(Double.valueOf(innMap.get("mrp").toString()));
							lineItem.setSaleRate(Double.valueOf(innMap.get("itemTotal").toString())/lineItem.getQty());
							lineItem.setItemTotal(Float.valueOf(innMap.get("itemTotal").toString()));
							lineItem.setApiSaleRate(Double.valueOf(innMap.get("saleRate").toString()));
							lineItem.setGstCode(innMap.get("gstCode").toString());
							lineItem.setCgstPer(Float.valueOf(innMap.get("cgstPer").toString()));
							lineItem.setCgstAmt(Double.valueOf(innMap.get("cgstAmt").toString()));
							lineItem.setSgstPer(Float.valueOf(innMap.get("sgstPer").toString()));
							lineItem.setSgstAmt(Double.valueOf(innMap.get("sgstAmt").toString()));
							lineItem.setIgstPer(Float.valueOf(innMap.get("igstPer").toString()));
							lineItem.setIgstAmt(Double.valueOf(innMap.get("igstAmt").toString()));
							lineItem.setCessPer(Float.valueOf(innMap.get("cessPer").toString()));
							lineItem.setCessAmt(Double.valueOf(innMap.get("cessAmt").toString()));
							notes.getLineItems().add(lineItem);
						}
						grnList.add(notes);
					
					}
				}
				if(!grnList.isEmpty()) {
					for(GoodsReceiptNotes note : grnList) {
						repository.save(note);
					}
					//NEED TO UNCOMMENT LATER
					/*System.out.println(env.getProperty("server.url")+"grn/create?userLogin="+env.getProperty("pinkpharma.grn.username"));
					CloseableHttpClient client = HttpClients.createDefault();
					HttpPost httpPost = new HttpPost(env.getProperty("server.url")+"grn/create?userLogin="+env.getProperty("pinkpharma.grn.username"));
					StringEntity entity = new StringEntity(gson.toJson(grnList));
					httpPost.setEntity(entity);
					httpPost.setHeader("Content-Type", "application/json");
					ResponseHandler<String> responseHandler=new BasicResponseHandler();
					String responseBody = client.execute(httpPost, responseHandler);
					JSONObject jsonObj=new JSONObject(responseBody);
					response = jsonObj.toString();*/
				}
			}
		}catch(Exception e) {
			response = e.getMessage();
			e.printStackTrace();
		}
		return response;
	}
	
	@GetMapping(value="/url_ports")
	public @ResponseBody Map<String, Object> testUrls() {
		Map<String, Object> responseMap = new HashMap<>();
		try {
			List<Object[]> objList = repository.find("patient.pharma.header",
					new Object[] {0});
			responseMap.put("status", "success");
			responseMap.put("data", objList);
			
		}catch(Exception e) {
			responseMap.put("status", "failure");
			responseMap.put("message", e.getMessage());
		}
		return responseMap;
	}
}
