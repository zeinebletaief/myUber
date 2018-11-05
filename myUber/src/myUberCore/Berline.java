package myUberCore;

import java.util.ArrayList;

public class Berline extends Car{
	static int seats = 4;
	static long counter=0;
	private String carID;
	public GPS carPosition ; 
	public ArrayList<Driver> owners;
	
	//constructor	
		public Berline(ArrayList<Driver> owners) {
			super();
			counter ++;
			this.carID = "Standard"+String.valueOf(counter);
			this.owners = owners;
			this.carPosition = new GPS();
		}


		public Berline() {
			super();
			counter ++;
			this.carID = "Standard"+String.valueOf(counter);
			this.owners = new ArrayList<Driver>();
			this.carPosition = new GPS();
		}
		
		//getters & setters 
		public static int getSeats() {
			return seats;
		}


		public String getCarID() {
			return carID;
		}


		public GPS getCarPosition() {
			return carPosition;
		}


		public ArrayList<Driver> getOwners() {
			return owners;
		}


		public void setCarPosition(GPS carPosition) {
			this.carPosition = carPosition;
		}


		public void setOwners(ArrayList<Driver> owners) {
			this.owners = owners;
		}


}
