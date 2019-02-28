package SJWParkinglot;

import static org.junit.Assert.assertSame;

import org.junit.Test;

public class SmartParkingBoyTest {
	@Test(expected = FullException.class)
	public void should_park_a_car_given_an_empty_parkinglot() throws Exception {
		ParkingLot parkingLot = new ParkingLot(1);
		SmartParkingBoy smartparkingBoy = new SmartParkingBoy(parkingLot);
		Car car = new Car();

		Ticket ticket = smartparkingBoy.park(car);

		assertSame(car, smartparkingBoy.pickup(ticket));
	}

	@Test(expected = FullException.class)
	public void should_park_into_lot1_when_lot1_have_more_spaces() {
		ParkingLot parkingLot1 = new ParkingLot(5);
		ParkingLot parkingLot2 = new ParkingLot(10);
		SmartParkingBoy smartparkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2);

		Car car1 = new Car();
		Ticket ticket1 = smartparkingBoy.park(car1);

		assertSame(car1, parkingLot2.pickup(ticket1));
	}

	@Test(expected = FullException.class)
	public void should_park_into_lot2_when_lot2_have_more_spaces() {
		ParkingLot parkingLot1 = new ParkingLot(10);
		ParkingLot parkingLot2 = new ParkingLot(5);
		SmartParkingBoy smartparkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2);

		Car car1 = new Car();
		Ticket ticket1 = smartparkingBoy.park(car1);

		assertSame(car1, parkingLot1.pickup(ticket1));
	}

	@Test(expected = FullException.class)
	public void should_park_into_either_when_two_lots_have_same_spaces() {
		ParkingLot parkingLot1 = new ParkingLot(3);
		ParkingLot parkingLot2 = new ParkingLot(3);
		SmartParkingBoy smartparkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2);
		
		Car car1 = new Car();
		Ticket ticket1 = smartparkingBoy.park(car1);
		assertSame(car1, parkingLot1.pickup(ticket1));
		
		Car car2 = new Car();
		Ticket ticket2 = smartparkingBoy.park(car2);
		assertSame(car2, parkingLot2.pickup(ticket2));
	}

	@Test(expected = FullException.class)
	public void should_fail_when_park_a_car_given_two_parking_lots_both_full() {
		ParkingLot parkingLot1 = new ParkingLot(1);
		ParkingLot parkingLot2 = new ParkingLot(1);
		SmartParkingBoy smartparkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2);

		Car car1 = new Car();
		Car car2 = new Car();
		Ticket ticket1 = smartparkingBoy.park(car1);
		Ticket ticket2 = smartparkingBoy.park(car2);
		assertSame(car1, parkingLot1.pickup(ticket1));
		assertSame(car2, parkingLot2.pickup(ticket2));

		Car car3 = new Car();
		Car car4 = new Car();
		Ticket ticket3 = smartparkingBoy.park(car3);
		Ticket ticket4 = smartparkingBoy.park(car4);
	}

	@Test(expected = FullException.class)
	public void should_pickup_mycar_from_lot1_when_park_mycar_from_lot1() {
		ParkingLot parkingLot1 = new ParkingLot(3);
		ParkingLot parkingLot2 = new ParkingLot(2);
		SmartParkingBoy smartparkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2);

		Car myCar = new Car();
		Ticket ticket = smartparkingBoy.park(myCar);

		assertSame(myCar, parkingLot1.pickup(ticket));
	}
	
	@Test(expected = FullException.class)
	public void should_pickup_mycar_from_lot2_when_park_mycar_from_lot2() {
		ParkingLot parkingLot1 = new ParkingLot(2);
		ParkingLot parkingLot2 = new ParkingLot(3);
		SmartParkingBoy smartparkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2);

		Car myCar = new Car();
		Ticket ticket = smartparkingBoy.park(myCar);

		assertSame(myCar, parkingLot1.pickup(ticket));
	}

	@Test(expected = FullException.class)
	public void should_pickup_mycar_from_parkingboy_when_park_mycar_from_lots() {
		ParkingLot parkingLot1 = new ParkingLot(2);
		ParkingLot parkingLot2 = new ParkingLot(2);
		SmartParkingBoy smartparkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2);
		
		Car myCar = new Car();
		Ticket ticket = smartparkingBoy.park(myCar);

		assertSame(myCar, smartparkingBoy.pickup(ticket));
	}
	
	@Test(expected = NotExist.class)
	public void should_failed_get_my_car_when_given_a_ticket_not_exists() throws Exception {
		ParkingLot parkingLot = new ParkingLot(1);
		SmartParkingBoy smartparkingBoy = new SmartParkingBoy(parkingLot);
		
		Ticket ticketNotExist = new Ticket();
		smartparkingBoy.pickup(ticketNotExist);
	}

	@Test(expected = NotExist.class)
	public void should_failed_get_my_car_when_repeat_pickup_use_same_ticket() throws Exception {
		ParkingLot parkingLot = new ParkingLot(1);
		SmartParkingBoy smartparkingBoy = new SmartParkingBoy(parkingLot);
		
		Car car = new Car();
		Ticket ticket = parkingLot.park(car);
		Car result = smartparkingBoy.pickup(ticket);
		Car result2 = smartparkingBoy.pickup(ticket);
	}
}
