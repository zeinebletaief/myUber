package myUberCore;

public abstract class Balance {
	private long numRides;
	private Duration time;
	private double money;
	public long getNumRides() {
		return numRides;
	}
	public void setNumRides(long numRides) {
		this.numRides = numRides;
	}
	public Duration getTime() {
		return time;
	}
	public void setTime(Duration time) {
		this.time = time;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	//methods
	public void addRide(double cost,Duration duration) {
		this.setNumRides(this.getNumRides()+1);
		this.setMoney(this.getMoney()+cost);
		this.setTime(this.getTime().add(duration));
	}
}
