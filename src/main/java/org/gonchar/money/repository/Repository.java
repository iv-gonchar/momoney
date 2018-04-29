package org.gonchar.money.repository;

import java.util.Objects;
import java.util.stream.Stream;

public abstract class Repository<E> {

    public abstract void add(final E entity);

    public abstract void update(final E entity);

    public abstract void remove(final E entity);

    public abstract Stream<E> get(final Criteria criteria);
    
    protected void validate(final Criteria criteria) {
        Objects.requireNonNull(criteria, "criteria should not be null");
        Objects.requireNonNull(criteria.get(), "criteria condition should not be null");
    }
}
