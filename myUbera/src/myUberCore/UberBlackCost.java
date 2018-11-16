package myUberCore;

public class UberBlackCost extends Cost implements RideCost{	
	
	//constructor 	
	public UberBlackCost(TrafficState traffic, double length) {
		this.setLength(length);
		this.setTrafficKind(traffic);
	}
	
	//*****************************************************************//
//	Methods 							   //
//*****************************************************************//	
    
	//calculate the ride fare
	@Override
	public double rideFare() {
		double trafficRate = 1;
		switch(this.getTrafficKind()) {
		    case Low:
			trafficRate = 1;break;
			case Medium:
				trafficRate = 1.3;break;
			case High:
				trafficRate = 1.6;break;
		}
		double basicRate = 0;
		if (this.getLength() <5) { 
			basicRate = 6.2;
		} else if (10 > this.getLength()){
			basicRate = 5.5;
		} else if (this.getLength() <20){
			basicRate = 3.25;
		} else {
			basicRate = 2.6;
		}
		
		return basicRate * trafficRate * getLength();
	}
}


