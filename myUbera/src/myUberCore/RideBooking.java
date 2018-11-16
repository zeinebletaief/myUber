package myUberCore;

import java.util.ArrayList;

public class RideBooking {
	private Customer user;
	private GPS destination;
	private int seats;
	private Time requestTime;
	private TrafficState traffic;
	public String ridePreference; 
	
//constructors
	public RideBooking(Customer user, GPS destination,Time requesttime,TrafficState traffic,int seats) {
		this.user = user;
		this.destination = destination;
		this.requestTime=requesttime;
		this.traffic=traffic;
		this.seats=seats;
		this.ridePreference = null; 
	}

	//getters & setters 
    
    public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public Customer getUser() {
		return user;
	}

	public void setUser(Customer user) {
		this.user = user;
	}

	public GPS getDestination() {
		return destination;
	}

	public void setDestination(GPS destination) {
		this.destination = destination;
	}

	public Time getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Time requestTime) {
		this.requestTime = requestTime;
	}

	public TrafficState getTraffic() {
		return traffic;
	}

	public void setTraffic(TrafficState traffic) {
		this.traffic = traffic;
	}

	public String getRidePreference() {
		return ridePreference;
	}

	public void setRidePreference(String ridePreference) {
		this.ridePreference = ridePreference;
	}
	
//*****************************************************************//
//	Methods 							   //
//*****************************************************************//	
    protected void Print(ArrayList<Double> fares) { //affichage de la fare list
    	String ch="";
    	ch=ch+"UberPool :"+String.format ("%.2f", fares.get(1))+"€ | ";
    	ch=ch+"UberX :"+String.format ("%.2f", fares.get(3))+"€ | ";
    	ch=ch+"UberBlack :"+String.format ("%.2f", fares.get(0))+"€ | ";
    	ch=ch+"UberVan :"+String.format ("%.2f", fares.get(2))+"€ | ";
    	System.out.println(ch);
    }
	public double getDistance() { //distance de la reservation pour calculer les frais 
		return this.getDestination().distance(this.getUser().getCusPosition());
	}
	public ArrayList<Double> estimateFares(){ //method that sends back price list 
	    ArrayList<Double> fares = new ArrayList<Double> ();
	    UberBlackCost black = new UberBlackCost(this.getTraffic(), this.getDistance());
	    fares.add(black.rideFare());
	    UberPoolCost pool = new UberPoolCost(this.getTraffic(), this.getDistance());	 
	    fares.add(pool.rideFare());
	    UberVanCost van = new UberVanCost(this.getTraffic(), this.getDistance());
	    fares.add(van.rideFare());
	    UberXCost x = new UberXCost(this.getTraffic(), this.getDistance());
	    fares.add(x.rideFare());
	    this.Print(fares);
	    return fares;
	 	 
 } //this list needs to be communicated to customer
 

//calculating mean Ride duration in seconds
   public Duration rideDuration() {
	    double length = this.getDistance();
	    double speed=0;
	    switch(this.getTraffic()) {
	    case Low:
		    speed = 15;break;
		case Medium:
			speed = 7.5;break;
		case High:
			speed = 3;break;
	    }
		Duration duree = new Duration((int)(3600*(length/speed)));
		return duree ;
	}

}
