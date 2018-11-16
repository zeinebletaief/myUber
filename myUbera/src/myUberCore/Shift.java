package myUberCore;

public class Shift {
	private Time start;
	private Time end;
	//constructor
	public Shift(Time start, Time end) {
		this.start = start;
		this.end = end;
	}
	//methods
	public Duration getDuration() {
		return new Duration(getStart(), getEnd());
	}
	
	//getters&setters
	public Time getStart() {
		return start;
	}
	public void setStart(Time start) {
		this.start = start;
	}
	public Time getEnd() {
		return end;
	}
	public void setEnd(Time end) {
		this.end = end;
	}

}
