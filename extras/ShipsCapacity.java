package extras;

import java.util.HashMap;

public class ShipsCapacity {
	private static HashMap<ShipSize, Integer> shipsCapacity = new HashMap<ShipSize, Integer>() {
		{this.put(ShipSize.ONE, 5);}
		{this.put(ShipSize.TWO, 4);}
		{this.put(ShipSize.THREE, 3);}
		{this.put(ShipSize.FOUR, 2);}

	};

	public static int getShipCapacity(ShipSize size) {
		return shipsCapacity.get(size).intValue();
	}
}