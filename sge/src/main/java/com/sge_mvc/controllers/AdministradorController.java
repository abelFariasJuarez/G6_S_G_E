package com.sge_mvc.controllers;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sge.modelo.Repositorio;
import sge.modelo.valueobjects.ClienteVO;
import sge.modelo.valueobjects.DispositivoDisponibleVO;

@Controller
public class AdministradorController {

	@RequestMapping( value = "/AdministradorVO",method = RequestMethod.POST)
	public String x() {
		return "AdministradorVO";
	}
	
	@RequestMapping( value = "/AdministradorVO",method = RequestMethod.GET)
	public String T() {
		return "AdministradorVO";
	}

	@RequestMapping(value = "AdministradorVO/cargadispo", method = RequestMethod.POST, params = "snd")
	public String s(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
		List<DispositivoDisponibleVO> disponibles = new ArrayList<DispositivoDisponibleVO>();
		Repositorio repo = new Repositorio();
		repo.abrir();
		
	
		modelMap.addAttribute("file", file);
		
		String content = new String(file.getOriginalFilename());
		repo.dispositivosDisponibles().cargarDispositivos("pruebaCargaDisponible.json");// aca iria el content si le mando otro archivo
		repo.dispositivosDisponibles().guardarDispositivosDisponibles();
		
		disponibles = repo.dispositivosDisponibles().all();
		modelMap.addAttribute("dispositivosDisponibles", disponibles);
		repo.cerrar();
		return "cargadispo";
	}


	/*@RequestMapping(value = "cargadispo", method = RequestMethod.POST, params = "oculto2")
	public String submit() {
		return "cargadispo";
	}
	*/

	
	@RequestMapping(value = "AdministradorVO/cargadispo", method = RequestMethod.POST)
	public String cargadispo(Model model) {
		List<DispositivoDisponibleVO> disponibles = new ArrayList<DispositivoDisponibleVO>();
		Repositorio repo = new Repositorio();
		repo.abrir();
		disponibles = repo.dispositivosDisponibles().all();
		model.addAttribute("dispositivosDisponibles", disponibles);
	
		repo.cerrar();
		return "cargadispo";

	}
	
	@RequestMapping(value = "AdministradorVO/consumo", method = RequestMethod.POST)
	public ModelAndView consumo() {
		List<ClienteVO> clienteVOs = new ArrayList<ClienteVO>();
		Repositorio repo = new Repositorio();
		repo.abrir();
		clienteVOs = repo.clientes().all();
		ModelAndView modelAndView = new ModelAndView("consumo");
		modelAndView.addObject("clienteVOs", clienteVOs);
		repo.cerrar();
		return modelAndView;

	}
	



}
