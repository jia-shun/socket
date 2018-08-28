package org.js;

import org.js.server.NettyServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by JiaShun on 2018/7/11.
 */
@SpringBootApplication
public class NettyServerApplication {


    public static void main(String[] args) {
        new NettyServer(8080).action();
    }

}
