package ru.dionisius;

/**
 * Created by Dionisius on 04.02.2017.
 * Abstract class for all ABase classes.
 */
public abstract class ABase {
    /**
     * ID for this class instance.
     */
    private String id;

    /**
     * ID getter.
     * @return this class instance id.
     */
    public String getId() {
        return this.id;
    }
    /**
     * Sets ID for this class instance.
     */
    public void setId(String id) {
        this.id = id;
    }
}
