/**
 * @author - Andrew Edwards
 * A class to create, manipulate, and sort the event list
 */
package event_simulation;

import java.util.*;

import event_simulation.Event.EventType;

public class EventList {

	private LinkedList<Event> eventQueue = new LinkedList<Event>();

	/**
	 * Checks to see if the eventQueue is empty
	 * @return True if the eventQueue is empty; otherwise returns false
	 */
	public boolean isEmpty() {
		return eventQueue.size() == 0;
	}

	/**
	 * Inserts a new event into the eventQueue in the correct order based on arrival and departure
	 * @param givenEvent The given event
	 */
	public void insert(Event givenEvent) {
		if (eventQueue.size() > 0) {
			if (eventQueue.peek().getTime() > givenEvent.getTime()) {
				Event temp = eventQueue.poll();
				eventQueue.add(givenEvent);
				eventQueue.add(temp);	
			}
			else if (eventQueue.peek().getTime() == givenEvent.getTime() &&
					eventQueue.peek().getType() == EventType.DEPARTURE &&
					givenEvent.getType() == EventType.ARRIVAL) {
				Event temp = eventQueue.poll();
				eventQueue.add(givenEvent);
				eventQueue.add(temp);
			}
			
			else {
				eventQueue.add(givenEvent);
			}
		}
		
		else {
			eventQueue.add(givenEvent);
		}
	}

	/**
	 * Removes an event from the eventQueue
	 * @param givenEvent The given event
	 */
	public void remove(Event givenEvent) {
		eventQueue.remove();
	}

	/**
	 * Retrieves the first event in the EventQueue
	 * @return The first event in the eventQueue
	 */
	public Event retrieve() {
		return eventQueue.peek();	
	}
}
