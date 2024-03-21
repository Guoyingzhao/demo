package com.example.demo;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 操作NIO实现http并发请求
 */
public class HttpTemplate {
    public static String httpGet(String url) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.exchange(url, HttpMethod.GET, null, String.class).getBody();
        return result;
    }

    public static String httpPost(String url, String name) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(url, name, String.class).getBody();
    }

    @Test
    public void t1() {
//            long l = System.currentTimeMillis();
//            for (int i = 0; i < 10; i++) {
//                int length = HttpTemplate.httpGet("https://www.example.com").length();
//                System.out.println("get:"+length);
//            }
//            long l1 = System.currentTimeMillis();
//            for (int i = 0; i < 10; i++) {
//                int length = HttpTemplate.httpPost("https://www.example.com", "ming").length();
//                System.out.println("post:"+length);
//            }
//            long l2 = System.currentTimeMillis();
//            System.out.println("get用时"+(l1-l));
//            System.out.println("post用时"+(l2-l1));

        System.out.println(HttpTemplate.httpPost("https://www.example.com", "ming"));
    }

    @Test
    public void t2() throws IOException {
        // 创建一个SocketChannel实例
        SocketChannel socketChannel = SocketChannel.open();
        // 连接到目标服务器
        socketChannel.connect(new InetSocketAddress("www.example.com", 80));

        // 创建一个ByteBuffer来存储请求数据
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 写入HTTP GET请求
        buffer.put("GET / HTTP/1.1\r\n".getBytes());
        buffer.put("Host: www.example.com\r\n".getBytes());
        buffer.put("Connection: Close\r\n\r\n".getBytes());
        buffer.flip();

        // 发送请求
        socketChannel.write(buffer);

        // 读取响应
        buffer.clear();
        while (socketChannel.read(buffer) != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            buffer.clear();
        }
        // 关闭连接
        socketChannel.close();
    }


    @Test
    public void t4() throws IOException {

        // 创建一个Selector
        Selector selector = Selector.open();

        // 创建并连接SocketChannel
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        for (int i = 0; i < 5; i++) {
            socketChannel.connect(new InetSocketAddress("www.example.com", 80));
        }

        // 注册SocketChannel到Selector
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        while (true) {
            // 等待事件
            selector.select();

            // 处理事件
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                if (key.isConnectable()) {
                    // 连接完成
                    SocketChannel channel = (SocketChannel) key.channel();
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }

                    // 发送HTTP请求
                    ByteBuffer request = ByteBuffer.wrap("GET / HTTP/1.1\r\nHost: www.example.com\r\nConnection: Close\r\n\r\n".getBytes());
                    channel.write(request);

                    // 注册读事件
                    channel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    // 读取响应
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer response = ByteBuffer.allocate(1024);
                    channel.read(response);
                    response.flip();
                    while (response.hasRemaining()) {
                        System.out.print((char) response.get());
                    }
                    response.clear();

                    // 关闭连接
                    channel.close();
                }

                keyIterator.remove();
            }
        }


    }
}
