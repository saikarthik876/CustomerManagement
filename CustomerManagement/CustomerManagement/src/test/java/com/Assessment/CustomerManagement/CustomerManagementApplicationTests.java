package com.Assessment.CustomerManagement;

import com.Assessment.CustomerManagement.entity.Customer;
import com.Assessment.CustomerManagement.service.CustomerCalculationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CustomerManagementApplicationTests {
	private CustomerCalculationService tierCalculator;
	@Test
	void testTierCalculationSilver() {
		Customer customer = new Customer();
		customer.setAnnualSpend(BigDecimal.valueOf(500)); // less than 1000
		customer.setLastPurchaseDate(LocalDateTime.now());

		String tier = tierCalculator.calculateTier(customer);
		assertEquals("Silver", tier);
	}

	@Test
	void testTierCalculationGold() {
		Customer customer = new Customer();
		customer.setAnnualSpend(BigDecimal.valueOf(5000)); // between 1000 and 10000
		customer.setLastPurchaseDate(LocalDateTime.now().minusMonths(6)); // within 12 months

		String tier = tierCalculator.calculateTier(customer);
		assertEquals("Gold", tier);
	}

	@Test
	void testTierCalculationPlatinum() {
		Customer customer = new Customer();
		customer.setAnnualSpend(BigDecimal.valueOf(15000)); // >= 10000
		customer.setLastPurchaseDate(LocalDateTime.now().minusMonths(2)); // within 6 months

		String tier = tierCalculator.calculateTier(customer);
		assertEquals("Platinum", tier);
	}

	@Test
	void testTierCalculationUnknownDueToOldPurchase() {
		Customer customer = new Customer();
		customer.setAnnualSpend(BigDecimal.valueOf(15000)); // qualifies for platinum spend
		customer.setLastPurchaseDate(LocalDateTime.now().minusMonths(7)); // purchase older than 6 months

		String tier = tierCalculator.calculateTier(customer);
		assertEquals("Unknown", tier);
	}

	@Test
	void testTierCalculationUnknownDueToNullValues() {
		Customer customer = new Customer();
		customer.setAnnualSpend(null);
		customer.setLastPurchaseDate(null);

		String tier = tierCalculator.calculateTier(customer);
		assertEquals("Unknown", tier);
	}

}
