package org.CUCEI.TSO.Practica3.Model;

public class SOProcess implements Runnable {

	public final int STATUS_NEW = 0;
	public final int STATUS_READY = 1;
	public final int STATUS_BLOQUED = 2;
	public final int STATUS_EXECUTING = 3;
	public final int STATUS_FINISHED = 4;

	private int id;
	private String name;
	private int status;

	public SOProcess(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.status = STATUS_NEW;
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
			Thread.sleep(new Double((Math.random() * 500 + 1)).longValue());
			this.status = status;
			printStatus();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			printStatus();
			Thread.sleep(new Double((Math.random() * 7000 + 1)).longValue());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void printStatus() {
		System.out.println("-> name:" + name + " status:" + getStatusValue());
	}

}
