package org.CUCEI.TSO.Practica1.Model;

import java.util.List;

import org.CUCEI.TSO.Practica1.UI.MainFrame;

public class CPU implements Runnable {

	private List<SOProcess> processList;
	MainFrame mainFrame;

	public CPU(List<SOProcess> processList, MainFrame mainFrame) {
		this.processList = processList;
		this.mainFrame = mainFrame;
	}

	@Override
	public void run() {
		print("Iniciando CPU");
		while (true) {
			for (SOProcess process : processList) {
				if (!(process.getStatus() == process.STATUS_BLOQUED || process.getStatus() == process.STATUS_FINISHED))
					if (process.getStatus() == process.STATUS_NEW) {
						print("Cambiando satus de: " + process.getName());
						process.setStatus(process.STATUS_READY);
						mainFrame.addRows();
					}
			}

			for (SOProcess process : processList) {
				if (process.getStatus() == process.STATUS_BLOQUED) {
					break;
				}
				if (!(process.getStatus() == process.STATUS_BLOQUED || process.getStatus() == process.STATUS_FINISHED))
					if (process.getStatus() == process.STATUS_READY) {
						print("Executando: " + process.getName());
						process.setStatus(process.STATUS_EXECUTING);
						mainFrame.addRows();
						process.run();
						process.setStatus(process.STATUS_FINISHED);
						mainFrame.addRows();
						break;
					}
			}
		}
	}

	private void print(String string) {
		System.out.println("*_>" + string);
	}

}
