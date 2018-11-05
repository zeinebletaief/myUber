package myUberCore;

public class CustomerBalance {
	private long numberOfRides;
	private double totaTime;
	private double totalCharges;
	

	//constructors
	public CustomerBalance() {
		super();
		this.numberOfRides = 0;
		this.totalCharges = 0;
		this.totaTime = 0;
	}


	//getters & setters
	public long getNumberOfRides() {
		return numberOfRides;
	}


	public double getTotaTime() {
		return totaTime;
	}


	public double getTotalCharges() {
		return totalCharges;
	}
	

	

}
