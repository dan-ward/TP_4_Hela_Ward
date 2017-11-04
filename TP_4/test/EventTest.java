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
		
		Event event = new Event(patron, copy);
		
		assertNotNull("event is null", event);
	}

	@Test
	public void test_get_event() {
		FakeDB db = new FakeDB();		
		Copy copy = db.getCopy("C1");
		Patron patron = db.getPatron("P1");
		
		Event event = new Event(patron, copy);
			
		assertEquals("event Patron not correct", patron, event.getPatron());
		assertEquals("event Copy not correct", copy, event.getCopy());
	}
	
	@Test
	public void test_event_to_string() {
		FakeDB db = new FakeDB();		
		Copy copy = db.getCopy("C1");
		Patron patron = db.getPatron("P1");
		
		Event event = new Event(patron, copy);		
		
		assertEquals("event not as expected", event.getEventDateTime().getTime()
				+ "\n\t" + patron.toString() 
				+ "\n\t" + copy.toString(), event.toString());
	}

}
