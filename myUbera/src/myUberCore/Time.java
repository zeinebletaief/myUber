package myUberCore;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class Time extends Date{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Constructors
	public Time() {
		super();
	}
	public Time(long time) {
		super(time);
	}
	
	//methods
	public Time advanceTime(Duration duree) {
		return(new Time(this.getTime()+duree.getDuree()*1000));
	}
	public TrafficState getTraffic(Time date) { //makes a choice of traffic according to the probability of the occurance
		TrafficState traffic=null;
		@SuppressWarnings("deprecation")
		int hour = date.getHours();
		Map<TrafficState, Double> probaMap =new HashMap<TrafficState, Double>();
		if (hour>=22 || hour<7) {
			probaMap.put(TrafficState.Low, 0.95);
			probaMap.put(TrafficState.Medium, 0.04);
			probaMap.put(TrafficState.High, 0.01);
		}else if(hour<11) {
			probaMap.put(TrafficState.Low, 0.05);
			probaMap.put(TrafficState.Medium, 0.20);
			probaMap.put(TrafficState.High, 0.75);
		}else if(hour<17) {
			probaMap.put(TrafficState.Low, 0.15);
			probaMap.put(TrafficState.Medium, 0.70);
			probaMap.put(TrafficState.High, 0.15);
		} else {
			probaMap.put(TrafficState.Low, 0.01);
			probaMap.put(TrafficState.Medium, 0.04);
			probaMap.put(TrafficState.High, 0.95);
		}
		ArrayList<TrafficState> probaList = new ArrayList<TrafficState>();
		for(TrafficState key:probaMap.keySet()) {
			for(int i=0;i<(int)(probaMap.get(key)*100);i++) {
				probaList.add(key);
			}
		}
		int choix = (int)(Math.random()*probaList.size());
		traffic = probaList.get(choix);
		return traffic;
	}
	//toString
	@SuppressWarnings("deprecation")
	public String toString() {
		return this.getDay()+"/"+this.getMonth()+"/"+this.getYear()+"  "+this.getHours()+":"+this.getMinutes()+":"+this.getSeconds();
	}
}
