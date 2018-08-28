package org.js;

import java.io.*;
import java.net.Socket;

/**
 * Created by JiaShun on 2018/7/7.
 */

public class BioServerTask implements Runnable {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    public BioServerTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //1：拿到客户端的请求信息
        try {
            //大数据量传输的时候，限制文件最大内存
            //因为JVM内存放不下
            inputStream =  socket.getInputStream();//阻塞 inputStream 没有缓冲区，只能一次读完
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String lineStr = reader.readLine();
            System.out.println("BioServerTask -> 读到数据:" + lineStr);
            //2：将数据写回客户端
            String result = "hello";
            if(lineStr.endsWith("mmp")){
                result = "cnm";
            }
            outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream);
            writer.println(result);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
