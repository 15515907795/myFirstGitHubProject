package SJWParkinglot;

public class SmartParkingBoy extends ParkingBoy {

	public SmartParkingBoy(ParkingLot... parkingLots) {
		super(parkingLots);
	}

	@Override
	public Ticket park(Car car) {
		float left = 0;
		for (ParkingLot parkingLot : parkingLots) {
			if ((capacity - cars.size()) > left) {
				return parkingLot.park(car);
			}
		}
		throw new FullException();
	}
}