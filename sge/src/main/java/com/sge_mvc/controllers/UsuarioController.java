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
import org.springframework.web.servlet.ModelAndView;

import sge.modelo.dispositivo.DispositivoDisponible;
import sge.modelo.dispositivo.DispositivoFactoryMethod;
import sge.modelo.usuarios.Cliente;
import sge.modelo.usuarios.UsuarioSGE;
import sge.repositorios.Clientes;
import sge.repositorios.Repositorio;


@Controller
@RequestMapping("/demo/login/Usuario")
public class UsuarioController {

	@RequestMapping(value="adddis",method=RequestMethod.POST)
	public ModelAndView wse() {
		Repositorio repositorio = new Repositorio();
		repositorio.abrir();
		DispositivoFactoryMethod.cargaBasica();
		 List<DispositivoDisponible> disponibles = repositorio.dispositivosDisponibles().all();
		  ModelAndView modelAndView = new ModelAndView("adddis");
		  modelAndView.addObject("disponibles", disponibles);
		  return modelAndView;
	}
	@RequestMapping(value="adddis",method=RequestMethod.GET)
	public ModelAndView ASD() {
		Repositorio repositorio = new Repositorio();
		repositorio.abrir();
		DispositivoFactoryMethod.cargaBasica();
		 List<DispositivoDisponible> disponibles = repositorio.dispositivosDisponibles().all();
		  ModelAndView modelAndView = new ModelAndView("adddis");
		  modelAndView.addObject("disponibles", disponibles);
		  return modelAndView;
	}


	@RequestMapping(method=RequestMethod.GET)
	public String ow() {
		return "Usuario";
	}

}