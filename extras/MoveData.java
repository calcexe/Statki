package extras;
import java.awt.Point;
import java.io.Serializable;

public class MoveData implements Serializable {
	private static final long serialVersionUID = 1L;

	private Code code;
	private Point point;
	
	public MoveData(Code code){
		this.code = code;
		this.point = null;
	}
	
	public MoveData(Code code, Point point){
		this.code = code;
		this.point = point;
	}

	public Code getCode() {
		return code;
	}

	public Point getPoint() {
		return point;
	}
	
}
