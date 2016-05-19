package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;

import extras.ShipSize;
import extras.ShipsCapacity;

public class ShipButtonsListener implements ActionListener {

	private StartGameMapListener mapListener;

	public ShipButtonsListener(StartGameMapListener mapListener) {
		this.mapListener = mapListener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ShipSize size = (ShipSize) ((JButton) e.getSource()).getClientProperty("SIZE");

		if (mapListener.getShipCount(size) < ShipsCapacity.getShipCapacity(size)) {
			mapListener.setSize(size);
		}

	}

}
