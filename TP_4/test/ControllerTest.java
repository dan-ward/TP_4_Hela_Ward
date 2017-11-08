import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

public class ControllerTest {

	@Before
	public void setUp() throws Exception {
		Controller controller = new Controller();
		controller.checkInAllCopies();
	}

	@Test
	public void test_start_transaction() {
		String patronId = "123abc";
		Controller controller = new Controller();
		
		Patron patron = controller.startTransaction(patronId);
		
		assertEquals("patron name not as expected", "Test Patron", patron.getName());
	}
	
	@Test
	public void test_set_transaction_type() {
		Controller controller = new Controller();
		
		controller.setTransactionType("out");
		assertEquals("controller transaction type != out", "out", controller.getTransactionType());
		
		controller.setTransactionType("in");
		assertEquals("controller transaction type != in", "in", controller.getTransactionType());
		
		controller.setTransactionType("out");
		assertEquals("controller transaction type != out", false, controller.setTransactionType("bad transaction type"));
	}
	
	@Test
	public void test_check_out_copy() {
		Controller controller = new Controller();
		
		Patron patron = controller.startTransaction("123abc");
		
		controller.setTransactionType("out");
		
		Copy copy = controller.checkOutCopy("abc123");
		
		controller.completeSession();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 14);
		
		assertEquals("check out failure", "This is a Test Title", copy.getTitle());
		assertEquals("check out should set copy's isCheckedOut", true, copy.isCheckedOut());
		
		assertEquals("copy should be due in 14 days", calendar.getTime().toString(), copy.getDueDate().toString());
		
		assertEquals("patron should have 1 copy checked out", 1, patron.getCheckedOutCopyCount());
		assertEquals("patron's copy should match this copy", true, patron.checkInCopy(copy));
	}
	
	@Test
	public void test_log_event() {
		Controller controller = new Controller();
		Worker worker = controller.loginWorker("a1b2c3");
		Patron patron = controller.startTransaction("123abc");
		controller.setTransactionType("out");	
		Copy copy = controller.checkOutCopy("abc123");
		
		controller.completeSession();

		Event event = new Event(worker, patron, copy);
		
		assertEquals("event should contain worker, patron, copy", event.toString(), controller.getLastEvent().toString());
	}

	@Test
	public void test_login_worker() {
		String workerId = "W1";
		Controller controller = new Controller();
		
		Worker worker = controller.loginWorker(workerId);
		
		assertEquals("worker name not as expected", "Test Worker", worker.getName());
	}
	
	@Test
	public void test_add_copy_to_check_out_queue() {
		Controller controller = new Controller();
		Worker worker = controller.loginWorker("W2");
		Patron patron = controller.startTransaction("P2");
		controller.setTransactionType("out");
		Copy copy = controller.checkOutCopy("C2");
		
		Queue<Copy> checkOutQueue = new LinkedList<Copy>();
		checkOutQueue.add(copy);
		
		assertEquals("check out queue does not match expected value", checkOutQueue, controller.getCheckOutQueue());
	}
	
	@Test
	public void test_complete_session() {
		Controller controller = new Controller();
		Worker worker = controller.loginWorker("W2");
		Patron patron = controller.startTransaction("P2");
		controller.setTransactionType("out");
		Queue<Copy> checkOutQueue = new LinkedList<Copy>();
		Copy copy1 = controller.checkOutCopy("C1");
		checkOutQueue.add(copy1);
		Copy copy2 = controller.checkOutCopy("C2");
		checkOutQueue.add(copy2);
		
		StdOut.print(copy1);
		
		assertEquals("check out queue should have 2 copies", 2, controller.getCheckOutQueue().size());
		assertEquals("copy 1 shouldn't be checked out yet", false, copy1.isCheckedOut());

		controller.completeSession();
		
		assertEquals("copy 1 should be checked out", true, copy1.isCheckedOut());
		assertEquals("copy 2 should be checked out", true, copy2.isCheckedOut());
		checkOutQueue.poll();
		checkOutQueue.poll();
		assertEquals("the check out queue shoudl be empty", checkOutQueue, controller.getCheckOutQueue());
	}
	
	@Test
	public void test_get_log() {
		Log log = new Log();
		
		Controller controller = new Controller();
		Worker worker = controller.loginWorker("W2");
		Patron patron = controller.startTransaction("P2");
		controller.setTransactionType("out");
		Queue<Copy> checkOutQueue = new LinkedList<Copy>();
		Copy copy1 = controller.checkOutCopy("C1");
		log.logEvent(new Event(worker, patron, copy1));
		checkOutQueue.add(copy1);
		Copy copy2 = controller.checkOutCopy("C2");
		log.logEvent(new Event(worker, patron, copy2));
		checkOutQueue.add(copy2);
		controller.completeSession();		
		
		assertEquals("logs should match", log.toString(), controller.getLog().toString());
	}
	
	@Test
	public void test_check_in_all_copies() {
		Controller controller = new Controller();
		controller.checkInAllCopies();
		assertEquals("no copies should be checked out", 0, controller.getAllCheckedOutCopies().size());
	}
	
	
}
