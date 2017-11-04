import static org.junit.Assert.*;

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
		
		String patronName = controller.startTransaction(patronId);
		
		assertEquals("patron name not as expected", "Test Patron", patronName);
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
	
	public void test_check_out_copy() {
		Controller controller = new Controller();
		controller.startTransaction("123abc");
		controller.setTransactionType("out");
		
		String copyId = "abc123";
		
		controller.checkOutCopy(copyId);
		assertEquals("check out failure", "This is a Test Title", controller.checkOutCopy(copyId));
	}

}
