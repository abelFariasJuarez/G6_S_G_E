package sge.modelo;

import javax.persistence.Column;

import sge.modelo.valueobjects.DispositivoDisponibleVO;
import sge.modelo.valueobjects.DispositivoEstandarVO;
import sge.modelo.valueobjects.DispositivoInteligenteVO;
import sge.modelo.valueobjects.DispositivoVO;
import sge.modelo.valueobjects.RestriccionHorasFamiliaVO;

public class DispositivoFactoryMethod {

	public DispositivoFactoryMethod() {
	}

	public static void cargaBasica() {
		Repositorio repositorio = new Repositorio();
		repositorio.abrir();

		DispositivoDisponibleVO check = null;
		check = repositorio.findDisponibleBy("codigo", "Aire3500");
		if (check == null) {
			check = new DispositivoDisponibleVO("Aire3500", Boolean.TRUE, "De 3500 frigorías", Boolean.FALSE, 1.613,
					"AIRCONDITIONER");
			repositorio.persistir(check);
		}

		check = null;
		check = repositorio.findDisponibleBy("codigo", "Aire2200");
		if (check == null) {
			check = new DispositivoDisponibleVO("Aire2200", Boolean.TRUE, "De 2200 frigorías", Boolean.TRUE,
					1.013, "AIRCONDITIONER");
			repositorio.persistir(check);
		}	

		check = null;
		check = repositorio.findDisponibleBy("codigo", "TvTubo21");
		if (check == null) {
			check = new DispositivoDisponibleVO("TvTubo21", Boolean.FALSE, "Color de tubo fluorescente 21", 
					Boolean.FALSE, 0.075, "TV");
			repositorio.persistir(check);
		}		

		check = null;
		check = repositorio.findDisponibleBy("codigo", "TvTubo29a34");
		if (check == null) {
			check = new DispositivoDisponibleVO("TvTubo29a34", Boolean.FALSE, "Color de tubo fluorescente 21", 
					Boolean.FALSE, 0.175, "TV");
			repositorio.persistir(check);
		}	

		check = null;
		check = repositorio.findDisponibleBy("codigo", "TvLCD40");
		if (check == null) {
			check = new DispositivoDisponibleVO("TvLCD40", Boolean.FALSE, "LCD de 40", 
					Boolean.FALSE, 0.18, "TV");
			repositorio.persistir(check);
		}	

		check = null;
		check = repositorio.findDisponibleBy("codigo", "TvLED24");
		if (check == null) {
			check = new DispositivoDisponibleVO("TvLED24", Boolean.TRUE, "LED 24", 
					Boolean.TRUE, 0.04, "TV");
			repositorio.persistir(check);
		}	

		check = null;
		check = repositorio.findDisponibleBy("codigo", "TvLED32");
		if (check == null) {
			check = new DispositivoDisponibleVO("TvLED32", Boolean.TRUE, "LED 32", 
					Boolean.TRUE, 0.055, "TV");
			repositorio.persistir(check);
		}		

		check = null;
		check = repositorio.findDisponibleBy("codigo", "TvLED40");
		if (check == null) {
			check = new DispositivoDisponibleVO("TvLED40", Boolean.TRUE, "LED 40", 
					Boolean.TRUE, 0.08, "TV");
			repositorio.persistir(check);
		}

		check = null;
		check = repositorio.findDisponibleBy("codigo", "RefriConFreezer");
		if (check == null) {
			check = new DispositivoDisponibleVO("RefriConFreezer", Boolean.TRUE, "Con freezer", 
					Boolean.TRUE, 0.09, "REFRIGERATOR");
			repositorio.persistir(check);
		}

		check = null;
		check = repositorio.findDisponibleBy("codigo", "RefriSinFreezer");
		if (check == null) {
			check = new DispositivoDisponibleVO("RefriSinFreezer", Boolean.TRUE, "Sin freezer", 
					Boolean.TRUE, 0.075, "REFRIGERATOR");
			repositorio.persistir(check);
		}

		check = null;
		check = repositorio.findDisponibleBy("codigo", "LavaAutoHot");
		if (check == null) {
			check = new DispositivoDisponibleVO("LavaAutoHot", Boolean.FALSE, "Automático de 5 kg con calentamiento de agua", 
					Boolean.FALSE, 0.875, "WASHINGMACHINE");
			repositorio.persistir(check);
		}

		check = null;
		check = repositorio.findDisponibleBy("codigo", "LavaAuto");
		if (check == null) {
			check = new DispositivoDisponibleVO("LavaAuto", Boolean.TRUE, "Automático de 5 kg", 
					Boolean.TRUE, 0.175, "WASHINGMACHINE");
			repositorio.persistir(check);
		}

		check = null;
		check = repositorio.findDisponibleBy("codigo", "LavaSemiAuto");
		if (check == null) {
			check = new DispositivoDisponibleVO("LavaSemiAuto", Boolean.TRUE, "Semi-automático de 5 kg", 
					Boolean.TRUE, 0.1275, "WASHINGMACHINE");
			repositorio.persistir(check);
		}		

		check = null;
		check = repositorio.findDisponibleBy("codigo", "VentiladorPie");
		if (check == null) {
			check = new DispositivoDisponibleVO("VentiladorPie", Boolean.FALSE, "De pie", 
					Boolean.TRUE, 0.09, "FAN");
			repositorio.persistir(check);
		}		

		check = null;
		check = repositorio.findDisponibleBy("codigo", "VentiladorTecho");
		if (check == null) {
			check = new DispositivoDisponibleVO("VentiladorTecho", Boolean.TRUE, "De techo", 
					Boolean.TRUE, 0.06, "FAN");
			repositorio.persistir(check);
		}
		
		check = null;
		check = repositorio.findDisponibleBy("codigo", "Lampara40");
		if (check == null) {
			check = new DispositivoDisponibleVO("Lampara40", Boolean.TRUE, "Halógenas de 40 W", 
					Boolean.FALSE, 0.04, "LAMP");
			repositorio.persistir(check);
		}

		check = null;
		check = repositorio.findDisponibleBy("codigo", "Lampara60");
		if (check == null) {
			check = new DispositivoDisponibleVO("Lampara60", Boolean.TRUE, "Halógenas de 60 W", 
					Boolean.FALSE, 0.06, "LAMP");
			repositorio.persistir(check);
		}

		check = null;
		check = repositorio.findDisponibleBy("codigo", "Lampara100");
		if (check == null) {
			check = new DispositivoDisponibleVO("Lampara100", Boolean.TRUE, "Halógenas de 100 W", 
					Boolean.FALSE, 0.1, "LAMP");
			repositorio.persistir(check);
		}

		check = null;
		check = repositorio.findDisponibleBy("codigo", "Lampara11");
		if (check == null) {
			check = new DispositivoDisponibleVO("Lampara11", Boolean.TRUE, "de 11 W", 
					Boolean.TRUE, 0.011, "LAMP");
			repositorio.persistir(check);
		}

		check = null;
		check = repositorio.findDisponibleBy("codigo", "Lampara15");
		if (check == null) {
			check = new DispositivoDisponibleVO("Lampara15", Boolean.TRUE, "de 15 W", 
					Boolean.TRUE, 0.015, "LAMP");
			repositorio.persistir(check);
		}

		check = null;
		check = repositorio.findDisponibleBy("codigo", "Lampara20");
		if (check == null) {
			check = new DispositivoDisponibleVO("Lampara20", Boolean.TRUE, "de 20 W", 
					Boolean.TRUE, 0.020, "LAMP");
			repositorio.persistir(check);
		}
		
		check = null;
		check = repositorio.findDisponibleBy("codigo", "PC");
		if (check == null) {
			check = new DispositivoDisponibleVO("PC", Boolean.TRUE, "De escritorio", 
					Boolean.TRUE, 0.04, "COMPUTER");
			repositorio.persistir(check);
		}

		check = null;
		check = repositorio.findDisponibleBy("codigo", "Microondas");
		if (check == null) {
			check = new DispositivoDisponibleVO("Microondas", Boolean.FALSE, "Convencional", 
					Boolean.TRUE, 0.64, "MICROWAVE");
			repositorio.persistir(check);
		}

		check = null;
		check = repositorio.findDisponibleBy("codigo", "Plancha");
		if (check == null) {
			check = new DispositivoDisponibleVO("Plancha", Boolean.FALSE, "A vapor", 
					Boolean.TRUE, 0.75, "GRIDDLE");
			repositorio.persistir(check);
		}		

	}

	public static DispositivoVO getDispositivoByCode(String code) {
		DispositivoVO objeto = null;
		Repositorio repositorio = new Repositorio();
		repositorio.abrir();

		DispositivoDisponibleVO template = repositorio.findDisponibleBy("codigo", code);

		if (template.getIsInteligente()) {
			objeto = new DispositivoInteligenteVO(template.getNombre(), template.getConsumoPorHora(),
					template.getIsBajoConsumo(), new DriverBasico());
		} else {
			objeto = new DispositivoEstandarVO(template.getNombre(), template.getConsumoPorHora(),
					template.getIsBajoConsumo());
		}

		RestriccionHorasFamiliaVO restriccion = repositorio.findRestriccionHorasFamiliaBy("codigo",
				template.getCodigoRestriccionHoras());
		objeto.setRestriccionHoras(restriccion);

		repositorio.cerrar();
		return objeto;
	}

}
