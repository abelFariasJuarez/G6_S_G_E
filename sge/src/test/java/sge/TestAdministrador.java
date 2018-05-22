package sge;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
//import java.util.Calendar;
//import java.util.GregorianCalendar;

import org.junit.Test;

public class TestAdministrador {

	@Test
	public void MesesAdministrando() {

		Administrador admin = new Administrador("pedro", "saraska", "lavalle 148", LocalDate.of(2015, Month.APRIL, 19),
				"pepe", "pasti");

		assertEquals(37, admin.cantMesesAdministrando());

	}
	
}

