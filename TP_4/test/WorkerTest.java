import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WorkerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_worker_create() {
		Worker worker = new Worker();
		
		assertNotNull("worker is null", worker);
	}
	
	@Test
	public void test_worker_constructor() {
		Worker worker = new Worker("W1", "Bob");
		
		assertEquals("worker id not retrieved", "W1", worker.getId());
		assertEquals("worker id not retrieved", "Bob", worker.getName());
	}

}
