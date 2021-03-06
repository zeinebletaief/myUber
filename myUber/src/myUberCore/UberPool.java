package myUberCore;

public class UberPool implements Ride {
	double length; //Ride length in km
	String trafficKind; 
	public Driver driver;
	public Customer customer;
	
	
	public UberPool(Customer customer, String trafficKind, double length) {
		super();
		this.length = length;
		this.trafficKind = trafficKind;
		this.customer = customer;
	}




	@Override
	public double rideFare() {
		double trafficRate = 1;
		switch(trafficKind.toUpperCase()) {
			case "MEDIUM":
				trafficRate = 1.1;
			case "HEAVY":
				trafficRate = 1.2;}
		double basicRate = 0;
		if (length <5) { basicRate = 2.4;}
		else{
			if (10 > length)  { basicRate = 3;}
			else {
				if (length <20) { basicRate = 1.3;}
			else {basicRate = 1.1;}}
		}
		
		return basicRate * trafficRate * length;
	}
	

}
package myUberCore;

public class UberPool implements Ride {
	double length; //Ride length in km
	public Driver driver;
	public Customer customer;
	static Car car = new Standard(); //is this attribute necessary? 
	public double[] trafficState = new double[3]; //holds the traffic probability of each state (low, medium & high)
	
//constructor 	
	public UberPool(Customer customer, double[] trafficState , double length) {
		super();
		this.length = length;
		this.customer = customer;
		this.trafficState = trafficState;
	}

	public UberPool(Customer customer) {
		super();
		this.customer = customer;
	}




	@Override
	public double rideFare() {
		double trafficRate = 1 * trafficState[0] + 1.1 * trafficState[1] + 1.2 * trafficState[2];
		double basicRate = 0;
		if (length <5) { basicRate = 2.4;}
		else{
			if (10 > length)  { basicRate = 3;}
			else {
				if (length <20) { basicRate = 1.3;}
			else {basicRate = 1.1;}}
		}
		
		return basicRate * trafficRate * length;
	}




	@Override
	public void rideSearch() {
		// TODO Auto-generated method stub
		
	}
	

}
