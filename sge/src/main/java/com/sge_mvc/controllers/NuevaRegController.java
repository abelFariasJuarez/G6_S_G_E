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

import sge.modelo.DispositivoFactoryMethod;
import sge.modelo.Repositorio;
import sge.modelo.valueobjects.ClienteVO;
import sge.modelo.valueobjects.DispositivoDisponibleVO;
import sge.modelo.valueobjects.DispositivoVO;
import sge.modelo.valueobjects.UsuarioVO;


@Controller
@RequestMapping("/demo/login/Usuario/abmDisp/nuevaReg")
public class NuevaRegController {

	@RequestMapping(method=RequestMethod.GET)
	public String ow() {
		return "nuevaReg";
	}
	
	
}
