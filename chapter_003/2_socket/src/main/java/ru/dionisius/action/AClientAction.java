package ru.dionisius.action;

import ru.dionisius.input.Input;

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by Dionisius on 02.12.2016.
 */
public abstract class AClientAction implements IAction {

    /**
     * The name of this instance.
     */
    private String name;
    /**
     * The type of input.
     */
    private Input input;
    /**
     * The key of this instance.
     */
    private int key;

    /**
     * Constructor.
     * @param name name of this instance
     * @param input type of input
     * @param key identifying key of this instance
     * @throws IOException if IO error occurs
     */
    public AClientAction(String name, Input input, int key) throws IOException {
        this.name = name;
        this.input = input;
        this.key = key;
    }

    /**
     * Returns key of this instance.
     * @return key of this instance
     */
    public int key() {
        return this.key;
    }

    /**
     * Executes specified algorithm.
     * @param in input stream
     * @param out stream to out
     * @throws IOException if IO error occurs
     */
    public abstract void execute(DataInputStream in, DataOutputStream out) throws IOException;

    /**
     * Returns information about this instance.
     * @return information about this instance
     */
    public String info() {
        return String.format("%s. %s", this.key(), this.name);
    }

    /**
     * Sends key of this instance to server.
     * @param out stream to out
     * @throws IOException if IO error occurs
     */
    public void sendKey(DataOutputStream out) throws IOException {
        out.writeUTF(this.key() + "");
        out.flush();
    }

    /**
     * Sends specified message to server.
     * @param out stream to out
     * @param message message to server
     * @throws IOException if IO error occurs
     */
    public void sendMessage(DataOutputStream out, String message) throws IOException {
        out.writeUTF(message);
        out.flush();
    }

    /**
     * Gets and returns response from server.
     * @param in input stream
     * @return response from server
     * @throws IOException if IO error occurs
     */
    public String getResponse(DataInputStream in) throws IOException {
        return in.readUTF();
    }
}
