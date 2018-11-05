package myUberCore;

public class GPS {
	public double x;
	public double y;

	public GPS(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public GPS() {
		// TODO Auto-generated constructor stub
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public double distance(GPS point) {
		return Math.sqrt(Math.pow(this.getX() - point.getX(), 2)+
				Math.pow(this.getY()-point.getY(), 2));
	}

}
