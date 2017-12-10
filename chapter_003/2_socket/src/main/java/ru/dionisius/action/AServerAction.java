package ru.dionisius.action;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Dionisius on 05.12.2016.
 */
public abstract class AServerAction implements IAction {

    /**
     * @param in in input stream
     * @param out stream to send ru.dionisius.ru.dionisius.data out
     * @throws IOException if IO error occurs
     */
    public abstract void execute(DataInputStream in, DataOutputStream out) throws IOException;

    /**
     * Sends specified message to server.
     * @param out stream to out
     * @param message quastion
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

    /**
     * Returns information about this instance.
     * @return information about this instance
     */
    public String info() {
        return "";
    }
}
