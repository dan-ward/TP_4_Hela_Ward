
public class Controller {
	FakeDB db = new FakeDB();
	Patron activePatron;
	String transactionType;
	Copy activeCopy;
	Worker activeWorker;
	Log log = new Log();
	int lastEventKey;
	
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
		this.activePatron.addCheckedOutCopy(this.activeCopy);
		
		Event event = new Event(this.activeWorker, this.activePatron, this.activeCopy);
		lastEventKey = this.log.logEvent(event);
		
		return this.activeCopy;
	}
	
	public Event getLastEvent() {
		return log.getEvent(lastEventKey);
	}

	public Worker loginWorker(String workerId) {
		this.activeWorker = this.db.getWorker(workerId);
		return this.activeWorker;
	}

}
