package org.gonchar.money.repository;

import org.gonchar.money.entity.Currency;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CurrencyRepositoryTest {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Test
	public void testAdd() {
		thrown.expect(UnsupportedOperationException.class);
		CurrencyRepository.instance().add(new Currency(0, "GBP"));
	}

	@Test
	public void testUpdate() {
		thrown.expect(UnsupportedOperationException.class);
		CurrencyRepository.instance().update(new Currency(0, "GBP"));
	}

	@Test
	public void testRemove() {
		thrown.expect(UnsupportedOperationException.class);
		CurrencyRepository.instance().remove(new Currency(0, "GBP"));
	}

	@Test
	public void testGetById() {
		final Currency expected = new Currency(1, "UAH");
		final CurrencyRepository repository = CurrencyRepository.instance();
		Assert.assertEquals(1, repository.get(() -> "id=1").count());
		final Currency actual = repository.get(() -> "id=1").findFirst().get();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetByName() {
		final Currency expected = new Currency(3, "EUR");
		final CurrencyRepository repository = CurrencyRepository.instance();
		Assert.assertEquals(1, repository.get(() -> "name=EUR").count());
		final Currency actual = repository.get(() -> "name=EUR").findFirst().get();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetWrongCriteria() {
		thrown.expect(IllegalArgumentException.class);
		CurrencyRepository.instance().get(() -> "size=wrong");
	}

}
