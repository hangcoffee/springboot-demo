package com.example.demo.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Auther: Coffee
 * @Date: 2019/4/6
 * <p>
 * 数据返回到页面
 */
@Slf4j
// 有参构造函数
public class PrintUtil {

    private HttpServletResponse response;

    public PrintUtil(HttpServletResponse response) {
        this.response = response;
    }

    public  PrintUtil(HttpServletResponse response, String contentType) {
        this.response = response;
        // 设置字符集编码
        this.response.setContentType(contentType);
    }

    public void print(Object message) {
        PrintWriter writer = null;
        try {
            if (response != null) {
                // 若系统打开了OutputStream,将其关闭
                writer = new PrintWriter(response.getOutputStream());
                String temp = new String(String.valueOf(message));
                writer.write(temp);
                writer.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }


}
