package swc.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import swc.ctrl.CtrlGroup;

public class EditGroupDialog extends JDialog implements ActionListener, KeyListener {
	private JTextField title;
	private JLabel label;
	private JButton apply;
	private JButton cancel;
	private MouseEvent mEvent;
	public EditGroupDialog(MouseEvent mEvent) {
		this.mEvent = mEvent;
		setSize(400, 120);
		setLayout(new BorderLayout());
		setTitle("Edit Group");
		title = new JTextField();
		title.setPreferredSize(new Dimension(270, 25));
		label = new JLabel("Titel: ");
		apply = new JButton("Apply changes");
		cancel = new JButton("Cancel");
		
		apply.addActionListener(this);
		cancel.addActionListener(this);
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		panel1.add(label);
		panel1.add(title);
		panel2.add(apply);
		panel2.add(cancel);
		
		title.addKeyListener(this);
		add(panel1, BorderLayout.CENTER);
		add(panel2, BorderLayout.SOUTH);
		setLocationRelativeTo(getOwner());
		setResizable(false);
		setModal(true);
		setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		if(button.getText().equals("Apply changes")) {
			applyChanges();
		}
		else if(button.getText().equals("Cancel")) {
			title.setText("");
			setVisible(false);
		}
	}
	public void applyChanges() {
		JList list = (JList) mEvent.getSource();	
		String s = title.getText();
		if(title.getText().equals(""))
			JOptionPane.showMessageDialog(getOwner(), "Please Insert Text", "Error", JOptionPane.ERROR_MESSAGE);
		else if(CtrlGroup.getFlagIcon(s).getDescription().equals("default")) {
			int answer = JOptionPane.showConfirmDialog(this, "Want to show without flag?","No flag of this country", JOptionPane.YES_NO_OPTION);
			if(answer == JOptionPane.YES_OPTION) {
				DefaultListModel model = (DefaultListModel) list.getModel();
				model.add(list.getSelectedIndex(), s);
				model.removeElementAt(list.getSelectedIndex());
				list.setModel(model);
	
				setVisible(false);
			}
		}
		else {	
			DefaultListModel model = (DefaultListModel) list.getModel();
			model.add(list.getSelectedIndex(), s);
			model.removeElementAt(list.getSelectedIndex());
			list.setModel(model);

			setVisible(false);
		}
	}
	


	@Override
	public void keyTyped(KeyEvent e) {		
	}


	@Override
	public void keyPressed(KeyEvent e) {		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 10)
			applyChanges();	
	}

}
