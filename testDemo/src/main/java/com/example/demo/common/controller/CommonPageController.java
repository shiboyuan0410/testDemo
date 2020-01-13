package com.example.demo.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonPageController {

	/**
	 * 404
	 * 
	 * @return
	 */
	@RequestMapping("/404")
	public String notFoundPage() {
		return "404";
	}

	/**
	 * 404
	 * 
	 * @return
	 */
	@RequestMapping("/500")
	public String errorPage() {
		return "500";
	}

}
