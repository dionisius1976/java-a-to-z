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
    public Input input;
    private int key;

    public AClientAction(String name, Input input, int key) throws IOException {
        this.name = name;
        this.input = input;
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
        out.writeUTF(this.key() + "");
        out.flush();
    }

//    public void sendMessage(Input input, DataOutputStream out, String quastion) throws IOException {
//        String currentLine = input.ask(quastion);
//        out.writeUTF(currentLine);
//        out.flush();
//    }
    public void sendMessage(Input input, DataOutputStream out, String quastion) throws IOException {
//        String currentLine = input.ask(quastion);
        out.writeUTF(quastion);
        out.flush();
    }

    public String getResponse(DataInputStream in) throws IOException {
        return in.readUTF();
    }
}
