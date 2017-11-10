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
		Event setTransactionEvent = new Event();
		setTransactionEvent.setWorker(this.activeWorker);
		setTransactionEvent.setPatron(this.activePatron);
		
		if(transactionType.equalsIgnoreCase("out") || transactionType.equalsIgnoreCase("in")) {
			this.transactionType = transactionType;
			setTransactionEvent.setAction("Transaction Type of " + this.transactionType + " successfully set");
			log.logEvent(setTransactionEvent);
			return true;
		} else {
			setTransactionEvent.setAction("Transaction Type of " + this.transactionType + " failed to set");
			log.logEvent(setTransactionEvent);
			return false;
		}
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

	public boolean validateAndSetPatron(String patronID) {
		Event validatePatron = new Event();
		validatePatron.setWorker(this.activeWorker);
		
		if (this.db.validatePatronID(patronID)) {
			this.setActivePatron(startTransaction(patronID));
			//this.activePatron = startTransaction(patronID);
			validatePatron.setPatron(this.activePatron);
			validatePatron.setAction("Validated and Set Patron: " + patronID);
			log.logEvent(validatePatron);
			
			return true;
		} else {
			validatePatron.setAction("Failed to Validated and Set Patron: " + patronID);
			log.logEvent(validatePatron);

			return false;
		}
	}

	public Copy checkOutCopy(String copyId) {
		this.activeCopy = this.db.getCopy(copyId);
		checkOutQueue.add(this.activeCopy);
		return this.activeCopy;
	}

	public boolean validateAndCheckOutCopy(String copyID) {
		Event copyCheckoutEvent = new Event();
		copyCheckoutEvent.setWorker(this.activeWorker);
		copyCheckoutEvent.setPatron(this.getActivePatron());
		
		if (this.db.validateCopyID(copyID)) {

			copyCheckoutEvent.setCopy(this.checkOutCopy(copyID));
			copyCheckoutEvent.setAction("Copy Validated and Successfully Set, Copy ID: " + copyID);
			
			log.logEvent(copyCheckoutEvent);
			return true; 
			
		} else {
			copyCheckoutEvent.setAction("Copy Failed to Validate, Copy ID: " + copyID);
			return false;
		}
	}
	
	public Event getLastEvent() {
		return log.getEvent(lastEventKey);
	}
	
	public boolean validateAndLoginWorker(String workerID) {
		Event workerLoginEvent = new Event();
		
		if (this.db.validateWorkerID(workerID)) {
			this.activeWorker = this.loginWorker(workerID);
			
			workerLoginEvent.setWorker(this.activeWorker);
			workerLoginEvent.setAction("Worker Login Successful for WorkerID: " + workerID);
			
			log.logEvent(workerLoginEvent);
			return true;
			
		} else {
			workerLoginEvent.setAction("Worker Login Unsuccessful for WorkerID: " + workerID);
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
	
	public void completeSession() throws HoldException {
		while (checkOutQueue.size() > 0) {
			Copy c = checkOutQueue.poll();
			c.checkOut();
			//StdOut.println(c.toString());
			this.activePatron.checkOutCopy(c);
			Event event = new Event(this.activeWorker, this.activePatron, c, transactionType);
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