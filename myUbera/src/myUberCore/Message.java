package myUberCore;

public class Message {
	private String contenu;
	private String sender;
	private Time date;
	public Message(String contenu, String sender, Time date) {
		this.contenu = contenu;
		this.sender = sender;
		this.date = date;
	}
	
	//getters&setters
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public Time getDate() {
		return date;
	}
	public void setDate(Time date) {
		this.date = date;
	}
	
	
}
