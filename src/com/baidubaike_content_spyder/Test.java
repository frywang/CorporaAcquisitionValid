package com.baidubaike_content_spyder;

import java.io.IOException;
import java.net.URLStreamHandler;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.baidubaike_allhtml_spyder.ConnectNet;
import com.baidubaike_allhtml_spyder.Store;
import com.baidubaike_allhtml_spyder.URLManager;

public class Test {

	public static void main(String[] args) throws IOException {
		
		ConceptDisambiguation disamb = new ConceptDisambiguation();
		ContentParse parseHtml = new ContentParse();
		ConnectNet connectNet = new ConnectNet();
		Store store = new Store();
		
		String filepath = "喜羊羊与灰太狼人物列表.txt";
		List<String> rooturls = ReadAndWrite.readFile(filepath);
		
		for(String concept:rooturls){
			String rooturl = "http://baike.baidu.com/item/"+concept;
			Document rootdocument = Jsoup.connect(rooturl).get();
//			System.out.println(rooturl);

			/*放置要迭代的url*/
			List<String> urls = new ArrayList<>();
			/*放置简介字符串*/
			List<String> contents = new ArrayList<>();
			/*拿到概念所有可能指向的页面 */

			
			disamb.parse_a(rootdocument,rooturl,urls);
			/*
			 * 首先判断url集合里面是否还有可以爬取的url，然后从中按照顺序获取一个URL
			 * 然后URL管理器继续进行添加新的url，从提取出的URL获取dom对象，进行解析，存入txt文本，
			 * 循环进行，直到没有新的url可以爬取。
			 */
			for(String url:urls){
				System.out.println("网址为"+url);
			}

//			while(concepturl!=null) {
//				Document document = connectNet.getDom(concepturl);
//				parseHtml.parse_content(document, contents);
//
//				store.store_contents(contents);
//				System.out.println("StartSpyder.main()");
//			}
		}
		




	}

}