package com.sge_mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sge.repositorios.Repositorio;

@SpringBootApplication
public class SgeMvcApplication {

	public static void main(String[] args) {
		Repositorio.getInstance().abrir();
		SpringApplication.run(SgeMvcApplication.class, args);
		Repositorio.getInstance().cerrar();
	}
}
