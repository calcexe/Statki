package extras;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Player {

	String name = "gracz";
	Socket socket;
	ObjectInputStream input;
	ObjectOutputStream output;

	Ship map[][] = new Ship[10][10];
	private int score = 0;
	private boolean server = false;
	
	public Player(Socket socket, boolean server) throws IOException {
		this.socket = socket;
		this.server = server;
		
		output = new ObjectOutputStream(socket.getOutputStream());
		output.flush();
		input = new ObjectInputStream(socket.getInputStream());

	}

	public void setMove(MoveData moveData) throws IOException {

		output.writeObject(moveData);

	}

	public MoveData getMove() throws ClassNotFoundException, IOException {

		return (MoveData) input.readObject();

	}

	public void close() throws IOException {
		socket.close();
		output.close();
		input.close();
	}

	public Socket getSocket() {
		return socket;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Ship[][] getMap() {
		return map;
	}

	public void setMap(Ship[][] map) {
		this.map = map;
	}
	
	public Ship getShipAt(int x, int y){
		return map[x][y];
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isServer() {
		return server;
	}
	
	
	
}