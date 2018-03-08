package org.gonchar.money.repository;

public abstract class Repository<E> {

    public abstract void add(final E user);

    public abstract void update(final E user);

    public abstract void remove(final E user);

    public abstract E get(final Criteria criteria);
}
