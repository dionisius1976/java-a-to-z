package ru.dionisius.trackers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Dionisius on 05.12.2016.
 */
public interface ITracker {

    void init();

    void select (int key) throws IOException;

    int[] getRange();

    void fillActions() throws IOException;

    void loadProperties() throws IOException;

    void setConnection() throws IOException;

    void fileTransfer(InputStream in, OutputStream out) throws IOException;

    String getFilesList (File dir);

    String getSubDirectoriesList(File dir);

    String getCurrentDirList(File dir);
}
