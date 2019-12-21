package com.example.demo.common.service;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

public interface CommonFileService {

	JSONObject uploadFile(MultipartFile[] files);

}
