import java.util.HashMap;
import java.util.Map;

public class Log {

	private static Map<Integer, Event> log;
	private Integer nextKey = 1;
	
	static {
		log = new HashMap<Integer, Event>();
	}
	
	public Integer logEvent(Event event) {
		log.put(nextKey, event);
		return nextKey++;
	}
	
	public Event getEvent(int key) {
		return log.get(key);
	}
	
}
