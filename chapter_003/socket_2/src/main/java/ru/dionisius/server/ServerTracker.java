package ru.dionisius.server;

//import ru.dionisius.PropertiesSetter;
import ru.dionisius.action.AServerAction;
import ru.dionisius.trackers.ATracker;

import java.io.*;
import java.net.ServerSocket;

/**
 * Created by Dionisius on 02.12.2016.
 */
public class ServerTracker extends ATracker{

    /**
     *The current client directory path.
     */
    private final String path = String.format("%s%s%s", System.getProperty("user.dir"),
            File.separator, "chapter_003\\socket_2\\src\\main\\java\\ru\\dionisius\\client");

    private final File rootDir = new File (String.format("%s%s%s", System.getProperty("user.dir"),
            File.separator, "chapter_003\\socket_2\\src\\main\\java\\ru\\dionisius"));

    //private final File rootDir =
//    InputStream in  = this.getClass().getResourceAsStream("config.properties");

    private File currentDir;


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
            } while(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                this.socket.close();
                dis.close();
                dos.close();
            } catch(IOException e) {
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
        ServerSocket serverSocket = new ServerSocket(5000);
        socket = serverSocket.accept();
        System.out.println("Подключение клиента установлено.");
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
    }

    private int getKey(DataInputStream in) throws IOException{
        int inkey = Integer.parseInt(in.readUTF());
        System.out.println(inkey);
        return inkey;
    }

    /**
     *
     */
    private class SendCurrentDirFilesList extends AServerAction {

        @Override
        public void execute(DataInputStream in, DataOutputStream out) throws IOException {
            this.sendMessage(out, String.format("В директории: %s%s%s",
                    ServerTracker.this.currentDir.getAbsolutePath(), ServerTracker.this.sep,
                    ServerTracker.this.getCurrentDirList(ServerTracker.this.currentDir)));
        }
    }

    private class GoToSubDir extends AServerAction {

        @Override
        public void execute(DataInputStream in, DataOutputStream out) throws IOException {
            this.sendMessage(out, String.format("Выберете поддиректорию:%s%s",
                    ServerTracker.this.sep, ServerTracker.this.getSubDirectoriesList(currentDir)));
            String currentSubDir = this.getResponse(in);
            ServerTracker.this.currentDir = new File(String.format("%s\\%s",
                    ServerTracker.this.currentDir.getAbsolutePath(), currentSubDir));
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

    private class GoToParentsDir extends AServerAction {

        @Override
        public void execute(DataInputStream in, DataOutputStream out) throws IOException {
            ServerTracker.this.currentDir = new File(ServerTracker.this.currentDir.getParent());
            this.sendMessage(out, String.format("В директории: %s",
                    ServerTracker.this.currentDir.getAbsolutePath()));
        }
    }

    private class SendFile extends AServerAction {

        @Override
        public void execute(DataInputStream in, DataOutputStream out) throws IOException {
            this.sendMessage(out, String.format("Выберете файл для копирования:%s%s",
                    ServerTracker.this.sep, ServerTracker.this.getFilesList(ServerTracker.this.currentDir)));
            String currentMesssage = this.getResponse(in);
            File file = new File(String.format("%s\\%s",
                    ServerTracker.this.currentDir.getAbsolutePath(), currentMesssage));
            FileInputStream fin = new FileInputStream(file);
            ServerTracker.this.fileTransfer(fin, ServerTracker.this.dos);
            fin.close();
        }

    }

    private class GetFile extends AServerAction {

        @Override
        public void execute(DataInputStream in, DataOutputStream out) throws IOException {
            String currentMesssage = this.getResponse(in);
            File file = new File(String.format("%s\\%s",
                    ServerTracker.this.currentDir.getAbsolutePath(), currentMesssage));
            FileOutputStream fout = new FileOutputStream(file);
            ServerTracker.this.fileTransfer(ServerTracker.this.dis, fout);
            fout.close();
        }
    }

    /**
     * Starts this programm.
     * @param args arguments from console
     */
    public static void main(String[] args) {

//        InputStream in = getClass().getResourceAsStream("/file.txt");
//        File file = new File((String.format("%s%s%s", System.getProperty("user.dir"),
//                File.separator, "chapter_003\\socket_2\\src\\main\\java\\ru\\dionisius\\config.properties")));
//        System.out.println(String.format("%s%s%s", System.getProperty("user.dir"),
//                File.separator, "config.properties"));
        new ServerTracker("config.properties").init();
//        new ServerTracker(file).init();
    }

}