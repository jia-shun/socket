package org.js;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.Socket;

@SpringBootApplication
public class BioClientApplication {

    public static void main(String[] args) {

        Socket socket = null;
        try {
            socket = new Socket("localhost",8088);
            //1:写数据给服务端
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream);
            writer.write("mmp\r\n");
            writer.flush();
            //2：读取服务器端返回的数据
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String lineStr = reader.readLine();
            System.out.println("Client -> 服务器返回数据：" + lineStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
