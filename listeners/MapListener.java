package listeners;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import extras.InitMap;
import extras.Ship;
import extras.ShipSize;

public abstract class MapListener implements MouseListener {
	protected Ship[][] map;
	protected final static int MAPSIZE = 10;
	protected final int MAX_CAPACITY = 30;
	protected int capacity = 0;
	protected Object parent;

	protected MapListener() {
		map = new Ship[MAPSIZE][MAPSIZE];
		for (int i = 0; i < MAPSIZE; i++)
			for (int j = 0; j < MAPSIZE; j++)
				map[i][j] = new Ship();
	}

	public int getMaxCapacity(){
		return MAX_CAPACITY;
	}
	
	public MapListener(Object parent) {
		this();
		this.parent = parent;
	}

	public Ship[][] getMap() {
		return map;
	}

	public int getMapSize() {
		return MAPSIZE;
	}

	public InitMap generateMap(int size, int border, Point start) {

		ArrayList<JLabel> labels = new ArrayList<>();
		int j = 0;
		for (char i = 'A'; i < ('A' + MAPSIZE); i++, j++) {
			JLabel label = new JLabel(i + "");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBounds(start.x + (j + 1) * (size + border), start.y, size, size);
			labels.add(label);
		}

		for (int i = 0; i < MAPSIZE; i++) {
			JLabel label = new JLabel((i + 1) + "");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBounds(start.x, start.y + (i + 1) * (size + border), size, size);
			labels.add(label);
		}

		JLabel map[][] = new JLabel[MAPSIZE][MAPSIZE];
		for (int i = 0; i < MAPSIZE; i++) {
			for (int k = 0; k < MAPSIZE; k++) {
				JLabel label = new JLabel("");
				label.setOpaque(true);
				label.setBounds(start.x + (i + 1) * (size + border), start.y + (k + 1) * (size + border), size, size);
				label.addMouseListener(this);
				label.putClientProperty("POINT", new Point(i, k));
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setHorizontalAlignment(SwingConstants.CENTER);
				map[i][k] = label;
			}
		}

		return new InitMap(labels, map);
	}
}
