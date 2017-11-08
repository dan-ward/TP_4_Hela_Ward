import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeDB {
	
	private static Map<String, Patron> patronStore;
	private static Map<String, Copy> copyStore;
	private static Map<String, Worker> workerStore;
	
	static {
		copyStore = new HashMap<String, Copy>();
		copyStore.put("C1", new Copy("abc123", "This is a Test Title"));
		copyStore.put("abc123", new Copy("abc123", "This is a Test Title"));
		copyStore.put("abc124", new Copy("abc124", "This is a Test Title"));
		copyStore.put("C2", new Copy("C2", "This is a Test Title"));
		
		patronStore = new HashMap<String, Patron>();
		patronStore.put("P1", new Patron("123abc", "Test Patron"));
		patronStore.put("123abc", new Patron("123abc", "Test Patron"));
		patronStore.put("P2", new Patron("P2", "Test Patron"));
		
		workerStore = new HashMap<String, Worker>();
		workerStore.put("W1",  new Worker("a1b2c3", "Test Worker"));
		workerStore.put("a1b2c3",  new Worker("a1b2c3", "Test Worker"));
		workerStore.put("W2",  new Worker("W2", "Test Worker"));
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
	
	public void checkInAllCopies() {
		for(Copy c: copyStore.values()) {
			c.checkIn();
		}
	}
	
	public List<Copy> getAllCheckedOutCopies() {
		List<Copy> checkedOutCopies = new ArrayList<Copy>();
		
		for(Copy c: copyStore.values()) {
			if(c.isCheckedOut()) {
				checkedOutCopies.add(c);
			}
		}
		
		return checkedOutCopies;
	}

}
