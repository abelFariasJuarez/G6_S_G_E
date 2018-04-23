package sge;

import Repositorios.RepositorioDeDispositivos;



import Repositorios.RepositorioDeClientes;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		// Lo dejamos acá o lo pasamos a test?
		RepositorioDeClientes repo = RepositorioDeClientes.getinstance();
		repo.cargarClientes();

		for (Cliente client : repo.Clientes()) {
			client.presentate();
			for (Dispositivo disp : client.dispositivos) {
				disp.presentate();
			}

		}

		System.out.println("Hello World!");

		RepositorioDeDispositivos repo2 = RepositorioDeDispositivos.getinstance();
		repo2.cargarDispositivos();
		for (Dispositivo disp : repo2.Dispositivos()) {
			disp.presentate();
		}

	}
}
