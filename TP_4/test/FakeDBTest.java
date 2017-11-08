import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class FakeDBTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_create_fake_db() {
		FakeDB db = new FakeDB();
		assertNotNull("FakeDB is null", db);
	}
	
	@Test
	public void test_get_patron() {
		FakeDB db = new FakeDB();
		Patron p = db.getPatron("P1");
		assertNotNull("Patron is null", p);
		assertEquals("Patron Id id not as expected", "P1", p.getId());
	}	
	
	@Test
	public void test_get_copy() {
		FakeDB db = new FakeDB();
		Copy c = db.getCopy("C1");
		assertNotNull("Copy is null", c);
		assertEquals("Copy id not as expected", "C1", c.getId());
	}
	
	@Test
	public void test_get_worker() {
		FakeDB db = new FakeDB();
		Worker worker = db.getWorker("W1");
		assertNotNull("worker is null", worker);
		assertEquals("worker id not as expected", "W1", worker.getId());
	}

	@Test
	public void test_get_invalid_worker() {
		FakeDB db = new FakeDB();
		Worker worker = db.getWorker("W9");
		assertNull("worker is not null", worker);
	}
}
