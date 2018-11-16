package myUberCore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GPS {
	public double x;
	public double y;

	public GPS(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public GPS() {
		this.x=0;
		this.y=0;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	//on choisit la distance Manhatten qui est la plus réaliste pour des distances dans une ville
	//d(X1,X2)=|x1-x2|+|y1-y2|
	public double distance(GPS point) {
		return Math.abs(this.getX() - point.getX())+
				Math.abs(this.getY()-point.getY());
	}
	
	public Map<Double,GPS> rideDistance(ArrayList<GPS> pickups,ArrayList<GPS> dropoffs) {
		double cost = 0;
		GPS posFinal = new GPS();
		Map<Double,GPS> result = new HashMap<Double, GPS>();
		if (pickups.size()==1) { // si il ya un seul customer au Ride
			cost= this.distance(pickups.get(0))+pickups.get(0).distance(dropoffs.get(0));
			posFinal = dropoffs.get(0);
		} else if (pickups.size()==2) { // si il ya deux customers
			if (this.distance(pickups.get(0))<this.distance(pickups.get(1))) { //si le 1er le plus proche
				cost=this.distance(pickups.get(0))+pickups.get(0).distance(pickups.get(1));
				if (pickups.get(1).distance(dropoffs.get(0))<pickups.get(1).distance(dropoffs.get(1))) {
					cost=cost+pickups.get(1).distance(dropoffs.get(0))+dropoffs.get(0).distance(dropoffs.get(1));
					posFinal = dropoffs.get(1);
				} else {
					cost=cost+pickups.get(1).distance(dropoffs.get(1))+dropoffs.get(1).distance(dropoffs.get(0));
					posFinal = dropoffs.get(0);
				}
			} else { //si le 2eme est plus proche
				cost=this.distance(pickups.get(1))+pickups.get(1).distance(pickups.get(0));
				if (pickups.get(0).distance(dropoffs.get(0))<pickups.get(0).distance(dropoffs.get(1))) {
					cost=cost+pickups.get(0).distance(dropoffs.get(0))+dropoffs.get(0).distance(dropoffs.get(1));
					posFinal = dropoffs.get(1);
				} else {
					cost=cost+pickups.get(0).distance(dropoffs.get(1))+dropoffs.get(1).distance(dropoffs.get(0));
					posFinal = dropoffs.get(0);
				}
			}
		} else { //si il ya 3 customers
			if ((this.distance(pickups.get(0))<this.distance(pickups.get(1))) && (this.distance(pickups.get(0))<this.distance(pickups.get(2)))) { //si le 1er est le plus proche
				cost = this.distance(pickups.get(0));
				if (pickups.get(0).distance(pickups.get(1))<pickups.get(0).distance(pickups.get(2))) { //comparaison des deux autres
					cost=cost+pickups.get(0).distance(pickups.get(1))+pickups.get(1).distance(pickups.get(2));
					if ((pickups.get(2).distance(dropoffs.get(0))<pickups.get(2).distance(dropoffs.get(1))) && (pickups.get(2).distance(dropoffs.get(0))<pickups.get(2).distance(dropoffs.get(2)))) { // le plus proche des dropoffs
						cost=cost+pickups.get(2).distance(dropoffs.get(0));
						if (dropoffs.get(0).distance(dropoffs.get(1))<dropoffs.get(0).distance(dropoffs.get(2))) {
							cost=cost+dropoffs.get(0).distance(dropoffs.get(1))+dropoffs.get(1).distance(dropoffs.get(2));
							posFinal = dropoffs.get(2);
						} else {
							cost=cost+dropoffs.get(0).distance(dropoffs.get(2))+dropoffs.get(2).distance(dropoffs.get(1));
							posFinal = dropoffs.get(1);
						}
					} else if((pickups.get(2).distance(dropoffs.get(1))<pickups.get(2).distance(dropoffs.get(0))) && (pickups.get(2).distance(dropoffs.get(1))<pickups.get(2).distance(dropoffs.get(2)))) {
						cost=cost+pickups.get(2).distance(dropoffs.get(1));
						if (dropoffs.get(1).distance(dropoffs.get(0))<dropoffs.get(1).distance(dropoffs.get(2))) {
							cost=cost+dropoffs.get(1).distance(dropoffs.get(0))+dropoffs.get(0).distance(dropoffs.get(2));
							posFinal = dropoffs.get(2);
						} else {
							cost=cost+dropoffs.get(1).distance(dropoffs.get(2))+dropoffs.get(2).distance(dropoffs.get(0));
							posFinal = dropoffs.get(0);
						}
					} else {
						cost=cost+pickups.get(2).distance(dropoffs.get(2));
						if (dropoffs.get(2).distance(dropoffs.get(0))<dropoffs.get(2).distance(dropoffs.get(1))) {
							cost=cost+dropoffs.get(2).distance(dropoffs.get(0))+dropoffs.get(0).distance(dropoffs.get(1));
							posFinal = dropoffs.get(1);
						} else {
							cost=cost+dropoffs.get(2).distance(dropoffs.get(1))+dropoffs.get(1).distance(dropoffs.get(0));
							posFinal = dropoffs.get(0);
						}
					}
				} else {
					cost=cost+pickups.get(0).distance(pickups.get(2))+pickups.get(2).distance(pickups.get(1));
					if ((pickups.get(1).distance(dropoffs.get(0))<pickups.get(1).distance(dropoffs.get(1))) && (pickups.get(1).distance(dropoffs.get(0))<pickups.get(1).distance(dropoffs.get(2)))) { // le plus proche des dropoffs
						cost=cost+pickups.get(1).distance(dropoffs.get(0));
						if (dropoffs.get(0).distance(dropoffs.get(1))<dropoffs.get(0).distance(dropoffs.get(2))) {
							cost=cost+dropoffs.get(0).distance(dropoffs.get(1))+dropoffs.get(1).distance(dropoffs.get(2));
							posFinal = dropoffs.get(2);
						} else {
							cost=cost+dropoffs.get(0).distance(dropoffs.get(2))+dropoffs.get(2).distance(dropoffs.get(1));
							posFinal = dropoffs.get(1);
						}
					} else if((pickups.get(1).distance(dropoffs.get(1))<pickups.get(1).distance(dropoffs.get(0))) && (pickups.get(1).distance(dropoffs.get(1))<pickups.get(1).distance(dropoffs.get(2)))) {
						cost=cost+pickups.get(1).distance(dropoffs.get(1));
						if (dropoffs.get(1).distance(dropoffs.get(0))<dropoffs.get(1).distance(dropoffs.get(2))) {
							cost=cost+dropoffs.get(1).distance(dropoffs.get(0))+dropoffs.get(0).distance(dropoffs.get(2));
							posFinal = dropoffs.get(2);
						} else {
							cost=cost+dropoffs.get(1).distance(dropoffs.get(2))+dropoffs.get(2).distance(dropoffs.get(0));
							posFinal = dropoffs.get(0);
						}
					} else {
						cost=cost+pickups.get(1).distance(dropoffs.get(2));
						if (dropoffs.get(2).distance(dropoffs.get(0))<dropoffs.get(2).distance(dropoffs.get(1))) {
							cost=cost+dropoffs.get(2).distance(dropoffs.get(0))+dropoffs.get(0).distance(dropoffs.get(1));
							posFinal = dropoffs.get(1);
						} else {
							cost=cost+dropoffs.get(2).distance(dropoffs.get(1))+dropoffs.get(1).distance(dropoffs.get(0));
							posFinal = dropoffs.get(0);
						}
					}
				}
			} else if ((this.distance(pickups.get(1))<this.distance(pickups.get(0))) && (this.distance(pickups.get(1))<this.distance(pickups.get(2)))) { // si le 2eme est le plus proche
				cost = this.distance(pickups.get(1));
				if (pickups.get(1).distance(pickups.get(0))<pickups.get(1).distance(pickups.get(2))) { //comparaison des deux autres
					cost=cost+pickups.get(1).distance(pickups.get(0))+pickups.get(0).distance(pickups.get(2));
					if ((pickups.get(2).distance(dropoffs.get(0))<pickups.get(2).distance(dropoffs.get(1))) && (pickups.get(2).distance(dropoffs.get(0))<pickups.get(2).distance(dropoffs.get(2)))) { // le plus proche des dropoffs
						cost=cost+pickups.get(2).distance(dropoffs.get(0));
						if (dropoffs.get(0).distance(dropoffs.get(1))<dropoffs.get(0).distance(dropoffs.get(2))) {
							cost=cost+dropoffs.get(0).distance(dropoffs.get(1))+dropoffs.get(1).distance(dropoffs.get(2));
							posFinal = dropoffs.get(2);
						} else {
							cost=cost+dropoffs.get(0).distance(dropoffs.get(2))+dropoffs.get(2).distance(dropoffs.get(1));
							posFinal = dropoffs.get(1);
						}
					} else if((pickups.get(2).distance(dropoffs.get(1))<pickups.get(2).distance(dropoffs.get(0))) && (pickups.get(2).distance(dropoffs.get(1))<pickups.get(2).distance(dropoffs.get(2)))) {
						cost=cost+pickups.get(2).distance(dropoffs.get(1));
						if (dropoffs.get(1).distance(dropoffs.get(0))<dropoffs.get(1).distance(dropoffs.get(2))) {
							cost=cost+dropoffs.get(1).distance(dropoffs.get(0))+dropoffs.get(0).distance(dropoffs.get(2));
							posFinal = dropoffs.get(2);
						} else {
							cost=cost+dropoffs.get(1).distance(dropoffs.get(2))+dropoffs.get(2).distance(dropoffs.get(0));
							posFinal = dropoffs.get(0);
						}
					} else {
						cost=cost+pickups.get(2).distance(dropoffs.get(2));
						if (dropoffs.get(2).distance(dropoffs.get(0))<dropoffs.get(2).distance(dropoffs.get(1))) {
							cost=cost+dropoffs.get(2).distance(dropoffs.get(0))+dropoffs.get(0).distance(dropoffs.get(1));
							posFinal = dropoffs.get(1);
						} else {
							cost=cost+dropoffs.get(2).distance(dropoffs.get(1))+dropoffs.get(1).distance(dropoffs.get(0));
							posFinal = dropoffs.get(0);
						}
					}
				} else {
					cost=cost+pickups.get(1).distance(pickups.get(2))+pickups.get(2).distance(pickups.get(0));
					if ((pickups.get(0).distance(dropoffs.get(0))<pickups.get(0).distance(dropoffs.get(1))) && (pickups.get(0).distance(dropoffs.get(0))<pickups.get(0).distance(dropoffs.get(2)))) { // le plus proche des dropoffs
						cost=cost+pickups.get(0).distance(dropoffs.get(0));
						if (dropoffs.get(0).distance(dropoffs.get(1))<dropoffs.get(0).distance(dropoffs.get(2))) {
							cost=cost+dropoffs.get(0).distance(dropoffs.get(1))+dropoffs.get(1).distance(dropoffs.get(2));
							posFinal = dropoffs.get(2);
						} else {
							cost=cost+dropoffs.get(0).distance(dropoffs.get(2))+dropoffs.get(2).distance(dropoffs.get(1));
							posFinal = dropoffs.get(1);
						}
					} else if((pickups.get(0).distance(dropoffs.get(1))<pickups.get(0).distance(dropoffs.get(0))) && (pickups.get(0).distance(dropoffs.get(1))<pickups.get(0).distance(dropoffs.get(2)))) {
						cost=cost+pickups.get(0).distance(dropoffs.get(1));
						if (dropoffs.get(1).distance(dropoffs.get(0))<dropoffs.get(1).distance(dropoffs.get(2))) {
							cost=cost+dropoffs.get(1).distance(dropoffs.get(0))+dropoffs.get(0).distance(dropoffs.get(2));
							posFinal = dropoffs.get(2);
						} else {
							cost=cost+dropoffs.get(1).distance(dropoffs.get(2))+dropoffs.get(2).distance(dropoffs.get(0));
							posFinal = dropoffs.get(0);
						}
					} else {
						cost=cost+pickups.get(0).distance(dropoffs.get(2));
						if (dropoffs.get(2).distance(dropoffs.get(0))<dropoffs.get(2).distance(dropoffs.get(1))) {
							cost=cost+dropoffs.get(2).distance(dropoffs.get(0))+dropoffs.get(0).distance(dropoffs.get(1));
							posFinal = dropoffs.get(1);
						} else {
							cost=cost+dropoffs.get(2).distance(dropoffs.get(1))+dropoffs.get(1).distance(dropoffs.get(0));
							posFinal = dropoffs.get(0);
						}
					}
			  }
			} else { //si le 3eme est le plus proche
				cost = this.distance(pickups.get(2));
				
				
				if (pickups.get(2).distance(pickups.get(1))<pickups.get(2).distance(pickups.get(0))) { //comparaison des deux autres
					cost=cost+pickups.get(2).distance(pickups.get(1))+pickups.get(1).distance(pickups.get(0));
					if ((pickups.get(0).distance(dropoffs.get(0))<pickups.get(0).distance(dropoffs.get(1))) && (pickups.get(0).distance(dropoffs.get(0))<pickups.get(0).distance(dropoffs.get(2)))) { // le plus proche des dropoffs
						cost=cost+pickups.get(0).distance(dropoffs.get(0));
						if (dropoffs.get(0).distance(dropoffs.get(1))<dropoffs.get(0).distance(dropoffs.get(2))) {
							cost=cost+dropoffs.get(0).distance(dropoffs.get(1))+dropoffs.get(1).distance(dropoffs.get(2));
							posFinal = dropoffs.get(2);
						} else {
							cost=cost+dropoffs.get(0).distance(dropoffs.get(2))+dropoffs.get(2).distance(dropoffs.get(1));
							posFinal = dropoffs.get(1);
						}
					} else if((pickups.get(0).distance(dropoffs.get(1))<pickups.get(0).distance(dropoffs.get(0))) && (pickups.get(0).distance(dropoffs.get(1))<pickups.get(0).distance(dropoffs.get(2)))) {
						cost=cost+pickups.get(0).distance(dropoffs.get(1));
						if (dropoffs.get(1).distance(dropoffs.get(0))<dropoffs.get(1).distance(dropoffs.get(2))) {
							cost=cost+dropoffs.get(1).distance(dropoffs.get(0))+dropoffs.get(0).distance(dropoffs.get(2));
							posFinal = dropoffs.get(2);
						} else {
							cost=cost+dropoffs.get(1).distance(dropoffs.get(2))+dropoffs.get(2).distance(dropoffs.get(0));
							posFinal = dropoffs.get(0);
						}
					} else {
						cost=cost+pickups.get(0).distance(dropoffs.get(2));
						if (dropoffs.get(2).distance(dropoffs.get(0))<dropoffs.get(2).distance(dropoffs.get(1))) {
							cost=cost+dropoffs.get(2).distance(dropoffs.get(0))+dropoffs.get(0).distance(dropoffs.get(1));
							posFinal = dropoffs.get(1);
						} else {
							cost=cost+dropoffs.get(2).distance(dropoffs.get(1))+dropoffs.get(1).distance(dropoffs.get(0));
							posFinal = dropoffs.get(0);
						}
					}
				} else {
					cost=cost+pickups.get(0).distance(pickups.get(2))+pickups.get(0).distance(pickups.get(1));
					if ((pickups.get(1).distance(dropoffs.get(0))<pickups.get(1).distance(dropoffs.get(1))) && (pickups.get(1).distance(dropoffs.get(0))<pickups.get(1).distance(dropoffs.get(2)))) { // le plus proche des dropoffs
						cost=cost+pickups.get(1).distance(dropoffs.get(0));
						if (dropoffs.get(0).distance(dropoffs.get(1))<dropoffs.get(0).distance(dropoffs.get(2))) {
							cost=cost+dropoffs.get(0).distance(dropoffs.get(1))+dropoffs.get(1).distance(dropoffs.get(2));
							posFinal = dropoffs.get(2);
						} else {
							cost=cost+dropoffs.get(0).distance(dropoffs.get(2))+dropoffs.get(2).distance(dropoffs.get(1));
							posFinal = dropoffs.get(1);
						}
					} else if((pickups.get(1).distance(dropoffs.get(1))<pickups.get(1).distance(dropoffs.get(0))) && (pickups.get(1).distance(dropoffs.get(1))<pickups.get(1).distance(dropoffs.get(2)))) {
						cost=cost+pickups.get(1).distance(dropoffs.get(1));
						if (dropoffs.get(1).distance(dropoffs.get(0))<dropoffs.get(1).distance(dropoffs.get(2))) {
							cost=cost+dropoffs.get(1).distance(dropoffs.get(0))+dropoffs.get(0).distance(dropoffs.get(2));
							posFinal = dropoffs.get(2);
						} else {
							cost=cost+dropoffs.get(1).distance(dropoffs.get(2))+dropoffs.get(2).distance(dropoffs.get(0));
							posFinal = dropoffs.get(0);
						}
					} else {
						cost=cost+pickups.get(1).distance(dropoffs.get(2));
						if (dropoffs.get(2).distance(dropoffs.get(0))<dropoffs.get(2).distance(dropoffs.get(1))) {
							cost=cost+dropoffs.get(2).distance(dropoffs.get(0))+dropoffs.get(0).distance(dropoffs.get(1));
							posFinal = dropoffs.get(1);
						} else {
							cost=cost+dropoffs.get(2).distance(dropoffs.get(1))+dropoffs.get(1).distance(dropoffs.get(0));
							posFinal = dropoffs.get(0);
						}
					}
				}
				
				
			}
		}
		result.put(cost, posFinal);
		return result;
	}
  
	public double getRideDistance(ArrayList<GPS> pickups,ArrayList<GPS> dropoffs) {
    	Map<Double,GPS> result = this.rideDistance(pickups, dropoffs);
    	double k = 0;
    	for (double i:result.keySet()) {
    		k=i;
    	}
    	return k;
    }
    public GPS getRideFinal(ArrayList<GPS> pickups,ArrayList<GPS> dropoffs) {
    	Map<Double,GPS> result = this.rideDistance(pickups, dropoffs);
    	double k = this.getRideDistance(pickups, dropoffs);
    	return result.get(k);
    }
	
}
