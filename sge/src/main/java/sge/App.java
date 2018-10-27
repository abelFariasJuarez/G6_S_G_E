package sge;

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
import sge.repositorios.Clientes;
import sge.repositorios.Repositorio;
import sge.repositorios.Transformadores;
import sge.repositorios.Zonas;

public class App {

	public static void main(String[] args) throws InterruptedException {

		Regla unRegla = new Regla("regla 1");

		// Regla unRegla = new Regla("regla 1");
		// DispositivoInteligente unAire = new
		// DispositivoInteligente("AireAcondicionado", 2.3,"perez", false,false);
		DispositivoInteligente unAire = new DispositivoInteligente("AireAcondicionado", 2.3, "cazana", false, false,
				new DriverBasico());
		// el constructor ya me da un dispo en estado apagado
		// assertEquals(true,unAire.estoyOFF());
		// Regla unRegla = new Regla("regla 1");

		Sensor temperatura = new Sensor(30.0, 10000, "temperatura");
		Sensor humedad = new Sensor(100.0, 5000, "humedad");
		Condicion condTemp = new Condicion(temperatura, new Mayor(), 32.0);
		Condicion condHume = new Condicion(humedad, new Mayor(), 90.0);

		unRegla.agregarCondicion(condTemp);
		unRegla.agregarCondicion(condHume);

		List<Regla> reglas = new ArrayList<Regla>();
		reglas.add(unRegla);
		RegistroReglas.getInstance().registrarReglas(unAire, reglas);
		RegistroSensores.getInstance().registrarSensor(humedad, unAire);
		RegistroSensores.getInstance().registrarSensor(temperatura, unAire);
		AccionPrender prenderAire = new AccionPrender();
		unRegla.agregarAccion(prenderAire);

		humedad.activar();
		temperatura.setMedicion(40);
		temperatura.activar();
		// temperatura.actualizarMedicion();

		// assertEquals(true,unAire.estoyON());

		Double radio = 20.3;
		Double Xcentro = 40.3;
		Double yCenter = 70.3;
		Double xPoint = 60.8;
		Double yPoint = 70.4;
		if (Math.sqrt(Math.pow(Xcentro - xPoint, 2) + Math.pow((yCenter - yPoint), 2)) <= radio) {
			System.out.println("El punto est� contenido en el c�rculo.");
		} else {
			System.out.println("El punto no est� contenido en el c�rculo.");
		}

		System.out.println(Math.abs(Xcentro - xPoint) + Math.abs(yCenter - yPoint));

		// se comprueba que importa bien zonas
		Zonas repoZonas = new Zonas();
		repoZonas.cargarZonas();

		for (ZonaGeografica zona : repoZonas.getZonas()) {
			System.out.println(zona.getCentro().getLatitud());
		}

		// se comprueba que importa bien transformadores
		Transformadores repoTransformadores = new Repositorio().transformadores();
		repoTransformadores.cargarTransformadores();

		for (Transformador trafo : repoTransformadores.getTransformadores()) {
			System.out.println(trafo.getUbicacion().getLatitud());
		}

		// se comprueba union zona transformador.
		for (ZonaGeografica zona1 : repoZonas.getZonas()) {
			for (Transformador trans1 : repoTransformadores.getTransformadores()) {
				if (zona1.getId().equals(trans1.getIdZona())) {
					zona1.Add(trans1);

				}
			}
		}

		System.out.println(repoZonas.getZonas().get(0).getTransformadores().get(1).getOid());

		Clientes repoClientes = (new Repositorio()).clientes();
		repoClientes.cargarClientes();
		for (Cliente cli : repoClientes.getClientes()) {
			System.out.println(cli.getApellido());
		}

		System.out.println(repoZonas.getZonas().get(0).pertenece(repoClientes.getClientes().get(0)));
		System.out.println(repoZonas.getZonas().get(0).pertenece(repoClientes.getClientes().get(1)));
		System.out.println(repoZonas.getZonas().get(0).pertenece(repoClientes.getClientes().get(2)));

		// ultimas pruebas falta corregir esto nullpointer al querer agregar cliente

		for (Cliente clientet : repoClientes.getClientes()) {
			ZonaGeografica zona = repoZonas.getZonas().stream().filter(s -> s.pertenece(clientet)).findFirst().get();

			Transformador trans = Collections.min(zona.getTransformadores(),
					Comparator.comparing(t -> t.Distancia(clientet)));
			System.out.println(trans.getOid());
			trans.getClientes().add(clientet);

		}

		System.out.println(repoTransformadores.getTransformadores().get(3).getClientes().size() + " "
				+ repoTransformadores.getTransformadores().get(4).getClientes().size());

		// funciona bien , de la primer zona se fija en su tercer transformador y aca
		// hay dos clientes que se asignaron arriba
		System.out.println(repoZonas.getZonas().get(0).getTransformadores().get(2).getClientes().size());

	}
}
