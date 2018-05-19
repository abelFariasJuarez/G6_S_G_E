package sge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

public class TestDispositivoInteligente {

	@Test
	
	public void unaHoraPrendido() {
		
		DispositivoInteligente unDispo = new DispositivoInteligente("heladera",2.3,true);
		Intervalo inter = new Intervalo();
		unDispo.addIntervalo(inter);
		
		inter.setInicio(LocalDateTime.now().minusHours(1));
		inter.setFin(LocalDateTime.now());
		inter.setEstado(new EstadoPrendido());
		//le doy una tolerancia de 0.02 por que 
		assertTrue(2.3 == unDispo.consumo_ultimas_n_horas(1) );
		

	}
	
	/*public void mediaHoraApagadoMediaHoraPrendido() {
	}
	
	public void mediaHoraAhorroEnergiaMediaHoraPrendido() {
	}*/
}
