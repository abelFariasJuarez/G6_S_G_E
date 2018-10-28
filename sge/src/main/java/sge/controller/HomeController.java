/***************** 
 PRUEBAS
 **********************/

package sge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sge.modelo.usuarios.Administrador;

@Controller
public class HomeController {
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
