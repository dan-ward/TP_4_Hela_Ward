import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class EventTest {

	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void test_create_event() {
		FakeDB db = new FakeDB();		
		Copy copy = db.getCopy("C1");
		Patron patron = db.getPatron("P1");
		Worker worker = db.getWorker("W1");
		
		Event event = new Event(worker, patron, copy, "Check Out");
		
		assertNotNull("event is null", event);
	}

	@Test
	public void test_get_event() {
		FakeDB db = new FakeDB();		
		Copy copy = db.getCopy("C1");
		Patron patron = db.getPatron("P1");
		Worker worker = db.getWorker("W1");
		
		Event event = new Event(worker, patron, copy, "Check In");
			
		assertEquals("event Patron not correct", patron, event.getPatron());
		assertEquals("event Copy not correct", copy, event.getCopy());
	}
	
	@Test
	public void test_event_to_string() {
		FakeDB db = new FakeDB();		
		Copy copy = db.getCopy("C1");
		Patron patron = db.getPatron("P1");
		Worker worker = db.getWorker("W1");
		String action = "Check Out";
		
		Event event = new Event(worker, patron, copy, action);		
		
		assertEquals("event not as expected", event.getEventDateTime().getTime()
				+ "\n\t" + worker.toString()
				+ "\n\t" + patron.toString() 
				+ "\n\t" + copy.toString()
				+ "\n\t" + action, event.toString());
	}

}
