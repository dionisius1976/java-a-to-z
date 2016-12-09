package ru.dionisius.trackers;

import ru.dionisius.action.IAction;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.File;

/**
 * Created by Dionisius on 05.12.2016.
 */
public abstract class ATracker implements ITracker {
    /**
     * Number of available actions for this program.
     */
    static final int NUMBER_OF_USER_ACTIONS = 5;
    /**
     * Buffer size to read shecified file.
     */
    private static final int BUFFER_SIZE = 1024;
    /**
     * Store of available actions.
     */
    private IAction[] actions = new IAction[NUMBER_OF_USER_ACTIONS];
    /**
     *Path to file with settings.
     */
    private String propertiesFile;
    /**
     *Input stream.
     */
    private DataInputStream dis;
    /**
     *Output stream.
     */
    private DataOutputStream dos;

    /**
     * Current operating system line separator.
     */
    private final String sep = System.getProperty("line.separator");


    /**
     * Constructor.
     * @param propertiesFile path to file with properties
     */
    public ATracker(String propertiesFile) {
        this.propertiesFile = propertiesFile;
    }

    /**
     * Initial method to start.
     */
    public abstract void init();

    /**
     * Fills the store of available actions.
     * @throws IOException if IO error occurs
     */
    public abstract void fillActions() throws IOException;

    /**
     * Sets conection with spesified socket.
     * @throws IOException if IO error occurs
     */
    public abstract void setConnection() throws IOException;

    @Override
    public int[] getRange() {
        int[] result = new int[this.actions.length];
        for (int index = 0; index < this.actions.length; index++) {
            result[index] = index + 1;
        }
        return result;
    }

    @Override
    public abstract void select(int key) throws IOException;

    @Override
    public abstract void loadProperties() throws IOException;

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
        for (File item : dir.listFiles()) {
            if (!item.isDirectory()) {
                sb.append(String.format("%-30s%s%s", item.getName(), "файл", this.sep));
            }
        }
        return sb.toString();
    }

    @Override
    public String getSubDirectoriesList(File dir) {
        StringBuilder sb = new StringBuilder();
        for (File item : dir.listFiles()) {
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
