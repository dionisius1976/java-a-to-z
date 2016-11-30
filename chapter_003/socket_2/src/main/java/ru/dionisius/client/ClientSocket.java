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
    private Properties prop = new Properties();
    private final String path = String.format("%s%s%s", System.getProperty("user.dir"),
            File.separator, "chapter_003\\socket_2\\src\\main\\java\\ru\\dionisius\\client");
    private final String menu = "Выберете действие:\r\n" +
                                "1 - получить список текущего каталога\r\n"+
                                "2 - перейти в подкаталог\r\n"+
                                "3 - перейти в родительский каталог\r\n"+
                                "4 - скачать файл\r\n"+
                                "5 - загрузить файл";

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
            System.out.printf("Подключаемся к порту: %s\r\n", this.prop.getProperty("server_port"));
            Socket socket = new Socket(ipAdressObj, Integer.valueOf(this.prop.getProperty("server_port")));

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            DataInputStream dis = new DataInputStream(is);
            DataOutputStream dos = new DataOutputStream(os);

            String currentLine;
            System.out.println("Подключение прошло успешно.\r\n");
            while (true) {
                this.showMenu();
                currentLine = keyboard.readLine();
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
                    this.receiveFile(is, String.format("%s\\%s", this.path, currentLine));
                    System.out.println("Файл скопирован.");
                    continue;
                }
                if("5".equals(currentLine)) {
                    dos.writeUTF(currentLine);
                    dos.flush();
                    File currentDir = new File(this.path);
                    System.out.printf("Выберете файл: \r\n%s", this.getFilesList(currentDir));
                    currentLine = keyboard.readLine();
                    this.sendFile(os, String.format("%s\\%s", currentDir, currentLine));
                    continue;
                }
                else {
                    dos.writeUTF(currentLine);
                    dos.flush();
                    currentLine = dis.readUTF();
                    System.out.println(currentLine);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMenu() {
        System.out.println(this.menu);
    }

    private void receiveFile (InputStream in, String filePath) throws IOException {
        FileOutputStream out = new FileOutputStream(filePath);
        byte[] buf = new byte[1024];
        int r;
        do {
            r = in.read(buf, 0, buf.length);
            if (r > 0) out.write(buf, 0, r);
        } while (r > 0);
        in.close();
        out.close();
    }

    public void sendFile(OutputStream out, String filePath) throws IOException {
        InputStream in = new FileInputStream(filePath);
        File file = new File(filePath);
        byte[] buf = new byte[1024];
        long fileSize = file.length();
        while (fileSize > 0) {
            int count = in.read(buf);
            out.write(buf, 0, count);
            out.flush();
            fileSize -= count;
        }
        in.close();
        out.close();
    }
//    }public void sendFile(OutputStream out, String filePath) throws IOException {
//        InputStream in = new FileInputStream(filePath);
//        byte[] buf = new byte[1024];
//        int r;
//        do {
//            r = in.read(buf, 0, buf.length);
//            if (r > 0) out.write(buf, 0, r);
 //               out.flush();
//        } while (r > 0);
//        in.close();
//        out.close();
//    }

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
