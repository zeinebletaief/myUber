package myUberCore;
import java.util.Scanner;

public class Driver extends User {
	private DriverStatus status;
	private DriverBalance balance;
	private float rating;
	
	

// constructor
	public Driver(String name, String surname) {
		this.setID();
		this.setName(name);
		this.setSurname(surname);
		this.status = DriverStatus.offDuty;
		this.balance = new DriverBalance();
		this.rating=0;
	}
// Methods
	
	public boolean getDriverAnswer(Ride ride,Scanner reader) throws TypingError{
		boolean answer=false;
		System.out.println("Driver : "+this.getName()+" "+this.getSurname());
		System.out.println("You have a request :");
		System.out.println("Request Type : "+ride.getType());
		String ch="";
		for (Customer c:ride.getCustomers()) {
			ch=ch+"["+c.getCusPosition().getX()+","+c.getCusPosition().getY()+"] ";
		}
		System.out.println("Pick up locations : "+ch);
		if (this.getStatus()==DriverStatus.onDuty) {
			System.out.println("Enter your answer [Yes/No]: ");
			String a = reader.next();
			if(a.equalsIgnoreCase("Yes")) {
				answer=true;
			} else if(a.equalsIgnoreCase("No")){
				answer=false;
			} else {
				throw new TypingError();
			}
		}else {
			System.out.println("Driver's status : off duty ");
		}
		return answer;
	}
	
	//getters & setters
	public float getRating() {
		return rating;
	}



	public void setRating(float rating) {
		this.rating = rating;
	}



	public DriverStatus getStatus() {
		return status;
	}



	public void setStatus(DriverStatus status) {
		this.status = status;
	}



	public DriverBalance getBalance() {
		return balance;
	}



	public void setBalance(DriverBalance balance) {
		this.balance = balance;
	}
	//*****************************************************************//
//	Methods 							   //
//*****************************************************************//	

	
	
	@Override
	public void update(Ride ride,Scanner reader) {//when a car found 
		this.setStatus(DriverStatus.onARide);
		double cost =0;
		for (Cost i:ride.getCosts()) {
			cost=cost+i.rideFare();
		}
	}
	@Override
	public void updateFinish(Ride ride,Scanner reader) {//when a ride is finished 
		this.setStatus(DriverStatus.onDuty);
		double cost =0;
		for (Cost i:ride.getCosts()) {
			cost=cost+i.rideFare();
		}
		this.getBalance().addRide(cost, ride.getRideDuration());
		ride.getCar().setCarPosition(ride.getCar().getCarPosition().getRideFinal(ride.getStartpoints(), ride.getDestinations()));
	}

}
