package swc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.CellRendererPane;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;


import javafx.scene.control.TableColumn;
import swc.ctrl.CtrlGroup;
import swc.data.Game;
import swc.data.Group;
import swc.data.Team;

public class GroupPanel extends JPanel {
	private JLabel label1;
	private JLabel label2;
	private JTable table1;
	private JTable table2;
	private Vector<Team> teams;
	private int groupNumber;
	private Group group;
	private Game[] games;
	private GroupPanel grpPanel;

	public GroupPanel(Group group, int grpnmbr){
		this.group = group;
		grpPanel = this;
		games = new Game[group.getGames().size()];
		for (int i = 0; i < games.length; i++) {
			games[i] = group.getGames().get(i);
		}
		setLayout(new GridLayout(2, 1));
		label1 = new JLabel("Table for " + group.getStrGroupName());
		label2 = new JLabel("Matches for " + group.getStrGroupName());
		teams = group.getTeams();
		this.groupNumber = grpnmbr;
		if(table1 == null)
			initTable1();
		if(table2 == null)
			initTable2();
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel1.add(label1, BorderLayout.NORTH);
		panel1.add(new JScrollPane(table1), BorderLayout.CENTER);
		panel2.add(label2, BorderLayout.NORTH);
		panel2.add(new JScrollPane(table2), BorderLayout.CENTER);
		add(panel1);
		add(panel2);
	}
	public JTable initTable1() {
		String data[][] = new String[4][11];
		String columns[];
		columns = new String[]{"#","","Team","Played","Won","Draw","Loss","GF","GA","Difference", "Points"};
		java.util.Collections.sort(teams);
		for (int i = 0; i < data.length; i++) {
			data[i][0] = "" + (i+1);
			data[i][1] = "";
			data[i][2] = teams.get(i).getStrName();
			data[i][3] = "" + teams.get(i).getPlayed();
			data[i][4] = "" + teams.get(i).getWon();
			data[i][5] = "" + teams.get(i).getDraw();
			data[i][6] = "" + teams.get(i).getLoss();
			data[i][7] = "" + teams.get(i).getGf();
			data[i][8] = "" + teams.get(i).getGa();
			data[i][9] = "" + (teams.get(i).getGf() - teams.get(i).getGa());
			data[i][10] = "" + teams.get(i).getPoints();
		}
		table1 = new JTable(data, columns);	
		for (int i = 0; i < columns.length; i++) {
			if(i == 1)
				table1.getColumnModel().getColumn(i).setPreferredWidth(20);
			else if(i > 3 && i < 9)
				table1.getColumnModel().getColumn(i).setPreferredWidth(25);
		}
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				final JLabel c = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				c.setBackground(row % 2 == 0 ? Color.white : Color.CYAN);
				if(column == 1) {
					Icon pic = CtrlGroup.getFlagIcon(teams.get(row).getStrName());
					c.setIcon(pic);
				}
				else {
					c.setIcon(null);
				}
				c.setHorizontalAlignment(JLabel.CENTER);
				return c;
			}
		};
		table1.setDefaultRenderer(Object.class, renderer);
		table1.setDefaultEditor(Object.class, new TableCellEditor() {
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
			return table1;
	}
	
	public JTable initTable2() {
		String data[][] = new String[6][9];
		String columns[] = {"Match","Date","Time", "Venue","","","Result","",""};
		for (int i = 0; i < data.length; i++) {
			data[i][0] = "" + games[i].getIntId();
			data[i][1] = games[i].getDate();
			data[i][2] = games[i].getTime();
			data[i][3] = games[i].getLocation();
			data[i][4] = "";
			data[i][5] = games[i].getTeamH().getStrName();
			data[i][6] = games[i].getGoalsH() + " - " + games[i].getGoalsG();
			data[i][7]= games[i].getTeamG().getStrName();
		}
		table2 = new JTable(data, columns);
		for (int i = 0; i < columns.length; i++) {
			if(i == 1 || i == 2 || i == 6)
				table2.getColumnModel().getColumn(i).setPreferredWidth(25);
			else if (i == 4 || i == 8)
				table2.getColumnModel().getColumn(i).setPreferredWidth(10);
		}
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				final JLabel c = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				c.setBackground(row % 2 == 0 ? Color.white : Color.CYAN);
				if(column == 4) {
					Icon pic = CtrlGroup.getFlagIcon(games[row].getTeamH().getStrName());
					c.setIcon(pic);
				}
				else if (column == 8) {
					Icon pic = CtrlGroup.getFlagIcon(games[row].getTeamG().getStrName());
					c.setIcon(pic);
				}
				else {
					c.setIcon(null);
				}
				c.setHorizontalAlignment(JLabel.CENTER);
				return c;
			}
		};
		table2.setDefaultRenderer(Object.class, renderer);
		table2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				new EditGameDialog(games[e.getFirstIndex()], grpPanel, null);
			}
		});
		table2.setDefaultEditor(Object.class, new TableCellEditor() {
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
		return table2;
	}

	public JTable getTable2() {
		return table2;
	}
	public JTable getTable1() {
		return table1;
	}
	public JLabel getLabel1() {
		return label1;
	}
	public JLabel getLabel2() {
		return label2;
	}
	public Group getGroup() {
		return group;
	}
	public int getGroupNumber() {
		return groupNumber;
	}
	public Game[] getGames() {
		return games;
	}
}
