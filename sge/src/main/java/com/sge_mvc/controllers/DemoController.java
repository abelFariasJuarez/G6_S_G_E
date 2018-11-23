package com.sge_mvc.controllers;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import sge.modelo.posicionamiento.Transformador;
import sge.modelo.posicionamiento.Ubicacion;
import sge.modelo.posicionamiento.ZonaGeografica;
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
	
	@RequestMapping(value = "/mapa", method = RequestMethod.GET)
	public String mapa(Model model) {
		Ubicacion ubi = new Ubicacion(-34.597316 ,-58.420175);
		ZonaGeografica zona = new ZonaGeografica();
		zona.setCentro(ubi);
		zona.setRadio((float) 500);
		
		Ubicacion ubi2 = new Ubicacion(-34.580000 ,-58.4000000);
		ZonaGeografica zona2 = new ZonaGeografica();
		zona2.setCentro(ubi2);
		zona2.setRadio((float) 700);
		
	List<ZonaGeografica> zonas = new LinkedList<ZonaGeografica>();
	zonas.add(zona);
	zonas.add(zona2);
	model.addAttribute("zonas", zonas);
		//////////////////////////////////////////
		Ubicacion ubi3 = new Ubicacion(-34.597311 ,-58.420178);
	Transformador trans= new Transformador();
	Ubicacion ubi4 = new Ubicacion(-34.580000,-58.4000000);
	Transformador trans2 = new Transformador();
		trans.setUbicacion(ubi3);
		trans2.setUbicacion(ubi4);
		List<Transformador> transs = new LinkedList<Transformador>();
		transs.add(trans);
		transs.add(trans2);
		/*trans.consumoEnPeriodo( LocalDateTime.now(),LocalDateTime.now());*/
		model.addAttribute("transs", transs);
		
		
		
		
		return "mapa";
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