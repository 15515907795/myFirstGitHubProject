package SJWParkinglot;

public class ParkingManager extends ParkingBoy{
	public ParkingManager(ParkingLot... parkingLots) {
		super(parkingLots);
	}
	public ParkingManager(ParkingBoy...parkingBoys) {
	
	}

	@Override
	public Ticket park(Car car) {
		float left = 0;
		for (ParkingLot parkingLot : parkingLots) {
			if (cars.size() >= capacity) {
				return parkingLot.park(car);
			}
		}
		throw new FullException();
	}
}
