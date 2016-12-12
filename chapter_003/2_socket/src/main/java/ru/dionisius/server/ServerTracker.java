package ru.dionisius.server;

import ru.dionisius.action.AServerAction;
import ru.dionisius.action.IAction;
import ru.dionisius.trackers.ATracker;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * Created by Dionisius on 02.12.2016.
 */
public class ServerTracker extends ATracker {
    /**
     * Number of available actions for this program.
     */
    static final int NUMBER_OF_USER_ACTIONS = 5;

    /**
     *The current client directory path.
     */
    private final String path = String.format("%s%s%s", System.getProperty("user.dir"),
            File.separator, "chapter_003\\socket_2\\src\\main\\java\\ru\\dionisius\\client");

    /**
     * Root directory.
     */
    private final File rootDir = new File(String.format("%s%s%s", System.getProperty("user.dir"),
            File.separator, "chapter_003\\socket_2\\src\\main\\java\\ru\\dionisius"));

    /**
     * Current directory.
     */
    private File currentDir;
    /**
     * Store of available actions.
     */
    private IAction[] actions;
    /**
     *Object to work with file properties.
     */
    private Properties prop = new Properties();
    /**
     * File separator for paths supporting by current operation system.
     */
    private final String fSep = File.separator;
    /**
     * Current operating system line separator.
     */
    private final String sep = System.getProperty("line.separator");
    /**
     *Input stream.
     */
    private DataInputStream dis;
    /**
     *Output stream.
     */
    private DataOutputStream dos;
    /**
     * Socket for binding with server.
     */
    private Socket socket;


    /**
     * Constructor.
     * @param propertiesFile path to file with properties
     */
    public ServerTracker(String propertiesFile) {
        super(propertiesFile);
    }

    @Override
    public void init() {
        try {
            this.setConnection();
            this.currentDir = rootDir;
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            do {
                this.select(this.getKey(dis));
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.socket.close();
                dis.close();
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void fillActions() throws IOException {
        int counter = 0;
        this.actions[counter++] = this.new SendCurrentDirFilesList();
        this.actions[counter++] = this.new GoToSubDir();
        this.actions[counter++] = this.new GoToParentsDir();
        this.actions[counter++] = this.new SendFile();
        this.actions[counter++] = this.new GetFile();
    }

    @Override
    public void setConnection() throws IOException {
        this.actions = new AServerAction[this.NUMBER_OF_USER_ACTIONS];
        this.fillActions();
        this.loadProperties();
        System.out.println("Ожидание подключения клиента...");
        ServerSocket serverSocket = new ServerSocket(Integer.valueOf(this.prop.getProperty("server_port")));
        socket = serverSocket.accept();
        System.out.println("Подключение клиента установлено.");
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
    }

    /**
     * @throws IOException if IO error occurs
     */
    @Override
    public void loadProperties() throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        this.prop.load(in);
        in.close();
    }

    @Override
    public void select(int key) throws IOException {
        this.actions[key - 1].execute(this.dis, this.dos);
    }

    /**
     * Returns inputted by user key for specified action.
     * @param in data input stream
     * @return inputted by user key for specified action
     * @throws IOException if IO error occurs
     */
    private int getKey(DataInputStream in) throws IOException {
        int inkey = Integer.parseInt(in.readUTF());
        System.out.println(inkey);
        return inkey;
    }

    /**
     * Sends to client files and subdirectories list of current directory.
     */
    private class SendCurrentDirFilesList extends AServerAction {

        @Override
        public void execute(DataInputStream in, DataOutputStream out) throws IOException {
            this.sendMessage(out, String.format("В директории: %s%s%s",
                    ServerTracker.this.currentDir.getAbsolutePath(), ServerTracker.this.sep,
                    ServerTracker.this.getCurrentDirList(ServerTracker.this.currentDir)));
        }
    }

    /**
     * Changes current directory to subdirectory specified by user.
     */
    private class GoToSubDir extends AServerAction {

        @Override
        public void execute(DataInputStream in, DataOutputStream out) throws IOException {
            this.sendMessage(out, String.format("Выберете поддиректорию:%s%s",
                    ServerTracker.this.sep, ServerTracker.this.getSubDirectoriesList(currentDir)));
            String currentSubDir = this.getResponse(in);
            ServerTracker.this.currentDir = new File(String.format("%s%s%s",
                    ServerTracker.this.currentDir.getAbsolutePath(), ServerTracker.this.fSep, currentSubDir));
            if (ServerTracker.this.currentDir.isDirectory()) {
                this.sendMessage(out, String.format("В директории: %s",
                        ServerTracker.this.currentDir.getAbsolutePath()));
            } else {
                this.sendMessage(out, String.format("%s не является директорией. %s",
                        currentSubDir, ServerTracker.this.sep));
                ServerTracker.this.currentDir = new File(ServerTracker.this.currentDir.getParent());
            }
        }
    }

    /**
     * Changes current directory to parent directory.
     */
    private class GoToParentsDir extends AServerAction {

        @Override
        public void execute(DataInputStream in, DataOutputStream out) throws IOException {
            ServerTracker.this.currentDir = new File(ServerTracker.this.currentDir.getParent());
            this.sendMessage(out, String.format("В директории: %s%s",
                    ServerTracker.this.currentDir.getAbsolutePath(), ServerTracker.this.sep));
        }
    }

    /**
     * Sends the file specified by user.
     */
    private class SendFile extends AServerAction {

        @Override
        public void execute(DataInputStream in, DataOutputStream out) throws IOException {
            this.sendMessage(out, String.format("Выберете файл для копирования:%s%s",
                    ServerTracker.this.sep, ServerTracker.this.getFilesList(ServerTracker.this.currentDir)));
            String currentMesssage = this.getResponse(in);
            File file = new File(String.format("%s%s%s",
                    ServerTracker.this.currentDir.getAbsolutePath(), ServerTracker.this.fSep, currentMesssage));
            FileInputStream fin = new FileInputStream(file);
            ServerTracker.this.fileTransfer(fin, ServerTracker.this.dos);
            fin.close();
        }

    }

    /**
     * Downloads file specified by user.
     */
    private class GetFile extends AServerAction {

        @Override
        public void execute(DataInputStream in, DataOutputStream out) throws IOException {
            String currentMesssage = this.getResponse(in);
            File file = new File(String.format("%s%s%s",
                    ServerTracker.this.currentDir.getAbsolutePath(), ServerTracker.this.fSep, currentMesssage));
            FileOutputStream fout = new FileOutputStream(file);
            ServerTracker.this.fileTransfer(ServerTracker.this.dis, fout);
            fout.close();
        }
    }

    /**
     * Starts this program.
     * @param args arguments from console
     */
    public static void main(String[] args) {
        new ServerTracker("config.properties").init();
    }
}