package com.sge_mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/demo/login/Administrador")
public class AdministradorController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		
		
		return "login";
	}
	
}
