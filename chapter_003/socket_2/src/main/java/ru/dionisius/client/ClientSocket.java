package ru.dionisius.client;

import ru.dionisius.PropertiesSetter;

import java.util.Properties;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**ClientSocket.
 * This class creates client that connects with server
 * using properties file with specified settings
 * Created by Dionisius on 28.11.2016.
 */
public class ClientSocket {
    /**
     * Buffer size to write in log file.
     */
    private static final int BUFFER_SIZE = 1024;
    /**
     *Object with properties with specified settings
     */
    private  final File properties;
    /**
     *Input stream
     */
    private DataInputStream dis;
    /**
     *Output stream
     */
    private DataOutputStream dos;
    /**
     *Object to work with file properties
     */
    private Properties prop = new Properties();
    /**
     * Current operating system line separator.
     */
    private final String sep = System.getProperty("line.separator");
    /**
     *Path to current client directory
     */
    private final String path = String.format("%s%s%s", System.getProperty("user.dir"),
            File.separator, "chapter_003\\socket_2\\src\\main\\java\\ru\\dionisius\\client");

    /**ClientSocket(File properties).
     * Cunstructor
     * @param properties file with properties
     */
    public ClientSocket(File properties) {
        this.properties = properties;
    }

    /**init().
     * This method starts client
     */
    public void init() {
        try(InputStream input = new FileInputStream (properties)) {
            prop.load(input);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try (BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))) {
            InetAddress ipAdressObj = InetAddress.getByName(this.prop.getProperty("ip_address"));
            System.out.printf("Подключение к порту: %s%s", this.prop.getProperty("server_port"), this.sep );
            Socket socket = new Socket(ipAdressObj, Integer.valueOf(this.prop.getProperty("server_port")));
            System.out.println("Подключение установлено.");
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            String currentLine;
            while (true) {
                currentLine = dis.readUTF();
                System.out.println(currentLine);
                currentLine = keyboard.readLine();
                if("1".equals(currentLine) || "3".equals(currentLine)) {
                    dos.writeUTF(currentLine);
                    dos.flush();
                    currentLine = dis.readUTF();
                    System.out.println(currentLine);
                    continue;
                }
                if("2".equals(currentLine)) {
                    dos.writeUTF(currentLine);
                    dos.flush();
                    currentLine = dis.readUTF();
                    System.out.println(currentLine);
                    currentLine = keyboard.readLine();
                    dos.writeUTF(currentLine);
                    dos.flush();
                    currentLine = dis.readUTF();
                    System.out.println(currentLine);
                    continue;
                }
                if("4".equals(currentLine)) {
                    dos.writeUTF(currentLine);
                    dos.flush();
                    currentLine = dis.readUTF();
                    System.out.println(currentLine);
                    currentLine = keyboard.readLine();
                    dos.writeUTF(currentLine);
                    dos.flush();
                    File file = new File(String.format("%s\\%s", this.path, currentLine));
                    FileOutputStream fout = new FileOutputStream(file);
                    this.fileTransfer(this.dis, fout);
                    fout.close();
                    System.out.println("Файл получен.");
                    continue;
                }
                if("5".equals(currentLine)) {
                    dos.writeUTF(currentLine);
                    dos.flush();
                    File currentDir = new File(this.path);
                    System.out.printf("Выберете файл: %s%s", this.sep, this.getFilesList(currentDir));
                    currentLine = keyboard.readLine();
                    File file = new File(String.format("%s\\%s", currentDir, currentLine));
                    FileInputStream fin = new FileInputStream(file);
                    this.fileTransfer(fin, this.dos);
                    fin.close();
                    System.out.println("Файл отправлен.");
                    continue;
                }
                else System.out.println("Некорректный ввод.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**fileTransfer().
     * This method transfers the file
     * @param in - stream source file
     * @param out - stream transfer destination
     * @throws IOException
     */
    private void fileTransfer(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
            out.flush();
        }
    }

    /**getFilesList().
     * This method return all files in specified directory
     * @param dir specified directory
     * @return list of all files in specified directory
     */
    private String getFilesList (File dir) {
        StringBuilder sb = new StringBuilder();
        for(File item : dir.listFiles()) {
            if (!item.isDirectory()) {
                sb.append(String.format("%s\tфайл%s", item.getName(), this.sep));
            }
        }
        return sb.toString();
    }

    /**main().
     * This method starts this programm
     * @param args arguments from console
     */
    public static void main(String[] args) {
        File file = new File((String.format("%s%s%s", System.getProperty("user.dir"),
                File.separator, "chapter_003\\socket_2\\src\\main\\java\\ru\\dionisius\\config.properties")));
        if (!file.exists()) {
            PropertiesSetter.setProperties();
            file = new File((String.format("%s%s%s", System.getProperty("user.dir"),
                    File.separator, "chapter_003\\socket_2\\src\\main\\java\\ru\\dionisius\\config.properties")));
        }
        new ClientSocket(file).init();
    }
}
