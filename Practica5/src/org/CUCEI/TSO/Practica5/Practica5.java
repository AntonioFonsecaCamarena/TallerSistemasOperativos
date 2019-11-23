package org.CUCEI.TSO.Practica5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.CUCEI.TSO.Practica5.Model.CPU;
import org.CUCEI.TSO.Practica5.Model.SOProcess;
import org.CUCEI.TSO.Practica5.UI.MainFrame;

public class Practica5 {

	public static void main(String[] args) {
		System.out.println("Inicializando Practica 5");
		System.out.println("Jose Antonio Fonseca Camarena");
		System.out.println("Universidad de Guadalajara - CUCEI");
		System.out.println("Taller de Sistemas Operativos");
		System.out.println("*******\n\n Practica 5 - Prioridades.\n\n");
		int quantum = 5;
		List<SOProcess> processListtmp = new ArrayList<SOProcess>();
		try {

			// Cargando Lista de Procesos
			print("Cargando lista de procesos...");
			List<SOProcess> processList = loadProcessList(quantum);
			print("Lista de procesos cargada con exito");
			// Inicializando Frame
			print("Inicializando frame...");
			// Inicializando procesador
			print("Inicializando procesador...");
			new Thread(new CPU(processList, processListtmp, new MainFrame(processListtmp, quantum))).start();
			print("Frame inicializado con exito!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static List<SOProcess> loadProcessList(int quantum) {

		List<SOProcess> processList = new ArrayList<SOProcess>();
		try {
			String line;
			Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				if (line.length() > 0 && (!line.contains("Nombre") && !line.contains("=") && !line.contains("Services"))) {
					System.out.println(line.substring(30, 35).trim());
					processList
							.add(new SOProcess(Integer.valueOf(line.substring(30, 35).trim()), line.substring(0, 29).trim(), quantum));
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
