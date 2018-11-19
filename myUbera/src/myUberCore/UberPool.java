package myUberCore;

import java.util.ArrayList;

public class UberPool extends Ride{
	//Constructor by one request
	public UberPool(RideBooking request) {
		this.setID();
		this.setCar(new Standard());
		this.setCustomers(new ArrayList<Customer>());
		this.setStartpoints(new ArrayList<GPS>());
		this.setDestinations(new ArrayList<GPS>());
		this.setCosts(new ArrayList<Cost>());
		this.addRide(request.getUser(), request.getDestination(),new UberPoolCost(request.getTraffic(), request.getDistance()));
		this.setStatus(BookingStatus.unconfirmed);
		this.setType("UberPool");
		this.setTime(request.getRequestTime());
		
	}
	//Constructor by waitingList
	public UberPool(ArrayList<RideBooking> requests) {
		this.setID();
		this.setCar(new Standard());
		this.setCustomers(new ArrayList<Customer>());
		this.setStartpoints(new ArrayList<GPS>());
		this.setDestinations(new ArrayList<GPS>());
		this.setCosts(new ArrayList<Cost>());
		for (RideBooking request:requests) {
			this.addRide(request.getUser(), request.getDestination(),new UberPoolCost(request.getTraffic(), request.getDistance()));
		}
		this.setStatus(BookingStatus.unconfirmed);
		this.setType("UberPool");
		this.setTime(requests.get(requests.size()-1).getRequestTime());
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


