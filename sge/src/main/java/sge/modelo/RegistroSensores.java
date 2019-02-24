package sge.modelo;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import sge.modelo.valueobjects.InteligenteVO;
import sge.modelo.valueobjects.SensorVO;

public class RegistroSensores implements Observer {

	private Map<SensorVO, InteligenteVO> dispositivos;
	private static RegistroSensores instancia = null;

	// singleton
	private RegistroSensores() {
		dispositivos = new HashMap<>();
	}

	public static RegistroSensores getInstance() {
		if (instancia == null) {
			instancia = new RegistroSensores();
		}
		return instancia;
	}

	// singleton

	public void registrarSensor(SensorVO sensor, InteligenteVO dispositivo) {
		this.dispositivos.put(sensor, dispositivo);
	}

	public InteligenteVO getDispositivoParaSensor(SensorVO sensor) {
		return this.dispositivos.get(sensor);
	}

	public void update(Observable o, Object arg) {
		if (o instanceof SensorVO) {
			this.handleDispositivoUpdate((SensorVO) o);
		}
	}

	private void handleDispositivoUpdate(SensorVO sensor) {
		InteligenteVO inteligente = this.getDispositivoParaSensor(sensor);

		RegistroDispositivos.getInstance().accionarReglasDispositivo(inteligente);
	}
}
