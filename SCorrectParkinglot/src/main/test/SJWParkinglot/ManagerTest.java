package SJWParkinglot;

import static org.junit.Assert.assertSame;

import org.junit.Test;

public class ManagerTest {

	@Test
	public void should_park_car_when_lot_has_space_and_no_parking_boy() {
		ParkingLot parkingLot = new ParkingLot(1);
		ParkingManager parkingManager = new ParkingManager(parkingLot);
		Car car = new Car();
		Ticket ticket = parkingManager.park(car);

		assertSame(car, parkingLot.pickup(ticket));
	}

	@Test(expected = NotExist.class)
	public void should_get_mycar_when_park_mycar_into_parkinglot_and_only_have_manager() {
		ParkingLot parkingLot = new ParkingLot(2);
		ParkingManager parkingManager = new ParkingManager(parkingLot);
		parkingManager.park(new Car());

		Car myCar = new Car();
		Ticket ticket = parkingLot.park(myCar);

		assertSame(myCar, parkingManager.pickup(ticket));
	}

	@Test(expected = FullException.class)
	public void should_not_park_car_when_parking_lot_is_full_and_only_have_manager() {
		ParkingLot parkingLot = new ParkingLot(1);
		ParkingManager parkingManager = new ParkingManager(parkingLot);
		Car car1 = new Car();
		parkingManager.park(car1);

		parkingManager.park(new Car());
	}

	@Test(expected = FullException.class)
	public void should_park_car_when_one_manager_and_one_boy_and_have_space() {
		ParkingLot parkingLot = new ParkingLot(1);
		ParkingBoy parkingBoy = new SuperParkingBoy(parkingLot);
		ParkingManager parkingManager = new ParkingManager(parkingBoy);

		Car car = new Car();
		Ticket ticket = parkingManager.park(car);
		assertSame(car, parkingLot.pickup(ticket));
	}

	@Test(expected = FullException.class)
	public void should_park_when_have_two_boys_and_two_lots_and_first_is_full() {
		ParkingLot parkingLot1 = new ParkingLot(1);
		ParkingLot parkingLot2 = new ParkingLot(1);
		SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLot1);
		SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot2);
		ParkingManager parkingManager = new ParkingManager(superParkingBoy, smartParkingBoy);

		parkingLot1.park(new Car());

		Car car = new Car();
		Ticket ticket = parkingManager.park(car);
		assertSame(car, parkingLot2.pickup(ticket));
	}

	@Test(expected = FullException.class)
	public void should_park_when_have_two_boys_and_two_lots_and_second_is_full() {
		ParkingLot parkingLot1 = new ParkingLot(1);
		ParkingLot parkingLot2 = new ParkingLot(1);
		SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLot1);
		SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot2);
		ParkingManager parkingManager = new ParkingManager(superParkingBoy, smartParkingBoy);

		parkingLot2.park(new Car());

		Car car = new Car();
		Ticket ticket = parkingManager.park(car);
		assertSame(car, parkingLot1.pickup(ticket));
	}

	@Test
	public void should_not_park_when_have_two_boys_and_two_lots_are_full_() {
		ParkingLot parkingLot1 = new ParkingLot(1);
		ParkingLot parkingLot2 = new ParkingLot(1);
		SuperParkingBoy superParkingBoy = new SuperParkingBoy(parkingLot1);
		SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot2);
		ParkingManager parkingManager = new ParkingManager(superParkingBoy, smartParkingBoy);
		
		parkingLot1.park(new Car());
		parkingLot2.park(new Car());
	}
	
	@Test(expected = NotExist.class)
	public void should_failed_get_my_car_when_given_a_ticket_not_exists() throws Exception {
		ParkingLot parkingLot = new ParkingLot(1);
		ParkingManager parkingManager = new ParkingManager(parkingLot);
		
		Ticket ticketNotExist = new Ticket();
		parkingManager.pickup(ticketNotExist);
	}

	@Test(expected = NotExist.class)
	public void should_failed_get_my_car_when_repeat_pickup_use_same_ticket() throws Exception {
		ParkingLot parkingLot = new ParkingLot(1);
		ParkingManager parkingManager = new ParkingManager(parkingLot);
		
		Car car = new Car();
		Ticket ticket = parkingLot.park(car);
		Car result = parkingManager.pickup(ticket);
		Car result2 = parkingManager.pickup(ticket);
	}
}
