package myUberCore;

public class UberVan implements Ride{
	double length; //Ride length in km
	String trafficKind; 
	public Driver driver;
	public Customer customer;
	
	
	public UberVan(Customer customer, String trafficKind, double length) {
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
				trafficRate = 1.5;
			case "HEAVY":
				trafficRate = 1.8;}
		double basicRate = 0;
		if (length <5) { basicRate = 6.2;}
		else{
			if (10 > length)  { basicRate = 7.7;}
			else {
				if (length <20) { basicRate = 3.25;}
			else {basicRate = 2.6;}}
		}
		
		return basicRate * trafficRate * length;
	}
	

}
