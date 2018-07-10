package sge;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import Repositorios.RepositorioDeClientes;
import Repositorios.RepositorioDeDispositivos;
import Repositorios.RepositorioDeTransformadores;

import Repositorios.RepositorioDeZonas;
import posicionamiento.Transformador;
import posicionamiento.Ubicacion;
import posicionamiento.ZonaGeografica;
import sge.dispositivo.*;
import sge.regla.Actuador;
import sge.regla.ActuadorPrender;
import sge.regla.Condicion;
import sge.regla.Regla;
import sge.regla.Sensor;
import sge.regla.comparador.*;

public class App {

	public static void main(String[] args) throws InterruptedException {



	Regla unRegla = new Regla("regla 1");

		
		//Regla unRegla = new Regla("regla 1");
		//DispositivoInteligente unAire = new DispositivoInteligente("AireAcondicionado", 2.3,"perez", false,false);
		DispositivoInteligente unAire = new DispositivoInteligente("AireAcondicionado", 2.3,"cazana", false,false);
		// el constructor ya me da un dispo en estado apagado
		//assertEquals(true,unAire.estoyOFF());
		//Regla unRegla = new Regla("regla 1");
		
		Sensor temperatura = new Sensor(30.0, 10000,"temperatura");
		Sensor humedad = new Sensor(100.0, 5000,"humedad");
		humedad.agregarObserver(unRegla);
		temperatura.agregarObserver(unRegla);
		Condicion condTemp = new Condicion(temperatura, new Mayor(), 32.0);
		Condicion condHume = new Condicion(humedad, new Mayor(), 90.0);

		unRegla.agregarCondicion(condTemp);
		unRegla.agregarCondicion(condHume);
		
		Actuador prenderAire = new ActuadorPrender(unAire);
		unRegla.agregarActuador(prenderAire);
		
		humedad.activar();
		temperatura.setMedicion(40);
		temperatura.activar();
		//temperatura.actualizarMedicion();
		
		//assertEquals(true,unAire.estoyON());
		
		
		
		Double radio = 20.3;
		Double Xcentro = 40.3;
		Double yCenter = 70.3;
		Double xPoint = 60.8;
		Double yPoint = 70.4;
		if (Math.sqrt (Math.pow(Xcentro-xPoint,2) + Math.pow((yCenter-yPoint), 2)) <= radio) { System.out.println ("El punto está contenido en el círculo."); }
		else { System.out.println ("El punto no está contenido en el círculo."); }
		
		
		
		
		System.out.println(Math.abs(Xcentro-xPoint)+Math.abs(yCenter-yPoint));
		
		
		
		List<Transformador> transformadores=new ArrayList<Transformador>();
		Cliente unCliente=new Cliente("Carlos", "Sanazki", "condarco 148",LocalDate.of(2017,4,7), "cazana", "menToL2017", "Dni", 21321012,1543312310,new Ubicacion(0.21,2.9));
		Transformador transfo=new Transformador(3,1,new Ubicacion(1.0,3.0));
		Transformador transfo4=new Transformador(2,1,new Ubicacion(1.0,3.3));
		Transformador transfo5=new Transformador(5,1,new Ubicacion(0.9,3.3));
		Transformador transfo2=new Transformador(1,1,new Ubicacion(15.0,30.0));
		Transformador transfo3=new Transformador(10,1,new Ubicacion(50.0,60.0));
		transformadores.add(transfo);
		transformadores.add(transfo2);
		transformadores.add(transfo3);
		transformadores.add(transfo4);
		transformadores.add(transfo5);
		
		Transformador trans = Collections.min(transformadores, Comparator.comparing(t->t.Distancia(unCliente)));
	
		System.out.println(trans.getId());
		
		
		RepositorioDeTransformadores repotransfor = RepositorioDeTransformadores.getinstance();
		repotransfor.cargarTransformadores();
		RepositorioDeZonas repotransfor2 = RepositorioDeZonas.getinstance();
		repotransfor2.cargarZonas();
		
		
		
		for (ZonaGeografica zona1 : repotransfor2.zonas()) {
			for (Transformador trans1 : repotransfor.transformadores) {
				if(zona1.getId().equals(trans1.getIdZona())) {
					zona1.Add(trans1);
					repotransfor.transformadores.remove(trans1);
			}
		}
	}
	
	System.out.println(repotransfor2.zonas().get(0).getTransformadores().size());

	}
}
