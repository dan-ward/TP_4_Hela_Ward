import java.util.Calendar;
import java.util.Date;

public class Event {
	private Calendar eventDateTime;
	private Patron patron;
	private Copy copy;
	
	private Date date;
	
	public Event(Patron patron, Copy copy) {
		this.eventDateTime = Calendar.getInstance();
		this.patron = patron;
		this.copy = copy;
	}
	
	public Calendar getEventDateTime() {
		return eventDateTime;
	}

	public void setEventDateTime(Calendar eventDateTime) {
		this.eventDateTime = eventDateTime;
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
	
	public String toString() {
		return eventDateTime.getTime()
				+ "\n\t" + patron.toString() 
				+ "\n\t" + copy.toString();
	}



}
