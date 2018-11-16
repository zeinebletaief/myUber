package myUberCore;

import java.util.Date;

public class Duration {
	private long duree; //la  durée en secondes
	//constructors
	public Duration(Date start,Date end) {
		this.duree= Math.abs((int)((end.getTime()-start.getTime())/1000));
	}
	public Duration(long seconds) {
		this.duree=seconds;
	}
	//methods
	public Duration add(Duration b) {
		return new Duration(this.getDuree()+b.getDuree());
	}
	//getters&setters
	public long getDuree() {
		return duree;
	}
	public void setDuree(long duree) {
		this.duree = duree;
	}
	//toString
	public String toString() {
		String ch="";
		ch=ch+this.duree;
		return ch;
	}
}
