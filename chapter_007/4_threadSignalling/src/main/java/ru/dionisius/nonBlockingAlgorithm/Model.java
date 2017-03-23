package ru.dionisius.nonBlockingAlgorithm;

/**
 * Created by Dionisius on 23.03.2017.
 */
public class Model {
    /**
     * Number of version for this instance.
     */
    private volatile long version = 0;
    /**
     * Id number for this instance.
     */
    private final long id;
    /**
     * The name of this instance.
     */
    private String name;


    /**
     * Constructor.
     * @param name the name of this instance.
     * @param id id number for this instance.
     */
    public Model(final String name, final long id) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getter for id.
     * @return id.
     */
    public long getId() {
        return this.id;
    }

    /**
     * Getter for version.
     * @return version.
     */
    public long getVersion() {
        return this.version;
    }

    /**
     * Getter for name.
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for name.
     * @param name the new specified name.
     */
    public void setName(final String name) {
        this.name = name;
        this.version++;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Model model = (Model) o;

        if (version != model.version) return false;
        if (id != model.id) return false;
        return name.equals(model.name);
    }

    @Override
    public int hashCode() {
        int result = (int) (version ^ (version >>> 32));
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }
}
