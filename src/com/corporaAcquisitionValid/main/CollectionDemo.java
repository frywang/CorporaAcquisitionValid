package com.corporaAcquisitionValid.main;

import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.net.URL;  

public class CollectionDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String strUrl = "http://baike.baidu.com/item/%E5%A4%A9%E7%A5%9E";  
        try {  
            // 创建一个url对象来指向 该网站链接 括号里()装载的是该网站链接的路径  
            // 更多可以看看 http://wenku.baidu.com/view/8186caf4f61fb7360b4c6547.html  
            URL url = new URL(strUrl);  
            // InputStreamReader 是一个输入流读取器 用于将读取的字节转换成字符  
            // 更多可以看看 http://blog.sina.com.cn/s/blog_44a05959010004il.html   
            InputStreamReader isr = new InputStreamReader(url.openStream(),  
                    "utf-8"); // 统一使用utf-8 编码模式  
            // 使用 BufferedReader 来读取 InputStreamReader 转换成的字符  
            BufferedReader br = new BufferedReader(isr);  
            // 如果 BufferedReader 读到的内容不为空  
            while (br.readLine() != null) {  
                // 则打印出来 这里打印出来的结果 应该是整个网站的  
                System.out.println(br.readLine());  
            }  
            br.close(); // 读取完成后关闭读取器  
        } catch (IOException e) {  
            // 如果出错 抛出异常  
            e.printStackTrace();  
        }  

	}

}
