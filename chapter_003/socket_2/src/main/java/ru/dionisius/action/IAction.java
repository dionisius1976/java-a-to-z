package ru.dionisius.action;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Dionisius on 05.12.2016.
 */
public interface IAction {

    void execute(DataInputStream in, DataOutputStream out) throws IOException;

    String getResponse(DataInputStream in) throws IOException;

    String info();

}
