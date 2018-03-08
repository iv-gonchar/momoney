package org.gonchar.money.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.gonchar.money.entity.User;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public final class UserRepository extends Repository<User> {

    private static final UserRepository INSTANCE = new UserRepository();

    private final HashMap<String, User> users = new HashMap<>();

    public static UserRepository instance() {
        return INSTANCE;
    }

    @Override
    public void add(final User user) {
        validate(user);
        if (users.containsKey(user.getId())) {
            throw new IllegalArgumentException("user with " + user.getId() + " id already exist");
        }
        users.put(user.getId(), user);
    }

    @Override
    public void update(final User user) {
        validate(user);
        Optional.of(users.replace(user.getId(), user)).orElseThrow(() -> new IllegalArgumentException(user + " doesn't exist"));
    }

    @Override
    public void remove(final User user) {
        validate(user);
        users.remove(user.getId());
    }

    @Override
    public Stream<User> get(final Criteria criteria) {
        validate(criteria);
        if (criteria.get().startsWith("id=")) {
            final String id = criteria.get().replace("id=", "");
            return getAll().stream().filter(u -> id.equals(u.getId()));
        }
        if (criteria.get().startsWith("name=")) {
            final String name = criteria.get().replace("name=", "");
            return getAll().stream().filter(u -> name.equals(u.getDisplayName()));
        }
        throw new IllegalArgumentException("Wrong criteria " + criteria.get());
    }

    public Collection<User> getAll() {
        return users.values();
    }

    private void validate(final User user) {
        Objects.requireNonNull(user, "user should not be null");
        Objects.requireNonNull(user.getId(), "user.id should not be null");
    }
}
