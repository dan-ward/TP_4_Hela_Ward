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
		this.dueDate = Calendar.getInstance();
	}
	
	public Copy(String id, String title, Calendar dueDate) {
		this.id = id;
		this.textbook = new Textbook(title);
		this.isCheckedOut = false;
		this.dueDate = dueDate;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setCopyId(String id) {
		this.id = id;
	}
	
	public Calendar getDueDate() {
		return this.dueDate;
	}
	
	public void setDueDate(Calendar calendar) {
		dueDate = calendar;
	}
	
	public void checkOut() {
		this.isCheckedOut = true;
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
