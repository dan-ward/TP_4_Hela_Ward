import java.util.Calendar;
import java.util.Date;

public class Copy {
	private String id;
	private Textbook textbook;
	private Boolean isCheckedOut;
	private Calendar dueDate;
		
	public Copy() {
		this.id = "";
		this.textbook = null;
		this.isCheckedOut = false;
		this.dueDate = null;
	}
	
	public Copy(String id, String title) {
		this.id = id;
		this.textbook = new Textbook(title);
		this.isCheckedOut = false;
		this.dueDate = null;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setCopyId(String id) {
		this.id = id;
	}
	
	public Date getDueDate() {
		return this.dueDate.getTime();
	}
	
	public void setDueDate(Calendar calendar) {
		dueDate = calendar;
	}
	
	public void checkOut() {
		this.isCheckedOut = true;
		this.dueDate = Calendar.getInstance();
		this.dueDate.add(Calendar.DATE, 14);
	}
	
	public boolean isCheckedOut() {
		return this.isCheckedOut;
	}
	
	public String toString() {
		return this.id;
	}
	
	public String getTitle() {
		return textbook.getTitle();
	}
}
