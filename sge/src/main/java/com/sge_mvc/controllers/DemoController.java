package com.sge_mvc.controllers;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sge.modelo.Repositorio;
import sge.modelo.valueobjects.TransformadorVO;
import sge.modelo.valueobjects.UbicacionVO;
import sge.modelo.valueobjects.UsuarioVO;
import sge.modelo.valueobjects.ZonaVO;

@Controller
@RequestMapping("demo")
public class DemoController {

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	/*@RequestMapping(value = "/mapa", method = { RequestMethod.GET, RequestMethod.POST })
	public String mapa(Model model) {
		/*UbicacionVO ubi = new UbicacionVO(-34.597316 ,-58.420175);
		ZonaVO zona = new ZonaVO();
		zona.setCentro(ubi);
		zona.setRadio((float) 500);
		
		UbicacionVO ubi2 = new UbicacionVO(-34.580000 ,-58.4000000);
		ZonaVO zona2 = new ZonaVO();
		zona2.setCentro(ubi2);
		zona2.setRadio((float) 700);
		
	List<ZonaVO> zonas = new LinkedList<ZonaVO>();
	zonas.add(zona);
	zonas.add(zona2);
	model.addAttribute("zonas", zonas);
		//////////////////////////////////////////
		UbicacionVO ubi3 = new UbicacionVO(-34.597311 ,-58.420178);
	TransformadorVO trans= new TransformadorVO();
	UbicacionVO ubi4 = new UbicacionVO(-34.580000,-58.4000000);
	TransformadorVO trans2 = new TransformadorVO();
		trans.setUbicacion(ubi3);
		trans2.setUbicacion(ubi4);
		List<TransformadorVO> transs = new LinkedList<TransformadorVO>();
		transs.add(trans);
		transs.add(trans2);
		//model.addAttribute("transs", transs);
		/*trans.consumoEnPeriodo( LocalDateTime.now(),LocalDateTime.now());
		
		Repositorio repo = new Repositorio();
		repo.abrir();
		List<ZonaVO> zonas=repo.zonas().all();
		List<TransformadorVO> transforms=repo.transformadores().all();
		
		model.addAttribute("transs", transforms);
		model.addAttribute("zonas", zonas);
		
		
		return "mapa";
	}*/

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(String usuario, String contrasenia) {
		//try {

			Repositorio repo = new Repositorio();
			repo.abrir();
			UsuarioVO usu = repo.clientes().findBy("username", usuario);

			ModelAndView model = new ModelAndView("home");
			if (usu.getPassword().equalsIgnoreCase(contrasenia)) {
				model.addObject("message", "Logueado correctamente");
			} else {
				model.addObject("message", "Usuario o contraseña incorrectos");
			}

			model.addObject("user", usuario);
			model.addObject("password", contrasenia);
			repo.cerrar();
			return model;
	/*	} catch (Exception e) {
			// TODO: handle exception
			ModelAndView modelError = new ModelAndView("login");
			modelError.addObject("message", "Error en el logueo");
			return modelError;
		}*/
	}
}