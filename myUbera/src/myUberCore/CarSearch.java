package myUberCore;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CarSearch { //It is a car search "factory"
	//we use the class in order to separate the instantiation logic from the RideBooking class
	
	//return a sorted list (by distance cost) of cars of the corresponding type
	public ArrayList<Car> sortCars(Ride ride,ArrayList<Car> cars) {
		String carType ="";
		Map<Double, Car> carMap = new TreeMap<Double, Car>();
		ArrayList<Car> sortedCars = new ArrayList<Car>();
		
		//setting the car type corresponding to the ride type
		if ((ride.getType()=="UberPool") || (ride.getType()=="UberX")) {
			carType = "Standard";
		} else if ((ride.getType()=="UberBlack")) {
			carType = "Berline";
		} else {
			carType = "Van";
		}
		//Corresponding car type list
		for (Car i : cars) {
			if (i.getCarType()==carType) {
				carMap.put(i.getCarPosition().getRideDistance(ride.getStartpoints(),ride.getDestinations()), i);
			}
		}
		for (Car i:carMap.values()) {
			sortedCars.add(i);
		}
		return sortedCars;
	}
	public Car findCar (Ride ride,ArrayList<Car> cars,Scanner reader) {
		Car result =null;
		try {
			Car car = this.sortCars(ride, cars).get(0);
			int i=0;
			boolean answer = false;
			while (answer == false) {
				answer=car.getDriver().getDriverAnswer(ride,reader); //asking Drivers
				i++;
				result = car;
				car=this.sortCars(ride, cars).get(i);
			}
			
		} catch(TypingError e) {
			System.out.println("Typing Error");
		}
		return result;
	}
	/*
	public void createCarSearch(String ridePreference, RideBooking booking) {
		
		if (ridePreference == null){
			//return null
		}

		if (ridePreference.equalsIgnoreCase("X")) {
			UberX X = new UberX(booking.getUser());
			X.rideSearch();
		}
		if (ridePreference.equalsIgnoreCase("BLACK")) {
			UberBlack black = new UberBlack(booking.getUser());
			black.rideSearch();
		}
		if (ridePreference.equalsIgnoreCase("VAN")) {
			UberVan van = new UberVan(booking.getUser());
			van.rideSearch();
		}
		if (ridePreference.equalsIgnoreCase("POOL")) {
			UberPool pool = new UberPool(booking.getUser());
			pool.rideSearch();
		}
	}*/

}
