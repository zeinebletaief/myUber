package myUberCore;

import java.util.ArrayList;

public class Standard extends Car{
	//constructor	
	public Standard() {
		this.setNumber();
		this.setCarType("Standard");
		this.setID();
		this.setSeats(4);
		this.setOwners(new ArrayList<Driver>());
		this.setShifts(new ArrayList<Shift>());
		this.setCarPosition(new GPS(0,0));
	}
	public Standard(GPS pos) {
		this.setNumber();
		this.setCarType("Standard");
		this.setID();
		this.setSeats(4);
		this.setOwners(new ArrayList<Driver>());
		this.setShifts(new ArrayList<Shift>());
		this.setCarPosition(pos);
	}
	public Standard(ArrayList<Driver> owners,ArrayList<Shift> shifts) {
		this.setNumber();
		this.setCarType("Standard");
		this.setID();
		this.setSeats(4);
		this.setOwners(owners);
		this.setShifts(shifts);
	}	
}
