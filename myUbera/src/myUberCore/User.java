package myUberCore;

import java.util.Scanner;

public abstract class User implements Observer{
	private static long counter=0;
	private long ID;
	private String name;
	private String surname;
	
	//getters&setters
	public long getID() {
		return ID;
	}
	public void setID() {
		User.counter++;
		this.ID = User.counter;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	//Observer methods :
	public abstract void update(Ride ride,Scanner reader);
	public abstract void updateFinish(Ride ride,Scanner reader);
}
