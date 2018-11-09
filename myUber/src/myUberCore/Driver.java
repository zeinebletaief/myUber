package myUberCore;

import java.util.Observable;
import java.util.Observer;

public class Driver implements Observer{
	private long counter = 0;
	public String name;
	public String surname;
	private long driverID;
	public DriverStatus status;
	private DriverBalance balance; 
	
	

// constructor
	public Driver(String name, String surname, DriverStatus status) {
		super();
		this.name = name;
		this.surname = surname;
		this.counter ++;
		this.driverID = counter; 
		this.status = status;
		this.balance = new DriverBalance(); 
	}


	
//getters & setters

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}
	public long getDriverID() {
		return driverID;
	}
	public DriverStatus getStatus() {
		return status;
	}
	public DriverBalance getBalance() {
		return balance; 
	}


	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setDriverID(long driverID) {
		this.driverID = driverID;
	}
	public void setStatus(DriverStatus status) {
		this.status = status;
	}

	
	//*****************************************************************//
//	Methods 							   //
//*****************************************************************//	

	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	public void acceptRide() { //change driver status, add Ride to book, confirm booking, notify customer 
		
	}

}
