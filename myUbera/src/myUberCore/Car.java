package myUberCore;

import java.util.ArrayList;

public abstract class Car {
	private static long counter=0;
	private long number;
	private String ID;
	private int seats;
	private ArrayList<Driver> owners;
	private ArrayList<Shift> shifts;
	private Driver driver;
	private GPS carPosition ;
	private String carType;
	

	public ArrayList<Shift> getShifts() {
		return shifts;
	}
	public void setShifts(ArrayList<Shift> shifts) {
		this.shifts = shifts;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber() {
		Car.counter++;
		this.number=Car.counter;
	}
	public String getID() {
		return ID;
	}
	public void setID() {
		this.ID = this.carType+this.number;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public ArrayList<Driver> getOwners() {
		return owners;
	}
	public void setOwners(ArrayList<Driver> owners) {
		this.owners = owners;
	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public GPS getCarPosition() {
		return carPosition;
	}
	public void setCarPosition(GPS carPosition) {
		this.carPosition = carPosition;
	}
	public void addOwner(Driver driver) {
		this.owners.add(driver);
		driver.setStatus(DriverStatus.onDuty);
		this.setDriver(driver);
	}
	public void changeShifts(ArrayList<Shift> shifts) {
		this.setShifts(shifts);
	}

}
