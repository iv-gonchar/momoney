package org.gonchar.money.repository;

import org.gonchar.money.entity.Account;
import org.gonchar.money.entity.Currency;
import org.gonchar.money.entity.User;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AccountRepositoryTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void testAdd() {
    	final User user1 = new User("1", "User 1");
        final User user2 = new User("2", "User 2");

    	final Account account1 = new Account(1, user1, "Account 1", new Currency(1, "UAH"));
    	final Account account2 = new Account(2, user1, "Account 2", new Currency(2, "GBP"));
    	final Account account3 = new Account(3, user2, "Account 3", new Currency(3, "USD"));

        final AccountRepository repository = new AccountRepository();
        repository.add(account1);
        repository.add(account2);
        repository.add(account3);

        Assert.assertEquals(3, repository.getAll().size());
        Assert.assertTrue(repository.getAll().contains(account1));
        Assert.assertTrue(repository.getAll().contains(account2));
        Assert.assertTrue(repository.getAll().contains(account3));
    }

    @Test
    public void testAddExistent() {
        thrown.expect(IllegalArgumentException.class);
        final AccountRepository repository = new AccountRepository();
        final User user = new User("1", "User 1");

        repository.add(new Account(1, user, "Account 1", new Currency(1, "UAH")));
        repository.add(new Account(1, user, "Another account", new Currency(1, "UAH")));
    }

    @Test
    public void testUpdate() {
        final String originalName = "Account 1";
        final String updatedName = "Account Updated";
        final AccountRepository repository = new AccountRepository();
        final User user = new User("1", "User 1");

        repository.add(new Account(1, user, originalName, new Currency(1, "UAH")));
        final Account addedAccount = repository.get(() -> "id=1").findFirst().get();
        Assert.assertEquals(originalName, addedAccount.getDisplayName());

        repository.update(new Account(1, user, updatedName, new Currency(1, "UAH")));
        final Account updatedAccount = repository.get(() -> "id=1").findFirst().get();
        Assert.assertEquals(updatedName, updatedAccount.getDisplayName());
    }

//    @Test
//    public void testDelete() {
//        final UserRepository repository = new UserRepository();
//
//        repository.add(new User("1", "User 1"));
//        Assert.assertEquals(1, repository.getAll().size());
//
//        repository.remove(new User("1", ""));
//        Assert.assertEquals(0, repository.getAll().size());
//    }
//
//    @Test
//    public void testGetById() {
//        final UserRepository repository = new UserRepository();
//        final User expected = new User("1", "User 1");
//
//        repository.add(expected);
//        Assert.assertEquals(1, repository.get(() -> "id=1").count());
//        final User actual = repository.get(() -> "id=1").findFirst().get();
//        Assert.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testGetSingleByName() {
//        final UserRepository repository = new UserRepository();
//        final User expected = new User("1", "User 1");
//
//        repository.add(expected);
//        Assert.assertEquals(1, repository.get(() -> "name=User 1").count());
//        final User actual = repository.get(() -> "name=User 1").findFirst().get();
//        Assert.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void testGetMultipleByName() {
//        final UserRepository repository = new UserRepository();
//
//        repository.add(new User("1", "User"));
//        repository.add(new User("2", "User"));
//        Assert.assertEquals(2, repository.get(() -> "name=User").count());
//    }

}
