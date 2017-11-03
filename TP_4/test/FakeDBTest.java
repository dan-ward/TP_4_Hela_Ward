import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FakeDBTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_create_fake_db() {
		FakeDB db = new FakeDB();
		assertNotNull("db is null", db);
	}
	
	@Test
	public void test_get_copy() {
		FakeDB db = new FakeDB();
		Copy c = db.getCopy("C1");
		assertNotNull("copy is null", c);
		assertEquals("copy id not as expected", "abc123", c.getId());
	}
	
	

}
