package com.example.demo;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

/**
 * 操作NIO实现http并发请求
 */
public class HttpTemplate {
        public static String httpGet(String url) {
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.exchange(url, HttpMethod.GET, null, String.class).getBody();
            return result;
        }

        public  static String httpPost(String url, String name) {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.postForEntity(url, name, String.class).getBody();
        }

        @Test
        public  void t1() {
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

}
