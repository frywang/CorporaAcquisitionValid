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
            // ����һ��url������ָ�� ����վ���� ������()װ�ص��Ǹ���վ���ӵ�·��  
            // ������Կ��� http://wenku.baidu.com/view/8186caf4f61fb7360b4c6547.html  
            URL url = new URL(strUrl);  
            // InputStreamReader ��һ����������ȡ�� ���ڽ���ȡ���ֽ�ת�����ַ�  
            // ������Կ��� http://blog.sina.com.cn/s/blog_44a05959010004il.html   
            InputStreamReader isr = new InputStreamReader(url.openStream(),  
                    "utf-8"); // ͳһʹ��utf-8 ����ģʽ  
            // ʹ�� BufferedReader ����ȡ InputStreamReader ת���ɵ��ַ�  
            BufferedReader br = new BufferedReader(isr);  
            // ��� BufferedReader ���������ݲ�Ϊ��  
            while (br.readLine() != null) {  
                // ���ӡ���� �����ӡ�����Ľ�� Ӧ����������վ��  
                System.out.println(br.readLine());  
            }  
            br.close(); // ��ȡ��ɺ�رն�ȡ��  
        } catch (IOException e) {  
            // ������� �׳��쳣  
            e.printStackTrace();  
        }  

	}

}
