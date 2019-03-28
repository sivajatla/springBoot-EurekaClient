package com.spring.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	
	@RequestMapping("/")
	public String getWelcomePage() {
		System.out.println("get welcome");
		return "welcome.jsp";
	}
	
	@RequestMapping("/getWebSiteInfo")
	@ResponseBody 
	public String getWebSiteInfo() {
		System.out.println("inside getWebSiteInfo");
		return "this is secure login";
	}

}
