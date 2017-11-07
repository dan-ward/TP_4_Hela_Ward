import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class ControllerTest {

	@Before
	public void setUp() throws Exception {
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
		
		assertEquals("check out failure", "This is a Test Title", copy.getTitle());
		assertEquals("check out should set copy's isCheckedOut", true, copy.isCheckedOut());
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 14);
		
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

		Event event = new Event(worker, patron, copy);
		
		assertEquals("event should contain worker, patron, copy", event.toString(), controller.getLastEvent().toString());
	}

	@Test
	public void login_worker() {
		String workerId = "W1";
		Controller controller = new Controller();
		
		Worker worker = controller.loginWorker(workerId);
		
		assertEquals("worker name not as expected", "Test Worker", worker.getName());
		
	}
	
	
}
