package myUberCore;

import java.util.ArrayList;

public class Berline extends Car{
	public Berline() {
		this.setNumber();
		this.setCarType("Berline");
		this.setID();
		this.setSeats(4);
		this.setOwners(new ArrayList<Driver>());
		this.setShifts(new ArrayList<Shift>());
	}
	public Berline(ArrayList<Driver> owners,ArrayList<Shift> shifts) {
		this.setNumber();
		this.setCarType("Berline");
		this.setID();
		this.setSeats(4);
		this.setOwners(owners);
		this.setShifts(shifts);
	}


}
