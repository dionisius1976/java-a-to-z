package ru.dionisius.action;

import ru.dionisius.input.Input;

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by Dionisius on 02.12.2016.
 */
public abstract class AClientAction implements IAction{

    private String name;
    private int key;

    public AClientAction(String name, int key) throws IOException {
        this.name = name;
        this.key = key;
    }

    public int key() {
        return this.key;
    }

    abstract public void execute(DataInputStream in, DataOutputStream out) throws IOException;

    public String info() {
        return String.format("%s. %s", this.key(), this.name);
    }

    public void sendKey(DataOutputStream out) throws IOException {
        out.writeUTF(key() + "");
        out.flush();
    }

    public void sendMessage(Input input, DataOutputStream out, String quastion) throws IOException {
        out.writeUTF(input.ask(quastion));
        out.flush();
    }

    public String getResponse(DataInputStream in) throws IOException {
        return in.readUTF();
    }
}
