package sge;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import sge.modelo.ActuadorAhorro;
import sge.modelo.ActuadorApagar;
import sge.modelo.ActuadorPrender;
import sge.modelo.DriverBasico;
import sge.modelo.RegistroReglas;
import sge.modelo.RegistroSensores;
import sge.modelo.regla.*;
import sge.modelo.regla.comparador.*;
import sge.modelo.valueobjects.AccionApagarVO;
import sge.modelo.valueobjects.AccionPrenderVO;
import sge.modelo.valueobjects.CondicionVO;
import sge.modelo.valueobjects.DispositivoInteligenteVO;
import sge.modelo.valueobjects.IgualVO;
import sge.modelo.valueobjects.MenorVO;
import sge.modelo.valueobjects.ReglaVO;
import sge.modelo.valueobjects.SensorVO;

public class TestRegla {

	ReglaVO unRegla = new ReglaVO("regla 1");

	@Test
	public void cumpleCondicionesPrender() {
		
		DispositivoInteligenteVO unAire = new DispositivoInteligenteVO("AireAcondicionado", 2.3, "perez", false,false,new DriverBasico());
		// el constructor ya me da un dispo en estado apagado

		SensorVO temperatura = new SensorVO(40.0, 10000.0, "temperatura");
		SensorVO humedad = new SensorVO(100.0, 5000.0, "humedad");
		
		unAire.agregarSensor(temperatura);
		unAire.agregarSensor(humedad);
		
		//Condicion condTemp = new Condicion(temperatura, new Mayor(), 32.0);
		//Condicion condHume = new Condicion(humedad, new Mayor(), 90.0);

		CondicionVO condTemp = new CondicionVO(temperatura, new MenorVO(), 32.0);
		CondicionVO condHume = new CondicionVO(humedad, new MenorVO(), 90.0);

		unRegla.agregarCondicion(condTemp);
		unRegla.agregarCondicion(condHume);

		AccionPrenderVO prenderAire = new AccionPrenderVO();
		unRegla.agregarAccion(prenderAire);
		
		List <ReglaVO> reglas=new ArrayList<ReglaVO>();
		reglas.add(unRegla);
		
		RegistroSensores.getInstance().registrarSensor(humedad, unAire);
		RegistroSensores.getInstance().registrarSensor(temperatura, unAire);
		RegistroReglas.getInstance().registrarReglas(unAire,reglas );
		
		
		temperatura.actualizarMedicion();
		
		assertEquals(true, unAire.estoyON());

	}

	@Test
	public void noCumpleCondicionesNoApagar() {
		
		DispositivoInteligenteVO unAire = new DispositivoInteligenteVO("AireAcondicionado", 2.8, "perez",false, true,new DriverBasico());

		SensorVO temperatura2 = new SensorVO(18, 2000.0, "temperatura2");
		CondicionVO condTemp = new CondicionVO(temperatura2, new IgualVO(), 20.0);

		unRegla.agregarCondicion(condTemp);

		AccionApagarVO ApagarAire = new AccionApagarVO();
		unRegla.agregarAccion(ApagarAire);

		RegistroSensores.getInstance().registrarSensor(temperatura2, unAire);
		List <ReglaVO> reglas=new ArrayList<ReglaVO>();
		reglas.add(unRegla);
		RegistroReglas.getInstance().registrarReglas(unAire, reglas);
		temperatura2.setMedicion(10);
		

		assertEquals(false, unAire.estoyOFF());

	}

}
