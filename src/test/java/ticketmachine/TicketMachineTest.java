package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50;
	private TicketMachine machine;

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE);
	}

	@Test
	void priceIsCorrectlyInitialized() {
		assertEquals(PRICE, machine.getPrice());
	}

	@Test
	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals(30, machine.getBalance());
	}

	@Test
	void cannotPrintTicketIfBalanceIsInsufficient() {
		machine.insertMoney(PRICE - 10);
		assertFalse(machine.printTicket());
	}

	@Test
	void printTicketWhenBalanceIsSufficient() {
		machine.insertMoney(PRICE);
		assertTrue(machine.printTicket());
	}

	@Test
	void balanceDecreasesAfterPrintingTicket() {
		machine.insertMoney(PRICE);
		machine.printTicket();
		assertEquals(0, machine.getBalance());
	}

	@Test
	void totalIncreasesOnlyAfterPrintingTicket() {
		machine.insertMoney(PRICE);
		assertEquals(0, machine.getTotal());
		machine.printTicket();
		assertEquals(PRICE, machine.getTotal());
	}

	@Test
	void refundReturnsCorrectBalance() {
		machine.insertMoney(30);
		assertEquals(30, machine.refund());
	}

	@Test
	void refundResetsBalanceToZero() {
		machine.insertMoney(30);
		machine.refund();
		assertEquals(0, machine.getBalance());
	}

	@Test
	void cannotInsertNegativeAmount() {
		assertThrows(IllegalArgumentException.class, () -> machine.insertMoney(-10));
	}

	@Test
	void cannotCreateMachineWithNegativePrice() {
		assertThrows(IllegalArgumentException.class, () -> new TicketMachine(-50));
	}
}
