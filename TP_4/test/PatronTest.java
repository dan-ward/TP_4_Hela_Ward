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
	
	@Test
	public void test_patron_check_in_copy() {
		FakeDB db = new FakeDB();
		Patron patron = db.getPatron("P1");
		Copy copy = db.getCopy("C1");
		
		patron.addCheckedOutCopy(copy);
		
		assertEquals("copy not checked out, in correctly", true, patron.checkInCopy(copy));
	}
	
	@Test
	public void test_get_checked_out_copy_cout() {
		FakeDB db = new FakeDB();
		Patron patron = db.getPatron("P1");
		Copy copy1 = db.getCopy("abc123");
		Copy copy2 = db.getCopy("abc123");
		
		patron.addCheckedOutCopy(copy1);
		patron.addCheckedOutCopy(copy1);
		
		assertEquals("patron should have 2 copies checked out", 2, patron.getCheckedOutCopyCount());
		
		
		
	}

}
