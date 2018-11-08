package com.sge_mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sge.modelo.usuarios.UsuarioSGE;
import sge.repositorios.Repositorio;

@Controller
@RequestMapping("demo")
public class DemoController {

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(String usuario, String contrasenia) {
		//try {

			Repositorio repo = new Repositorio();
			repo.abrir();
			UsuarioSGE usu = repo.clientes().findBy("username", usuario);

			ModelAndView model = new ModelAndView("home");
			if (usu.getPassword().equalsIgnoreCase(contrasenia)) {
				model.addObject("message", "Logueado correctamente");
			} else {
				model.addObject("message", "Usuario o contraseña incorrectos");
			}

			model.addObject("user", usuario);
			model.addObject("password", contrasenia);
			repo.cerrar();
			return model;
	/*	} catch (Exception e) {
			// TODO: handle exception
			ModelAndView modelError = new ModelAndView("login");
			modelError.addObject("message", "Error en el logueo");
			return modelError;
		}*/
	}
}