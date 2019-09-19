package org.gonchar.money.repository;

import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Stream;

import org.gonchar.money.entity.Transaction;

public class TransactionRepository extends Repository<Transaction> {
	
	private static final TransactionRepository INSTANCE = new TransactionRepository();

	private final HashMap<Integer, Transaction> transactions = new HashMap<>();

	TransactionRepository() {
		// for internal use
	}
	
	public static TransactionRepository instance() {
		return INSTANCE;
	}

	@Override
	public void add(Transaction user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Transaction user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Transaction user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Stream<Transaction> get(Criteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void validate(final Transaction transaction) {
        Objects.requireNonNull(transaction, "transaction should not be null");
//        Objects.requireNonNull(transaction.getId(), "transaction.id should not be null");
	}

}
