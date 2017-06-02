package com.count.test;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/*
 * author:合肥工业大学 管院学院 钱洋 
 * email：1563178220@qq.com

*/
public class WordSort {
    public static void main(String args[]) throws IOException{
        Map<String,integer=""> wordCount=new HashMap<string, integer="">();
        //需要排序的文本
        BufferedReader buufBufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream("D:\\钱洋个人\\学术中心\\数据处理\\user_content_count.txt"), "utf-8"));
        String line=null;
        while((line=buufBufferedReader.readLine())!=null){
            wordCount.put(line.split(" ")[0], Integer.valueOf(line.split(" ")[1]));
        }
        buufBufferedReader.close();
        System.out.println("当前总字符的数量："+wordCount.size());
        //将出现次数为1的输出
        BufferedWriter Writer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( new File("D:\\钱洋个人\\学术中心\\数据处理\\wordcount_1.txt")),"utf-8"));
        for (Map.Entry<string, integer=""> j : wordCount.entrySet()) { 
            int value = j.getValue();
            if (value==1) {
                Writer.append(j.getKey()+"\r\n");
            }
        }
        Writer.close();
        //将map.entrySet()转换成list  
        List<map.entry<string, integer="">> list = new ArrayList<map.entry<string, integer="">>(wordCount.entrySet());  
        Collections.sort(list, new Comparator<map.entry<string, integer="">>() {  
            //降序排序  
            public int compare(Entry<string, integer=""> o1, Entry<string, integer=""> o2) {  
                //return o1.getValue().compareTo(o2.getValue());  
                return o2.getValue().compareTo(o1.getValue());  
            }  
        });  
        //将排序后的值文本输出
        BufferedWriter Writer1 = new BufferedWriter( new OutputStreamWriter( new FileOutputStream( new File("D:\\钱洋个人\\学术中心\\数据处理\\wordcount_gaopinci.txt")),"utf-8"));
        for (int i = 0; i < list.size()*0.01; i++) {
            Writer1.append(list.get(i).getKey()+"\r\n");
        }
        Writer1.close();

    }
}

