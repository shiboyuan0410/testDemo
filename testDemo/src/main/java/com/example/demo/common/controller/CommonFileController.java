package com.example.demo.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.service.CommonFileService;

@Controller
@RequestMapping("/file")
public class CommonFileController {

	@Autowired
	private CommonFileService commonFileService;

	/**
	 * 展示文件上传页面
	 * 
	 * @return
	 */
	@RequestMapping("/showWebupload")
	public String showWebupload() {
		return "file/webuploader";
	}

	/**
	 * 文件上传
	 * 
	 * @return
	 */
	@RequestMapping("/uploadFile")
	@ResponseBody
	public JSONObject uploadFile(@RequestParam("files") MultipartFile[] files, HttpServletRequest request,
			HttpServletResponse response) {

		return commonFileService.uploadFile(files);
	}

}
