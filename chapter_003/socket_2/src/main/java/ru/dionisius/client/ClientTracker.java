package ru.dionisius.client;

import ru.dionisius.action.AClientAction;
import ru.dionisius.action.IAction;
import ru.dionisius.trackers.AClientTracker;
import ru.dionisius.trackers.ATracker;
import ru.dionisius.input.Input;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Dionisius on 02.12.2016.
 */
public class ClientTracker extends AClientTracker{

    /**
     *The current client directory path.
     */
    private final String path = String.format("%s%s%s", System.getProperty("user.dir"),
            File.separator, "chapter_003\\socket_2\\src\\main\\java\\ru\\dionisius\\client");

    /**
     *
     */
    private BufferedReader keyboard;

    /**
     * @param input
     */
    public ClientTracker(Input input, File properties) {
        super(input, properties);
    }

    /**
     *
     */
    public void init() {
        try {
            this.setConnection();
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            do {
                this.showMenu();
                this.select(input.ask("Выберете действие: ", this.getRange()));
            } while(!"y".equals(this.input.ask("Выход? (y)")));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.keyboard.close();
                this.socket.close();
                dis.close();
                dos.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     *
     * @throws IOException
     */
    public void setConnection() throws IOException {
        this.fillActions();
        this.loadProperties();
        this.keyboard = new BufferedReader(new InputStreamReader(System.in));
        this.socket = new Socket(InetAddress.getByName(this.prop.getProperty("ip_address")),
                Integer.valueOf(this.prop.getProperty("server_port")));
    }

    /**
     *
     *
     * @throws IOException
     */
     public void fillActions() throws IOException {
        int counter = 0;
        this.actions[counter++] = this.new GetCurrentDirFilesList("Получить список текущего каталога.", counter);
        this.actions[counter++] = this.new GoToSubDir("Перейти в подкаталог.", counter);
        this.actions[counter++] = this.new GoToParentsDir("Перейти в родительский каталог.", counter);
        this.actions[counter++] = this.new DownloadFile("Скачать файл.", counter);
        this.actions[counter++] = this.new SendFile("Отправить файл.", counter);
    }

    /**
     *
     */
    private void showMenu() {
        System.out.println("Выберете действие:");
        for(IAction action: this.actions){
                System.out.println(action.info());
        }
    }

    /**
     *
     */
    private class GetCurrentDirFilesList extends AClientAction implements IAction{

        public GetCurrentDirFilesList(String name, int key) throws IOException {
            super(name, key);
        }

        public void execute(DataInputStream in, DataOutputStream out) throws IOException {
            this.sendKey(out);
            System.out.println(this.getResponse(in));
        }
    }

    /**
     *
     */
    private class GoToSubDir extends AClientAction {

        public GoToSubDir(String name, int key) throws IOException {
            super(name, key);
        }

        public void execute(DataInputStream in, DataOutputStream out) throws IOException {
            this.sendKey(out);
            String currentLine = this.getResponse(in);
            System.out.println(currentLine);
            this.sendMessage(input, out, input.ask(currentLine));
            System.out.println(this.getResponse(in));
        }
    }

    /**
     *
     */
    private class GoToParentsDir extends AClientAction {

        public GoToParentsDir(String name, int key) throws IOException {
            super(name, key);
        }

        public void execute(DataInputStream in, DataOutputStream out) throws IOException {
            this.sendKey(out);
            System.out.println(this.getResponse(in));
        }
    }

    /**
     *
     */
    private class DownloadFile extends AClientAction {

        public DownloadFile(String name, int key) throws IOException {
            super(name, key);
        }

        public void execute(DataInputStream in, DataOutputStream out) throws IOException {
            this.sendKey(out);
            String currentLine = this.getResponse(in);
            currentLine = input.ask(currentLine);
            this.sendMessage(input, out, currentLine);
            File file = new File(String.format("%s\\%s", ClientTracker.this.path, currentLine));
            FileOutputStream fout = new FileOutputStream(file);
            ClientTracker.this.fileTransfer(in, fout);
            fout.close();
            System.out.printf("Файл %s получен.%s", file.getName(), ClientTracker.this.sep);
        }
    }

    /**
     *
     */
    private class SendFile extends AClientAction {

        public SendFile(String name, int key) throws IOException {
            super(name, key);
        }

        public void execute(DataInputStream in, DataOutputStream out) throws IOException {
            this.sendKey(out);
            File currentDir = new File(ClientTracker.this.path);
            String currentFile = input.ask(String.format("Выберете файл: %s%s", ClientTracker.this.sep,
                    ClientTracker.this.getFilesList(currentDir)));
            File file = new File(String.format("%s\\%s", currentDir, currentFile));
            FileInputStream fin = new FileInputStream(file);
            ClientTracker.this.fileTransfer(fin, out);
            fin.close();
            System.out.printf("Файл %s отправлен.%s", file.getName(), ClientTracker.this.sep);
        }
    }

}
