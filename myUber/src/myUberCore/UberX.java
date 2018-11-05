package myUberCore;

public class UberX implements Ride{
	double length; //Ride length in
	String trafficKind; 
	public Driver driver;
	public Customer customer;
	
	
	public UberX(Customer customer, String trafficKind,  double length) {
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
				trafficRate = 1.5;}
		double basicRate = 0;
		if (length <5) { basicRate = 3.3;}
		else{
			if (10 > length)  { basicRate = 4.2;}
			else {
				if (length <20) { basicRate = 1.91;}
			else {basicRate = 1.5;}}
		}
		
		return basicRate * trafficRate * length;
	}
	
	}


