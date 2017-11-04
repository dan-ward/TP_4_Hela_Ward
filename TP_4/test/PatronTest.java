import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PatronTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void test_create_patron() {
		Patron p = new Patron();
		assertNotNull("Patron is null", p);
	}
	
	@Test
	public void test_set_patron_id() {
		Patron p = new Patron();
		p.setId("123abc");
		assertEquals("patronId not set", "123abc", p.getId());
	}
	
	@Test
	public void test_patron_to_string() {
		FakeDB db = new FakeDB();		
		Patron patron = db.getPatron("P1");
		
		assertEquals("patron toString() failure", "123abc", patron.toString());
	}

}
