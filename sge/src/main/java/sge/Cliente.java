package sge;

import java.util.ArrayList;
import java.util.List;
import sge.Dispositivo;

public class Cliente extends UsuarioSGE {
	String tipodoc;
	Integer nrodoc;
	Integer telefono;
	Categoria categoria;
	List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();

	public Cliente(String _tipodoc, Integer _nrodoc, Integer _telefono, List<Dispositivo> _dispositivos) {
		tipodoc = _tipodoc;
		nrodoc = _nrodoc;
		telefono = _telefono;
		dispositivos = _dispositivos;

	}

	public Cliente(String _tipodoc, Integer _nrodoc, Integer _telefono) {
		tipodoc = _tipodoc;
		nrodoc = _nrodoc;
		telefono = _telefono;

	}

	public boolean tengoAlgunDispositivoON() {
		return true;
	}

	public Integer cantDispositivosON() {
		return null;
	}

	Integer cantDispositivosOFF() {
		return null;
	}

	Integer cantDispositivos() {
		return null;
	}

	public String Dni() {
		return tipodoc;
	}

	public int nroDoc() {
		return nrodoc;
	}

	public int telefono() {
		return telefono;
	}

	public void mostrarDispositivos() {
		for (Dispositivo disp : dispositivos) {
			System.out.println("\t"+ disp.getNombre() + " " + disp.toString());
		}
	}

}
