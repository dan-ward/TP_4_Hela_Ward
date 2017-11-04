import java.util.HashMap;
import java.util.Map;

public class FakeDB {
	
	private static Map<String, Patron> patronStore;
	private static Map<String, Copy> copyStore;
	private static Map<String, Worker> workerStore;
	
	static {
		copyStore = new HashMap<String, Copy>();
		copyStore.put("C1", new Copy("abc123", "This is a Test Title", null));
		copyStore.put("abc123", new Copy("abc123", "This is a Test Title", null));
		
		patronStore = new HashMap<String, Patron>();
		patronStore.put("P1", new Patron("123abc", "Test Patron"));
		patronStore.put("123abc", new Patron("123abc", "Test Patron"));
		
		workerStore = new HashMap<String, Worker>();
		workerStore.put("W1",  new Worker("a1b2c3", "Test Worker"));
		workerStore.put("a1b2c3",  new Worker("a1b2c3", "Test Worker"));
	}
	
	public Copy getCopy(String key) {
		return copyStore.get(key);
	}
	
	public Patron getPatron(String key) {
		return patronStore.get(key);
	}
	
	public Worker getWorker(String key) {
		return workerStore.get(key);
	}

}
