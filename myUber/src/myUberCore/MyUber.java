package myUberCore;

import java.util.ArrayList;

public class MyUber {
	private ArrayList<Car> cars;
	private ArrayList<Driver> drivers;
	private ArrayList<Customer> customers;
	private BookOfRides ridesHistory;
	
//constructors  
	public MyUber() {
		this.cars = new ArrayList<Car>();
		this.customers = new ArrayList<Customer>();
		this.drivers = new ArrayList<Driver>();
		this.ridesHistory = new BookOfRides();
		
	}
	
	public MyUber(long Nc,long Nd,long Nu) {
		this.cars = new ArrayList<Car>();
		this.customers = new ArrayList<Customer>();
		this.drivers = new ArrayList<Driver>();
		this.ridesHistory = new BookOfRides();
		
	}

	public MyUber(ArrayList<Car> cars, ArrayList<Driver> drivers, ArrayList<Customer> customers) {
		super();
		this.cars = cars;
		this.drivers = drivers;
		this.customers = customers;
	}

//getters & setters 

	public ArrayList<Car> getCars() {
		return cars;
	}

	public ArrayList<Driver> getDrivers() {
		return drivers;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public BookOfRides getRidesHistory() {
		return ridesHistory;
	}

	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}

	public void setDrivers(ArrayList<Driver> drivers) {
		this.drivers = drivers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	public void setRidesHistory(BookOfRides ridesHistory) {
		this.ridesHistory = ridesHistory;
	}

	
	//*****************************************************************//
//	Methods 							   //
//*****************************************************************//	

	public void addCustomer() {
		
	}
	
	public void addCar() {
		
	}
	
	public void addDriver() {
		
	}

	public void addRide() {
		
	}

	
}
