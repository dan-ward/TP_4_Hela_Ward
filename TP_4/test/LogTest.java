import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LogTest {

	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void test_log_event() {
		FakeDB db = new FakeDB();		
		Copy c = db.getCopy("C1");
		Patron p = db.getPatron("P1");
		Worker w = db.getWorker("W1");
		Event event = new Event(w, p, c);
		Log log = new Log();
		
		int key = log.logEvent(event);
		
		assertEquals("event", event, log.getEvent(key));
	}
	
	@Test
	public void test_log_event2() {
		FakeDB db = new FakeDB();		
		Copy c = db.getCopy("C1");
		Patron p = db.getPatron("P1");
		Worker w = db.getWorker("W1");
		Event event = new Event(w, p, c);
		Log log = new Log();
		
		int key = log.logEvent(event);
		
		assertEquals("event", event, log.getEvent(key));
	}	

}
