package com.sge_mvc.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sge.modelo.dispositivo.Dispositivo;
import sge.modelo.dispositivo.DispositivoDisponible;
import sge.modelo.dispositivo.DispositivoFactoryMethod;
import sge.modelo.dispositivo.Inteligente;
import sge.modelo.hogareficiente.Recomendacion;
import sge.modelo.usuarios.Cliente;
import sge.modelo.usuarios.GestorCliente;
import sge.modelo.usuarios.UsuarioSGE;
import sge.repositorios.Clientes;
import sge.repositorios.Repositorio;

@Controller
public class UsuarioController {

	@RequestMapping(value = "/Cliente", method = RequestMethod.GET)
	public String mostrarHomeCliente(HttpServletRequest request, ModelMap modelMap) {
		HttpSession misession= (HttpSession) request.getSession();
		String username =  (String) misession.getAttribute("username");
		Repositorio repo = Repositorio.getInstance();
		repo.abrir();
		Cliente cli = repo.clientes().findBy("username", username);
		modelMap.addAttribute("usuarioLogueado",cli);		
		return "Cliente";
	}

	@RequestMapping(value = "/Cliente/MiHogar", method = RequestMethod.POST)
	public String miHogar(HttpServletRequest request, ModelMap modelMap) {
		HttpSession misession= (HttpSession) request.getSession();
		String username =  (String) misession.getAttribute("username");
		Repositorio repo = Repositorio.getInstance();
		Cliente cli = repo.clientes().findBy("username", username);
		List<Inteligente> list = cli.misInteligentes().collect(Collectors.toList());
		
		modelMap.addAttribute("usuarioLogueado",cli);		
		modelMap.addAttribute("misInteligentes",list);		
		return "mihogar";
	}
	
	@RequestMapping(value = "/Cliente/ConsumoPeriodo", method = RequestMethod.POST)
	public String consumoPeriodo() {
		return "consumoperiodo";
	}
	
	@RequestMapping(value = "/Cliente/CargarDispositivos", method = RequestMethod.POST)
	public String cargarDispositivos(Model model) {
		Repositorio repositorio = Repositorio.getInstance();
		List<DispositivoDisponible> disponibles = repositorio.dispositivosDisponibles().all();
		model.addAttribute("disponibles", disponibles);
		return "cargardispositivos";
	}
	
	@RequestMapping(value = "/Cliente/Simplex", method = RequestMethod.POST)
	public String ejecutarSimplex() {
		return "simplex";
	}

	@RequestMapping(value = "/Cliente/ABMDispositivos", method = RequestMethod.POST)
	public String abmDispositivos(HttpServletRequest request) {
		return "abmdispositivos";
	}
	
	@RequestMapping(value = "/Cliente/ABMDispositivos", method = RequestMethod.POST, params = "nuevoDisp")
	public String addDispositivoAlCliente(@RequestParam("fcodigo") String fcodigo, 
			@RequestParam("fname") String fname,
			HttpServletRequest request) {
		
		  HttpSession misession= (HttpSession) request.getSession();
		  Cliente usu =  (Cliente) misession.getAttribute("usuarioLogueado");
			return "abmdispositivos";
	}


	@RequestMapping(value = "Cliente/simplex", method = RequestMethod.POST)
	public String simplex(HttpServletRequest request,Model model) {
		  HttpSession misession= (HttpSession) request.getSession();
		  Cliente usu =  (Cliente) misession.getAttribute("usuarioLogueado");
		  model.addAttribute("dispositivos", usu.getDispositivos());
		 Recomendacion reco = usu.getMejorCombinacionDispositivos();
		 model.addAttribute("recomendacion", usu.getMejorCombinacionDispositivos());
		 
		 
		
		  return "simplex";
		  
		  
	}
	
	@RequestMapping(value = "Cliente/simplex", method = RequestMethod.POST,params="send")
	public String simplex2(HttpServletRequest request,Model model) {
		  HttpSession misession= (HttpSession) request.getSession();
		  Cliente usu =  (Cliente) misession.getAttribute("usuarioLogueado");
		  model.addAttribute("dispositivos", usu.getDispositivos());
		 Recomendacion reco = usu.getMejorCombinacionDispositivos();
		 model.addAttribute("recomendacion", usu.getMejorCombinacionDispositivos());
		 GestorCliente gestor=new GestorCliente();
		 gestor.mejorarEficienciaHogaresA(usu);
		
		
		  return "simplex";
		  
		  
	}
	
	@RequestMapping(value = "Cliente/simplex", method = RequestMethod.GET)
	public String Simplex2() {
	
		return "simplex";

	}
	
}
