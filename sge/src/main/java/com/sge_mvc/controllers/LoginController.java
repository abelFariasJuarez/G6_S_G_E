package com.sge_mvc.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sge.modelo.posicionamiento.Transformador;
import sge.modelo.posicionamiento.ZonaGeografica;
import sge.modelo.usuarios.Cliente;
import sge.modelo.usuarios.UsuarioSGE;
import sge.repositorios.Clientes;
import sge.repositorios.Repositorio;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String mostrarLogin() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String verificarLogin(@RequestParam String username, @RequestParam String password, HttpSession session,
			Model model) {
		Repositorio repo = new Repositorio();
		repo.abrir();
		// Clientes usuarios = repo.clientes();
		UsuarioSGE usu = (UsuarioSGE) repo.findBy(UsuarioSGE.class, "username", username);

		if (usu == null || !usu.getPassword().equalsIgnoreCase(password)) {
			model.addAttribute("loginError", "Usuario o contraseña incorrectos.");
			return "login";

		} else {
			String clazzName = usu.getClass().getSimpleName();
			session.setAttribute("usuarioLogueado", usu);
			return "redirect:/" + clazzName;
			/*
			 * String clazzName = usu.getClass().getSimpleName(); if
			 * (clazzName.equals("Cliente")) clazzName="Usuario";
			 */
		}

	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("usuarioLogueado");
		return "redirect:/login";
	}	
	
	@RequestMapping(value = "/mapa", method = { RequestMethod.GET, RequestMethod.POST })
	public String mapa(Model model) {
		/*Ubicacion ubi = new Ubicacion(-34.597316 ,-58.420175);
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
		//model.addAttribute("transs", transs);
		/*trans.consumoEnPeriodo( LocalDateTime.now(),LocalDateTime.now());*/
		
		Repositorio repo = new Repositorio();
		repo.abrir();
		List<ZonaGeografica> zonas=repo.zonas().all();
		List<Transformador> transforms=repo.transformadores().all();
		
		model.addAttribute("transs", transforms);
		model.addAttribute("zonas", zonas);
		
		
		return "mapa";
	}
	
	
}
/*
 * @RequestMapping(method = RequestMethod.POST) public ModelAndView
 * handleRequest(HttpServletRequest request, HttpServletResponse response)
 * throws ServletException, IOException { ModelAndView modelAndView = new
 * ModelAndView(); Repositorio repo = new Repositorio(); repo.abrir(); String
 * usuario = request.getParameter("username"); String password =
 * request.getParameter("password"); Clientes usuarios = repo.clientes();
 * UsuarioSGE usu = (UsuarioSGE) repo.findBy(UsuarioSGE.class, "username",
 * usuario);
 * 
 * 
 * 
 * if (usu == null || !usu.getPassword().equalsIgnoreCase(password)) {
 * modelAndView.setViewName("login"); modelAndView.addObject("message",
 * "Usuario o Contraseña incorrecta");
 * 
 * } else { String clazzName = usu.getClass().getSimpleName(); if
 * (clazzName.equals("Cliente")) clazzName="Usuario";
 * modelAndView.setViewName(clazzName); modelAndView.addObject("usuarios",
 * usuarios); modelAndView.addObject("message", usu.getClass().toString());
 * modelAndView.addObject("user", usuario); modelAndView.addObject("password",
 * password); } repo.cerrar(); return modelAndView; }
 * 
 * }
 * 
 * /*
 * 
 * @RequestMapping(value = "demo/login" , method = RequestMethod.POST) public
 * ModelAndView home( HttpServletRequest request, HttpServletResponse response)
 * { //try { String user=request.getParameter("usuario"); String
 * password=request.getParameter("password"); Repositorio repo = new
 * Repositorio(); repo.abrir(); UsuarioSGE usu =
 * repo.clientes().findBy("username", user);
 * 
 * if ( usu!=null) { ModelAndView model = new ModelAndView("home"); if
 * (usu.getPassword().equalsIgnoreCase(password)) { model.addObject("message",
 * "Logueado correctamente"); } else { model.addObject("message",
 * "Usuario o contraseña incorrectos"); }
 * 
 * model.addObject("user", user); model.addObject("password", password);
 * repo.cerrar(); return model; } return null; }
 */
