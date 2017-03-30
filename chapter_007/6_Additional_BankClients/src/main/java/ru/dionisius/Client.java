package ru.dionisius;

import java.time.LocalTime;

/**
 * Created by Dionisius on 28.03.2017.
 * Client class.
 */
public class Client {
    /**
     * Bank entry time of this client.
     */
    private final int entryTime;
    /**
     * Bank exit time for this client.
     */
    private final int exitTime;
    /**
     * This client's id.
     */
    private final long id;

    /**
     * Constructor.
     * @param id client's id.
     * @param entryTime bank entry time of this client.
     * @param exitTime bank exit time for this client.
     */
    public Client(final long id, final LocalTime entryTime, final LocalTime exitTime) {
        this.entryTime = entryTime.getHour() * 60 + entryTime.getMinute();
        this.exitTime = exitTime.getHour() * 60 + exitTime.getMinute();
        this.id = id;
    }

    /**
     * Getter for client's id.
     * @return client's id.
     */
    public long getId() {
        return this.id;
    }

    /**
     * Getter for client's bank entry time.
     * @return client's bank entry time.
     */
    public int getEntryTime() {
        return this.entryTime;
    }

    /**
     * Getter for client's bank exit time.
     * @return client's bank exit time.
     */
    public int getExitTime() {
        return this.exitTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return id == client.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
