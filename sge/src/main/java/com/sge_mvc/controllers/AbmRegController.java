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
import sge.modelo.regla.Regla;
import sge.modelo.usuarios.Cliente;
import sge.modelo.usuarios.UsuarioSGE;
import sge.repositorios.Clientes;
import sge.repositorios.Dispositivos;
import sge.repositorios.Repositorio;


@Controller
@RequestMapping("/demo/login/Usuario/abmReg")
public class AbmRegController {

	String usuario;
	
	@RequestMapping(value="abmDisp",method=RequestMethod.POST)
	public ModelAndView abmDisp(@RequestParam("user") String user) {
		Repositorio repositorio = new Repositorio();
		repositorio.abrir(); 
		 List<Dispositivo> dispositivos = (List<Dispositivo>) repositorio.dispositivos().findBy("username", user)  ;
		  ModelAndView modelAndView = new ModelAndView("abm");
		  modelAndView.addObject("dispositivos", dispositivos);
		  return modelAndView;
	}
	
	@RequestMapping(value="abmDisp",method=RequestMethod.GET)
	public ModelAndView abmdis(@RequestParam("user") String user) {
		Repositorio repositorio = new Repositorio();
		repositorio.abrir();
		usuario = user;
		List<Dispositivo> dispositivos = (List<Dispositivo>) repositorio.dispositivos().findBy("username", user)  ;
		  ModelAndView modelAndView = new ModelAndView("abm");
		  modelAndView.addObject("dispositivos", dispositivos);
		  return modelAndView;
	}
	
	
	@RequestMapping(method=RequestMethod.GET,params="Select")
	public String cancel() {
		return "abmDisp";
	}
	@RequestMapping(method=RequestMethod.GET)
	public String abm() {
		return "abmDisp";
	}
	
	@RequestMapping(value="nuevaReg", method = RequestMethod.POST)
	public ModelAndView nuevoDispositivo() throws ServletException, IOException {
			ModelAndView modelAndView = new ModelAndView("nuevaReg");
			//modelAndView.addObject("dispdisponibles", dispdisponibles);
			return modelAndView;

	}
	@RequestMapping(value="modifReg", method = RequestMethod.POST)
	public ModelAndView modificarDispositivo() throws ServletException, IOException {
			ModelAndView modelAndView = new ModelAndView("modifReg");
			//modelAndView.addObject("dispdisponibles", dispdisponibles);
			return modelAndView;
		
	}
	
	@RequestMapping(value="SelectDisp",method=RequestMethod.POST)
	public ModelAndView abmDispo(@RequestParam("dispositivo") String dispositivo) {
		Repositorio repo = new Repositorio();
		repo.abrir();
		//checkear como busca las reglas, no me cierra algo
		List<Dispositivo> dispositivos = (List<Dispositivo>) repo.dispositivos().findBy("username", usuario);
		Dispositivo dispo = repo.dispositivos().findBy("nombre",dispositivo);
		List<Regla> reglasUser = (List<Regla>) repo.reglas().findBy("username", dispo.getNombre());
		ModelAndView modelAndView = new ModelAndView("abmReg");
		  modelAndView.addObject("reglas",reglasUser);
		  return modelAndView;
	}
	
	
}
