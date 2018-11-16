package myUberCore;

import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends User{
	private GPS cusPosition;
	private ArrayList<Message>  messageBox;
	private long creditCard;
	private CustomerBalance balance;
	private static ArrayList<RideBooking> poolRequests= new ArrayList<RideBooking>();//UberPool Waiting list
	private static WaitingState full=WaitingState.Empty; //tells if the UberPool waiting list is full or not
	//constructors
	public Customer(String name, String surname, long creditCard) {
		this.setID();
		this.setName(name);
		this.setSurname(surname);
		this.creditCard = creditCard;
		this.messageBox = new ArrayList<Message>();  
		this.cusPosition = new GPS(0, 0);
		this.balance= new CustomerBalance();
	}
	//observer methods
	@Override
	public void update(Ride ride,Scanner reader) {//when a car is found for the ride
		this.addMessage(new Message("Driver is on his way ! ","Uber",ride.getTime()));
		boolean cancel=false;
		try {
		    cancel = this.doYouCancel(reader);
		}catch(TypingError e) {
			System.out.println("Typing Error");
		}
		if(cancel==false) {
			this.getBalance().addRide(ride.getCosts().get(ride.getCustomers().indexOf(this)).rideFare(), ride.getRideDuration());
			ride.setStatus(BookingStatus.ongoing);
			
		} else {
			ride.setStatus(BookingStatus.canceled);
			BookOfRides.addRide(ride);
		}
		
	}
	public void updateFinish(Ride ride,Scanner reader) {//when a ride is desserved
		this.addMessage(new Message("Ride desserved ! ","Uber",ride.getTime()));
		ride.getCar().getDriver().setStatus(DriverStatus.onDuty);
		ride.setStatus(BookingStatus.completed);
		try {
		    this.rate(ride.getCar().getDriver(),reader);
		}catch(TypingError e) {
			System.out.println("Typing Error");
		}
		this.setCusPosition(ride.getCar().getCarPosition().getRideFinal(ride.getStartpoints(), ride.getDestinations()));
	}
	public boolean doYouCancel(Scanner reader) throws TypingError {
		boolean answer = false;
		System.out.println("Do you want to cancel the ride ? [T/F] ");
		String a = reader.next(); 
		if(a=="T") {
			answer=true;
		} else if(a == "F"){
			answer=false;
		} else {
			throw new TypingError();
		}
		return answer;
	}
	public void rate(Driver driver,Scanner reader) throws TypingError {
		System.out.println("Rate your Driver : ");
		int a = reader.nextInt(); 
		if(a>5 || a<0) {
			throw new TypingError();
		}
		System.out.println("Thank you for rating.");
		driver.setRating(((driver.getRating()*(driver.getBalance().getNumRides()-1))+a)/(driver.getBalance().getNumRides()));
	}
	//getters&setters

	public void addMessage(Message message) {
		this.getMessageBox().add(message);
	}
	public CustomerBalance getBalance() {
		return balance;
	}

	public void setBalance(CustomerBalance balance) {
		this.balance = balance;
	}

	public GPS getCusPosition() {
		return cusPosition;
	}

	public void setCusPosition(GPS cusPosition) {
		this.cusPosition = cusPosition;
	}

	public ArrayList<Message> getMessageBox() {
		return messageBox;
	}

	public void setMessageBox(ArrayList<Message> messageBox) {
		this.messageBox = messageBox;
	}

	public long getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(long creditCard) {
		this.creditCard = creditCard;
	}
	public void addPoolRequest(RideBooking poolRequest) {
		int totalSeats = 0;
		if (Customer.poolRequests.size()>0) {
			for (RideBooking pool:Customer.poolRequests) {
				totalSeats=totalSeats+pool.getSeats();
			}//calcul des seats reservés
		}
		
		if ((totalSeats+poolRequest.getSeats()<=4) && (Customer.poolRequests.size()<3)) {
			Customer.poolRequests.add(poolRequest);	
		} else {
			Customer.full=WaitingState.NotFit;
		}
		
		if ((totalSeats+poolRequest.getSeats()==4) || (Customer.poolRequests.size()==3)) {
			Customer.full=WaitingState.Full;
		}
	}
	public void endPoolRequest() {
		Customer.poolRequests.removeAll(Customer.poolRequests);
		Customer.full=WaitingState.Empty;
	}
	//toString
	public String toString() {
		return this.getName() + " "+this.getSurname()+" ID : "+this.getID();
	}
	//*****************************************************************//
//	Methods 							   //
//*****************************************************************//	
    //Request Factory
	@SuppressWarnings("resource")
	public Ride rideFactory(Time requesttime,ArrayList<Car> allCars,Scanner reader) throws TypingError {
		System.out.println("Please choose your destination :");
		System.out.println("X :");
		double x = reader.nextDouble(); 
		System.out.println("Y :");
		double y = reader.nextDouble();
		GPS destination = new GPS(x,y);
		System.out.println("Please choose the number of persons :");
		int seats = reader.nextInt(); 
		if(seats<0 || seats>2) {
			throw new TypingError();
		}
		RideBooking request = this.bookaRide(destination, requesttime, seats);
		@SuppressWarnings("unused")
		ArrayList<Double> fares= request.estimateFares();
		System.out.println("Please choose the type of ride : [UberPool / UberX / UberBlack / UberVan]");
		String type = reader.next();
		reader.close();
		if(!(type.equalsIgnoreCase("UberX") || type.equalsIgnoreCase("UberPool")||type.equalsIgnoreCase("UberBlack")||type.equalsIgnoreCase("UberVan"))) {
			throw new TypingError();
		}
		Ride ride = this.chooseRideType(type,request);
		ride.lookForCar(allCars,reader);
		return (ride);
	}
	
	
	//create a request
	public RideBooking bookaRide(GPS destination,Time requestTime,int seats) {
		TrafficState traffic=requestTime.getTraffic(requestTime);
		RideBooking ongoingBooking = new RideBooking(this, destination,requestTime,traffic,seats);
		return ongoingBooking;
	}
	
	//reply to list of fares
	public Ride chooseRideType(String type,RideBooking ridebooked) {
		Ride result = null;
		RideFactory factory = new RideFactory();
		if (type=="UberPool") {
			if (Customer.full==WaitingState.Empty) { //if we can put the request in the waiting list, we add it
			    this.addPoolRequest(ridebooked);
			    this.balance.addRide(ridebooked.estimateFares().get(1), ridebooked.rideDuration());
			    result = factory.createPoolRide(Customer.poolRequests);
			} 
			if (Customer.full==WaitingState.NotFit) { // if we can't although the list is not full, we launch the ride and put the request in a new waitinglist
				result = factory.createPoolRide(Customer.poolRequests);
				this.endPoolRequest();
				this.addPoolRequest(ridebooked);
			}
			if (Customer.full==WaitingState.Full) { //if the waiting list is filled with the new request we launch the ride
		    	result = factory.createPoolRide(Customer.poolRequests);
		    	this.endPoolRequest();
		    }
		}else {
			result= factory.createRide(type, ridebooked);
		}
		result.registerObserver(this);
		return result;
	}
	
	
	//test de bookaRide
	/*
	public static void main(String[] args) {
		long a = 12568468;
		Customer moez = new Customer("Moez", "Dbira", a);
		Customer zeineb= new Customer("Zeineb","Ltaief",454524);
		Customer khalil= new Customer("Khalil","Bergaoui",111524);
		RideBooking aa = moez.bookaRide(new GPS(10,5), new Time(), 2);
		RideBooking bb = zeineb.bookaRide(new GPS(0,5), new Time(), 1);
		RideBooking cc = khalil.bookaRide(new GPS(15,10), new Time(), 2);
		RideBooking dd = moez.bookaRide(new GPS(10,5), new Time(), 2);
		Ride x = moez.chooseRideType("UberPool", aa);
		x = zeineb.chooseRideType("UberPool", bb);
		Ride z = khalil.chooseRideType("UberPool", cc);
		z= moez.chooseRideType("UberPool", dd);
		for(Customer c:x.getCustomers()) {
			System.out.println(c);
			System.out.println(x.getID());
			System.out.println(x.getType());
			System.out.println(x.getCosts().get(0));
		}
		System.out.println("-----------------------------");*/
		/*for(int c=0;c<y.getCustomers().size();c++) {
			System.out.println(y.getCustomers().get(c));
			System.out.println(y.getID());
			System.out.println(y.getType());
			System.out.println(y.getCosts().get(c));
		}*//*
		System.out.println("-----------------------------");
		for(int c=0;c<z.getCustomers().size();c++) {
			System.out.println(z.getCustomers().get(c));
			System.out.println(z.getID());
			System.out.println(z.getType());
			System.out.println(z.getCosts().get(c));
		}
		System.out.println("-----------------------------");
		/*for(int c=0;c<w.getCustomers().size();c++) {
			System.out.println(w.getCustomers().get(c));
			System.out.println(w.getID());
			System.out.println(w.getType());
			System.out.println(w.getCosts().get(c));
		}*/
		
		/*for(int i=1;i<12;i++) {
			RideBooking aa = moez.bookaRide(new GPS(10,5), new Time(i*12340000), 2);
			ArrayList<Double> ff = aa.estimateFares();
			System.out.println("Time : "+aa.getRequestTime());
			System.out.println("Traffic : "+aa.getTraffic());
			System.out.println("Distance : "+aa.getDistance());
			System.out.println("durée : "+aa.rideDuration()+" secondes");
		}*/ /*
	}         */  
	
}


