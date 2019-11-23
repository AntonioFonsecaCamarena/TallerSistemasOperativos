package org.CUCEI.TSO.Practica6.UI;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.CUCEI.TSO.Practica6.Model.SOProcess;

public class MainFrame {

	private JFrame mainFrame;
	private List<SOProcess> processListtmp;
	DefaultTableModel processTableModel;

	public MainFrame(List<SOProcess> processListtmp, int quantum) {
 		this.processListtmp = processListtmp;
		mainFrame = new JFrame("TSO - Colas Multiples - Jose Antonio Fonseca - Practica 6");
		mainFrame.setSize(600, 400);
 		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel quantumPanel = new JPanel();
		JPanel tablePanel = new JPanel();
		quantumPanel.add(new Label("Quantum - " + quantum));
		JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(getSOProcessTable());
		tablePanel.add(scrollPane);
		mainFrame.setLayout(new FlowLayout());
		mainFrame.add(quantumPanel);
		mainFrame.add(tablePanel);
		mainFrame.setVisible(true);
	}

	public DefaultTableModel getTableModel() {
		return processTableModel;
	}

	private Component getSOProcessTable() {
		createTableModel();
		addRows();
		JTable processTable = new JTable(processTableModel);
		processTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = processTable.rowAtPoint(e.getPoint());
				int column = processTable.columnAtPoint(e.getPoint());
				if ((row > -1) && (column > -1)) {
					popupProcess(row, column);
				}
			}

			private void popupProcess(int row, int column) {
			 new ProcessDialog(row, processListtmp, mainFrame);

			}
		});
		JScrollPane tablePane = new JScrollPane(processTable);
		return tablePane;
	}

	private void createTableModel() {
		processTableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		processTableModel.addColumn("Id");
		processTableModel.addColumn("Name");
		processTableModel.addColumn("Arrival Time");
		processTableModel.addColumn("Status");
		processTableModel.addColumn("Burst Time");
		processTableModel.addColumn("Priority");
	}

	public void addRows() {
		try {
			if (processTableModel.getRowCount() > 0) {
				for (int i = processTableModel.getRowCount(); i != 0; i--) {
					processTableModel.removeRow(i - 1);
				}
			}
		}
		catch(Exception e) {}
		for (SOProcess soProcess : processListtmp) {
			Object[] obj = { soProcess.getId(), soProcess.getName(), soProcess.getArrivalTime(), soProcess.getStatusValue(), soProcess.getBurstTime(), soProcess.getPriority() };
			processTableModel.addRow(obj);
		}
		processTableModel.fireTableDataChanged();
	}

}
