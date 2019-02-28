package SJWParkinglot;

public class SuperParkingBoy extends ParkingBoy {

	public SuperParkingBoy(ParkingLot... parkingLots) {
		super(parkingLots);
	}

	@Override
	public Ticket park(Car car) {
		float left = 0;
		for (ParkingLot parkingLot : parkingLots) {
			if ((capacity / cars.size()) > left) {
				return parkingLot.park(car);
			}
		}
		throw new FullException();
	}
}
