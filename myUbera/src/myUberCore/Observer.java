package myUberCore;

import java.util.Scanner;

public interface Observer {
	public void update(Ride ride,Scanner reader);
	public void updateFinish(Ride ride,Scanner reader);
}
