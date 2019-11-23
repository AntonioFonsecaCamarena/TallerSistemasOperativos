package org.CUCEI.TSO.Practica6.Model;

import java.util.Collections;
import java.util.List;

import org.CUCEI.TSO.Practica6.UI.MainFrame;

public class CPU implements Runnable {

	private List<SOProcess> processList;
	private List<SOProcess> processListtmp;

	MainFrame mainFrame;

	public CPU(List<SOProcess> processList, List<SOProcess> processListtmp, MainFrame mainFrame) {
		this.processList = processList;
		this.processListtmp = processListtmp;
		this.mainFrame = mainFrame;
	}

	@Override
	public void run() {
		print("Iniciando CPU");
		int arrivalTime = 0;
		for (; arrivalTime < processList.size() / 2; arrivalTime++) {
			processListtmp.add(processList.get(arrivalTime));
			processListtmp.get(arrivalTime).setArrivalTime(arrivalTime);
			processList.remove(arrivalTime);
		}
		while (true) {
			for (SOProcess process : processListtmp) {
				if (!(process.getStatus() == process.STATUS_BLOQUED || process.getStatus() == process.STATUS_FINISHED))
					if (process.getStatus() == process.STATUS_NEW) {
						print("Cambiando satus de: " + process.getName());
						process.setStatus(process.STATUS_READY);
						mainFrame.addRows();
					}
			}
			sortProcessByPriority(processListtmp);
			for (SOProcess process : processListtmp) {
				if (process.getStatus() == process.STATUS_BLOQUED) {
					break;
				}
				if (!(process.getStatus() == process.STATUS_BLOQUED || process.getStatus() == process.STATUS_FINISHED))
					if (process.getStatus() == process.STATUS_READY) {
						print("Executando: " + process.getName());
						process.setStatus(process.STATUS_EXECUTING);
						mainFrame.addRows();
						process.run();
						if (process.getBurstTime() < 0) {
							process.setBurstTime(0);
							process.setStatus(process.STATUS_FINISHED);
							process.setPriority(200);
						} else {
							process.setStatus(process.STATUS_READY);
						}
						mainFrame.addRows();
						// break;
					}
			}

			if (arrivalTime < processList.size()) {
				processListtmp.add(processList.get(arrivalTime));
				processList.remove(arrivalTime);
			}
		}
	}

	private void sortProcessByPriority(List<SOProcess> processList) {
		Collections.sort(processList);
	}

	private void print(String string) {
		System.out.println("*_>" + string);
	}

}
