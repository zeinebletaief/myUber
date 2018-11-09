package myUberCore;


//import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Date;

public class MyUber {
	private ArrayList<Car> cars;
	private ArrayList<Driver> drivers;
	private ArrayList<Customer> customers;
	private BookOfRides ridesHistory;
	private Calendar currentTime; 
	
	
//constructors  
	public MyUber() {
		this.cars = new ArrayList<Car>();
		this.customers = new ArrayList<Customer>();
		this.drivers = new ArrayList<Driver>();
		this.ridesHistory = new BookOfRides();
		
	}
	

	//creation of a myUber network avec un nombre determiné de chaque element //TODO 
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


	public Calendar getCurrentTime() {
		//Date date = new Date();
		//Calendar calendar = new Calendar( );
		//calendar.setTime(date);
		//this.currentTime = calendar; 
		return currentTime;
	
	}


	public void setCurrentTime(Calendar currentTime) {
		this.currentTime = currentTime;
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

