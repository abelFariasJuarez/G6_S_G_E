package com.sge_mvc.controllers;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sge.modelo.dispositivo.DispositivoDisponible;
import sge.modelo.dispositivo.RestriccionHorasFamilia;
import sge.modelo.usuarios.Cliente;
import sge.repositorios.RowReport;
import sge.repositorios.Repositorio;

@Controller
public class AdministradorController {

	@RequestMapping( value = "/Administrador",method = RequestMethod.POST)
	public String x() {
		return "Administrador";
	}
	
	@RequestMapping( value = "/Administrador",method = RequestMethod.GET)
	public String T() {
		return "Administrador";
	}

	@RequestMapping(value = "Administrador/cargadispo", method = RequestMethod.POST, params = "snd")
	public String s(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
		List<DispositivoDisponible> disponibles = new ArrayList<DispositivoDisponible>();
		Repositorio repo = Repositorio.getInstance();

		modelMap.addAttribute("file", file);
		
		String content = new String(file.getOriginalFilename());
		repo.dispositivosDisponibles().cargarDispositivos(content);// aca iria el content si le mando otro archivo
		repo.dispositivosDisponibles().guardarDispositivosDisponibles();
		
		disponibles = repo.dispositivosDisponibles().all();
		modelMap.addAttribute("dispositivosDisponibles", disponibles);
		return "cargadispo";
	}
	
	@RequestMapping(value = "Administrador/cargadispo", method = RequestMethod.POST)
	public String cargadispo(Model model) {
		List<DispositivoDisponible> disponibles = new ArrayList<DispositivoDisponible>();
		Repositorio repo = Repositorio.getInstance();

		disponibles = repo.dispositivosDisponibles().all();
		model.addAttribute("dispositivosDisponibles", disponibles);

		return "cargadispo";
	}

	@RequestMapping(value = "Administrador/restriccionesHorarias", method = RequestMethod.POST)
	public String restriccionesHorarias(ModelMap modelMap) {
		
		List<RestriccionHorasFamilia> restriccionesList = new ArrayList<RestriccionHorasFamilia>();
		Repositorio repo = Repositorio.getInstance();
		restriccionesList = repo.restriccionesHorasFamilia().all();		
		
		modelMap.addAttribute("restriccionesHorasList", restriccionesList);
		return "restriccionesHorarias";
	}
	
	@RequestMapping(value = "Administrador/consumo", method = RequestMethod.POST)
	public ModelAndView consumo() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		Repositorio repo = Repositorio.getInstance();

		clientes = repo.clientes().all();
		ModelAndView modelAndView = new ModelAndView("consumo");
		modelAndView.addObject("clientes", clientes);

		return modelAndView;
	}

	@RequestMapping(value = "Administrador/reportes", method = RequestMethod.GET)
	public String mostrarReporte(@RequestParam("codReporte") String codReporte, 
			@RequestParam("datedesde") String dateDesdeString,
			@RequestParam("datehasta") String dateHastaString,
			ModelMap modelMap) {
		
		List<String> toppings = new ArrayList<String>();
		String titulo = "";
		
		
		LocalDateTime desde = LocalDateTime.parse(dateDesdeString);
		LocalDateTime hasta = LocalDateTime.parse(dateDesdeString);;		
		Repositorio repo = Repositorio.getInstance();
	
		List<RowReport> rows = null;
		if(codReporte.compareTo("hogares") == 0)
		{
			titulo = "Consumo por hogar";
			toppings.add("Oid");
			toppings.add("User Name");
			toppings.add("Consumo");
			rows = repo.consumo_hogar_periodo(desde, hasta);
		}

		if(codReporte.compareTo("dispositivos") == 0)
		{
			titulo = "Consumo promedio por tipo de dispositivo";
			toppings.add("Device Type");
			toppings.add("Promedio");
			rows = repo.consumo_promedio_tipo_dispositivo_periodo(desde, hasta);
		}
		
		if(codReporte.compareTo("transdormadores") == 0)
		{
			titulo = "Consumo por transformador";
			toppings.add("Oid");
			toppings.add("Consumo");
			rows = repo.consumo_transformador_periodo(desde, hasta);
		}		
			
		modelMap.addAttribute("codReporte", codReporte);
		modelMap.addAttribute("tituloReporte", titulo);
		modelMap.addAttribute("columnasReporte", toppings);
		modelMap.addAttribute("rows", rows);
			
		return "reportes";
	}

}
