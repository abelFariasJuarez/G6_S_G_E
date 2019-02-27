package com.sge_mvc.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sge.modelo.usuarios.Cliente;
import sge.modelo.usuarios.UsuarioSGE;
import sge.repositorios.Repositorio;

@Controller
/* @RequestMapping("/Cliente/ConsumoPeriodo") */
public class ConsumoPeriodoController {

	@RequestMapping(value = "/Cliente/ConsumoPeriodo/Consumo", method = RequestMethod.POST)
	public String calcularConsumo(@RequestParam String datedesde, @RequestParam String datehasta, Model model) throws ParseException {
		
		if(!datedesde.equals("") && !datehasta.equals("")) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date desdeDate = sdf.parse(datedesde);

		if (datedesde.equals("")) {
			datehasta = "9999-12-28";
		}

		Date hastaDate = sdf.parse(datehasta);
		LocalDateTime desde = convertToLocalDateTimeViaInstant(desdeDate);
		LocalDateTime hasta = convertToLocalDateTimeViaInstant(hastaDate);
		
		model.addAttribute("desde", desde);
		model.addAttribute("hasta", hasta);
		}

		return "consumoperiodo";

	}

	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

}