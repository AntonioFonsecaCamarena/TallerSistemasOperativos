package org.CUCEI.TSO.Practica4.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import org.CUCEI.TSO.Practica4.Model.SOProcess;

public class ProcessDialog extends JDialog implements ActionListener {
	List<SOProcess> processList;
	int row;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProcessDialog(int row, List<SOProcess> processList, JFrame mainframe) {
		this.row = row;
		this.processList = processList;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(mainframe);
		setSize(160, 120);
		setTitle(this.processList.get(row).getName());
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		JButton btnPause = new JButton("Pausar");
		btnPause.addActionListener(this);
		JButton btnResume = new JButton("Reanudar");
		btnResume.addActionListener(this);
		JButton btnFinish = new JButton("Finalizar");
		btnFinish.addActionListener(this);
		getContentPane().add(btnPause);
		getContentPane().add(btnResume);
		getContentPane().add(btnFinish);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("---------->" + e.getActionCommand());
		switch (e.getActionCommand()) {
		case "Pausar":
			this.processList.get(this.row).setStatus(2);
			break;
		case "Reanudar":
			this.processList.get(this.row).setStatus(1);
			break;
		case "Finalizar":
			this.processList.get(this.row).setStatus(4);
			break;
		default:
			break;
		}
		dispose();
	}
}
