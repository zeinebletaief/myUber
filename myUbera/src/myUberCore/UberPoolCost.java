package myUberCore;

public class UberPoolCost extends Cost implements RideCost{	
	
	//constructor 	
	public UberPoolCost(TrafficState traffic, double length) {
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
				trafficRate = 1.2;break;
		}
		double basicRate = 0;
		if (this.getLength() <5) { 
			basicRate = 2.4;
		} else if (10 > this.getLength()){
			basicRate = 3;
		} else if (this.getLength() <20){
			basicRate = 1.3;
		} else {
			basicRate = 1.1;
		}
		
		return basicRate * trafficRate * getLength();
	}

}


