package ru.dionisius.trackers;

import ru.dionisius.action.IAction;

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
    public final int NUMBER_OF_USER_ACTIONS = 5;

    /**
     *
     */
    protected IAction[] actions;

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
    protected final String fSep = File.separator;
    /**
     *
     */
    protected Socket socket;

    /**
     *
     */
//    public ATracker(File properties) {
//        this.properties = properties;
//    }

    abstract public void init();

    abstract public void fillActions() throws IOException;

    abstract public void setConnection() throws IOException;

    @Override
    public int[] getRange() {
        int[] result = new int[this.actions.length];
        for(int index = 0; index < this.actions.length; index++){
            result[index] = index + 1;
        }
        return result;
    }

    @Override
    public void select(int key) throws IOException {
        this.actions[key - 1].execute(this.dis, this.dos);
    }

    @Override
    public void loadProperties() throws IOException {
//        InputStream in = getClass().getResourceAsStream("config.properties");
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
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
                sb.append(String.format("%-30s%s%s", item.getName(), "файл", this.sep));
            }
        }
        return sb.toString();
    }

    @Override
    public String getSubDirectoriesList(File dir) {
        StringBuilder sb = new StringBuilder();
        for(File item : dir.listFiles()) {
            if (item.isDirectory()) {
                sb.append(String.format("%-30s%s%s", item.getName(), "каталог", this.sep));
            }
        }
        return sb.toString();
    }

    @Override
    public String getCurrentDirList(File dir) {
        return this.getSubDirectoriesList(dir) + this.getFilesList(dir);
    }
}
