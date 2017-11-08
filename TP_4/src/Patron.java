import java.util.ArrayList;
import java.util.List;

public class Patron {

	private String id;
	private String name;
	private List<Copy> checkedOutCopies = new ArrayList<Copy>();
	
	public Patron() {
		this.id = "";
		this.name = "";
	}
	
	public Patron(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	
	public String toString() {
		return this.id;
	}
	
	public void addCheckedOutCopy(Copy copy) {
		checkedOutCopies.add(copy);
	}
	
	public boolean checkInCopy(Copy copy) {
		return checkedOutCopies.remove(copy);
	}
	
	public int getCheckedOutCopyCount() {
		return checkedOutCopies.size();
	}
	
	public List<Copy> getCheckedOutCopies() {
		return checkedOutCopies;
	}
}
