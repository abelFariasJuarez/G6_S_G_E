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

import sge.modelo.dispositivo.DispositivoDisponible;
import sge.modelo.usuarios.Cliente;
import sge.repositorios.Repositorio;

@Controller
@RequestMapping("/demo/login/Administrador")
public class AdministradorController {

	@RequestMapping(method = RequestMethod.POST)
	public String x() {
		return "Administrador";
	}

	@RequestMapping(value = "cargadispo", method = RequestMethod.POST, params = "snd")
	public String s(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
		modelMap.addAttribute("file", file);
		Repositorio repo = new Repositorio();
		repo.abrir();
		String content = new String(file.getOriginalFilename());
		repo.dispositivosDisponibles().cargarDispositivos(content);
		repo.dispositivosDisponibles().guardarDispositivosDisponibles();
		return "cargadispo";
	}


	/*@RequestMapping(value = "cargadispo", method = RequestMethod.POST, params = "oculto2")
	public String submit() {
		return "cargadispo";
	}
	*/

	
	@RequestMapping(value = "cargadispo", method = RequestMethod.POST)
	public ModelAndView cargadispo() {
		List<DispositivoDisponible> disponibles = new ArrayList<DispositivoDisponible>();
		Repositorio repo = new Repositorio();
		repo.abrir();
		disponibles = repo.dispositivosDisponibles().all();
		ModelAndView modelAndView = new ModelAndView("cargadispo");
		modelAndView.addObject("dispositivosDisponibles", disponibles);
		repo.cerrar();
		return modelAndView;

	}
	
	@RequestMapping(value = "consumo", method = RequestMethod.POST)
	public ModelAndView consumo() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		Repositorio repo = new Repositorio();
		repo.abrir();
		clientes = repo.clientes().all();
		ModelAndView modelAndView = new ModelAndView("consumo");
		modelAndView.addObject("clientes", clientes);
		repo.cerrar();
		return modelAndView;

	}

	@RequestMapping(method = RequestMethod.GET)
	public String wse() {
		return "Administrador";
	}
}
