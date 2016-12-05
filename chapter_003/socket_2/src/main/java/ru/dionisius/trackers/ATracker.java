package ru.dionisius.trackers;

import ru.dionisius.action.AClientAction;
import ru.dionisius.action.IAction;
import ru.dionisius.input.Input;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

/**
 * Created by Dionisius on 05.12.2016.
 */
public abstract class ATracker implements ITracker {

    /**
     *
     */
    private final int NUMBER_OF_USER_ACTIONS = 5;

    /**
     *
     */
    protected IAction[] actions = new AClientAction[NUMBER_OF_USER_ACTIONS];

    /**
     * Buffer size to read shecified file.
     */
    protected final int BUFFER_SIZE = 1024;

    /**
     *
     */
    protected File properties = null;
    /**
     *Input stream.
     */
    protected DataInputStream dis;
    /**
     *Output stream.
     */
    protected DataOutputStream dos;
    /**
     *Object to work with file properties.
     */
    protected Properties prop = new Properties();
    /**
     * Current operating system line separator.
     */
    protected final String sep = System.getProperty("line.separator");
    /**
     *
     */
    protected Socket socket;

    /**
     *
     */
    public ATracker(File properties) {
        this.properties = properties;
    }

    abstract public void init();

    abstract public void fillActions() throws IOException;

    abstract public void setConnection() throws IOException;

    @Override
    public int[] getRange() {
        int[] result = new int[this.actions.length];
        for(int index = 0; index < this.actions.length; index++){
            result[index] = index;
        }
        return result;
    }


    @Override
    public void select(int key) throws IOException {
        this.actions[key].execute(this.dis, this.dos);
    }

    @Override
    public void loadProperties() throws IOException {
        InputStream in = new FileInputStream (properties);
        this.prop.load(in);
        in.close();
    }

    @Override
    public void fileTransfer(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
            out.flush();
        }
    }

    @Override
    public String getFilesList(File dir) {
        StringBuilder sb = new StringBuilder();
        for(File item : dir.listFiles()) {
            if (!item.isDirectory()) {
                sb.append(String.format("%s\tфайл%s", item.getName(), this.sep));
            }
        }
        return sb.toString();
    }

    @Override
    public String getSubDirectoriesList(File dir) {
        StringBuilder sb = new StringBuilder();
        for(File item : dir.listFiles()) {
            if (item.isDirectory()) {
                sb.append(String.format("%s\tкаталог%s", item.getName(), this.sep));
            }
        }
        return sb.toString();
    }

    @Override
    public String getCurrentDirList(File dir) {
        return this.getSubDirectoriesList(dir) + this.getFilesList(dir);
    }
}
