package org.gonchar.money.entity;

import lombok.Data;

@Data
public class Account {

    private final int id;

    private final User user;

    private final String displayName;

    private final Currency currency;

    /**
     * Returns amount of money in this account
     * SQL
     * 
     * @return money available in this account
     */
    public double getAmount() {
        return 0.0;
    }
}
