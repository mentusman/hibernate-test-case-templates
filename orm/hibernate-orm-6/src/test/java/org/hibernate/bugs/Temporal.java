package org.hibernate.bugs;

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Temporal <T>  extends TemporalWrapper<T> {
    @Embedded
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    protected Temporal() {}
    protected Temporal(T state) {
        super(state);
    }

    public boolean equals(Object obj) {
        Temporal<T> other = (Temporal<T>)obj;
        if (other == null) return false;
        return this.getId() == other.getId();
    }
}
