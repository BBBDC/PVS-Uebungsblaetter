package swc.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import swc.ctrl.CtrlGroup;
import swc.data.Game;

public class EditGameDialog extends JDialog implements ActionListener, KeyListener{
	private JLabel icon1;
	private JLabel icon2;
	private JTextField goalsTeamH;
	private JTextField goalsTeamG;	
	private JButton apply;
	private Game game;
	private GroupPanel grpPanel;
	private FinalsPanel finPanel;
	private JButton cancel;
	public EditGameDialog(Game game, GroupPanel grpPanel, FinalsPanel finPanel) {
		this.game = game;
		this.grpPanel = grpPanel;
		this.finPanel = finPanel;
		setTitle("Edit Game");
		setSize(300, 150);
		setLocationRelativeTo(null);
		icon1 = new JLabel(game.getTeamH().getStrName());
		icon1.setIcon(CtrlGroup.getFlagIcon(game.getTeamH().getStrName()));
		icon2 = new JLabel(game.getTeamG().getStrName());
		icon2.setIcon(CtrlGroup.getFlagIcon(game.getTeamG().getStrName()));
		goalsTeamH = new JTextField();
		goalsTeamH.setMaximumSize(new Dimension(200, 25));
		goalsTeamG = new JTextField();
		goalsTeamG.setMaximumSize(new Dimension(200, 25));
		apply = new JButton("Apply Changes");
		apply.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		goalsTeamG.addKeyListener(this);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2, 1));
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2, 1));
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(2, 1));
		JPanel panel4 = new JPanel();
		panel1.add(icon1);
		panel1.add(icon2);
		panel3.add(new JLabel("Goals"));
		panel3.add(new JLabel("Goals"));
		panel2.add(goalsTeamH);
		panel2.add(goalsTeamG);
		panel4.add(apply);
		panel4.add(cancel);
		add(new JLabel("   "), BorderLayout.NORTH);
		add(panel1, BorderLayout.WEST);
		add(panel2, BorderLayout.CENTER);
		add(panel3, BorderLayout.EAST);
		add(panel4, BorderLayout.SOUTH);
		setModal(true);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		if(button.getText().equals("Apply Changes")) 
			apply();
		else if (button.getText().equals("Cancel"))
			setVisible(false);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 10)
			apply();
	}
	public void apply() {			
		if(game.getIntId() < 49 || !goalsTeamH.getText().equals(goalsTeamG.getText())) {

			if(game.isPlayed() == false) {
				String goalsH = goalsTeamH.getText();
				try {
					int goalsh = Integer.parseInt(goalsH);
					String goalsG = goalsTeamG.getText();
					int goalsg = Integer.parseInt(goalsG);
					
					game.setGoalsH(goalsh);
					game.setGoalsG(goalsg);
				}catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(this, "Please Insert Integer!", "Error", JOptionPane.ERROR_MESSAGE);
					}
			if(game.getIntId() < 49)
				CtrlGroup.calculateGroupTable(game);
			game.setPlayed(true);
				}
			else {
				int ync = JOptionPane.showConfirmDialog(this, "Do you want to change the result?", "Game already entered", JOptionPane.YES_NO_CANCEL_OPTION);
				if(ync == JOptionPane.YES_OPTION) {
					String goalsH = goalsTeamH.getText();
					try {
						int goalsh = Integer.parseInt(goalsH);
						String goalsG = goalsTeamG.getText();
						int goalsg = Integer.parseInt(goalsG);
						if(game.getIntId() < 49)
						CtrlGroup.calculateGroupTable(game);
							game.setGoalsH(goalsh);
							game.setGoalsG(goalsg);
					}catch(NumberFormatException e) {
						JOptionPane.showMessageDialog(this, "Please Insert Integer!", "Error", JOptionPane.ERROR_MESSAGE);
						}
					if(game.getIntId() < 49)
						CtrlGroup.calculateGroupTable(game);
				}
					
			}
		
			if(grpPanel != null) {
				grpPanel.removeAll();
				JPanel panel1 = new JPanel();
				panel1.setLayout(new BorderLayout());
				JPanel panel2 = new JPanel();
				panel2.setLayout(new BorderLayout());
				panel1.add(grpPanel.getLabel1(), BorderLayout.NORTH);
				panel1.add(new JScrollPane(grpPanel.initTable1()), BorderLayout.CENTER);
				panel2.add(grpPanel.getLabel2(), BorderLayout.NORTH);
				panel2.add(new JScrollPane(grpPanel.initTable2()), BorderLayout.CENTER);
				grpPanel.add(panel1);
				grpPanel.add(panel2);
				grpPanel.revalidate();
				setVisible(false);
			}
			if(finPanel != null) {
				finPanel.removeAll();
				JPanel panel1 = new JPanel();
				panel1.setLayout(new BorderLayout());
				JPanel panel2 = new JPanel();
				panel2.setLayout(new BorderLayout());
				JPanel panel3 = new JPanel();
				panel3.setLayout(new BorderLayout());
				JPanel panel4 = new JPanel();
				panel4.setLayout(new BorderLayout());
				JPanel panel5 = new JPanel();
				panel5.setLayout(new BorderLayout());
				panel1.add(finPanel.getLabel1(), BorderLayout.NORTH);
				panel1.add(new JScrollPane(finPanel.initTable(finPanel.getTable1(),finPanel.getRoundOf16())), BorderLayout.CENTER);
				panel2.add(finPanel.getLabel2(), BorderLayout.NORTH);
				panel2.add(new JScrollPane(finPanel.initTable(finPanel.getTable2(), finPanel.getQuarterFinals())), BorderLayout.CENTER);
				panel3.add(finPanel.getLabel3(), BorderLayout.NORTH);
				panel3.add(new JScrollPane(finPanel.initTable(finPanel.getTable3(), finPanel.getSemiFinals())), BorderLayout.CENTER);
				panel4.add(finPanel.getLabel4(), BorderLayout.NORTH);
				panel4.add(new JScrollPane(finPanel.initTable(finPanel.getTable4(), finPanel.getThirdGame())), BorderLayout.CENTER);
				panel5.add(finPanel.getLabel5(), BorderLayout.NORTH);
				panel5.add(new JScrollPane(finPanel.initTable(finPanel.getTable5(),finPanel.getFinalGame())), BorderLayout.CENTER);
				finPanel.add(panel1);
				finPanel.add(panel2);
				finPanel.add(panel3);
				finPanel.add(panel4);
				finPanel.add(panel5);
				finPanel.revalidate();
				setVisible(false);
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Invalid Insert for this Match", "Error", JOptionPane.ERROR_MESSAGE);
			goalsTeamG.setText("");
			goalsTeamH.setText("");
		}
		
		
	}
	
}
