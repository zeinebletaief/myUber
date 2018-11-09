package myUberCore;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Customer implements Observer{
	private static long counter;
	public String name;
	public String surname;
	private Long customerId;
	public GPS cusPosition;
	private ArrayList<Message>  messageBox;
	private CustomerBalance balance;
	private long creditCard; 
	public Ride currentRide; 
	
	//constructors
	public Customer(String name, String surname, long creditCard) {
		super();
		this.name = name;
		this.surname = surname;
		this.creditCard = creditCard;
		Customer.counter ++;
		this.customerId = counter;
		this.messageBox = new ArrayList<Message>();  
		this.cusPosition = new GPS(0, 0);
		this.balance = new CustomerBalance();
		this.currentRide = null; 
	}
	public Customer(String name, String surname, long creditCard, GPS point) {
		super();
		this.name = name;
		this.surname = surname;
		this.creditCard = creditCard;
		Customer.counter ++;
		this.customerId = counter;
		this.messageBox = new ArrayList<Message>();  
		this.cusPosition = point;
		this.balance = new CustomerBalance();
		this.currentRide = null; 
	}
	
	
	
//Getters & setters
	public long getCounter() {
		return counter;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public GPS getCusPosition() {
		return cusPosition;
	}
	public ArrayList<Message> getMessageBox() {
		return messageBox;
	}
	public CustomerBalance getBalance() {
		return balance;
	}
	public long getCreditCard() {
		return creditCard;
	}


	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public void setCreditCard(long creditCard) {
		this.creditCard = creditCard;
	}


	//*****************************************************************//
//	Methods 							   //
//*****************************************************************//	

	public void bookRide(GPS destination ) {
	//i.e creation RideBooking it is an observable (so this is an addObservable Method)		
	}
	public void bookRide(GPS start, GPS destination ) {
		//i.e creation RideBooking it is an observable (so this is an addObservable Method)		
		}
	
	
	public void chooserideType() { //TODO and how?
		
	}
	
	public void evaluate(Ride ride) {
		//TODO
		//on termination of the ride , so must know when it ends and compare with current time
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	
}


