package ru.dionisius.server;

import ru.dionisius.PropertiesSetter;
import java.util.Properties;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

/**ServerSocket.
 * This class creates server that connects with client
 * using properties file with specified settings
 * Created by Dionisius on 29.11.2016.
 */
public class ServerSocket {
    /**
     * Buffer size to write in log file.
     */
    private static final int BUFFER_SIZE = 1024;
    /**
     * Object with properties with specified settings
     */
    private  final File properties;
    /**
     * menu list
     */
    private final String menu = "Выберете действие:\r\n" +
                                "1 - получить список текущего каталога\r\n"+
                                "2 - перейти в подкаталог\r\n"+
                                "3 - перейти в родительский каталог\r\n"+
                                "4 - скачать файл\r\n"+
                                "5 - отправить файл";
    /**
     * Current directory
     */
    private File dir;
    /**
     * Object to work with file properties
     */
    private Properties prop = new Properties();

    /**ServerSocket().
     * Constructor
     * @param properties file with properties
     */
    public ServerSocket (File properties) {
        this.properties = properties;
        this.dir = new File (String.format("%s%s%s", System.getProperty("user.dir"),
                File.separator, "chapter_003\\socket_2\\src\\main\\java\\ru\\dionisius"));
    }

    /**init().
     * This method starts server
     */
    private void init() {
        try(InputStream input = new FileInputStream (properties)) {
            prop.load(input);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.println("Ожидание подключения клиента...");
        try (java.net.ServerSocket servSocket = new java.net.ServerSocket(Integer.parseInt(this.prop.getProperty("server_port")));
             Socket socket = servSocket.accept()){
            System.out.println("Подключение клиента установлено.");

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            String currentLine;
            File currentDir = this.dir;

            while (true) {
                currentLine = this.menu;
                dos.writeUTF(currentLine);
                dos.flush();
                currentLine = dis.readUTF();
                if ("1".equals(currentLine)) {
                    dos.writeUTF(this.getRootsList(currentDir));
                    dos.flush();
                }
                if ("2".equals(currentLine)) {
                    dos.writeUTF(String.format("Выберете директорию:\r\n%s", this.getSubDirectoriesList(currentDir)));
                    dos.flush();
                    currentLine = dis.readUTF();
                    currentDir = new File(String.format("%s\\%s",this.dir.getAbsolutePath(), currentLine));
                    dos.writeUTF(String.format("В поддиректории: %s\n", currentDir.getAbsolutePath()));
                    dos.flush();
                }
                if ("3".equals(currentLine)) {
                    currentDir = new File(currentDir.getParent());
                    dos.writeUTF(String.format("В родительской директории: %s", currentDir.getAbsolutePath()));
                    dos.flush();
                }
                if ("4".equals(currentLine)) {
                    dos.writeUTF(String.format("Выберете файл для копирования:\r\n%s", this.getFilesList(currentDir)));
                    dos.flush();
                    currentLine = dis.readUTF();
                    File file = new File(String.format("%s\\%s", currentDir.getAbsolutePath(), currentLine));
                    FileInputStream fin = new FileInputStream(file);
                    this.fileTransfer(fin, dos);
                    fin.close();
                    continue;
                }
                if ("5".equals(currentLine)) {
                    System.out.printf("Принимается файл в: %s", currentDir.getAbsolutePath());
                    File file = new File(String.format("%s\\%s", currentDir.getAbsolutePath(), currentLine));
                    FileOutputStream fout = new FileOutputStream(file);
                    this.fileTransfer(dis, fout);
                    fout.close();
                    continue;
                }
                else continue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param dir
     * @return
     */
    private String getRootsList(File dir) {
        return this.getSubDirectoriesList(dir)+this.getFilesList(dir);
    }

    /**
     * @param dir
     * @return
     */
    private String getFilesList (File dir) {
        StringBuilder sb = new StringBuilder();
        for(File item : dir.listFiles()) {
            if (!item.isDirectory()) {
                sb.append(String.format("%s\tфайл\r\n", item.getName()));
            }
        }
        return sb.toString();
    }

    /**
     * @param dir
     * @return
     */
    private String getSubDirectoriesList(File dir) {
        StringBuilder sb = new StringBuilder();
        for(File item : dir.listFiles()) {
            if (item.isDirectory()) {
                sb.append(String.format("%s\tкаталог\r\n", item.getName()));
            }
        }
        return sb.toString();
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

    /**main().
     * This method starts this programm
     * @param args arguments from console
     */
    public static void main(String[] args) {

        File file = new File((String.format("%s%s%s", System.getProperty("user.dir"),
                File.separator, "chapter_003\\socket_2\\src\\main\\java\\ru\\dionisius\\config.properties")));
        if (!file.exists()) {
            PropertiesSetter.setProperties();
        }
        new ServerSocket(file).init();
    }
}


