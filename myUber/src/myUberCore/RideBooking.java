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
	}package myUberCore;

import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class RideBooking extends Observable {

	private Customer user;
	private GPS start;
	private GPS destination;
	public BookingStatus status;
	private Driver driver;
	public MyUber myUber;
	private double length; 
	public String ridePreference; 
	//private Ride ride; //necessary?
	private boolean changed ; //to notify customer and driver about status change(i.e. booking confirmed) 
	private ArrayList<Observer> observers = new ArrayList<Observer>(); 
	
//constructors
	public RideBooking(Customer user, GPS start, GPS destination, MyUber uberNetwork ) {
		super();
		this.user = user;
		this.start = start;
		this.destination = destination;
		this.status = BookingStatus.unconfirmed ; 
		this.myUber = uberNetwork; 
		this.changed = false;
		this.addObserver(user);
		this.ridePreference = null; 
	}

	public RideBooking(Customer user, GPS destination, MyUber uberNetwork) {
		super();
		this.user = user;
		this.destination = destination;
		this.start = user.cusPosition;
		this.status = BookingStatus.unconfirmed;
		this.myUber = uberNetwork; 
		this.changed = false; 
		this.addObserver(user);
		this.ridePreference = null; 
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

	public void setDriver(Driver driver) {
		this.driver = driver;
	} 
	
	//setting length using the start and destination GPS
	public void setLength() {
		this.length = destination.distance(start);
	
	}

	public void setRidePreference(String Type) {
		this.ridePreference = Type; 
	}
	
	//*****************************************************************//
//	Methods 							   //
//*****************************************************************//	

 public ArrayList<Double> estimateFares(){               //method that sends back price list 
	 ArrayList<Double> fares = new ArrayList<Double> ();
	 UberBlack black = new UberBlack(this.user, this.getTrafficState(), this.length);
	 fares.add(black.rideFare());
	 UberPool pool = new UberPool(this.user, this.getTrafficState(), this.length);	 
	 fares.add(pool.rideFare());
	 UberVan van = new UberVan(this.user, this.getTrafficState(), this.length);
	 fares.add(van.rideFare());
	 UberX x = new UberX(this.user, this.getTrafficState(), this.length);
	 fares.add(x.rideFare());
	 //addPrint(fares) //TODO
	 return fares;
	 	 
 } //this list needs to be communicated to customer (?)

 public double[] getTrafficState() { //list holding traffic probability at current time 
	double[] trafficState = new double[3];
	Calendar cal = this.myUber.getCurrentTime();
	int time = cal.get(Calendar.HOUR_OF_DAY);
	if (time>=7 && time <= 10) {
		trafficState[0] = 0.05; trafficState[1] = 0.2; trafficState[2] = 0.75;  
	}
	if (time >= 11 && time <= 16){
		trafficState[0] = 0.15; trafficState[1] = 0.75; trafficState[2] = 0.15;
	}
	if (time >= 17 && time <= 21) {
		trafficState[0] = 0.1; trafficState[1] = 0.4; trafficState[2] = 0.95;
	}
	if ((time >= 0 && time<= 6) || (time>= 22)) {
		trafficState[0] = 0.95; trafficState[1] = 0.4; trafficState[2] = 0.1;
	}
	return trafficState;
 
 }
 
 
 
 // method that sends back nearby cars given  ride preference 
public void carSearch(){ 
	if (this.ridePreference != null ) {
		CarSearch search = new CarSearch();
		search.createCarSearch(this.ridePreference, this);
	//not finished TODO
	}
}


//calculating mean Ride duration in hours  //verify!!! 
public double duration(double lenght) {
	double[] averageSpeed = {15, 7.5, 3};
	double duration = 0;
	for (int i =0 ; i<=3; i++) {
		duration += this.getTrafficState()[i] * averageSpeed[i];
	}
	return duration ;
}


//observer pattern 
/**
 * Observable functions 
 */

//notifying observers (customers, drivers... )
//notifying customers is sending back price list, notifying about ride confirmation
//notifying drivers is about ride offer, driver can accept or not 

	public  void addObserver(Observer o) {
		this.observers.add(o);
	}

	public void notifyObservers() {
		if (this.changed) {
			for (Observer ob:observers) {
				ob.update(this, this.status);
			this.changed = false;	
			}
		}
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
		this.changed = true;
		this.notifyObservers();
	}
	
	protected  void setChanged() { //how do we define change, it depends on the observer
	    }

	
	
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
