package sge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sge.modelo.dispositivo.*;
import sge.modelo.driver.ActuadorAhorro;
import sge.modelo.driver.ActuadorApagar;
import sge.modelo.driver.ActuadorPrender;
import sge.modelo.driver.DriverBasico;
import sge.modelo.posicionamiento.Transformador;
import sge.modelo.posicionamiento.ZonaGeografica;
import sge.modelo.regla.AccionPrender;
import sge.modelo.regla.Condicion;
import sge.modelo.regla.RegistroReglas;
import sge.modelo.regla.RegistroSensores;
import sge.modelo.regla.Regla;
import sge.modelo.regla.Sensor;
import sge.modelo.regla.comparador.*;
import sge.modelo.usuarios.Cliente;
import sge.modelo.usuarios.UsuarioSGE;
import sge.repositorios.Clientes;
import sge.repositorios.Repositorio;
import sge.repositorios.Transformadores;
import sge.repositorios.Zonas;
import utils.ImportadorDeJsonDisponible;

public class App {

	public static void main(String[] args) throws InterruptedException, IOException {

		Repositorio repo = new Repositorio();
		repo.abrir();
		//repo.dispositivosDisponibles().cargarDispositivos("ewwe.json");
		
		
		//repo.dispositivosDisponibles().guardarDispositivosDisponibles();
		
    repo.transformadores().cargarTransformadores();
		repo.transformadores().guardarTransforamdores();
		repo.zonas().cargarZonas();
		repo.zonas().guardarZonas();
}
}
