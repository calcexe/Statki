package extras;
public class Ship {
	private ShipSize size = ShipSize.ZERO;
	private ShipDirection direction;
	private int part = 0;

	public Ship(){
		
	}
	
	public Ship(ShipSize size, ShipDirection direction, int part) {
		this.size = size;
		this.direction = direction;
		this.part = part;
	}
	
	public boolean isEmpty() {
		if (size == ShipSize.ZERO)
			return true;
		else
			return false;
	}

	public ShipSize getSize() {
		return size;
	}

	public void setSize(ShipSize size) {
		this.size = size;
	}

	public ShipDirection getDirection() {
		return direction;
	}

	public void setDirection(ShipDirection direction) {
		this.direction = direction;
	}

	public int getPart() {
		return part;
	}

	public void setPart(int part) {
		this.part = part;
	}
	
}
