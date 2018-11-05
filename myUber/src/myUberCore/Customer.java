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


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	
}
