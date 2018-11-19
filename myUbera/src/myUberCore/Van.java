package myUberCore;

import java.util.ArrayList;

public class Van extends Car{
	public Van() {
		this.setNumber();
		this.setCarType("Van");
		this.setID();
		this.setSeats(6);
		this.setOwners(new ArrayList<Driver>());
		this.setShifts(new ArrayList<Shift>());
		this.setCarPosition(new GPS(0,0));
	}
	public Van(GPS pos) {
		this.setNumber();
		this.setCarType("Van");
		this.setID();
		this.setSeats(6);
		this.setOwners(new ArrayList<Driver>());
		this.setShifts(new ArrayList<Shift>());
		this.setCarPosition(pos);
	}
	public Van(ArrayList<Driver> owners,ArrayList<Shift> shifts) {
		this.setNumber();
		this.setCarType("Van");
		this.setID();
		this.setSeats(4);
		this.setOwners(owners);
		this.setShifts(shifts);
	}
}
