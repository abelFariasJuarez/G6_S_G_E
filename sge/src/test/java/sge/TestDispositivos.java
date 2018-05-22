package sge;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import Repositorios.RepositorioDeDispositivos;
import sge.dispositivo.*;


public class TestDispositivos {

	RepositorioDeDispositivos repo2 = RepositorioDeDispositivos.getinstance();
	List<Dispositivo> Dispositivos;
	@Before
	public void setUp() {
	repo2.cargarDispositivos();
	Dispositivos = repo2.Dispositivos();
	 
	for (Dispositivo disp : Dispositivos) {
		disp.presentate();
	}
	
	
	}
	
	@Test
	public void hayDispositivos() {
		assertEquals(false,Dispositivos.isEmpty());
		
	}
	@Test
	public void ConsumoDispositivoEstandar() {
		DispositivoEstandar disestandar = new DispositivoEstandar("microondas", 12.0,"perez",12.5);
		LocalDateTime desde = LocalDateTime.parse("2018-05-18T20:30:00.775887700");
		LocalDateTime hasta = LocalDateTime.parse("2018-06-18T20:30:00.775887700");
		//seteo aproximado del cliente
		 disestandar.setHorasEncendido(9.0);
		 assertEquals(3348.0,disestandar.consumo_periodo(desde, hasta),0);
	}
	

}
