package com.example.demo.api.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.api.model.ExpressInfoReqBody;
import com.example.demo.api.model.ExpressListReqBody;
import com.example.demo.api.service.ExpressService;
import com.example.demo.api.utils.HttpUtils;



@Service
public class ExpressServiceImpl implements ExpressService {

	private String appcode ="f7332a283ffb4829bb85e3503402072e";


	@Override
	public JSONObject getExpressList(ExpressListReqBody reqBody) {
		String host = "https://ali-deliver.showapi.com";
		String path = "/showapi_expressList";
		String method = "GET";

		Map<String, String> headers = new HashMap<>(16);
		//最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<>(16);
		querys.put("expName", reqBody.getExpName());
		querys.put("maxSize", String.valueOf(reqBody.getMaxSize()));
		querys.put("page", String.valueOf(reqBody.getPage()));
		return getJsonObject(host, path, method, headers, querys);
	}


	@Override
	public JSONObject getExpressInfo(ExpressInfoReqBody reqBody) {
		String host = "https://ali-deliver.showapi.com";
		String path = "/showapi_expInfo";
		String method = "GET";
		Map<String, String> headers = new HashMap<>(16);
		//最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<>(16);
		querys.put("com", reqBody.getCom());
		querys.put("nu", reqBody.getNu());
		querys.put("receiverPhone", reqBody.getReceiverPhone());
		querys.put("senderPhone", reqBody.getSenderPhone());
		return getJsonObject(host, path, method, headers, querys);
	}


	@Override
	public JSONObject fetchCom(String nu) {
		String host = "https://ali-deliver.showapi.com";
		String path = "/fetchCom";
		String method = "GET";
		Map<String, String> headers = new HashMap<>(16);
		//最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<>(16);
		querys.put("nu", nu);
		return getJsonObject(host, path, method, headers, querys);
	}

	/**
	 *
	 * @param host
	 * @param path
	 * @param method
	 * @param headers
	 * @param querys
	 * @return
	 */
	private JSONObject getJsonObject(String host, String path, String method, Map<String, String> headers, Map<String, String> querys) {
		HttpResponse response;
		String str = null;
		try {
			response = HttpUtils.doGet(host, path, method, headers, querys);
			str = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSONObject.parseObject(str);
		return jsonObject;
	}

}
