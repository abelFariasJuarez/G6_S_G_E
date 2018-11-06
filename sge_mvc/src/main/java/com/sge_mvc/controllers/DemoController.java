package com.sge_mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("demo")
public class DemoController {

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView home(String usuario, String contrasenia) {
		try {
			ModelAndView model = new ModelAndView("home");
			model.addObject("message", "Logueado correctamente");
			model.addObject("user", usuario);
			model.addObject("password", contrasenia);
		    return model;
		} catch (Exception e) {
			// TODO: handle exception
			ModelAndView modelError = new ModelAndView("login");
			modelError.addObject("message", "Error en el logueo");
			return modelError; 
		}
	}
}