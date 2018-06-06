package swc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.EventObject;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;

import javafx.scene.control.ScrollPane;
import swc.ctrl.CtrlFinals;
import swc.ctrl.CtrlGroup;
import swc.data.Game;
import swc.data.SoccerWC;

public class FinalsPanel extends JPanel  {
	private SoccerWC wC;
	private Game[] roundOf16;
	private Game[] quarterFinals;
	private Game[] semiFinals;
	private Game[] finalGame;
	private Game[] thirdGame;
	private String winner;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	
	private JTable table1;
	private JTable table2;
	private JTable table3;
	private JTable table4;
	private JTable table5;
	private FinalsPanel finalsPanel;

	public FinalsPanel(SoccerWC wC) {
		this.wC = wC;
		try {
			CtrlFinals.createDefaultFinals(wC);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finalsPanel = this;
		initFinPanel();
		
	}
	public FinalsPanel initFinPanel() {
		CtrlFinals.calculateFinals(wC);
		roundOf16 = new Game[8];
		for (int i = 0; i < roundOf16.length; i++) {
			roundOf16[i] = wC.getFinals().getRoundOf16().get(i);
		}
		quarterFinals = new Game[4];
		for (int i = 0; i < quarterFinals.length; i++) {
			quarterFinals[i] = wC.getFinals().getQuarterFinals().get(i);
		}
		semiFinals = new Game[2];
		for (int i = 0; i < semiFinals.length; i++) {
			semiFinals[i] = wC.getFinals().getSemiFinals().get(i);
		}
		thirdGame = new Game[] {wC.getFinals().getThirdGame()};
		finalGame = new Game[] {wC.getFinals().getFinalGame()};
		
		table1 = initTable(table1, roundOf16);
		table2 = initTable(table2, quarterFinals);
		table3 = initTable(table3, semiFinals);
		table4 = initTable(table4, thirdGame);
		table5 = initTable(table5, finalGame);
		label1 = new JLabel("Round of 16: ");
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.add(label1, BorderLayout.NORTH);
		panel1.add(new JScrollPane(table1), BorderLayout.CENTER);
		
		label2 = new JLabel("Quarterfinals: ");
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.add(label2, BorderLayout.NORTH);
		panel2.add(new JScrollPane(table2), BorderLayout.CENTER);
		
		label3 = new JLabel("Semifinals: ");
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		panel3.add(label3, BorderLayout.NORTH);
		panel3.add(new JScrollPane(table3), BorderLayout.CENTER);
		
		label4 = new JLabel("Match for Third Place: ");
		JPanel panel4 = new JPanel();
		panel4.setLayout(new BorderLayout());
		panel4.add(label4, BorderLayout.NORTH);
		panel4.add(new JScrollPane(table4), BorderLayout.CENTER);
		
		label5 = new JLabel("Final: ");
		JPanel panel5 = new JPanel();
		panel5.setLayout(new BorderLayout());
		panel5.add(label5, BorderLayout.NORTH);
		panel5.add(new JScrollPane(table5), BorderLayout.CENTER);
		
		ImageIcon image = new ImageIcon();
		try {
			image.setImage(ImageIO.read(new File("src/swc/gui/pokal.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		image.setImage(image.getImage().getScaledInstance(-1, 60, Image.SCALE_SMOOTH));
		label6 = new JLabel(image);
		label6.setText("Winner: " + wC.getFinals().getWinner());
		JPanel panel6 = new JPanel();
		panel6.setLayout(new BorderLayout());
		panel6.add(label6, BorderLayout.CENTER);
		
		setLayout(new GridLayout(6, 1));
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		add(panel5);
		add(panel6);
		return this;
	}
	public JTable initTable(JTable table, Game[] finals) {
		String data[][] = new String[finals.length][9];
		String columns[] = {"Match","Date","Time", "Venue","","","Result","",""};
		for (int i = 0; i < data.length; i++) {
			data[i][0] = "" + finals[i].getIntId();
			data[i][1] = finals[i].getDate();
			data[i][2] = finals[i].getTime();
			data[i][3] = finals[i].getLocation();
			data[i][4] = "";
			data[i][5] = finals[i].getTeamH().getStrName();
			data[i][6] = finals[i].getGoalsH() + " - " + finals[i].getGoalsG();
			data[i][7]= finals[i].getTeamG().getStrName();
		}
		table = new JTable(data, columns);
		for (int i = 0; i < columns.length; i++) {
			if(i == 1 || i == 2 || i == 6)
				table.getColumnModel().getColumn(i).setPreferredWidth(25);
			else if (i == 4 || i == 8)
				table.getColumnModel().getColumn(i).setPreferredWidth(10);
		}
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				final JLabel c = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				c.setBackground(row % 2 == 0 ? Color.white : Color.CYAN);
				if(column == 4) {
					Icon pic = CtrlGroup.getFlagIcon(finals[row].getTeamH().getStrName());
					c.setIcon(pic);
				}
				else if (column == 8) {
					Icon pic = CtrlGroup.getFlagIcon(finals[row].getTeamG().getStrName());
					c.setIcon(pic);
				}
				else {
					c.setIcon(null);
				}
				c.setHorizontalAlignment(JLabel.CENTER);
				return c;
			}
		};
		table.setDefaultRenderer(Object.class, renderer);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				new EditGameDialog(finals[e.getFirstIndex()], null, finalsPanel);
			}
		});
		table.setDefaultEditor(Object.class, new TableCellEditor() {
			@Override
			public boolean stopCellEditing() {
				return false;
			}
			
			@Override
			public boolean shouldSelectCell(EventObject anEvent) {
				return false;
			}
			
			@Override
			public void removeCellEditorListener(CellEditorListener l) {
				
			}
			
			@Override
			public boolean isCellEditable(EventObject anEvent) {
				return false;
			}
			
			@Override
			public Object getCellEditorValue() {
				return null;
			}
			
			@Override
			public void cancelCellEditing() {
				
			}
			
			@Override
			public void addCellEditorListener(CellEditorListener l) {
				
			}
			
			@Override
			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
				return null;
			}
		});
		return table;
		
	}
	
	
	public JLabel getLabel1() {
		return label1;
	}
	public JLabel getLabel2() {
		return label2;
	}
	public JLabel getLabel3() {
		return label3;
	}
	public JLabel getLabel4() {
		return label4;
	}
	public JLabel getLabel5() {
		return label5;
	}
	public Game[] getRoundOf16() {
		return roundOf16;
	}
	public Game[] getQuarterFinals() {
		return quarterFinals;
	}
	public Game[] getSemiFinals() {
		return semiFinals;
	}
	public Game[] getFinalGame() {
		return finalGame;
	}
	public Game[] getThirdGame() {
		return thirdGame;
	}
	public JTable getTable1() {
		return table1;
	}
	public JTable getTable2() {
		return table2;
	}
	public JTable getTable3() {
		return table3;
	}
	public JTable getTable4() {
		return table4;
	}
	public JTable getTable5() {
		return table5;
	}
	public void setRoundOf16(Game[] roundOf16) {
		this.roundOf16 = roundOf16;
	}
	public void setQuarterFinals(Game[] quarterFinals) {
		this.quarterFinals = quarterFinals;
	}
	public void setSemiFinals(Game[] semiFinals) {
		this.semiFinals = semiFinals;
	}
	public void setFinalGame(Game[] finalGame) {
		this.finalGame = finalGame;
	}
	public void setThirdGame(Game[] thirdGame) {
		this.thirdGame = thirdGame;
	}
	public void setTable1(JTable table1) {
		this.table1 = table1;
	}
	public void setTable2(JTable table2) {
		this.table2 = table2;
	}
	public void setTable3(JTable table3) {
		this.table3 = table3;
	}
	public void setTable4(JTable table4) {
		this.table4 = table4;
	}
	public void setTable5(JTable table5) {
		this.table5 = table5;
	}
	
	
}
