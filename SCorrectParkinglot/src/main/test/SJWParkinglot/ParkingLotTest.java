package SJWParkinglot;

import static org.junit.Assert.assertSame;

import org.junit.Test;

public class ParkingLotTest {
	@Test
	public void should_park_a_car_given_an_empty_parkinglot() throws Exception {
		// Given
		ParkingLot parkingLot = new ParkingLot(1);
		Car car = new Car();

		// when
		Ticket ticket = parkingLot.park(car);
		Car result = parkingLot.pickup(ticket);

		// then
		assertSame(car, result);
	}

	@Test
	public void should_park_the_same_cars_when_park_2_cars_given_an_empty_parkinglot() throws Exception {
		// Given
		ParkingLot parkingLot = new ParkingLot(2);
		Car car1 = new Car();
		Car car2 = new Car();

		// when
		Ticket ticket1 = parkingLot.park(car1);
		Ticket ticket2 = parkingLot.park(car2);

		Car result1 = parkingLot.pickup(ticket1);
		Car result2 = parkingLot.pickup(ticket2);

		// then
		assertSame(car1, result1);
		assertSame(car2, result2);
	}

	@Test(expected = FullException.class)
	public void should_not_park_car_if_parkinglot_is_full() throws Exception {
		// 这个测试用例，我想验证当停车场满的时候，返回parkinglot P1 is full。
		// Given
		ParkingLot parkingLot = new ParkingLot(1);
		parkingLot.park(new Car());

		// When
		parkingLot.park(new Car());
	}

	@Test(expected = NotExist.class)
	public void should_failed_get_my_car_when_given_a_ticket_not_exists() throws Exception {
		// Given 有一个停车场
		ParkingLot parkingLot = new ParkingLot(1);

		// when 我现在拿一个错误的票想去取车
		Ticket ticketNotExist = new Ticket();
		parkingLot.pickup(ticketNotExist);
	}

	@Test(expected = NotExist.class)
	public void should_failed_get_my_car_when_repeat_pickup_use_same_ticket() throws Exception {
		// Given 有一个停车场
		ParkingLot parkingLot = new ParkingLot(1);

		// when 我现在存了一辆车，用ticket取我的车，然后再用同一张卡再取一次车
		Car car = new Car();
		Ticket ticket = parkingLot.park(car);
		Car result = parkingLot.pickup(ticket);
		Car result2 = parkingLot.pickup(ticket);
	}
}
