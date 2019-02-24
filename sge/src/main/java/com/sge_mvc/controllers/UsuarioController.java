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
import java.util.stream.Stream;

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

import sge.modelo.DispositivoFactoryMethod;
import sge.modelo.GestorCliente;
import sge.modelo.Recomendacion;
import sge.modelo.Repositorio;
import sge.modelo.valueobjects.ClienteVO;
import sge.modelo.valueobjects.DispositivoDisponibleVO;
import sge.modelo.valueobjects.DispositivoVO;
import sge.modelo.valueobjects.UsuarioVO;

@Controller
public class UsuarioController {

	@RequestMapping(value = "/ClienteVO", method = RequestMethod.GET)
	public String mostrarHomeCliente() {
		return "ClienteVO";
	}

	@RequestMapping(value = "/ClienteVO/MiHogar", method = RequestMethod.POST)
	public String miHogar() {
		return "mihogar";
	}
	
	@RequestMapping(value = "/ClienteVO/ConsumoPeriodo", method = RequestMethod.POST)
	public String consumoPeriodo() {
		return "consumoperiodo";
	}
	
	@RequestMapping(value = "/ClienteVO/CargarDispositivos", method = RequestMethod.POST)
	public String cargarDispositivos(Model model) {
		Repositorio repositorio = new Repositorio();
		repositorio.abrir();
		List<DispositivoDisponibleVO> disponibles = repositorio.dispositivosDisponibles().all();
		model.addAttribute("disponibles", disponibles);
		repositorio.cerrar();
		return "cargardispositivos";
	}
	
	@RequestMapping(value = "/ClienteVO/Simplex", method = RequestMethod.POST)
	public String ejecutarSimplex() {
		return "simplex";
	}

	@RequestMapping(value = "/ClienteVO/ABMDispositivos", method = RequestMethod.POST)
	public String abmDispositivos(HttpServletRequest request) {
		  
		  //HttpSession misession= (HttpSession) request.getSession();
		  //String usu = (String) misession.getAttribute("usuarioLogueado");
		  //Repositorio repositorio = new Repositorio(); 
		  //repositorio.abrir();
		  //List<Dispositivo> dispositivos = (List<Dispositivo>)
		  //repositorio.dispositivos().findBy("username", usu); 
		  //ClienteVO usuarioBD = () repositorio.clientes().findBy("username", usu);
		  //ClienteVO usuarioBD = (ClienteVO) repositorio.findBy(Usuario.class, "username", usu);
		  //ModelAndView modelAndView = new ModelAndView("abmdispositivos");
		  //modelAndView.addObject("usuarioBD", usuarioBD);
		  //System.out.println(usu+"       MABEEEEEEEEEEEEEEEEEEEEEl");
		  //System.out.println(usuarioBD.getDispositivos()+"       --------------------------------");
		return "abmdispositivos";
	}
	@RequestMapping(value = "/ClienteVO/ABMReglas", method = RequestMethod.POST)
	public String abmReglas(HttpServletRequest request,Model model) {
		/*
		 * Repositorio repositorio = new Repositorio(); repositorio.abrir();
		 * List<Dispositivo> dispositivos = (List<Dispositivo>)
		 * repositorio.dispositivos().findBy("username", user); ModelAndView
		 * modelAndView = new ModelAndView("abmDisp");
		 * modelAndView.addObject("dispositivos", dispositivos);
		 */
		 HttpSession misession= (HttpSession) request.getSession();
		 ClienteVO usu =  (ClienteVO) misession.getAttribute("usuarioLogueado");
		 model.addAttribute("dispositivosInt",usu.misInteligentes().toArray());
		 Stream<DispositivoVO> dispositivosInteligentes = usu.misInteligentes();
		 //dispositivosInteligentes.forEach(p -> System.out.println(p));
		 return "abmreglas";
	}

	/* NO SE SI VUELA @RequestMapping(method = RequestMethod.POST)
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		Repositorio repo = new Repositorio();
		repo.abrir();
		String Mensaje = "";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/aaaa");
		String desdeStr = request.getParameter("datedesde");
		String hastaStr = request.getParameter("datehasta");
		Date desdeDate = sdf.parse(desdeStr);
		Date hastaDate = sdf.parse(hastaStr);
		LocalDateTime desde = convertToLocalDateTimeViaInstant(desdeDate);
		LocalDateTime hasta = convertToLocalDateTimeViaInstant(hastaDate);
		return null;
	} 

	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	*/
	@RequestMapping(value = "ClienteVO/simplex", method = RequestMethod.POST)
	public String simplex(HttpServletRequest request,Model model) {
		  HttpSession misession= (HttpSession) request.getSession();
		  ClienteVO usu =  (ClienteVO) misession.getAttribute("usuarioLogueado");
		  model.addAttribute("dispositivos", usu.getDispositivos());
		 Recomendacion reco = usu.getMejorCombinacionDispositivos();
		 model.addAttribute("recomendacion", usu.getMejorCombinacionDispositivos());
		 
		 
		
		  return "simplex";
		  
		  
	}
	
	@RequestMapping(value = "ClienteVO/simplex", method = RequestMethod.POST,params="send")
	public String simplex2(HttpServletRequest request,Model model) {
		  HttpSession misession= (HttpSession) request.getSession();
		  ClienteVO usu =  (ClienteVO) misession.getAttribute("usuarioLogueado");
		  model.addAttribute("dispositivos", usu.getDispositivos());
		 Recomendacion reco = usu.getMejorCombinacionDispositivos();
		 model.addAttribute("recomendacion", usu.getMejorCombinacionDispositivos());
		 GestorCliente gestor=new GestorCliente();
		 gestor.mejorarEficienciaHogaresA(usu);
		
		
		  return "simplex";
		  
		  
	}
	
	@RequestMapping(value = "ClienteVO/simplex", method = RequestMethod.GET)
	public String Simplex2() {
	
		return "simplex";

	}
	
}
