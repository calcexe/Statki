package extras;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

public class InitMap {
	private List<JLabel> labels;
	private JLabel[][] map;
	
	public InitMap(List<JLabel> labels, JLabel[][] map){
		this.labels = labels;
		this.map = map;
	}

	public List<JLabel> getLabels() {
		return labels;
	}

	public JLabel[][] getMap() {
		return map;
	}
	
	
}
