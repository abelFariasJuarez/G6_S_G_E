package com.sge_mvc.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import sge.modelo.usuarios.UsuarioSGE;
import sge.repositorios.Repositorio;

@Controller
@RequestMapping("demo/login")
public class LoginController {
	public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        String Mensaje = "";
        String usuario=request.getParameter("usuario");
        String password=request.getParameter("password");
        
        if(usuario.equalsIgnoreCase("myutilsjava") &&  password.equalsIgnoreCase("1234567")){
            Mensaje = "Bienvenido Login Correcto!";
        }else{
            Mensaje = "Error Login Incorrecto!";
        }
 
        ModelAndView modelAndView = new ModelAndView("inicio");
        modelAndView.addObject("message", Mensaje);
 
        return modelAndView;
    }
	    

	/*@RequestMapping(value = "demo/login" , method = RequestMethod.POST)
	public ModelAndView home(      HttpServletRequest request, 
	        HttpServletResponse response) {
		//try {
		 String user=request.getParameter("usuario");
		 String password=request.getParameter("password");
			Repositorio repo = new Repositorio();
			repo.abrir();
			UsuarioSGE usu = repo.clientes().findBy("username", user);
			
			if ( usu!=null) {
			ModelAndView model = new ModelAndView("home");
			if (usu.getPassword().equalsIgnoreCase(password)) {
				model.addObject("message", "Logueado correctamente");
			} else {
				model.addObject("message", "Usuario o contraseña incorrectos");
			}

			model.addObject("user", user);
			model.addObject("password", password);
			repo.cerrar();
			return model;
	}
			return null;
	}*/
			
}

