package myUberCore;

import java.util.ArrayList;
import java.util.Observable;

public class RideBooking extends Observable {

	private Customer user;
	private GPS start;
	private GPS destination;
	public BookingStatus status;
	private Driver driver;
	public String traffic;
	private double length; 

//constructors
	public RideBooking(Customer user, GPS start, GPS destination, String traffic ) {
		super();
		this.user = user;
		this.start = start;
		this.destination = destination;
		this.status = BookingStatus.unconfirmed ; 
		this.traffic = traffic;
	}

	public RideBooking(Customer user, GPS destination, String traffic) {
		super();
		this.user = user;
		this.destination = destination;
		this.start = user.cusPosition;
		this.status = BookingStatus.unconfirmed;
		this.traffic = traffic;

	}

	//getter & setters 

	public Customer getUser() {
		return user;
	}

	public GPS getStart() {
		return start;
	}

	public GPS getDestination() {
		return destination;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setUser(Customer user) {
		this.user = user;
	}

	public void setStart(GPS start) {
		this.start = start;
	}

	public void setDestination(GPS destination) {
		this.destination = destination;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	} 
	
	//setting length using the start and destination GPS
	public void setLength() {
		this.length = destination.distance(start);
	
	}

	//*****************************************************************//
//	Methods 							   //
//*****************************************************************//	

 public ArrayList<Double> estimateFares(){               //method that sends back price list 
	 ArrayList<Double> fares = new ArrayList<Double> ();
	 UberBlack black = new UberBlack(this.user, this.traffic, this.length);
	 fares.add(black.rideFare());
	 UberPool pool = new UberPool(this.user, this.traffic, this.length);	 
	 fares.add(pool.rideFare());
	 UberVan van = new UberVan(this.user, this.traffic, this.length);
	 fares.add(van.rideFare());
	 UberX x = new UberX(this.user, this.traffic, this.length);
	 fares.add(x.rideFare());
	 return fares;
	 
	 
 }

 // method that sends back nearby cars
public ArrayList<Car> nearbyCars(double maxSearchRadius, GPS position , ArrayList<Car> cars, double seats){                
	ArrayList<Car> nearbyCars = new ArrayList<Car>();
	double radius = maxSearchRadius;
	for (Car c : cars) {
		if (c.carPosition.distance(position) < radius ) {
			
		}
	}
	return nearbyCars; 
}
	
	
	
	
}
