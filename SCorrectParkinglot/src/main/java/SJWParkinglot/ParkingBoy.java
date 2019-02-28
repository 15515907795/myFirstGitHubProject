package SJWParkinglot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingBoy {
	private float size;
	private Car car;
	protected List<ParkingLot> parkingLots = new ArrayList<>();
	protected Map<Ticket, Car> cars = new HashMap<Ticket, Car>();
	protected float capacity;

	public float getCapacity() {
		return capacity;
	}

	public void setCapacity(float capacity) {
		this.capacity = capacity;
	}

	public ParkingBoy(ParkingLot... parkingLots) {
		this.parkingLots.addAll(Arrays.asList(parkingLots));
	}

	public Car pickup(Ticket ticket) {
		if (!cars.containsKey(ticket)) {
			throw new NotExist();
		}
		return cars.remove(ticket);
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Ticket park(Car car) {
		if (cars.size() >= capacity) {
			throw new FullException();
		}
		
		Ticket ticket = new Ticket();
		cars.put(ticket, car);
		
		return ticket;
	}
}
