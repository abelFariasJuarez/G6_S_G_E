package com.sge_mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sge.repositorios.Repositorio;

@SpringBootApplication
public class SgeMvcApplication {

	public static void main(String[] args) {
		Repositorio repo = new Repositorio();
		repo.abrir();
		repo.cargaDeDatosIniciales();
		repo.cerrar();
		SpringApplication.run(SgeMvcApplication.class, args);
	}
}
