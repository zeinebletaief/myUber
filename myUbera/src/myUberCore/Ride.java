package myUberCore;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Ride implements Observable{
	private static long counter=0;
	private long ID;
	private Time time;
	private ArrayList<GPS> startpoints;
	private ArrayList<GPS> destinations;
	private Car car;
	private ArrayList<Customer> customers;
	private BookingStatus status;
	private ArrayList<Cost> costs;
	private String type;
	private boolean carfound=false;
	private boolean finish=false;
	
	private ArrayList<Observer> observers=new ArrayList<Observer>();

	//methods
	public Duration getRideDuration() {
		long seconds=0;
		TrafficState traffic = this.getCosts().get(0).getTrafficKind();
		double speed=0;
	    switch(traffic) {
	    case Low:
		    speed = 15;break;
		case Medium:
			speed = 7.5;break;
		case High:
			speed = 3;break;
	    }
	    double distance = this.getCar().getCarPosition().getRideDistance(this.getStartpoints(), this.getDestinations());
	    seconds =(long) (distance/speed)*3600;
	    return new Duration(seconds);
	}
	public void addRide(Customer customer,GPS destination,Cost cost) {
		this.customers.add(customer);
		this.destinations.add(destination);
		this.startpoints.add(customer.getCusPosition());
		this.costs.add(cost);
	}
	public void lookForCar(ArrayList<Car> allCars,Scanner reader) {
		CarSearch carsearch = new CarSearch();
		Car car = carsearch.findCar(this,allCars,reader);
		this.setCar(car);
		this.registerObserver(car.getDriver());
		this.setStatus(BookingStatus.confirmed);
		this.setCarfound(true);
		this.notifyObservers(reader);
	}
	public void finishRide(Scanner reader) {
		this.setStatus(BookingStatus.completed);
		this.setFinish(true);
		this.notifyObserversFinish(reader);
	}
	//Observer pattern methods
	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}
	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	@Override
	public void notifyObservers(Scanner reader) {
		if (this.isCarfound()) {
			for (Observer ob : observers)
			ob.update(this,reader);
			this.setCarfound(false);
			}
	}
	public void notifyObserversFinish(Scanner reader) {
		if(this.isFinish()) {
			for (Observer ob: observers) {
				ob.updateFinish(this,reader);
				this.setFinish(false);
				this.removeObserver(ob);
			}
		}
	}
	//getters&setters
	
	public boolean isFinish() {
		return finish;
	}
	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
		public boolean isCarfound() {
		return carfound;
	}
	public void setCarfound(boolean carfound) {
		this.carfound = carfound;
	}
		public void setID() {
			Ride.counter++;
			this.ID=Ride.counter;
		}
		public long getID() {
			return this.ID;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public ArrayList<Cost> getCosts() {
			return costs;
		}
		public void setCosts(ArrayList<Cost> costs) {
			this.costs = costs;
		}
		public ArrayList<GPS> getStartpoints() {
			return startpoints;
		}
		public void setStartpoints(ArrayList<GPS> startpoints) {
			this.startpoints = startpoints;
		}
		public ArrayList<GPS> getDestinations() {
			return destinations;
		}
		public void setDestinations(ArrayList<GPS> destinations) {
			this.destinations = destinations;
		}
		public Car getCar() {
			return car;
		}
		public void setCar(Car car) {
			this.car = car;
		}
		public ArrayList<Customer> getCustomers() {
			return customers;
		}
		public void setCustomers(ArrayList<Customer> customers) {
			this.customers = customers;
		}
		public BookingStatus getStatus() {
			return status;
		}
		public void setStatus(BookingStatus status) {
			this.status = status;
		}
		
		public ArrayList<Observer> getObservers() {
			return observers;
		}
		public void setObservers(ArrayList<Observer> observers) {
			this.observers = observers;
		}
		//toString
		public String toString() {
			String ch="";
			ch="Ride Date: "+this.getTime()+"|Customers: ";
			for (Customer i:this.getCustomers()) {
				ch=ch+i.getName()+" ; ";
			}
			ch=ch+"|Prices: ";
			for(Cost c:this.getCosts()) {
				ch=ch+c;
			}
			ch=ch+"|Car: "+this.getCar().getID()+"|Driver :"+this.getCar().getDriver().getName()+"|Duration(s): "+this.getRideDuration().getDuree();
		    return ch;
		}
		
}
