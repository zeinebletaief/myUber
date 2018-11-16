package myUberCore;

public class DriverBalance extends Balance{
	private double drivingTime;
	//Constructor
	public DriverBalance() {
		this.setNumRides(0);
		this.setMoney(0);
		this.setTime(new Duration(0));
		this.drivingTime=0;
	}
	//getters&setters
	public double getDrivingTime() {
		return drivingTime;
	}
	public void setDrivingTime(double drivingTime) {
		this.drivingTime = drivingTime;
	}
	//methods
	public void addDrivingTime(double duration) {
		this.setDrivingTime(this.getDrivingTime()+duration);
	}

}
