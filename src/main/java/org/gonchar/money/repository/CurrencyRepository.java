package org.gonchar.money.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Stream;

import org.gonchar.money.entity.Currency;

public class CurrencyRepository extends Repository<Currency> {

	private static final CurrencyRepository INSTANCE = new CurrencyRepository();

	private final HashMap<Integer, Currency> currencies = new HashMap<>();

	CurrencyRepository() {
		currencies.put(1, new Currency(1, "UAH"));
		currencies.put(2, new Currency(2, "USD"));
		currencies.put(3, new Currency(3, "EUR"));
	}

	public static CurrencyRepository instance() {
		return INSTANCE;
	}

	@Override
	public void add(final Currency currency) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(final Currency currency) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void remove(final Currency currency) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Stream<Currency> get(final Criteria criteria) {
		validate(criteria);
		if (criteria.get().startsWith("id=")) {
			final int id = Integer.parseInt(criteria.get().replace("id=", ""));
			return getAll().stream().filter(c -> id == c.getId());
		}
		if (criteria.get().startsWith("name=")) {
			final String name = criteria.get().replace("name=", "");
			return getAll().stream().filter(c -> name.equals(c.getDisplayName()));
		}
		throw new IllegalArgumentException("Wrong criteria " + criteria.get());
	}

	public Collection<Currency> getAll() {
		return currencies.values();
	}

}
