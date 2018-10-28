/***************** 
 PRUEBAS
 **********************/

package sge.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sge.modelo.usuarios.Administrador;

@Controller
public class HomeController  extends HttpServlet{
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String goHome() {
	 return "home";	
	}
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String mostrarDetalle(Model model) {
		Administrador adm = new Administrador("carlos","picho","olazabal",null,"cpich","123");
		model.addAttribute("Admin", adm);
		return "detail";
	}
	

	
	
	

}
