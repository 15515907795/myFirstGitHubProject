package SJWParkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
	private int capacity;

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	private Integer size;
	private Car car;
	private Map<Ticket, Car> cars = new HashMap<Ticket, Car>();

	public Integer getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public ParkingLot(int capacity) {
		this.capacity = capacity;
	}

	public Ticket park(Car car) {
		if (cars.size() >= capacity) {
			throw new FullException();
		}

		Ticket ticket = new Ticket();
		cars.put(ticket, car);

		return ticket;
	}

	public Car pickup(Ticket ticket) {
		if (!cars.containsKey(ticket)) {
			throw new NotExist();
		}
		return cars.remove(ticket);
	}
}
