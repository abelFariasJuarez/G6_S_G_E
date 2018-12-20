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
import sge.repositorios.Repositorio;

@Controller
public class UsuarioController {

	@RequestMapping(value = "/Cliente", method = RequestMethod.GET)
	public String mostrarHomeCliente() {
		return "Cliente";
	}

	@RequestMapping(value = "/Cliente/MiHogar", method = RequestMethod.POST)
	public String miHogar() {
		return "mihogar";
	}

	@RequestMapping(value = "/Cliente/ConsumoPeriodo", method = RequestMethod.POST)
	public String consumoPeriodo() {
		return "consumoperiodo";
	}

	@RequestMapping(value = "/Cliente/CargarDispositivos", method = RequestMethod.POST)
	public String cargarDispositivos(Model model) {
		Repositorio repositorio = new Repositorio();
		repositorio.abrir();
		List<DispositivoDisponible> disponibles = repositorio.dispositivosDisponibles().all();
		model.addAttribute("disponibles", disponibles);
		repositorio.cerrar();
		return "cargardispositivos";
	}

	@RequestMapping(value = "/Cliente/Simplex", method = RequestMethod.POST)
	public String ejecutarSimplex() {
		return "simplex";
	}

	@RequestMapping(value = "/Cliente/ABMDispositivos", method = RequestMethod.POST)
	public String abmDispositivos() {
		/*
		 * Repositorio repositorio = new Repositorio(); repositorio.abrir();
		 * List<Dispositivo> dispositivos = (List<Dispositivo>)
		 * repositorio.dispositivos().findBy("username", user); ModelAndView
		 * modelAndView = new ModelAndView("abmDisp");
		 * modelAndView.addObject("dispositivos", dispositivos);
		 */
		return "abmreglasydispositivos";
	}

	@RequestMapping(method = RequestMethod.POST)
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
}
