package ru.dionisius.client;

import ru.dionisius.PropertiesSetter;

import java.util.Properties;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Dionisius on 28.11.2016.
 */
public class ClientSocket {
    private  final File properties;
    private DataInputStream dis;
    private DataOutputStream dos;
    private Properties prop = new Properties();
    private final String path = String.format("%s%s%s", System.getProperty("user.dir"),
            File.separator, "chapter_003\\socket_2\\src\\main\\java\\ru\\dionisius\\client");

    public ClientSocket(File properties) {
        this.properties = properties;
    }

    public void init() {
        try(InputStream input = new FileInputStream (properties)) {
            prop.load(input);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        try (BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in))) {
            InetAddress ipAdressObj = InetAddress.getByName(this.prop.getProperty("ip_address"));
            System.out.printf("Подключение к порту: %s\r\n", this.prop.getProperty("server_port"));
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
                    System.out.printf("Выберете файл: \r\n%s", this.getFilesList(currentDir));
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

    private void fileTransfer(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
    }

    private String getFilesList (File dir) {
        StringBuilder sb = new StringBuilder();
        for(File item : dir.listFiles()) {
            if (!item.isDirectory()) {
                sb.append(String.format("%s\tфайл\r\n", item.getName()));
            }
        }
        return sb.toString();
    }

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
