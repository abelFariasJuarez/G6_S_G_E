package com.sge_mvc.controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sge.modelo.dispositivo.Dispositivo;
import sge.modelo.dispositivo.DispositivoDisponible;
import sge.modelo.dispositivo.DispositivoFactoryMethod;
import sge.modelo.usuarios.Cliente;
import sge.modelo.usuarios.UsuarioSGE;
import sge.repositorios.Clientes;
import sge.repositorios.Dispositivos;
import sge.repositorios.Repositorio;


@Controller
@RequestMapping("/Cliente/ABMDispositivos")
public class AbmDispController {
	
	String usuario;

	@RequestMapping(value="abmdispositivos",method=RequestMethod.POST)
	public ModelAndView abmDisp(@RequestParam("user") String user) {
		Repositorio repositorio = Repositorio.getInstance();
		 List<Dispositivo> dispositivos = (List<Dispositivo>) repositorio.dispositivos().findBy("username", user)  ;
		  ModelAndView modelAndView = new ModelAndView("abm");
		  modelAndView.addObject("dispositivos", dispositivos);
		  return modelAndView;
	}
	
	@RequestMapping(value="abmdispositivos",method=RequestMethod.GET)
	public ModelAndView abmdis(@RequestParam("user") String user) {
		Repositorio repositorio = Repositorio.getInstance();
		usuario = user;
		List<Dispositivo> dispositivos = (List<Dispositivo>) repositorio.dispositivos().findBy("username", user)  ;
		  ModelAndView modelAndView = new ModelAndView("abm");
		  modelAndView.addObject("dispositivos", dispositivos);
		  return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String abm() {
		return "abmdispositivos";
	}
	
	@RequestMapping(value="nuevodisp", method = RequestMethod.POST)
	public String nuevoDispositivo(Model model) throws ServletException, IOException {
			Repositorio repo = Repositorio.getInstance();
			List<DispositivoDisponible> disponibles = repo.dispositivosDisponibles().all();
			model.addAttribute("disponibles", disponibles);
			//ModelAndView modelAndView = new ModelAndView("nuevodisp");
			//modelAndView.addObject("dispdisponibles", dispdisponibles);
			return "nuevodisp";

	}
	@RequestMapping(value="modifDisp", method = RequestMethod.POST)
	public ModelAndView modificarDispositivo() throws ServletException, IOException {
			Repositorio repo = Repositorio.getInstance();
			ModelAndView modelAndView = new ModelAndView("modifDisp");
			//modelAndView.addObject("dispdisponibles", dispdisponibles);
			return modelAndView;
		
	}
	
	@RequestMapping(method=RequestMethod.GET, params="EliminDisp")
	public ModelAndView abmDispo(@RequestParam("dispositivo") String dispositivo) {
			Repositorio repo = Repositorio.getInstance();
			List<Dispositivo> dispositivos = (List<Dispositivo>) repo.dispositivos().findBy("username", usuario);
			Dispositivo dispo = repo.dispositivos().findBy("nombre",dispositivo);
			repo.borrar(dispo);
			ModelAndView modelAndView = new ModelAndView("abmDisp");
		return modelAndView;
	}

}
