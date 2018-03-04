package org.gonchar.money.entity;

import lombok.Data;

@Data
public class User {

    /**
     * Unique email of current user
     */
    private final String id;

    private final String displayName;
}
