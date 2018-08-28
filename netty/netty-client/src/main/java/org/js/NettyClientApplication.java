package org.js;

import org.js.client.NettyClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by JiaShun on 2018/7/11.
 */
@SpringBootApplication
public class NettyClientApplication {

    public static void main(String[] args) {
        new NettyClient("localhost",8080).action();
    }
}
