package org.hibernate.bugs;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "User")
public class User extends PersistentEntity {
    @Entity
    @Table(name = "UserTemporal")
    public static class Address extends Temporal<AddressRelation> {
        public Address() {}
        public Address(User.AddressRelation value) {
            super(value);
        }
    }

    @Embeddable
    public static class AddressRelation {
        @ManyToOne
        @Cascade(value = { org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE })
        @JoinColumn(name = "objektId")
        public User objekt;

        public AddressRelation() {}

        public User getUser() {
            return objekt;
        }
        public void setUser(User objekt) {
            this.objekt = objekt;
        }
    }
}
