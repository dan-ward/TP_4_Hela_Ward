import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class CopyTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_create_copy() {
		Copy c = new Copy();
		assertNotNull("Copy is null", c);
	}
	
	@Test
	public void test_get_copy_Id() {
		Copy c = new Copy();
		assertNotNull("copy id is null", c.getId());
	}
	
	@Test
	public void test_set_copy_id() {
		Copy c = new Copy();
		c.setCopyId("abc123");
		assertEquals("copy id not set", "abc123", c.getId());
	}
	
	@Test
	public void test_get_due_date() {
		Copy c = new Copy();
		assertNotNull("dueDate is null", c.getDueDate());
	}
	
	@Test
	public void test_set_due_date() {
		Copy c = new Copy();
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 14); 
		c.setDueDate(calendar);
		
		assertEquals("dueDate is not set", calendar.toString(), c.getDueDate().toString());
	}
	
	@Test
	public void test_is_checked_out() {
		Copy c = new Copy();
		assertNotNull("checkedOut is null", c.isCheckedOut());
	}
	
	@Test
	public void test_check_out() {
		Copy c = new Copy();
		c.checkOut();
		assertEquals("not checked out", true, c.isCheckedOut());
	}
	
	@Test
	public void test_copy_constructor() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 14);
		
		Copy c = new Copy("abc123", "This is a Test Title", calendar);
		
		assertEquals("copy constructor failure - id", "abc123", c.getId());
		assertEquals("copy constructor failure - isCheckedOut", false, c.isCheckedOut());
		assertEquals("copy constructor failure - dueDate", calendar.toString(), c.getDueDate().toString());
	}

	@Test
	public void test_copy_to_string() {
		FakeDB db = new FakeDB();		
		Copy copy = db.getCopy("C1");
		
		assertEquals("copy toString() failure", "abc123", copy.toString());
	}
}
