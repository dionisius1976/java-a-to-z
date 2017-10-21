package ru.dionisius;

/**
 * Created by Dionisius on 19.10.2017.
 */
public class MyPostgreDialect extends org.hibernate.dialect.HSQLDialect {
    @Override
    public String getDropSequenceString(String sequenceName) {
        // Adding the "if exists" clause to avoid warnings
        return "drop sequence if exists " + sequenceName;
    }

    @Override
    public boolean dropConstraints() {
        // We don't need to drop constraints before dropping tables, that just leads to error
        // messages about missing tables when we don't have a schema in the database
        return false;
    }
}
