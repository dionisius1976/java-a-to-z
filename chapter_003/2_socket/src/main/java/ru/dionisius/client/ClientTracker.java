package ru.dionisius.client;

import ru.dionisius.action.AClientAction;
import ru.dionisius.action.IAction;
import ru.dionisius.input.ConsoleInput;
import ru.dionisius.input.Input;
import ru.dionisius.input.ValidateInput;
import ru.dionisius.trackers.ATracker;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;

/**
 * Created by Dionisius on 02.12.2016.
 */
public class ClientTracker extends ATracker {
    /**
     * Number of available actions for this program.
     */
    static final int NUMBER_OF_USER_ACTIONS = 5;
    /**
     *The current client directory path.
     */
    private final String path = String.format("%s%s%s", System.getProperty("user.dir"),
            File.separator, "chapter_003\\socket_2\\src\\main\\java\\ru\\ru.dionisius\\client");
    /**
     * Buffered reader for console input.
     */
    private BufferedReader keyboard;
    /**
     *inputs stream for transfer ru.dionisius.data.
     */
    private DataInputStream dis;
    /**
     *Output stream for transfer ru.dionisius.data.
     */
    private DataOutputStream dos;
    /**
     * Store of available actions.
     */
    private IAction[] actions = new  AClientAction[this.NUMBER_OF_USER_ACTIONS];
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
     * Socket for binding with server.
     */
    private Socket socket;
    /**
     * Type for ru.dionisius.data input.
     */
    private final Input input;
    /**
     * Constructor.
     * @param input type for ru.dionisius.data input
     * @param propertiesFile path to properties file
     */
    public ClientTracker(final String propertiesFile, final Input input) {
        super(propertiesFile);
        this.input = input;
    }
    /**
     * Starts executing.
     */
    public void init() {
        try {
            this.setConnection();
            this.fillActions();
            this.dis = new DataInputStream(this.socket.getInputStream());
            this.dos = new DataOutputStream(this.socket.getOutputStream());
            do {
                this.showMenu();
                this.select(input.ask("Выберете действие: ", this.getRange()));
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.keyboard.close();
                this.socket.close();
                this.dis.close();
                this.dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Sets connection with server via socket.
     * And fills array of actions
     * and loads properties from specified file
     * @throws IOException if IO error occurs
     */
    public void setConnection() throws IOException {
        this.loadProperties();
        this.keyboard = new BufferedReader(new InputStreamReader(System.in));
        System.out.printf("Подключение к порту: %s%s", this.prop.getProperty("server_port"), this.sep);
        this.socket = new Socket(InetAddress.getByName(this.prop.getProperty("ip_address")),
                Integer.valueOf(this.prop.getProperty("server_port")));
    }
    /**
     * Loads properties for this client.
     * @throws IOException if IO error occurs
     */
    @Override
    public void loadProperties() throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        this.prop.load(in);
        in.close();
    }
    @Override
    public void select(final int key) throws IOException {
        this.actions[key - 1].execute(this.dis, this.dos);
    }
    /**
     * Fills array of available actions.
     * @throws IOException if IO error occurs
     */
     public void fillActions() throws IOException {
         ConsoleInput ci = new ConsoleInput();
        int counter = 0;
        this.actions[counter++] = this.new GetCurrentDirFilesList("Получить список текущего каталога.", ci, counter);
        this.actions[counter++] = this.new GoToSubDir("Перейти в подкаталог.", ci, counter);
        this.actions[counter++] = this.new GoToParentsDir("Перейти в родительский каталог.", ci, counter);
        this.actions[counter++] = this.new DownloadFile("Скачать файл.", ci, counter);
        this.actions[counter++] = this.new SendFile("Отправить файл.", ci, counter);
    }
    /**
     * Shows menu of available actions.
     */
    private void showMenu() {
        System.out.println();
        System.out.println("Выберете действие:");
        for (IAction action: this.actions) {
                System.out.println(action.info());
        }
    }
    /**
     * Gets and prints files and subdirectories of current directory.
     */
    private class GetCurrentDirFilesList extends AClientAction implements IAction {
        /**
         * Constructor.
         * @param name info about this class action
         * @param input input stream
         * @param key dentifying key of this instance
         * @throws IOException if IO error occurs
         */
        GetCurrentDirFilesList(final String name, final Input input, final int key) throws IOException {
            super(name, input, key);
        }
        /**
         * Gets and prints files and subdirectories of current server directory.
         * @param in input stream
         * @param out stream to out
         * @throws IOException if IO error occurs
         */
        public void execute(final DataInputStream in, final DataOutputStream out) throws IOException {
            this.sendKey(out);
            System.out.println(this.getResponse(in));
        }
    }
    /**
     * Changes current directory to subdirectory specified by user.
     */
    private class GoToSubDir extends AClientAction {
        /**
         * Constructor.
         * @param name info about this class action
         * @param input input stream
         * @param key dentifying key of this instance
         * @throws IOException if IO error occurs
         */
        GoToSubDir(final String name, final Input input, final int key) throws IOException {
            super(name, input, key);
            System.out.println(key);
        }
        /**
         * Changes current directory to subdirectory specified by user.
         * @param in input stream
         * @param out stream to out
         * @throws IOException if IO error occurs
         */
        public void execute(final DataInputStream in, final DataOutputStream out) throws IOException {
            this.sendKey(out);
            String currentLine = this.getResponse(in);
            currentLine = ClientTracker.this.input.ask(currentLine);
            this.sendMessage(out, currentLine);
            System.out.println(this.getResponse(in));
        }
    }
    /**
     * Changes current directory to parent directory.
     */
    private class GoToParentsDir extends AClientAction {
        /**
         * Constructor.
         * @param name info about this class action
         * @param input input stream
         * @param key dentifying key of this instance
         * @throws IOException if IO error occurs
         */
        GoToParentsDir(final String name, final Input input, final int key) throws IOException {
            super(name, input, key);
        }
        /**
         * Changes current directory to parent directory.
         * @param in input stream
         * @param out stream to out
         * @throws IOException if IO error occurs
         */
        public void execute(final DataInputStream in, final DataOutputStream out) throws IOException {
            this.sendKey(out);
            System.out.println(this.getResponse(in));
        }
    }
    /**
     * Downloads file specified by user.
     */
    private class DownloadFile extends AClientAction {
        /**
         * Constructor.
         * @param name info about this class action
         * @param input input stream
         * @param key dentifying key of this instance
         * @throws IOException if IO error occurs
         */
        DownloadFile(final String name, final Input input, final int key) throws IOException {
            super(name, input, key);
        }
        /**
         * Downloads file specified by user.
         * @param in input stream
         * @param out stream to out
         * @throws IOException if IO error occurs
         */
        public void execute(final DataInputStream in, final DataOutputStream out) throws IOException {
            this.sendKey(out);
            String currentLine = this.getResponse(in);
            currentLine = ClientTracker.this.input.ask(currentLine);
            this.sendMessage(out, currentLine);
            File file = new File(String.format("%s%s%s", ClientTracker.this.path,
                    ClientTracker.this.fSep, currentLine));
            FileOutputStream fout = new FileOutputStream(file);
            ClientTracker.this.fileTransfer(in, fout);
            fout.close();
            System.out.printf("Файл %s получен в директорию: %s%s", file.getName(),
                    ClientTracker.this.path, ClientTracker.this.sep);
        }
    }
    /**
     * Sends the file specified by user.
     */
    private class SendFile extends AClientAction {
        /**
         * Constructor.
         * @param name info about this class action
         * @param input input stream
         * @param key dentifying key of this instance
         * @throws IOException if IO error occurs
         */
        SendFile(final String name, final Input input, final int key) throws IOException {
            super(name, input, key);
        }
        /**
         * Sends the file specified by user.
         * @param in input stream
         * @param out stream to out
         * @throws IOException if IO error occurs
         */
        public void execute(final DataInputStream in, final DataOutputStream out) throws IOException {
            this.sendKey(out);
            File currentDir = new File(ClientTracker.this.path);
            String currentFile = ClientTracker.this.input.ask(String.format("Выберете файл: %s%s", ClientTracker.this.sep,
                    ClientTracker.this.getFilesList(currentDir)));
            this.sendMessage(out, currentFile);
            File file = new File(String.format("%s%s%s", ClientTracker.this.path,
                    ClientTracker.this.fSep, currentFile));
            FileInputStream fin = new FileInputStream(file);
            ClientTracker.this.fileTransfer(fin, out);
            fin.close();
            System.out.printf("Файл %s отправлен.%s", file.getName(), ClientTracker.this.sep);
        }
    }

    /**
     * This method starts this program.
     * @param args arguments from console
     */
    public static void main(String[] args) {
        new ClientTracker("config.properties", new ValidateInput()).init();
    }
}
