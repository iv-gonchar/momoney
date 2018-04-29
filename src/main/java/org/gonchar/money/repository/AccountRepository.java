package org.gonchar.money.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.gonchar.money.entity.Account;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class AccountRepository extends Repository<Account> {

	private static final AccountRepository INSTANCE = new AccountRepository();

	private final HashMap<Integer, Account> accounts = new HashMap<>();

	public static AccountRepository instance() {
		return INSTANCE;
	}

	@Override
	public void add(final Account account) {
		validate(account);
		if (accounts.containsKey(account.getId())) {
			throw new IllegalArgumentException("account with " + account.getId() + " id already exist");
		}
		accounts.put(account.getId(), account);
	}

	@Override
	public void update(final Account account) {
		validate(account);
		Optional.of(accounts.replace(account.getId(), account))
				.orElseThrow(() -> new IllegalArgumentException(account + " doesn't exist"));
	}

	@Override
	public void remove(final Account account) {
		validate(account);
		accounts.remove(account.getId());
	}

	@Override
	public Stream<Account> get(final Criteria criteria) {
		validate(criteria);
		if (criteria.get().startsWith("id=")) {
			final Integer id = Integer.parseInt(criteria.get().replace("id=", ""));
			return getAll().stream().filter(a -> id.equals(a.getId()));
		}
		if (criteria.get().startsWith("userId=")) {
			final String userId = criteria.get().replace("userId=", "");
			return getAll().stream().filter(a -> userId.equals(a.getUser().getId()));
		}
		throw new IllegalArgumentException("Wrong criteria " + criteria.get());
	}

	public Collection<Account> getAll() {
		return accounts.values();
	}

	private void validate(final Account account) {
		Objects.requireNonNull(account, "account should not be null");
		Objects.requireNonNull(account.getId(), "account.id should not be null");
	}

}
