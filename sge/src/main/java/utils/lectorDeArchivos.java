package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class lectorDeArchivos {

	private FileReader fileReader;
	private BufferedReader bufferedReader;

	public lectorDeArchivos(String archi) throws FileNotFoundException {
		File archivo = new File(archi);
		fileReader = new FileReader(archivo);
		bufferedReader = new BufferedReader(fileReader);
	}

	public boolean lecturaFinalizada() throws IOException {
		return !bufferedReader.ready();
	}

	public String leerSiguiente() throws IOException {
		return bufferedReader.readLine();
	}

	public void cerrar() throws IOException {
		bufferedReader.close();
		fileReader.close();
	}

}
