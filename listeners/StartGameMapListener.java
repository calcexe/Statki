package listeners;

import java.awt.Button;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import extras.ShipDirection;
import extras.ShipSize;
import extras.ShipsCapacity;
import frames.StartGame;

public class StartGameMapListener extends MapListener {

	private HashMap<ShipSize, Integer> shipsCount;
	private ShipSize size = ShipSize.ZERO;
	private ShipDirection direction = ShipDirection.HORIZONTAL;

	public StartGameMapListener(Object parent) {
		super(parent);

		shipsCount = new HashMap<>();
		for (int i = 0; i < ShipSize.values().length - 1; i++) {
			shipsCount.put(ShipSize.values()[i + 1], new Integer(0));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Point point = (Point) ((JLabel) e.getSource()).getClientProperty("POINT");
		if (direction == ShipDirection.VERTICAL) {
			if (point.y + size.getValue() > MAPSIZE)
				return;

			for (int i = 0; i < size.getValue(); i++)
				if (!map[point.x][point.y + i].isEmpty())
					return;

			for (int i = 0; i < size.getValue(); i++)
				((StartGame) parent).getLabel(new Point(point.x, point.y + i)).setBackground(Color.LIGHT_GRAY);
		}
		else {
			if (point.x + size.getValue() > MAPSIZE)
				return;

			for (int i = 0; i < size.getValue(); i++)
				if (!map[point.x + i][point.y].isEmpty())
					return;

			for (int i = 0; i < size.getValue(); i++)
				((StartGame) parent).getLabel(new Point(point.x + i, point.y)).setBackground(Color.LIGHT_GRAY);
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		Point point = (Point) ((JLabel) e.getSource()).getClientProperty("POINT");

		if (direction == ShipDirection.VERTICAL) {
			if (point.y + size.getValue() > MAPSIZE)
				return;

			for (int i = 0; i < size.getValue(); i++)
				if (!map[point.x][point.y + i].isEmpty())
					return;

			for (int i = 0; i < size.getValue(); i++)
				((StartGame) parent).getLabel(new Point(point.x, point.y + i)).setBackground(null);
		}
		else {
			if (point.x + size.getValue() > MAPSIZE)
				return;

			for (int i = 0; i < size.getValue(); i++)
				if (!map[point.x + i][point.y].isEmpty())
					return;

			for (int i = 0; i < size.getValue(); i++)
				((StartGame) parent).getLabel(new Point(point.x + i, point.y)).setBackground(null);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

		// RMB
		if (e.getButton() == MouseEvent.BUTTON3) {

			mouseExited(e);

			if (direction == ShipDirection.HORIZONTAL)
				direction = ShipDirection.VERTICAL;
			else
				direction = ShipDirection.HORIZONTAL;

			mouseEntered(e);

			return;

		}

		Point point = (Point) ((JLabel) e.getSource()).getClientProperty("POINT");

		if (!map[point.x][point.y].isEmpty() && map[point.x][point.y].getPart() == 1) {

			ShipSize tempSize = map[point.x][point.y].getSize();
			if (map[point.x][point.y].getDirection() == ShipDirection.VERTICAL){
				for(int i = 0; i < tempSize.getValue(); i++){
					map[point.x][point.y + i].setDirection(null);
					map[point.x][point.y + i].setPart(0);
					map[point.x][point.y + i].setSize(ShipSize.ZERO);
					((StartGame) parent).getLabel(new Point(point.x, point.y + i)).setBackground(null);
				}
			}
			else{
				for(int i = 0; i < tempSize.getValue(); i++){
					map[point.x + i][point.y].setDirection(null);
					map[point.x + i][point.y].setPart(0);
					map[point.x + i][point.y].setSize(ShipSize.ZERO);
					((StartGame) parent).getLabel(new Point(point.x + i, point.y)).setBackground(null);
				}
			}
			

			capacity -= tempSize.getValue();
			if (capacity != MAX_CAPACITY)
				((StartGame) parent).getStartGameButton().setEnabled(false);

			shipsCount.put(tempSize, shipsCount.get(tempSize).intValue() - 1);
			if (shipsCount.get(tempSize) < ShipsCapacity.getShipCapacity(tempSize)) {
				switch (tempSize) {
				case ONE:
					((StartGame) parent).getOneMastedButton().setEnabled(true);
					break;

				case TWO:
					((StartGame) parent).getTwoMastedButton().setEnabled(true);
					break;

				case THREE:
					((StartGame) parent).getThreeMastedButton().setEnabled(true);
					break;

				case FOUR:
					((StartGame) parent).getFourMastedButton().setEnabled(true);
					break;

				default:
					break;
				}

				size = ShipSize.ZERO;
			}
			
			return;
		}

		if (size == ShipSize.ZERO)
			return;
		
		if (direction == ShipDirection.VERTICAL) {

			if (point.y + size.getValue() > MAPSIZE)
				return;

			for (int i = 0; i < size.getValue(); i++)
				if (!map[point.x][point.y + i].isEmpty())
					return;

			for (int i = 0; i < size.getValue(); i++) {
				map[point.x][point.y + i].setDirection(direction);
				map[point.x][point.y + i].setSize(size);
				map[point.x][point.y + i].setPart(i + 1);
				((StartGame) parent).getLabel(new Point(point.x, point.y + i)).setBackground(Color.GRAY);
			}
		}
		else {
			if (point.x + size.getValue() > MAPSIZE)
				return;

			for (int i = 0; i < size.getValue(); i++)
				if (!map[point.x + i][point.y].isEmpty())
					return;

			for (int i = 0; i < size.getValue(); i++) {
				map[point.x + i][point.y].setDirection(direction);
				map[point.x + i][point.y].setSize(size);
				map[point.x + i][point.y].setPart(i + 1);
				((StartGame) parent).getLabel(new Point(point.x + i, point.y)).setBackground(Color.GRAY);
			}
		}

		capacity += size.getValue();
		if (capacity == MAX_CAPACITY)
			((StartGame) parent).getStartGameButton().setEnabled(true);

		shipsCount.put(size, shipsCount.get(size).intValue() + 1);
		if (shipsCount.get(size) == ShipsCapacity.getShipCapacity(size)) {
			switch (size) {
			case ONE:
				((StartGame) parent).getOneMastedButton().setEnabled(false);
				break;

			case TWO:
				((StartGame) parent).getTwoMastedButton().setEnabled(false);
				break;

			case THREE:
				((StartGame) parent).getThreeMastedButton().setEnabled(false);
				break;

			case FOUR:
				((StartGame) parent).getFourMastedButton().setEnabled(false);
				break;

			default:
				break;
			}

			size = ShipSize.ZERO;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public int getShipCount(ShipSize size) {
		return shipsCount.get(size);
	}

	public ShipSize getSize() {
		return size;
	}

	public void setSize(ShipSize size) {
		this.size = size;
	}
}
