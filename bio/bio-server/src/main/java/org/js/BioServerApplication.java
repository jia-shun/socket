package org.js;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

@SpringBootApplication
public class BioServerApplication {
    /**
     * 针对网络编程：
     * 1：客户端传入的数据首先第一步是和操作系统OS打交道
     * 2：OS底层有一个缓冲区buffer，客户端将数据流先缓冲到OS buffer
     * 3：随后OS和JVM交互，将数据流复制到JVM内存中。
     * 4：随后我们使用socket编程将JVM内存中数据流和我们的业务交互
     * @param args
     */

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost",8088));
            //响应客户端的请求
            while (true) {
                Socket socket = serverSocket.accept();//阻塞
                System.out.println("接受用户请求");
                new Thread(new BioServerTask(socket)).start();//阻塞
                /**
                 * 高并发情况下50QPS new Thread是要耗费时间的，假设new的过程耗费0.1s，
                 * 那个在50个QPS的情况下会收到5个左右连接，这样就会丢失4个连接，会导致连接超时。
                 * 即使是用线程池，new也会耗费时间。假设500个QPS呢
                 * Tomcat默认就是使用的这种模式，虽然加了连接池。但默认情况下一般能承受QPS：100~150
                 */
                }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
