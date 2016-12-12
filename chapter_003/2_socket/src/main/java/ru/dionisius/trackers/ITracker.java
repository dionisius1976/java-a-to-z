package ru.dionisius.trackers;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.File;

/**
 * Created by Dionisius on 05.12.2016.
 */
public interface ITracker {

    /**
     * Initial method to start.
     */
    void init();

    /**
     * @param key specified key for specified action
     * @throws IOException if IO error occurs
     */
    void select(int key) throws IOException;

    /**
     * Store of available actions.
     * @return range of available actions
     */
    int[] getRange();

    /**
     * Fills the store of available actions.
     * @throws IOException if IO error occurs
     */
    void fillActions() throws IOException;

    /**
     * Loads properties for server or client from specified file.
     * @throws IOException if IO error occurs
     */
    void loadProperties() throws IOException;

    /**
     * Sets conection with spesified socket.
     * @throws IOException if IO error occurs
     */
    void setConnection() throws IOException;

    /**
     * Tranfers the file between server and client.
     * @param in input stream
     * @param out output stream
     * @throws IOException if IO error occurs
     */
    void fileTransfer(InputStream in, OutputStream out) throws IOException;

    /**
     * Returns list of files and subdirectories of current directory.
     * @param dir current directory
     * @return list of files and subdirectories of current directory
     */
    String getFilesList(File dir);

    /**
     * Returns list of subdirectories of current directory.
     * @param dir current directory
     * @return list of subdirectories of current directory
     */
    String getSubDirectoriesList(File dir);

    /**
     * Returns list of files of current directory.
     * @param dir current directory
     * @return list of files of current directory
     */
    String getCurrentDirList(File dir);
}
