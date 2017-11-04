import java.util.HashMap;
import java.util.Map;

public class FakeDB {
	
	private static Map<String, Patron> patronStore;
	private static Map<String, Copy> copyStore;
	
	static {
		copyStore = new HashMap<String, Copy>();
		copyStore.put("C1", new Copy("abc123", false, null));
		
		patronStore = new HashMap<String, Patron>();
		patronStore.put("P1", new Patron("123abc", "Test Patron"));
	}
	
	public Copy getCopy(String key) {
		return copyStore.get(key);
	}
	
	public Patron getPatron(String key) {
		return patronStore.get(key);
	}

}
