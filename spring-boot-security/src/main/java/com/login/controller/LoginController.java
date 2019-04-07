package com.login.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netflix.client.http.HttpHeaders;

@Controller
public class LoginController {
	
	@RequestMapping("/getWelcomePage")
	public String getWelcomePage() {
		System.out.println("get welcome");
		return "welcome.jsp";
	}
	
	@RequestMapping("/getWebSiteInfo")
	@ResponseBody 
	public String getWebSiteInfo(@RequestHeader("appKey") String appKey) {
		System.out.println("inside getWebSiteInfo::"+appKey);
		
		return "this is secure login";
	}
	
	@RequestMapping("/reqHttpHeaderToken")
	@ResponseBody 
	public String reqHttpHeaderToken(@RequestHeader String header) {
		System.out.println("inside getWebSiteInfo::"+header);
		
		return "this is secure login";
	}

}
