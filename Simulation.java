/**
 * @author - Andrew Edwards
 * A class that represents an event-driven simulation of a bank
 */
package event_simulation;

import java.io.*;
import java.util.*;

import event_simulation.Event.EventType;

public class Simulation {

	public static void main(String[] args) {
		LinkedList<Event> bankQueue = new LinkedList<Event>();
		EventList eventList = new EventList();
		String filename = "simulation_input.txt";
		Scanner inputStream = null;
		
		int peopleProcessed = 0;
		
		// Data in position 0 - currentTime 
		// Data in position 1 - waitingTime
		int[] data = new int[2];
		
		try {
			inputStream = new Scanner(new File(filename));
		}
		catch (FileNotFoundException e) {
			System.out.println("Error opening file " + filename);
			System.exit(1);
		}
		
		if (inputStream.hasNextLine()) {
			System.out.println("Simulation Begins");
			
			Event arrival = new Event(inputStream.nextInt(), inputStream.nextInt());
			data[0] = arrival.getTime();
			eventList.insert(arrival);
		}
		
		while(!eventList.isEmpty()) {
			Event newEvent = eventList.retrieve();
			
			if (newEvent.getType() == EventType.ARRIVAL) {
				data = processArrival(newEvent, inputStream, eventList, bankQueue, data);
				peopleProcessed++;
			}
			else {
				data = processDeparture(newEvent, eventList, bankQueue, data);
			}
		}
		
		inputStream.close();
		System.out.println("Simulation Ends");
		System.out.println();
		System.out.println("Final Statistics:");
		System.out.println("Total number of people processed: " + peopleProcessed);
		System.out.println("Average of time spent waiting: " + (double)data[1] / peopleProcessed);
	}
	
	/**
	 * Process an arrival event
	 * @param arrivalEvent The current arrival event
	 * @param inputStream The given inputStream
	 * @param eventList The current eventList
	 * @param bankQueue The current bankQueue
	 * @param data The current data stored in an array
	 * @return An array of data that consists of the current time in position 0 and waiting time in position 1
	 */
	public static int[] processArrival(Event arrivalEvent, Scanner inputStream, EventList eventList, LinkedList<Event> bankQueue, int[] data) {
		boolean atFront = bankQueue.isEmpty();
		
		bankQueue.add(arrivalEvent);
		eventList.remove(arrivalEvent);
		
		if (atFront) {
			Event newDeparture = new Event(arrivalEvent.getTime() + arrivalEvent.getTransaction());
			eventList.insert(newDeparture);
			data[0] = newDeparture.getTime();
		}
		
		if (inputStream.hasNextLine()) {
			Event arrival = new Event(inputStream.nextInt(), inputStream.nextInt());
			eventList.insert(arrival);
		}
		
		System.out.println("Processing an arrival event at time:\t" + arrivalEvent.getTime());
		return data;
		
	}
	
	/**
	 * Process a departure event
	 * @param departureEvent The current departure event
	 * @param eventList The current eventList
	 * @param bankQueue The current bankQueue
	 * @param data The current data stored in an array
	 * @return An array of data that consists of the current time in position 0 and waiting time in position 1
	 */
	public static int[] processDeparture(Event departureEvent, EventList eventList, LinkedList<Event> bankQueue, int[] data) {
		
		bankQueue.remove();
		eventList.remove(departureEvent);
		
		if (!bankQueue.isEmpty()) {
			data[1] += data[0] - bankQueue.peek().getTime();
			Event newDeparture = new Event(data[0] + bankQueue.peek().getTransaction());
			eventList.insert(newDeparture);
			data[0] = newDeparture.getTime();	
		}
		
		System.out.println("Processing a departure event at time:\t" + departureEvent.getTime());
		return data;
	}

}
