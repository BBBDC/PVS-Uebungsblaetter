package swc.gui;

import java.awt.event.*;
import java.io.IOException;
import java.util.Vector;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import swc.ctrl.CtrlFinals;
import swc.ctrl.CtrlGroup;
import swc.data.Final;
import swc.data.Group;
import swc.data.SoccerWC;
import swc.data.Team;

public class Mainframe extends JFrame implements ActionListener{

	private JMenuBar menu;
	private JMenu file;
	private JMenu extra;
	private JMenu help;
	
	private JMenuItem lwc;
	private JMenuItem nwc;
	private JMenuItem save;
	private JMenuItem saveas;
	private JMenuItem exit;
	
	private JMenuItem wcb;
	private JMenuItem load;
	
	private JMenuItem about;
	
	private JTabbedPane tpane;
	
	private CreateDialog cD;
	
	private Group groupA;
	private Group groupB;
	private Group groupC;
	private Group groupD;
	private Group groupE;
	private Group groupF;
	private Group groupG;
	private Group groupH;

	private GroupPanel tabA;
	private GroupPanel tabB;
	private GroupPanel tabC;
	private GroupPanel tabD;
	private GroupPanel tabE;
	private GroupPanel tabF;
	private GroupPanel tabG;
	private GroupPanel tabH;
	private FinalsPanel tabFin;
	
	
	
	private SoccerWC wC;

	
	public Mainframe() {
		wC = new SoccerWC();
		initMainFrame();
		cD = new CreateDialog(this, wC);
	}
	public void initMainFrame() {
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		initMenu();
		nwc.addActionListener(this);
		about.addActionListener(this);
		if(!wC.getGroups().isEmpty() && tpane == null) {
			initTabPane();
			add(tpane);
		}
		setJMenuBar(menu);
		setVisible(true);
	}
	public void initMenu() {
		menu = new JMenuBar();
		file = new JMenu("File");
		extra = new JMenu("Extra");
		help = new JMenu("Help");
		
		lwc = new JMenuItem("Load World Cup");
		nwc = new JMenuItem("New World Cup");
		save = new JMenuItem("Save");
		saveas = new JMenuItem("Save As...");
		exit = new JMenuItem("Exit");
		
		wcb = new JMenuItem("World Cup Betting");
		load = new JMenuItem("Load from Server...");
		
		about = new JMenuItem("About");
		file.add(lwc);
		file.add(nwc);
		file.add(save);
		file.add(saveas);
		file.add(exit);
		
		extra.add(wcb);
		extra.add(load);
		
		help.add(about);
		
		
		menu.add(file);
		menu.add(extra);
		menu.add(help);
	}
	public void initTabPane() {
		tabA = new GroupPanel(wC.getGroups().get(0), 0);
		tabB = new GroupPanel(wC.getGroups().get(1), 1);
		tabC = new GroupPanel(wC.getGroups().get(2),2);
		tabD = new GroupPanel(wC.getGroups().get(3),3);
		tabE = new GroupPanel(wC.getGroups().get(4),4);
		tabF = new GroupPanel(wC.getGroups().get(5),5);
		tabG = new GroupPanel(wC.getGroups().get(6),6);
		tabH = new GroupPanel(wC.getGroups().get(7),7);
		tabFin = new FinalsPanel(wC);

		tpane = new JTabbedPane();
		tpane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				JTabbedPane f = (JTabbedPane)e.getSource();
				if(f.getTitleAt(f.getSelectedIndex()).equals("Finals")) {
					tabFin.removeAll();
					tabFin = tabFin.initFinPanel();
				}
			}
		});
		tpane.addTab("Gruppe A", tabA);
		tpane.addTab("Gruppe B", tabB);
		tpane.addTab("Gruppe C", tabC);
		tpane.addTab("Gruppe D", tabD);
		tpane.addTab("Gruppe E", tabE);
		tpane.addTab("Gruppe F", tabF);
		tpane.addTab("Gruppe G", tabG);
		tpane.addTab("Gruppe H", tabH);
		tpane.addTab("Finals", tabFin);
		
	}

	
	public Group getGroupA() {
		return groupA;
	}
	public Group getGroupB() {
		return groupB;
	}
	public Group getGroupC() {
		return groupC;
	}
	public Group getGroupD() {
		return groupD;
	}
	public Group getGroupE() {
		return groupE;
	}
	public Group getGroupF() {
		return groupF;
	}
	public Group getGroupG() {
		return groupG;
	}
	public Group getGroupH() {
		return groupH;
	}


	public static void main(String[] args) {
		new Mainframe();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem item = (JMenuItem)e.getSource();
		if(item.getText().equals("New World Cup")) {
			cD.setVisible(true);
		}
		if(item.getText().equals("About")) {
			JOptionPane.showMessageDialog(this, "Jonas H‰uﬂler", "About", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
