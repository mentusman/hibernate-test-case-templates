package org.hibernate.bugs;

import java.io.Serializable;

public class Name implements Serializable {
    private final String first;
    private final String last;

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public String firstName() {
        return first;
    }

    public String lastName() {
        return last;
    }
}
