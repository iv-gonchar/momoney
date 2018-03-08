package org.gonchar.money.repository.criteria;

import java.util.Objects;

import org.gonchar.money.repository.Criteria;

public class ById implements Criteria {

    private static final int UNASSIGNED = -1;

    private static final String CONDITION = "id=";

    private int intId = UNASSIGNED;

    private String strId;

    public ById(final int id) {
        validate(id);
        this.intId = id;
    }

    public ById(final String id) {
        Objects.requireNonNull(id, "id should not be null");
        validate(Integer.parseInt(id));
        this.strId = id;
    }

    @Override
    public String get() {
        if (intId != UNASSIGNED) {
            return CONDITION + intId;
        }
        if (strId != null) {
            return CONDITION + "'" + strId + "'";
        }
        throw new AssertionError("both intId and strId are not assigned");
    }

    private void validate(final int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id should be positive " + id);
        }
    }

}
