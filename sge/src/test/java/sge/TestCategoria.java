package sge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import sge.modelo.valueobjects.CategoriaVO;

public class TestCategoria {


	@Test
	public void testCategoriaEsDeTuRango() {
		Float fijo = 13.f;
		Float variable = 0.05f;
		Float inf = 1.5f;
		Float sup = 10f;

		CategoriaVO unaCate = new CategoriaVO("R0", fijo, variable, inf, sup);
		assertEquals(true, unaCate.estaEnTuRango(2f));
	}

	@Test
	public void testCategoriaNoEsDeTuRango() {
		Float fijo = 13.f;
		Float variable = 0.05f;
		Float inf = 1.5f;
		Float sup = 10f;

		CategoriaVO unaCate = new CategoriaVO("R0", fijo, variable, inf, sup);
		assertEquals(false, unaCate.estaEnTuRango(21f));
	}
	
	@Test
	public void testCategoriaMenorEsDeTuRango() {
		Float fijo = 13.f;
		Float variable = 0.05f;
		Float inf = 0f;
		Float sup = 1.5f;

		CategoriaVO unaCate = new CategoriaVO("R0", fijo, variable, inf, sup);
		assertEquals(true, unaCate.estaEnTuRango(1f));
	}

	@Test
	public void testCategoriaMenorNoEsDeTuRango() {
		Float fijo = 13.f;
		Float variable = 0.05f;
		Float inf = 0f;
		Float sup = 1.5f;

		CategoriaVO unaCate = new CategoriaVO("R0", fijo, variable, inf, sup);
		assertEquals(false, unaCate.estaEnTuRango(2f));
	}

	@Test
	public void testCategoriaMayorEsDeTuRango() {
		Float fijo = 13.f;
		Float variable = 0.05f;
		Float inf = 20f;
		Float sup =Float.MAX_VALUE;

		CategoriaVO unaCate = new CategoriaVO("R0", fijo, variable, inf, sup);
		assertEquals(true, unaCate.estaEnTuRango(21f));
	}

	@Test
	public void testCategoriaMayorNoEsDeTuRango() {
		Float fijo = 13.f;
		Float variable = 0.05f;
		Float inf = 20f;
		Float sup = Float.MAX_VALUE;

		CategoriaVO unaCate = new CategoriaVO("R0", fijo, variable, inf, sup);
		assertEquals(false, unaCate.estaEnTuRango(2f));
	}

}
