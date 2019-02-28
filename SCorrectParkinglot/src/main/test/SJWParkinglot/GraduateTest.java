package SJWParkinglot;

import static org.junit.Assert.assertSame;

import org.junit.Test;

public class GraduateTest {

	@Test(expected = NotExist.class)
	public void should_park_a_car_given_an_empty_parkinglot() throws Exception {
		// Given
		ParkingLot parkingLot = new ParkingLot(1);
		GraduateParkingBoy parkingBoy = new GraduateParkingBoy(parkingLot);
		Car car = new Car();

		// when
		Ticket ticket = parkingBoy.park(car);
		Car result = parkingBoy.pickup(ticket);

		// then
		assertSame(car, result);
	}

	@Test
	public void should_park_in_lot1_when_two_lots_both_have_enough_capacity() throws Exception {
		// Given:2个停车场,空位都充足,当第一个停车场不满的时候，来的车都优先停在lot1.去第一个停车场能够合法取车。
		ParkingLot parkingLot1 = new ParkingLot(3);
		ParkingLot parkingLot2 = new ParkingLot(2);
		GraduateParkingBoy parkingBoy = new GraduateParkingBoy(parkingLot1, parkingLot2);
		Car car1 = new Car();
		Car car2 = new Car();
		Car car3 = new Car();

		// when
		Ticket ticket1 = parkingBoy.park(car1);
		Ticket ticket2 = parkingBoy.park(car2);
		Ticket ticket3 = parkingBoy.park(car3);

		// then
		assertSame(car1, parkingLot1.pickup(ticket1));
		assertSame(car2, parkingLot1.pickup(ticket2));
		assertSame(car3, parkingLot1.pickup(ticket3));
	}

	@Test(expected = FullException.class)
	public void should_park_in_lot2_when_lot1_is_full() throws Exception {
		// Given:2个停车场,当停车场1满，2不满的时候，来了车能够停在停车场2.去第二个停车场能够合法取车。
		ParkingLot parkingLot1 = new ParkingLot(2);
		ParkingLot parkingLot2 = new ParkingLot(2);
		GraduateParkingBoy parkingBoy = new GraduateParkingBoy(parkingLot1, parkingLot2);
		Car car1 = new Car();
		Car car2 = new Car();
		Car car3 = new Car();

		// when
		Ticket ticket1 = parkingBoy.park(car1);
		Ticket ticket2 = parkingBoy.park(car2);
		Ticket ticket3 = parkingBoy.park(car3);

		// then
		assertSame(car1, parkingLot1.pickup(ticket1));
		assertSame(car2, parkingLot1.pickup(ticket2));
		assertSame(car3, parkingLot2.pickup(ticket3));
	}

	@Test(expected = FullException.class)
	public void should_not_park_car_if_two_parkinglots_is_full() throws Exception {
		// 停车场都满的时候，不让停车
		ParkingLot parkingLot1 = new ParkingLot(1);
		ParkingLot parkingLot2 = new ParkingLot(1);
		GraduateParkingBoy parkingBoy = new GraduateParkingBoy(parkingLot1, parkingLot2);
		Car car1 = new Car();
		Car car2 = new Car();
		Car car3 = new Car();

		parkingLot1.park(car1);
		parkingLot2.park(car2);
		parkingLot1.park(car3);
		parkingLot2.park(car3);

	}

	@Test(expected = FullException.class)
	public void should_park_car_when_pickup_so_lot_is_not_full() throws Exception {
		// Given 有一个停车场,都满。
		ParkingLot parkingLot1 = new ParkingLot(1);
		ParkingLot parkingLot2 = new ParkingLot(1);
		GraduateParkingBoy parkingBoy = new GraduateParkingBoy(parkingLot1, parkingLot2);
		Car car1 = new Car();
		Car car2 = new Car();
		Car car3 = new Car();
		Ticket ticket1 = parkingBoy.park(car1);
		Ticket ticket2 = parkingBoy.park(car2);

		parkingLot1.park(car1);
		parkingLot2.park(car2);

		// 来了一辆车，不能停车。这时P1走了一辆车，腾出一个空位，能停车了。
		parkingLot1.park(car3);
		parkingLot1.pickup(ticket1);

		// 能停成功
		parkingLot1.park(car3);
	}
	
	@Test(expected = NotExist.class)
	public void should_failed_get_my_car_when_given_a_ticket_not_exists() throws Exception {
		// Given 有一个停车场
		ParkingLot parkingLot = new ParkingLot(1);
		GraduateParkingBoy parkingBoy = new GraduateParkingBoy(parkingLot);

		// when 我现在拿一个错误的票想去取车
		Ticket ticketNotExist = new Ticket();
		parkingBoy.pickup(ticketNotExist);
	}

	@Test(expected = NotExist.class)
	public void should_failed_get_my_car_when_repeat_pickup_use_same_ticket() throws Exception {
		// Given 有一个停车场
		ParkingLot parkingLot = new ParkingLot(1);
		GraduateParkingBoy parkingBoy = new GraduateParkingBoy(parkingLot);

		// when 我现在存了一辆车，用ticket取我的车，然后再用同一张卡再取一次车
		Car car = new Car();
		Ticket ticket = parkingLot.park(car);
		Car result = parkingBoy.pickup(ticket);
		Car result2 = parkingBoy.pickup(ticket);
	}
}
