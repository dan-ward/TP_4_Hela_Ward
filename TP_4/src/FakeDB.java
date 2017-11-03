import java.util.HashMap;
import java.util.Map;

public class FakeDB {
	
	private static Map<String, Copy> copyStore;
//	private static Map<String, Patron> patronStore;
	
	static {
		copyStore = new HashMap<String, Copy>();
//		patronStore = new HashMap<String, Patron>();
		
		copyStore.put("C1", new Copy("abc123", false, null));
	}
	
	public Copy getCopy(String key) {
		return copyStore.get(key);
	}

}
