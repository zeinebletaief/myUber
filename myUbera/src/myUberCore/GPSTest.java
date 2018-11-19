package myUberCore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class GPSTest {

	@Test
	void testDistance() {
		GPS A=new GPS(0,0);
		GPS B=new GPS(1,1);
		assertTrue(A.distance(B)==2);
	}

	@Test
	void testGetRideDistance() {
		GPS A=new GPS(0,0);
		GPS B=new GPS(2,2);
		GPS C=new GPS(1,2);
		GPS D=new GPS(1,1);
		GPS E=new GPS(-1,0);
		GPS F=new GPS(-1,1);
		GPS car = new GPS(1,1);
		ArrayList<GPS> AA = new ArrayList<GPS>();
		ArrayList<GPS> BB = new ArrayList<GPS>();
		AA.add(A);
		AA.add(B);
		AA.add(C);
		BB.add(D);
		BB.add(E);
		BB.add(F);
		assertTrue(car.getRideDistance(AA, BB)==10);
	}

	@Test
	void testGetRideFinal() {
		GPS A=new GPS(0,0);
		GPS B=new GPS(2,2);
		GPS C=new GPS(1,2);
		GPS D=new GPS(1,1);
		GPS E=new GPS(-1,0);
		GPS F=new GPS(-1,1);
		GPS car = new GPS(1,1);
		ArrayList<GPS> AA = new ArrayList<GPS>();
		ArrayList<GPS> BB = new ArrayList<GPS>();
		AA.add(A);
		AA.add(B);
		AA.add(C);
		BB.add(D);
		BB.add(E);
		BB.add(F);
		assertTrue((car.getRideFinal(AA, BB).getX()==D.getX())&&(car.getRideFinal(AA, BB).getY()==D.getY()));
	}

}
