package com.example.demo.api.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.api.model.ExpressInfoReqBody;
import com.example.demo.api.model.ExpressListReqBody;
import com.example.demo.api.service.ExpressService;

@RequestMapping("/api/v1/express/")
@RestController
public class ExpressController {

	@Resource
    private ExpressService expressService;
 
    /**
     * 快递公司查询
     *
     * @param reqBody
     * @return
     */
    @PostMapping(value = "getExpressList")
    public JSONObject getExpressList(@RequestBody ExpressListReqBody reqBody) {
        JSONObject jsonObject = null;
        try {
            jsonObject = expressService.getExpressList(reqBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
 
    /**
     * 快递物流节点跟踪
     */
    @PostMapping(value = "getExpressInfo")
    public JSONObject getExpressInfo(@RequestBody ExpressInfoReqBody reqBody) {
        JSONObject jsonObject = null;
        try {
            jsonObject = expressService.getExpressInfo(reqBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
 
    /**
     *单号查快递公司名
     */
    @GetMapping(value = "fetchCom")
    public JSONObject fetchCom(@RequestParam(value = "nu",required = false) String nu) {
        JSONObject jsonObject = null;
        try {
            jsonObject = expressService.fetchCom(nu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

	
}
