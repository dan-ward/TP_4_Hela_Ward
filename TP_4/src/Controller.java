
public class Controller {
	FakeDB db = new FakeDB();
	Patron activePatron;
	String transactionType;
	Copy activeCopy;
	Worker activeWorker;
	
	public Controller() {
		this.db = new FakeDB();
	}
	
	public Patron startTransaction(String patronId) {
		this.activePatron = this.db.getPatron(patronId);
		return this.activePatron;
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
	
	public Copy checkOutCopy(String copyId) {
		this.activeCopy = this.db.getCopy(copyId);
		this.activeCopy.checkOut();
		return this.activeCopy;
	}

	public Worker loginWorker(String workerId) {
		this.activeWorker = this.db.getWorker(workerId);
		return this.activeWorker;
	}

}
