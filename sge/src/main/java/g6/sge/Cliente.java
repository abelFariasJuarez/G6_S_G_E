package g6.sge;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends UsuarioSGE {
	String tipodoc;
	Integer nrodoc;
	Integer telefono;
	Categoria categoria;
	List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();

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

}
