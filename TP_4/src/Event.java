import java.util.Calendar;

public class Event {
	private Calendar eventDateTime;
	private Worker worker;
	private Patron patron;
	private Copy copy;
	private String action;
	
	public Event(Worker worker, Patron patron, Copy copy, String action) {
		this.eventDateTime = Calendar.getInstance();
		this.worker = worker;
		this.patron = patron;
		this.copy = copy;
		this.action = action;
	}
	
	public Event() {
		this.eventDateTime = Calendar.getInstance();
	}
	
	public Calendar getEventDateTime() {
		return eventDateTime;
	}

	public void setEventDateTime(Calendar eventDateTime) {
		this.eventDateTime = eventDateTime;
	}
	
	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public Patron getPatron() {
		return patron;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}
	
	public Copy getCopy() {
		return copy;
	}

	public void setCopy(Copy copy) {
		this.copy = copy;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String toString() {
		return eventDateTime.getTime()
				+ "\n\t" + worker.toString()
				+ "\n\t" + patron.toString() 
				+ "\n\t" + copy.toString()
				+ "\n\t" + action;
	}
}