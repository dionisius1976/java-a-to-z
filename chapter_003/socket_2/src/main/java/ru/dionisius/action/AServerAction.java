package ru.dionisius.action;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Dionisius on 05.12.2016.
 */
public abstract class AServerAction implements IAction {

    abstract public void execute(DataInputStream in, DataOutputStream out) throws IOException;

    public void sendMessage(DataOutputStream out, String message) throws IOException {
        out.writeUTF(message);
        out.flush();
    }

    public String getResponse(DataInputStream in) throws IOException {
        return in.readUTF();
    }

    public String info(){
        return "";
    }
}
