import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Controller {
	FakeDB db = new FakeDB();
	Log log = new Log();
	int lastEventKey;
	Worker activeWorker;
	Patron activePatron;
	String transactionType;
	Copy activeCopy;
	Queue<Copy> checkOutQueue = new LinkedList<Copy>();
	
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
		checkOutQueue.add(this.activeCopy);
		return this.activeCopy;
	}
	
	public Event getLastEvent() {
		return log.getEvent(lastEventKey);
	}
	
	public boolean validateAndLoginWorker(String workerID) {
		if (this.db.validateWorkerID(workerID)) {
			this.activeWorker = this.loginWorker(workerID);
			return true;
		} else {
			return false;
		}
	}
	
	public Worker loginWorker(String workerId) {
		this.activeWorker = this.db.getWorker(workerId);
		return this.activeWorker;
	}
	
	public Queue<Copy> getCheckOutQueue() {
		return this.checkOutQueue;
	}
	
	public void completeSession() {
		while (checkOutQueue.size() > 0) {
			Copy c = checkOutQueue.poll();
			c.checkOut();
			StdOut.println(c.toString());
			this.activePatron.checkOutCopy(c);
			Event event = new Event(this.activeWorker, this.activePatron, c);
			lastEventKey = this.log.logEvent(event);
			checkOutQueue.remove(c);
		}
	}
	
	public Log getLog() {
		return this.log;
	}
	
	public void checkInAllCopies() {
		db.checkInAllCopies();
	}
	
	public List<Copy> getAllCheckedOutCopies() {
		return db.getAllCheckedOutCopies();
	}
}