package myUberCore;

public abstract class Cost {
	private double length; //Ride length in km
	private TrafficState trafficKind;
	
	//getters&setters
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public TrafficState getTrafficKind() {
		return trafficKind;
	}
	public void setTrafficKind(TrafficState trafficKind) {
		this.trafficKind = trafficKind;
	}
	//Methods
	public abstract double rideFare();
	
	//toString
	public String toString() {
		return String.format ("%.2f", this.rideFare())+"€";
	}
	
}
