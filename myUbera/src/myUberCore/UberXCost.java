package myUberCore;

public class UberXCost extends Cost implements RideCost{	
	//constructor 	
	public UberXCost(TrafficState traffic, double length) {
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
				trafficRate = 1.1;break;
			case High:
				trafficRate = 1.5;break;
		}
		double basicRate = 0;
		if (this.getLength() <5) { 
			basicRate = 3.3;
		} else if (10 > this.getLength()){
			basicRate = 4.2;
		} else if (this.getLength() <20){
			basicRate = 1.91;
		} else {
			basicRate = 1.5;
		}
		
		return basicRate * trafficRate * this.getLength();
	}

}


