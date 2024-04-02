package org.hibernate.bugs;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CompositeType;

@MappedSuperclass
public abstract class TemporalWrapper <V> extends PersistentEntity {
    @Embedded
    @AttributeOverride(name = "firstName", column = @Column(name = "firstName"))
    @AttributeOverride(name = "lastName", column = @Column(name = "lastName"))
    @CompositeType(NameCompositeUserType.class)
    public Name name;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    protected TemporalWrapper() {
    }

    public TemporalWrapper(V value){
        setValue(value);
    }
    protected abstract void setValue(V value);
}
