package extras;

import java.io.IOException;

import javax.sound.midi.MidiDevice.Info;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import frames.MenuFrame;
import frames.PlayGameFrame;
import listeners.PlayGameMapListener;

public class GameManager implements Runnable {

	Player player;
	PlayGameFrame parent;
	boolean playerMove = false;
	boolean lostConection = false;

	public GameManager(Player player, PlayGameFrame parent) {
		this.player = player;
		this.parent = parent;
		if (player.isServer())
			try {
				player.setMove(new MoveData(Code.START));
			}
			catch (IOException e) {
				conectionLost();
			}
	}

	public void makeMove(MoveData moveData) {
		try {
			player.setMove(moveData);
		}
		catch (IOException e) {
			conectionLost();
		}
	}

	@Override
	public void run() {
		MoveData moveData;
		try {
			while ((moveData = player.getMove()) != null) {
				StringBuilder builder = new StringBuilder();
				switch (moveData.getCode()) {
				case MOVE:
					playerMove = true;
					builder.append("Przeciwnik: ").append((char) ('A' + moveData.getPoint().x))
							.append(moveData.getPoint().y + 1);
					if (player.getShipAt(moveData.getPoint().x, moveData.getPoint().y).isEmpty()) {
						moveData = new MoveData(Code.MISS, moveData.getPoint());
						builder.append(" - pud³o.\n");
						parent.getSmallLabel(moveData.getPoint()).setText("-");
					}
					else {
						player.setScore(player.getScore() + 1);

						if (player.getScore() < parent.getMapListener().getMaxCapacity())
							moveData = new MoveData(Code.HIT, moveData.getPoint());
						else
							moveData = new MoveData(Code.WIN, moveData.getPoint());

						builder.append(" - trafiony.\n");
						parent.getSmallLabel(moveData.getPoint()).setText("x");

					}
					parent.addCommunicate(builder.toString());
					player.setMove(moveData);
					break;
				case HIT:
					//playerMove = false;
					builder.append("Mój ruch: ").append((char) ('A' + moveData.getPoint().x))
							.append(moveData.getPoint().y + 1).append(" - trafiony.\n");
					parent.addCommunicate(builder.toString());

					parent.getLabel(moveData.getPoint()).setText("x");
					break;
				case MISS:
					//playerMove = false;
					builder.append("Mój ruch: ").append((char) ('A' + moveData.getPoint().x))
							.append(moveData.getPoint().y + 1).append(" - pud³o.\n");
					parent.addCommunicate(builder.toString());

					parent.getLabel(moveData.getPoint()).setText("-");
					break;

				case WIN:
					playerMove = false;
					parent.getLabel(moveData.getPoint()).setText("x");
					player.setMove(new MoveData(Code.LOSE));
					JOptionPane.showMessageDialog(parent, "Wygra³eœ!", "Wygrana", JOptionPane.INFORMATION_MESSAGE);
					parent.dispose();
					new MenuFrame().setVisible(true);
					break;

				case LOSE:
					playerMove = false;
					JOptionPane.showMessageDialog(parent, "Przegra³eœ!", "Przegrana", JOptionPane.INFORMATION_MESSAGE);
					parent.dispose();
					new MenuFrame().setVisible(true);
					break;

				case START:
					if (!player.isServer()) {
						playerMove = false;
						makeMove(new MoveData(Code.START));
					}
					else {
						parent.addCommunicate("Twój ruch!\n");
						playerMove = true;
					}
					break;
				}
			}
		}
		catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(parent, "Niestety wyst¹pi³ b³¹d:\n" + e.getMessage(), "B³¹d",
					JOptionPane.ERROR_MESSAGE);
		}
		catch (IOException e) {
			conectionLost();
		}
	}

	public boolean isPlayerMove() {
		return playerMove;
	}

	public void changePlayerMove(){
		if (playerMove)
			playerMove = false;
		else
			playerMove = true;
	}
	
	private void conectionLost() {
		
		if (!lostConection) {
			JOptionPane.showMessageDialog(parent, "Utracono po³¹czenie!", "B³¹d", JOptionPane.ERROR_MESSAGE);
			lostConection = true;
			new MenuFrame().setVisible(true);
		}
		parent.dispose();
	}

}
