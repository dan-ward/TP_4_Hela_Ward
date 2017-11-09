import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
		
		assertEquals("patron toString() failure", "P1", patron.toString());
	}
	
	@Test
	public void test_patron_check_in_copy() {
		FakeDB db = new FakeDB();
		Patron patron = db.getPatron("P1");
		Copy copy = db.getCopy("C1");
		
		patron.checkOutCopy(copy);
		
		assertEquals("copy not checked out, in correctly", true, patron.checkInCopy(copy));
	}
	
	@Test
	public void test_get_checked_out_copy_count() {
		FakeDB db = new FakeDB();
		Patron patron = db.getPatron("P1");
		Copy copy1 = db.getCopy("C1");
		Copy copy2 = db.getCopy("C2");
		
		patron.checkOutCopy(copy1);
		patron.checkOutCopy(copy1);
		
		assertEquals("patron should have 2 copies checked out", 2, patron.getCheckedOutCopyCount());
	}

	@Test
	public void test_patron_hold() {
		Textbook textbook = new Textbook("Test hold title");
		Copy copy = new Copy("C3", textbook);
		Hold hold = new Hold(copy, "Overdue book");
		Patron patron = new Patron("P3", "Test hold patron");

		assertEquals("patron should have 0 holds", 0, patron.getHolds().size());
		
		patron.addHold(hold);		
		List<Hold> holds = new ArrayList<Hold>();
		holds.add(hold);

		assertEquals("patron should have 1 hold", 1, patron.getHolds().size());
		assertEquals("hold lists should match", holds, patron.getHolds());
		
		patron.removeHold(hold);
		holds.remove(hold);
		
		assertEquals("patron should have 0 holds", 0, patron.getHolds().size());
	}
	
	@Test
	public void test_get_checked_out_copy_out() {
		Textbook textbook = new Textbook("Test hold title");
		Copy copy1 = new Copy("C1", textbook);
		Copy copy2 = new Copy("C2", textbook);
		Hold hold = new Hold(copy1, "Overdue book");
		Patron patron = new Patron("P3", "Test hold patron");
		
		patron.checkOutCopy(copy1);
		patron.addHold(hold);
		patron.checkOutCopy(copy2);
		
		assertEquals("patron should have 2 copies checked out", 2, patron.getCheckedOutCopyCount());
	}	
	
}
