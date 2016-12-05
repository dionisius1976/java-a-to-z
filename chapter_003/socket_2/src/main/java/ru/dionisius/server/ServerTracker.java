package ru.dionisius.server;

import ru.dionisius.PropertiesSetter;
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
            File.separator, "chapter_003\\socket_2\\src\\main\\java\\ru\\dionisius"));;

    private File currentDir;


    public ServerTracker(File properties) {
        super(properties);
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
        } finally {
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
        this.fillActions();
        this.loadProperties();
        System.out.println("Ожидание подключения клиента...");
        ServerSocket servSocket =
                new ServerSocket(Integer.parseInt(this.prop.getProperty("server_port")));
        socket = servSocket.accept();
        System.out.println("Подключение клиента установлено.");
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
    }

    private int getKey(DataInputStream in) throws IOException{
        return Integer.parseInt(in.readUTF());
    }

    /**
     *
     */
    private class SendCurrentDirFilesList extends AServerAction {

        @Override
        public void execute(DataInputStream in, DataOutputStream out) throws IOException {
            this.sendMessage(out, ServerTracker.this.getCurrentDirList(ServerTracker.this.rootDir));
        }
    }

    private class GoToSubDir extends AServerAction {

        @Override
        public void execute(DataInputStream in, DataOutputStream out) throws IOException {
            this.sendMessage(out, String.format("Выберете директорию:%s%s",
                    ServerTracker.this.sep, ServerTracker.this.getSubDirectoriesList(currentDir)));
            String currentMesssage = this.getResponse(in);
            ServerTracker.this.currentDir = new File(String.format("%s\\%s",
                    ServerTracker.this.currentDir.getAbsolutePath(), currentMesssage));
            this.sendMessage(out, String.format("В поддиректории: %s",
                    ServerTracker.this.currentDir.getAbsolutePath()));
        }
    }

    private class GoToParentsDir extends AServerAction {

        @Override
        public void execute(DataInputStream in, DataOutputStream out) throws IOException {
            ServerTracker.this.currentDir = new File(ServerTracker.this.currentDir.getParent());
            this.sendMessage(out, String.format("В родительской директории: %s",
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
            this.sendMessage(out, String.format("Принимается файл в: %s",
                    ServerTracker.this.currentDir.getAbsolutePath()));
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

        File file = new File((String.format("%s%s%s", System.getProperty("user.dir"),
                File.separator, "chapter_003\\socket_2\\src\\main\\java\\ru\\dionisius\\config.properties")));
        if (!file.exists()) {
            PropertiesSetter.setProperties();
        }
        new ServerTracker(file).init();
    }

}
