import java.util.Calendar;
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
	
	public static void main(String[] args) {
		Log l = new Log();
		
		l.logEvent(new Event(new Patron(), new Copy()));
		
		for(Integer key: log.keySet()) {
			System.out.println(key + " - " + log.get(key));
		}
	}
	
}
