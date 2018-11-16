package myUberCore;

import java.util.ArrayList;

public class UberBlack extends Ride{
	//Constructor only by request
	public UberBlack(RideBooking request) {
		this.setID();
		this.setCar(new Berline());
		this.setCustomers(new ArrayList<Customer>());
		this.setStartpoints(new ArrayList<GPS>());
		this.setDestinations(new ArrayList<GPS>());
		this.setCosts(new ArrayList<Cost>());
		this.addRide(request.getUser(), request.getDestination(),new UberBlackCost(request.getTraffic(), request.getDistance()));
		this.setStatus(BookingStatus.unconfirmed);
		this.setType("UberBlack");
		this.setTime(request.getRequestTime());
	}
	
	// Methods
	/*
	public void getAnswer(NotPoolAnswer answer ) {
		this.setCar(answer.getCar());
		this.setCost(answer.getRequest().getCost());
		this.addRide(answer.getRequest().getCustomer(), answer.getRequest().getDestination());
		this.setStatus(answer.getStatus());
		this.setType("UberBlack");
	}  */	

}


