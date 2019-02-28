package SJWParkinglot;

public class GraduateParkingBoy extends ParkingBoy {

	public GraduateParkingBoy(ParkingLot... parkingLots) {
		super(parkingLots);
	}

	public Ticket park(Car car) {
		for (ParkingLot parkingLot : parkingLots) {
			if (cars.size() <= capacity) {
				return parkingLot.park(car);
			}
		}
		throw new FullException();
	}

	public Car pickup(Ticket ticket) {
		for (ParkingLot parkingLot : parkingLots) {
			if (cars.containsKey(ticket)) {
				return cars.remove(ticket);
			}
		}
		throw new NotExist();
	}
}
