package myUberCore;

import java.util.Scanner;

public interface Observable {
	public void registerObserver(Observer observer);
	public void removeObserver(Observer observer);
	public void notifyObservers(Scanner reader);
	public void notifyObserversFinish(Scanner reader);
}
