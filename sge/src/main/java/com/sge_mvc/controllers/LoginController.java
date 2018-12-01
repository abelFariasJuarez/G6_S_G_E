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

import sge.modelo.usuarios.Cliente;
import sge.modelo.usuarios.UsuarioSGE;
import sge.repositorios.Clientes;
import sge.repositorios.Repositorio;

@Controller
@RequestMapping("/demo/login")
public class LoginController {
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		Repositorio repo = new Repositorio();
		repo.abrir();
        String Mensaje = "";
        String usuario=request.getParameter("username");
        String password=request.getParameter("password");
        Clientes usuarios = repo.clientes();
        UsuarioSGE usu = (UsuarioSGE) repo.findBy(UsuarioSGE.class,"username", usuario);
        
        if(usu == null || !usu.getPassword().equalsIgnoreCase(password)) {
        	
            ModelAndView modelAndView = new ModelAndView("login");
        	repo.cerrar();
        	modelAndView.addObject("message", "Usuario o Contraseña incorrecta");
            return modelAndView;
              
        }
        else {
           if(usu.getUsertype().equals("C")) {
        	  
        	  ModelAndView modelAndView = new ModelAndView("Usuario");

        	  modelAndView.addObject("usuarios", usuarios);
              modelAndView.addObject("message", usu.getUsertype());
          
              modelAndView.addObject("user", usuario);
              modelAndView.addObject("password", password);
              repo.cerrar();

             return modelAndView;
           }
           else 
           {
        	 ModelAndView modelAndView = new ModelAndView("Administrador");
             modelAndView.addObject("message", usu.getUsertype());
         
             modelAndView.addObject("user", usuario);
             modelAndView.addObject("password", password);
             repo.cerrar();

            return modelAndView;
           }
        }
     }
	
	@RequestMapping(value = "/Administrador", method = RequestMethod.GET)
	public String Admin(Model model) {
		Repositorio repo = new Repositorio();
		repo.abrir();
		model.addAttribute("clientes", repo.clientes().getClientes());
		repo.cerrar();
		return "Administrador";
	}
	
	
	@RequestMapping(value = "/Usuario", method = RequestMethod.GET)
	public String user(Model model) {
		
		List<Cliente> clientes = new LinkedList<Cliente>();
        Cliente cli = new Cliente("martin",null,null,null,null,null,null,null,null);
        clientes.add(cli);
        
        Repositorio repo = new Repositorio();
		repo.abrir();
		repo.clientes().cargarClientes();
		model.addAttribute("clientes", repo.clientes().getClientes());
		repo.cerrar();
		return "Usuario";
	}
}

/*
 * @RequestMapping(value = "demo/login" , method = RequestMethod.POST) public
 * ModelAndView home( HttpServletRequest request, HttpServletResponse response)
 * { //try { String user=request.getParameter("usuario"); String
 * password=request.getParameter("password"); Repositorio repo = new
 * Repositorio(); repo.abrir(); UsuarioSGE usu =
 * repo.clientes().findBy("username", user);
 * 
 * if ( usu!=null) { ModelAndView model = new ModelAndView("home"); if
 * (usu.getPassword().equalsIgnoreCase(password)) { model.addObject("message",
 * "Logueado correctamente"); } else { model.addObject("message",
 * "Usuario o contraseña incorrectos"); }
 * 
 * model.addObject("user", user); model.addObject("password", password);
 * repo.cerrar(); return model; } return null; }
 */
