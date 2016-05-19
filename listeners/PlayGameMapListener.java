package listeners;

import java.awt.Color;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import extras.Code;
import extras.GameManager;
import extras.MoveData;
import extras.ShipSize;
import frames.PlayGameFrame;

public class PlayGameMapListener extends MapListener {

	private GameManager manager;
	

	public PlayGameMapListener(Object parent, GameManager manager) {
		super(parent);
		this.manager = manager;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Point point = (Point) ((JLabel) e.getSource()).getClientProperty("POINT");
		
		if (!manager.isPlayerMove() || !map[point.x][point.y].isEmpty())
			return;
		
		((PlayGameFrame)parent).getLabel(point).setBackground(Color.LIGHT_GRAY);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Point point = (Point) ((JLabel)e.getSource()).getClientProperty("POINT");
		
		if (!manager.isPlayerMove() || !map[point.x][point.y].isEmpty())
			return;
		
		((PlayGameFrame)parent).getLabel(point).setBackground(null);

	}

	@Override
	public void mousePressed(MouseEvent e) {

		Point point = (Point) ((JLabel) e.getSource()).getClientProperty("POINT");
	
		switch(e.getButton()){
		//left button
		case MouseEvent.BUTTON1:
			if (manager.isPlayerMove()) {
				map[point.x][point.y].setSize(ShipSize.ONE);
				MoveData moveData = new MoveData(Code.MOVE, point);
				manager.changePlayerMove();
				manager.makeMove(moveData);
			}
			break;
			
		//right button
		case MouseEvent.BUTTON3:
			if (map[point.x][point.y].isEmpty()){
				JLabel label = ((PlayGameFrame)parent).getLabel(point);
				if (label.getText().equals("-"))
					label.setText("");
				else
					label.setText("-");
			}
			break;
		}
		


	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
