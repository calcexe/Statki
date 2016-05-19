package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import extras.Player;
import frames.PlayGameFrame;
import frames.StartGame;

public class StartGameListener implements ActionListener {

	StartGame parent;
	Player player;
	
	public StartGameListener(StartGame parent, Player player) {
		this.parent = parent;
		this.player = player;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		player.setMap(parent.getMapListener().getMap());
		
		new PlayGameFrame(player).setVisible(true);
		
		parent.dispose();
	}

}
