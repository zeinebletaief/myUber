package myUberCore;

import java.util.ArrayList;

public class UberVan extends Ride{
	//Constructor only by request
	public UberVan(RideBooking request) {
		this.setID();
		this.setCar(new Van());
		this.setCustomers(new ArrayList<Customer>());
		this.setStartpoints(new ArrayList<GPS>());
		this.setDestinations(new ArrayList<GPS>());
		this.setCosts(new ArrayList<Cost>());
		this.addRide(request.getUser(), request.getDestination(),new UberVanCost(request.getTraffic(), request.getDistance()));
		this.setStatus(BookingStatus.unconfirmed);
		this.setType("UberVan");
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


