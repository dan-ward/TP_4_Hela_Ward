import java.util.Calendar;
import java.util.Date;

public class Copy {
	private String id;
	private Boolean isCheckedOut;
	private Calendar dueDate;
		
	public Copy() {
		this.id = "";
		this.isCheckedOut = false;
		this.dueDate = Calendar.getInstance();
	}
	
	public Copy(String id, boolean isCheckedOut, Calendar calendar) {
		this.id = id;
		this.isCheckedOut = isCheckedOut;
		this.dueDate = calendar;
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
}
