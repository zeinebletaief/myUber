package myUberCore;

public class UberBlack implements Ride{
	double length; //Ride length in km
	String trafficKind; 
	public Driver driver;
	public Customer customer;
	

	
	//constructor 	
	public UberBlack(Customer customer, String traffic, double length) {
		super();
		this.customer = customer;
		this.trafficKind = traffic;
		this.length = length;
		
	}

//getters & setters
	
	                      



	
	//*****************************************************************//
//	Methods 							   //
//*****************************************************************//	

	@Override
	public double rideFare() {
		double trafficRate = 1;
		switch(trafficKind.toUpperCase()) {
			case "MEDIUM":
				trafficRate = 1.3;
			case "HEAVY":
				trafficRate = 1.6;}
		double basicRate = 0;
		if (length <5) { basicRate = 6.2;}
		else{
			if (10 > length)  { basicRate = 5.5;}
			else {
				if (length <20) { basicRate = 3.25;}
			else {basicRate = 2.6;}}
		}
		
		return basicRate * trafficRate * length;
	}
	}


