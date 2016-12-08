package ru.dionisius.action;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Dionisius on 05.12.2016.
 */
public interface IAction {

    /**
     * Executes specified algorithm.
     * @param in input stream
     * @param out stream to out
     * @throws IOException if IO error occurs
     */
    void execute(DataInputStream in, DataOutputStream out) throws IOException;

    /**
     * Gets and returns response from server.
     * @param in input stream
     * @return response from server
     * @throws IOException if IO error occurs
     */
    String getResponse(DataInputStream in) throws IOException;

    /**
     * Returns information about this instance.
     * @return information about this instance
     */
    String info();

}
