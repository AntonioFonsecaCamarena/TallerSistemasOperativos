package org.CUCEI.TSO.Practica1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.CUCEI.TSO.Practica1.Model.CPU;
import org.CUCEI.TSO.Practica1.Model.SOProcess;
import org.CUCEI.TSO.Practica1.UI.MainFrame;

public class Practica1 {

	public static void main(String[] args) {
		System.out.println("Inicializando Practica 1");
		System.out.println("Jose Antonio Fonseca Camarena");
		System.out.println("Universidad de Guadalajara - CUCEI");
		System.out.println("Taller de Sistemas Operativos");
		System.out.println("31 de Agosto del 2019");
		System.out.println("*******\n\n Practica 1 - Monitor de procesos.\n\n");

		try {

			// Cargando Lista de Procesos
			print("Cargando lista de procesos...");
			List<SOProcess> processList = loadProcessList();
			print("Lista de procesos cargada con exito");
			// Inicializando Frame
			print("Inicializando frame...");
			// Inicializando procesador
			print("Inicializando procesador...");
			new Thread(new CPU(processList, new MainFrame(processList))).start();
			print("Frame inicializado con exito!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static List<SOProcess> loadProcessList() {

		List<SOProcess> processList = new ArrayList<SOProcess>();
		try {
			String line;
			Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				if (line.length() > 0 && (!line.contains("Nombre") && !line.contains("=") && !line.contains("Services"))) {
					System.out.println(line.substring(30, 35).trim());
					processList
							.add(new SOProcess(Integer.valueOf(line.substring(30, 35).trim()), line.substring(0, 29).trim()));
				}
			}
			input.close();
		} catch (Exception err) {
			err.printStackTrace();
		}

		return processList;
	}

	private static void print(String string) {
		System.out.println("->" + string);
	}

}
