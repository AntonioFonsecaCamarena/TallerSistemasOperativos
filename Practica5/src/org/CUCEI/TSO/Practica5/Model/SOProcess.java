package org.CUCEI.TSO.Practica5.Model;

import java.util.Random;

public class SOProcess implements Runnable, Comparable<SOProcess> {

	public final int STATUS_NEW = 0;
	public final int STATUS_READY = 1;
	public final int STATUS_BLOQUED = 2;
	public final int STATUS_EXECUTING = 3;
	public final int STATUS_FINISHED = 4;

	private int id;
	private String name;
	private int status;
	private int burstTime;
	private int quantum;
	private int priority;
	private int arrivalTime;

	public SOProcess(int id, String name, int quantum) {
		super();
		this.id = id;
		this.name = name;
		this.status = STATUS_NEW;
		this.burstTime = getRandomNumberInRange(quantum);
		this.quantum = quantum;
		this.priority = getRandomNumberInRange(10);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getStatusValue() {
		switch (status) {
		case STATUS_NEW:
			return "New";
		case STATUS_READY:
			return "Ready";
		case STATUS_EXECUTING:
			return "Executing";
		case STATUS_BLOQUED:
			return "Bloqued";
		case STATUS_FINISHED:
			return "Finished";
		default:
			return "INVALID";
		}
	}

	public void setStatus(int status) {
		try {
			Thread.sleep(new Double((Math.random() * 50 + 1)).longValue());
			this.status = status;
			printStatus();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}


	@Override
	public void run() {
		try {
			printStatus();
			Thread.sleep(new Double(quantum * 50).longValue());
			this.burstTime -= quantum;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void printStatus() {
		System.out.println("-> name:" + name + " status:" + getStatusValue());
	}

	private static int getRandomNumberInRange(int max) {
		int min = 1;
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt(((max * 3) - min) + 1) + min;
	}

	@Override
	public int compareTo(SOProcess compareProcess) {
		return this.priority - compareProcess.getPriority();
	}


}
