package org.hibernate.bugs;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PersistentEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    protected Object clone() throws CloneNotSupportedException {
        PersistentEntity clone = PersistentEntity.class.cast(super.clone());
        clone.id = null;
        return clone;
    }
}
