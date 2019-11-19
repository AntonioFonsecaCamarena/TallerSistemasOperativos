package org.CUCEI.TSO.Practica1;

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
		// TODO Auto-generated method stub
		List<SOProcess> processList = new ArrayList<SOProcess>();
		for (int i = 0; i < 45; i++) {
			processList.add(new SOProcess(1, "test"+i));
		}
		
		return processList;
	}

	private static void print(String string) {
		System.out.println("->" + string);
	}

}
