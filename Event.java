/**
 * @author - Andrew Edwards
 * This class for Event.
 */
package event_simulation;

public class Event {

	private int time;
	private int transaction;
	
	private EventType type;
	
	/**
	 * Constructor for departure with a given time
	 * @param givenTime The given time
	 */
	public Event (int givenTime) {
		this.time = givenTime;
		this.type = EventType.DEPARTURE;
	}
	
	/**
	 * Constructor for arrival with a given time and a given transaction time
	 * @param givenTime The given time
	 * @param givenTransaction The given transaction time
	 */
	public Event (int givenTime, int givenTransaction) {
		this.time = givenTime;
		this.transaction = givenTransaction;
		this.type = EventType.ARRIVAL;
	}
	
	/**
	 * Retrieve the time
	 * @return The time
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * Retrieve the transaction time
	 * @return The transaction time
	 */
	public int getTransaction() {
		return transaction;
	}
	
	/**
	 * Retrieve the event type
	 * @return The type
	 */
	public EventType getType() {
		return type;
	}
	
	/**
	 * Events that can occur
	 */
	public enum EventType {
	    ARRIVAL, 
	    DEPARTURE;
	 }
	
}
