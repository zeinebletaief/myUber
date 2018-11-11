package myUberCore;

public class UberX implements Ride{
	double length; //Ride length in
	public Driver driver;
	public Customer customer;
	static Car car = new Standard();
	public double[] trafficState = new double[3]; //holds the traffic probability of each state (low, medium & high)

	
//constructor 		
	public UberX(Customer customer, double[] trafficState,  double length) {
		super();
		this.length = length;
		this.customer = customer;
		this.trafficState = trafficState;
	}

	public UberX(Customer customer) {
		this.customer = customer;
	}



	@Override
	public double rideFare() {
		double trafficRate = 1 * trafficState[0] + 1.1 * trafficState[1] + 1.5 * trafficState[2];
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



	@Override
	public void rideSearch() {
		// TODO Auto-generated method stub
		
	}
	
	}

