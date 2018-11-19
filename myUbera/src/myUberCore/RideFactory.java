package myUberCore;

import java.util.ArrayList;

public class RideFactory {
	
	
	public Ride createRide(String rideType, RideBooking request) {
		Ride result = null;
		if(rideType.equalsIgnoreCase("UberX")) {
			request.getUser().getBalance().addRide(request.estimateFares().get(3), request.rideDuration());
			result= new UberX(request);
		}else if (rideType.equalsIgnoreCase("UberBlack")) {
			request.getUser().getBalance().addRide(request.estimateFares().get(0), request.rideDuration());
			result= new UberBlack(request);
		}else if (rideType.equalsIgnoreCase("UberVan")) {
			request.getUser().getBalance().addRide(request.estimateFares().get(2), request.rideDuration());
			result= new UberVan(request);
		}
		return result;
	}
	public Ride createPoolRide(ArrayList<RideBooking> poolWaitingList) {
		return new UberPool(poolWaitingList);
	}
	
}
