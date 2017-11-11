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
//		Event setTransactionEvent = new Event();
//		setTransactionEvent.setWorker(this.activeWorker);
//		setTransactionEvent.setPatron(this.activePatron);
		
		if(transactionType.equalsIgnoreCase("out") || transactionType.equalsIgnoreCase("in")) {
			this.transactionType = transactionType;
			String action = "Transaction Type of " + this.transactionType + " successfully set";
			Event setTransactionType = new Event.EventBuilder(action)
					.worker(this.activeWorker)
					.patron(this.activePatron)
					.build();
			log.logEvent(setTransactionType);
			return true;
		} else {
			String action = "Transaction Type of " + this.transactionType + " failed to set";
			Event setTransactionType = new Event.EventBuilder(action)
					.worker(this.activeWorker)
					.patron(this.activePatron)
					.build();
			log.logEvent(setTransactionType);
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

	public String getActivePatronString() {
		return "Patron ID: " + this.activePatron.getId() + " Patron Name: " + this.activePatron.getName();
	}  

	public String getActiveCopyString() {
		return "Copy ID: " + this.activeCopy.getId() + " Title Name: " + this.activeCopy.getTitle();
	}  

	
	public boolean validateAndSetPatron(String patronID) {
//		Event validatePatron = new Event();
//		validatePatron.setWorker(this.activeWorker);
		
		if (this.db.validatePatronID(patronID)) {
			this.setActivePatron(startTransaction(patronID));
			String action = "Validated and Set Patron: " + patronID;
			Event validatePatron = new Event.EventBuilder(action)
					.worker(this.activeWorker)
					.patron(this.activePatron)
					.build();
			log.logEvent(validatePatron);
			return true;
		} else {
			String action = "Failed to Validated and Set Patron: " + patronID;
			Event validatePatron = new Event.EventBuilder(action)
					.worker(this.activeWorker)
					.patron(this.activePatron)
					.build();
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
//		Event copyCheckoutEvent = new Event();
//		copyCheckoutEvent.setWorker(this.activeWorker);
//		copyCheckoutEvent.setPatron(this.getActivePatron());
		
		if (this.db.validateCopyID(copyID)) {
			this.activeCopy = this.checkOutCopy(copyID);
			String action = "Copy Validated and Successfully Set, Copy ID: " + copyID; 
			Event checkOutCopy = new Event.EventBuilder(action)
					.worker(this.activeWorker)
					.patron(this.activePatron)
					.copy(this.activeCopy)
					.build();
			log.logEvent(checkOutCopy);
			return true; 
		} else {
			String action = "Copy Failed to Validate, Copy ID: " + copyID;
			Event checkOutCopy = new Event.EventBuilder(action)
					.worker(this.activeWorker)
					.patron(this.activePatron)
					.copy(this.activeCopy)
					.build();
			log.logEvent(checkOutCopy);	
			return false;
		}
	}
	
	public Event getLastEvent() {
		return log.getEvent(lastEventKey);
	}
	
	public boolean validateAndLoginWorker(String workerID) {
//		Event workerLoginEvent = new Event();
		
		if (this.db.validateWorkerID(workerID)) {
			this.activeWorker = this.loginWorker(workerID);
			
			String action = "Worker Login Successful for WorkerID: " + workerID;
			Event workerLogin = new Event.EventBuilder(action)
					.worker(this.activeWorker)
					.patron(this.activePatron)
					.copy(this.activeCopy)
					.build();
			log.logEvent(workerLogin);
			return true;
			
		} else {
			String action = "Worker Login Unsuccessful for WorkerID: " + workerID;
			Event workerLogin = new Event.EventBuilder(action)
					.worker(this.activeWorker)
					.patron(this.activePatron)
					.copy(this.activeCopy)
					.build();
			log.logEvent(workerLogin);
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
			this.activeCopy = c;
			this.activePatron.checkOutCopy(this.activeCopy);
//			Event event = new Event(this.activeWorker, this.activePatron, c, transactionType);
			String action = "Complete Session - Check Out";
			Event completeSession = new Event.EventBuilder(action)
					.worker(this.activeWorker)
					.patron(this.activePatron)
					.copy(this.activeCopy)
					.build();
			lastEventKey = this.log.logEvent(completeSession);
			checkOutQueue.remove(this.activeCopy);
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