package org.gonchar.money.repository;

import org.gonchar.money.entity.User;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserRepositoryTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void testAdd() {
        final User user1 = new User("1", "User 1");
        final User user2 = new User("2", "User 2");
        final User user3 = new User("3", "User 3");

        final UserRepository repository = new UserRepository();
        repository.add(user1);
        repository.add(user2);
        repository.add(user3);

        Assert.assertEquals(3, repository.getAll().size());
        Assert.assertTrue(repository.getAll().contains(user1));
        Assert.assertTrue(repository.getAll().contains(user2));
        Assert.assertTrue(repository.getAll().contains(user3));
    }

    @Test
    public void testAddExistent() {
        thrown.expect(IllegalArgumentException.class);
        final UserRepository repository = new UserRepository();

        repository.add(new User("1", "User"));
        repository.add(new User("1", "Another user but with existent id"));
    }

    @Test
    public void testUpdate() {
        final String originalName = "User 1";
        final String updatedName = "User Updated";
        final UserRepository repository = new UserRepository();

        repository.add(new User("1", originalName));
        final User addedUser = repository.get(() -> "id=1").findFirst().get();
        Assert.assertEquals(originalName, addedUser.getDisplayName());

        repository.update(new User("1", updatedName));
        final User updatedUser = repository.get(() -> "id=1").findFirst().get();
        Assert.assertEquals(updatedName, updatedUser.getDisplayName());
    }

    @Test
    public void testDelete() {
        final UserRepository repository = new UserRepository();

        repository.add(new User("1", "User 1"));
        Assert.assertEquals(1, repository.getAll().size());

        repository.remove(new User("1", ""));
        Assert.assertEquals(0, repository.getAll().size());
    }

    @Test
    public void testGetById() {
        final UserRepository repository = new UserRepository();
        final User expected = new User("1", "User 1");

        repository.add(expected);
        Assert.assertEquals(1, repository.get(() -> "id=1").count());
        final User actual = repository.get(() -> "id=1").findFirst().get();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetSingleByName() {
        final UserRepository repository = new UserRepository();
        final User expected = new User("1", "User 1");

        repository.add(expected);
        Assert.assertEquals(1, repository.get(() -> "name=User 1").count());
        final User actual = repository.get(() -> "name=User 1").findFirst().get();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetMultipleByName() {
        final UserRepository repository = new UserRepository();

        repository.add(new User("1", "User"));
        repository.add(new User("2", "User"));
        Assert.assertEquals(2, repository.get(() -> "name=User").count());
    }
}
