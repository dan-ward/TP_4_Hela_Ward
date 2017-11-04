
public class Controller {
	FakeDB db = new FakeDB();
	Patron activePatron;
	String transactionType;
	Copy activeCopy;

	public Controller() {
		this.db = new FakeDB();
	}
	
	public String startTransaction(String patronId) {
		this.activePatron = this.db.getPatron(patronId);
		System.out.println(patronId);
		System.out.println(this.activePatron.getName());
		return this.activePatron.getName();
	}
	
	public Boolean setTransactionType(String transactionType) {
		if(transactionType == "out" || transactionType == "in") {
			this.transactionType = transactionType;
			return true;
		}
		return false;
	}
	
	public String getTransactionType() {
		return this.transactionType;
	}
	
	public Patron getActivePatron() {
		return this.activePatron;
	}

	public void setActivePatron(Patron activePatron) {
		this.activePatron = activePatron;
	}
	
	public String checkOutCopy(String copyId) {
		this.activeCopy = this.db.getCopy(copyId);
		return this.activeCopy.getTitle();
	}

}
