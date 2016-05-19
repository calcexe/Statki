package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorListener;
import javax.swing.text.DefaultCaret;

import extras.GameManager;
import extras.InitMap;
import extras.Player;
import extras.Ship;
import extras.ShipSize;
import listeners.MapListener;
import listeners.PlayGameMapListener;
import listeners.PlayGameSmallMapListener;
import listeners.ShipButtonsListener;
import listeners.StartGameListener;
import listeners.StartGameMapListener;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Policy;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class PlayGameFrame extends JFrame {

	private JPanel mainPanel;
	private JLabel[][] mapLabels;
	private JLabel[][] smallMapLabels;
	private MapListener mapListener;
	private MapListener smallMapListener;
	private Player player;
	private JTextArea txtrOczekiwanieNaPrzeciwnika;
	private GameManager manager;

	public PlayGameFrame(Player player) {

		manager = new GameManager(player, this);

		
		mapListener = new PlayGameMapListener(this, manager);		
		smallMapListener = new PlayGameSmallMapListener(this);
		
		mapLabels = new JLabel[mapListener.getMapSize()][mapListener.getMapSize()];
		this.player = player;
		initComponents();
		
		Thread thread = new Thread(manager);
		thread.start();
	}

	private void initComponents() {

		setTitle("Statki");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 749, 420);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Gra");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Poddaj");
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem = new JMenuItem("Zako\u0144cz");
		mnNewMenu.add(mntmNewMenuItem);

		JMenu mnOProgramie = new JMenu("Pomoc");
		menuBar.add(mnOProgramie);

		JMenuItem mntmPomoc = new JMenuItem("Zasady");
		mnOProgramie.add(mntmPomoc);

		JMenuItem mntmPomoc_1 = new JMenuItem("Pomoc");
		mnOProgramie.add(mntmPomoc_1);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("O programie");
		mnOProgramie.add(mntmNewMenuItem_1);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel leftPanel = new JPanel();
		mainPanel.add(leftPanel);
		leftPanel.setLayout(null);

		JLabel gridLabel = new JLabel("");
		gridLabel.setVerticalAlignment(SwingConstants.TOP);
		gridLabel.setHorizontalAlignment(SwingConstants.LEFT);
		gridLabel.setIgnoreRepaint(true);
		gridLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		gridLabel.setIcon(new ImageIcon(StartGame.class.getResource("/images/grid.png")));
		gridLabel.setBounds(0, 0, 356, 344);
		leftPanel.add(gridLabel);

		InitMap initMap = mapListener.generateMap(30, 1, new Point(1, 1));
		for (JLabel gridLabels : initMap.getLabels())
			leftPanel.add(gridLabels);

		for (JLabel[] gridLabels : initMap.getMap())
			for (JLabel lbl : gridLabels)
				leftPanel.add(lbl);

		this.mapLabels = initMap.getMap();

		JPanel rightPanel = new JPanel();
		mainPanel.add(rightPanel);
		rightPanel.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel panel_1 = new JPanel();
		rightPanel.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 2, 0, 0));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);

		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);

		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);

		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);

		JPanel panel = new JPanel();
		rightPanel.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_6 = new JPanel();
		panel.add(panel_6);
		panel_6.setLayout(null);

		InitMap initSmallMap = smallMapListener.generateMap(15, 1, new Point(1, 2));
		for (JLabel smallGridLabels : initSmallMap.getLabels())
			panel_6.add(smallGridLabels);
		for (JLabel[] gridLabels : initSmallMap.getMap())
			for (JLabel lbl : gridLabels)
				panel_6.add(lbl);
		
		smallMapLabels = initSmallMap.getMap();

		for (int i = 0; i < smallMapListener.getMapSize(); i++){
			for (int j = 0; j < smallMapListener.getMapSize(); j++){
				if (!player.getShipAt(i, j).isEmpty()){
					smallMapLabels[i][j].setBackground(Color.LIGHT_GRAY);
				}
			}
		}
		
		JLabel smallGridLabel = new JLabel("");
		smallGridLabel.setBounds(0, 1, 177, 177);
		smallGridLabel.setVerticalAlignment(SwingConstants.TOP);
		smallGridLabel.setHorizontalAlignment(SwingConstants.LEFT);
		smallGridLabel.setIcon(new ImageIcon(PlayGameFrame.class.getResource("/images/small_grid.png")));
		panel_6.add(smallGridLabel);

		JPanel panel_7 = new JPanel();
		panel.add(panel_7);
		panel_7.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_7.add(scrollPane);
		
		txtrOczekiwanieNaPrzeciwnika = new JTextArea();
		txtrOczekiwanieNaPrzeciwnika.setText("Oczekiwanie na przeciwnika...\r\n");
		txtrOczekiwanieNaPrzeciwnika.setFont(new Font("Monospaced", Font.PLAIN, 9));
		scrollPane.setViewportView(txtrOczekiwanieNaPrzeciwnika);
		txtrOczekiwanieNaPrzeciwnika.setEditable(false);
		((DefaultCaret) txtrOczekiwanieNaPrzeciwnika.getCaret()).setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
	}

	public JLabel[][] getMapLabels() {
		return mapLabels;
	}

	public JLabel getLabel(Point point) {
		return mapLabels[point.x][point.y];
	}
	
	public JLabel getSmallLabel(Point point){
		return smallMapLabels[point.x][point.y];
	}
	
	public void addCommunicate(String text){
		txtrOczekiwanieNaPrzeciwnika.setText(txtrOczekiwanieNaPrzeciwnika.getText() + text);
	}
	
	public MapListener getMapListener(){
		return mapListener;
	}
}
