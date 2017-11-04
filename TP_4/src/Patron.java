
public class Patron {

	private String id;
	private String name;
	
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
	
	public String toString() {
		return this.id;
	}
	
}
