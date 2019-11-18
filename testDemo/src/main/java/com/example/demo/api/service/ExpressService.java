package com.example.demo.api.service;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.api.model.ExpressInfoReqBody;
import com.example.demo.api.model.ExpressListReqBody;

public interface ExpressService {

	/**
     * 快递公司查询
     *
     * @param reqBody
     * @return
     */
    JSONObject getExpressList(ExpressListReqBody reqBody) throws IOException;
 
    /**
     * 快递物流节点跟踪
     *
     * @param reqBody
     * @return
     * @throws IOException
     */
    JSONObject getExpressInfo(ExpressInfoReqBody reqBody) throws Exception;
 
    /**
     * 单号查快递公司名
     *
     * @param nu
     * @return
     */
    JSONObject fetchCom(String nu);


}
