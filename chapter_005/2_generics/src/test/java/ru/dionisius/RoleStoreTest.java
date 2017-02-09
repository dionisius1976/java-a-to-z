package ru.dionisius;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Dionisius on 06.02.2017.
 * Testing class for RoleStore class.
 */
public class RoleStoreTest {
    /**
     * Role instance for tests.
     */
    final private Role testRole = new Role("12345");
    /**
     * RoleStore instance for tests.
     */
    final private RoleStore testStore = new RoleStore();
    /**
     * Tests if add() method adds the new instance in the RoleStore.
     */
    @Test
    public void whenNewObjectIsAddedInTheStoreThenItIsInTheStore() {
        this.testStore.add(this.testRole);
        boolean expectedValue = true;
        boolean resultValue = this.testStore.exist(this.testRole);
        assertThat(expectedValue, is(resultValue));
    }

    /**
     * Tests if update() method updates the old object in the RoleStore by the new instance.
     */
    @Test
    public void whenOldObjectIsUpdatedByNewObjectThenNewObjectIsOnOldObjectPlaceInTheStore() {
        Role newRole = new Role("000");
        this.testStore.add(this.testRole);
        boolean expectedValue = true;
        this.testStore.update(this.testRole, newRole);
        boolean resultValue = this.testStore.exist(newRole);
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if update() method updates the old object in the RoleStore by the new null instance.
     */
    @Test
    public void whenOldObjectIsUpdatedByNullThenNullIsOnOldObjectPlaceInTheStore() {
        Role newRole = null;
        this.testStore.add(this.testRole);
        boolean expectedValue = true;
        this.testStore.update(this.testRole, newRole);
        boolean resultValue = this.testStore.exist(newRole);
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if update() method updates the old null object in the RoleStore by the new instance.
     */
    @Test
    public void whenOldObjectIsNullAndIsUpdatedByNewObjectThenNewObjectIsOnOldObjectPlaceInTheStore() {
        Role newRole = this.testRole;
        boolean expectedValue = true;
        this.testStore.update(null, newRole);
        boolean resultValue = this.testStore.exist(newRole);
        assertThat(expectedValue, is(resultValue));
    }

    /**
     * Tests if delete() method deletes specified object from the RoleStore.
     */
    @Test
    public void whenDeleteSpecifiedObjectThenThisObjectIsNotExist() {
        this.testStore.add(this.testRole);
        this.testStore.delete(this.testRole);
        boolean expectedValue = false;
        boolean resultValue = this.testStore.exist(this.testRole);
        assertThat(expectedValue, is(resultValue));
    }
}