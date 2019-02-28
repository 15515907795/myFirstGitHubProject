package SJWParkinglot;

import static org.junit.Assert.assertSame;

import org.junit.Test;

public class SuperParkingBoyTest {
	@Test(expected = FullException.class)
	public void should_park_a_car_given_an_empty_parkinglot() throws Exception {
		ParkingLot parkingLot = new ParkingLot(1);
		SuperParkingBoy superparkingBoy = new SuperParkingBoy(parkingLot);
		Car car = new Car();

		Ticket ticket = superparkingBoy.park(car);

		assertSame(car, superparkingBoy.pickup(ticket));
	}
	
	@Test(expected = FullException.class)
	public void should_park_into_lot1_when_lot1_have_more_percent_spaces() {
		ParkingLot parkingLot1 = new ParkingLot(2);
		ParkingLot parkingLot2 = new ParkingLot(3);
		SuperParkingBoy superparkingBoy = new SuperParkingBoy(parkingLot1, parkingLot2);
		Car car1 = new Car();
		Ticket ticket1 = parkingLot1.park(car1);
		Car car2 = new Car();
		Ticket ticket2 = parkingLot2.park(car2);
		
		Car car3 = new Car();
		Ticket ticket3 = superparkingBoy.park(car2);
		
		assertSame(car1, parkingLot1.pickup(ticket1));
		assertSame(car2, parkingLot2.pickup(ticket2));
		assertSame(car3, parkingLot1.pickup(ticket3));
	}

	@Test(expected = FullException.class)
	public void should_park_into_lot2_when_lot2_have_more_percent_spaces() {
		ParkingLot parkingLot1 = new ParkingLot(3);
		ParkingLot parkingLot2 = new ParkingLot(2);
		SuperParkingBoy superparkingBoy = new SuperParkingBoy(parkingLot1, parkingLot2);
		Car car1 = new Car();
		Ticket ticket1 = parkingLot1.park(car1);
		Car car2 = new Car();
		Ticket ticket2 = parkingLot2.park(car2);
		
		Car car3 = new Car();
		Ticket ticket3 = superparkingBoy.park(car2);
		
		assertSame(car1, parkingLot1.pickup(ticket1));
		assertSame(car2, parkingLot2.pickup(ticket2));
		assertSame(car3, parkingLot2.pickup(ticket3));
	}

	@Test(expected = FullException.class)
	public void should_park_into_either_when_two_lots_have_same_spaces() {
		ParkingLot parkingLot1 = new ParkingLot(3);
		ParkingLot parkingLot2 = new ParkingLot(3);
		SuperParkingBoy superparkingBoy = new SuperParkingBoy(parkingLot1, parkingLot2);
		
		Car car1 = new Car();
		Ticket ticket1 = superparkingBoy.park(car1);
		assertSame(car1, parkingLot2.pickup(ticket1));
		
		Car car2 = new Car();
		Ticket ticket2 = superparkingBoy.park(car2);
		assertSame(car2, parkingLot1.pickup(ticket2));
	}

	@Test(expected = FullException.class)
	public void should_fail_when_park_a_car_given_two_parking_lots_both_full() {
		ParkingLot parkingLot1 = new ParkingLot(1);
		ParkingLot parkingLot2 = new ParkingLot(1);
		SuperParkingBoy superparkingBoy = new SuperParkingBoy(parkingLot1, parkingLot2);

		Car car1 = new Car();
		Car car2 = new Car();
		Ticket ticket1 = superparkingBoy.park(car1);
		Ticket ticket2 = superparkingBoy.park(car2);
		assertSame(car1, parkingLot1.pickup(ticket1));
		assertSame(car2, parkingLot2.pickup(ticket2));

		Car car3 = new Car();
		Car car4 = new Car();
		Ticket ticket3 = superparkingBoy.park(car3);
		Ticket ticket4 = superparkingBoy.park(car4);
	}
	
	@Test(expected = NotExist.class)
	public void should_failed_get_my_car_when_given_a_ticket_not_exists() throws Exception {
		// Given 有一个停车场
		ParkingLot parkingLot = new ParkingLot(1);
		SuperParkingBoy superparkingBoy = new SuperParkingBoy(parkingLot);

		// when 我现在拿一个错误的票想去取车
		Ticket ticketNotExist = new Ticket();
		superparkingBoy.pickup(ticketNotExist);
	}

	@Test(expected = NotExist.class)
	public void should_failed_get_my_car_when_repeat_pickup_use_same_ticket() throws Exception {
		// Given 有一个停车场
		ParkingLot parkingLot = new ParkingLot(1);
		SuperParkingBoy superparkingBoy = new SuperParkingBoy(parkingLot);

		// when 我现在存了一辆车，用ticket取我的车，然后再用同一张卡再取一次车
		Car car = new Car();
		Ticket ticket = parkingLot.park(car);
		Car result = superparkingBoy.pickup(ticket);
		Car result2 = superparkingBoy.pickup(ticket);
	}
}
