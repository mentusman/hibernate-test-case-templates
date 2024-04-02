package org.hibernate.bugs;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.metamodel.spi.ValueAccess;
import org.hibernate.usertype.CompositeUserType;

import java.io.Serializable;
import java.util.Objects;

public class NameCompositeUserType implements CompositeUserType<Name> {
    public NameCompositeUserType() {
    }

    public static class NameMapper {
        String firstName;
        String lastName;
    }

    @Override
    public Class<?> embeddable() {
        return NameMapper.class;
    }

    @Override
    public Class<Name> returnedClass() {
        return Name.class;
    }

    @Override
    public Name instantiate(ValueAccess valueAccess, SessionFactoryImplementor sessionFactory) {
        final String first = valueAccess.getValue( 0, String.class );
        final String last = valueAccess.getValue( 1, String.class );
        return new Name(first, last);
    }

    @Override
    public Object getPropertyValue(Name component, int property) throws HibernateException {
        switch ( property ) {
            case 0:
                return component.firstName();
            case 1:
                return component.lastName();
        }
        return null;
    }

    @Override
    public boolean equals(Name x, Name y) {
        return x == y || x != null && Objects.equals( x.firstName(), y.firstName() )
                && Objects.equals( x.lastName(), y.lastName() );
    }

    @Override
    public int hashCode(Name x) {
        return Objects.hash( x.firstName(), x.lastName() );
    }

    @Override
    public Name deepCopy(Name value) {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Name value) {
        return new String[] { value.firstName(), value.lastName() };
    }

    @Override
    public Name assemble(Serializable cached, Object owner) {
        final String[] parts = (String[]) cached;
        return new Name( parts[0], parts[1] );
    }

    @Override
    public Name replace(Name detached, Name managed, Object owner) {
        return detached;
    }
}
