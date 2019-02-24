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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sge.modelo.DispositivoFactoryMethod;
import sge.modelo.Repositorio;
import sge.modelo.valueobjects.ClienteVO;
import sge.modelo.valueobjects.DispositivoDisponibleVO;
import sge.modelo.valueobjects.DispositivoVO;
import sge.modelo.valueobjects.InteligenteVO;
import sge.modelo.valueobjects.ReglaVO;
import sge.modelo.valueobjects.UsuarioVO;


@Controller
@RequestMapping("/ClienteVO/ABMReglas")
public class AbmRegController {

	String usuario;
	
	@RequestMapping(value="abmDisp",method=RequestMethod.POST)
	public ModelAndView abmDisp(@RequestParam("user") String user) {
		Repositorio repositorio = new Repositorio();
		repositorio.abrir(); 
		 List<DispositivoVO> dispositivos = (List<DispositivoVO>) repositorio.dispositivos().findBy("username", user)  ;
		  ModelAndView modelAndView = new ModelAndView("abm");
		  modelAndView.addObject("dispositivos", dispositivos);
		  return modelAndView;
	}
	
	@RequestMapping(value="abmDisp",method=RequestMethod.GET)
	public ModelAndView abmdis(@RequestParam("user") String user) {
		Repositorio repositorio = new Repositorio();
		repositorio.abrir();
		usuario = user;
		List<DispositivoVO> dispositivos = (List<DispositivoVO>) repositorio.dispositivos().findBy("username", user)  ;
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
	
	@RequestMapping(value="/ClienteVO/ABMReglas",method=RequestMethod.POST,params="SelectDisp")
	public Model SelectDispo(@RequestParam("value") String dispSeleccionado,HttpServletRequest request, Model model) {
		Repositorio repo = new Repositorio();
		repo.abrir();
		//checkear como busca las reglas, no me cierra algo
		//List<Dispositivo> dispositivos = (List<Dispositivo>) repo.dispositivos().findBy("username", usuario);
		DispositivoVO dispo = repo.dispositivos().findBy("nombre",dispSeleccionado);
		List<ReglaVO> reglasUser = ((InteligenteVO) dispo).getReglas();
		  model.addAttribute("reglas",reglasUser);
		  //modelAndView.addObject("dispositivoElegido", dispSeleccionado);
		  return model;
	}
	
/*	@RequestMapping(value="/refresh")
	@ResponseBody 
	    public List<Regla> startCheckingStatus(@RequestParam("dispSeleccionado") Dispositivo dispSeleccionado){
		
		List<Regla> reglas = ((Inteligente) dispSeleccionado).getReglas();
		return reglas; 
	}*/
	
	
}
