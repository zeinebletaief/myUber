package myUberCore;

import java.util.ArrayList;
import java.util.Scanner;

public class MyUber {
	private Time iniTime;
	private Time currentTime;
	private ArrayList<Car> cars;
	private ArrayList<Driver> drivers;
	private ArrayList<Customer> customers;
	private BookOfRides ridesHistory;
	
//constructors  
	public MyUber() {
		this.iniTime=new Time();
		this.currentTime=new Time();
		this.cars = new ArrayList<Car>();
		this.customers = new ArrayList<Customer>();
		this.drivers = new ArrayList<Driver>();
		this.ridesHistory = new BookOfRides();
		
	}
	
	public MyUber(long Nc,long Nd,long Nu,Scanner reader) {
		this.iniTime=new Time();
		this.currentTime=new Time();
		this.cars = new ArrayList<Car>();
		this.customers = new ArrayList<Customer>();
		this.drivers = new ArrayList<Driver>();
		this.ridesHistory = new BookOfRides();
		
		for (int i=0;i<Nc;i++) {
			
			System.out.println("Enter the Name of the "+i+"th Customer : ");
			String name = reader.next();
			System.out.println("Enter the SurName of the "+i+"th Customer : ");
			String surname = reader.next();
			System.out.println("Enter the Credit Card Number of the "+i+"th Customer : ");
			long card = reader.nextLong();
			this.customers.add(new Customer(name,surname,card));
		}
		for (int i=0;i<Nd;i++) {
			System.out.println("Enter the Name of the "+i+"th Driver : ");
			String name = reader.next();
			System.out.println("Enter the SurName of the "+i+"th Driver : ");
			String surname = reader.next();
			this.drivers.add(new Driver(name,surname));
		}
		for (int i=0;i<Nu;i++) {
			System.out.println("Enter the Type of the"+i+"th Car :[Standard / Berline / Van] ");
			String type = reader.next();
			if (type.equalsIgnoreCase("Standard")) {
				this.cars.add(new Standard());
			}else if (type.equalsIgnoreCase("Berline")) {
				this.cars.add(new Berline());
			} else if (type.equalsIgnoreCase("Van")) {
				this.cars.add(new Van());
			}
		}
		for (Car c:this.cars) {
			for (Driver d:this.drivers) {
				System.out.println("Do you want to add "+d.getName()+" to the list of owners of "+c.getID()+" ? [Y/N]");
				String answer = reader.next();
				if(answer =="Y") {
					c.addOwner(d);
					System.out.println("Do you want to set "+d.getName()+" as the current driver of "+c.getID()+" ? [Y/N]");
					c.setDriver(d);
				}				
			}
		}
	}

	public MyUber(ArrayList<Car> cars, ArrayList<Driver> drivers, ArrayList<Customer> customers) {
		this.iniTime=new Time();
		this.currentTime=new Time();
		this.cars = cars;
		this.drivers = drivers;
		this.customers = customers;
	}

//getters & setters 

	public ArrayList<Car> getCars() {
		return cars;
	}

	public ArrayList<Driver> getDrivers() {
		return drivers;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public BookOfRides getRidesHistory() {
		return ridesHistory;
	}

	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}

	public void setDrivers(ArrayList<Driver> drivers) {
		this.drivers = drivers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	public void setRidesHistory(BookOfRides ridesHistory) {
		this.ridesHistory = ridesHistory;
	}
//*******************************************//
	//Simulation//
//*******************************************//
	public void simulation(Customer c,Scanner reader) {
		try {
			Ride ride=c.rideFactory(this.currentTime,this.cars,reader);
			this.advanceTime(ride.getRideDuration());
			ride.finishRide(reader);
		} catch (TypingError e) {
			System.out.println("Typing Error");
		}
		for(Ride i:BookOfRides.book) {
			System.out.println(i);
		}
	}
	
	//*****************************************************************//
//	Methods 							   //
//*****************************************************************//	
	public void advanceTime(Duration duree) {
		this.currentTime.advanceTime(duree);
	}

	public void addCustomer() {
		
	}
	
	public void addCar() {
		
	}
	
	public void addDriver() {
		
	}

	public void addRide() {
		
	}
public static void main(String[] args) throws TypingError{
	Scanner reader = new Scanner(System.in);
	MyUber univer=new MyUber(1,1,1,reader);
	univer.simulation(univer.getCustomers().get(0),reader);
	/*univer.simulation(univer.getCustomers().get(2),reader);
	univer.simulation(univer.getCustomers().get(1),reader);*/
	
	reader.close();
}
	
}
