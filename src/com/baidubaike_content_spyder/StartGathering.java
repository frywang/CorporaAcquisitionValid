package com.baidubaike_content_spyder;

import java.io.File;
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

import com.baidubaike_allhtml_spyder.URLManager;

public class StartGathering {

	public static void main(String[] args) throws IOException {
		
		ConceptDisambiguation disamb = new ConceptDisambiguation();
		ContentParse parseHtml = new ContentParse();
		ConnectNet connectNet = new ConnectNet();
		StoreList store = new StoreList();
		
//		String filepath = "喜羊羊与灰太狼人物列表.txt";
//		String filepath = "熊出没人物列表.txt";
		String filepath = "西游记人物列表.txt";
		
		/*所有的概念列表*/
		List<String> rooturls = ReadAndWrite.readFile(filepath);
		
		/*没有问题的概念url*/
		List<String> urls = new ArrayList<>();
		/*有问题的概念url*/
		List<String> questionUrls = new ArrayList<>();
		/*有用的内容组*/
		List<String> contents = new ArrayList<>();
		/*拿到概念所有可能指向的页面 */
		
		/*所有概念能生成的页面 */
		for(String concept:rooturls){
			String rooturl = "http://baike.baidu.com/item/"+concept;
			Document rootdocument = Jsoup.connect(rooturl).get();
//			System.out.println(rooturl);


			/*错误的生成页面网址*/
			File wrongUrls = new File("wrongUrl.txt");
			/*正确的生成页面网址*/
			File rightUrls = new File("rightUrl.txt");
			
			/*分别保存正确的生成页面和错误的生成页面*/
			disamb.parse_a(rootdocument,rooturl,urls,questionUrls);
			store.store_contents(questionUrls,wrongUrls);
			store.store_contents(urls,rightUrls);


			
			

			

//			while(concepturl!=null) {
//				Document document = connectNet.getDom(concepturl);
//				parseHtml.parse_content(document, contents);
//
//				store.store_contents(contents);
//				System.out.println("StartSpyder.main()");
//			}
		}
		
		/*
		 * 首先判断url集合里面是否还有可以爬取的url，然后从中按照顺序获取一个URL
		 * 然后URL管理器继续进行添加新的url，从提取出的URL获取dom对象，进行解析，存入txt文本，
		 * 循环进行，直到没有新的url可以爬取。
		 */
		/*要写入的文档*/
		File file = new File("西游记infobox.txt");
		for(String url:urls){
			Document document = connectNet.getDom(url);
			parseHtml.parse_content(document, contents);
			store.store_contents(contents,file);
		}


	}
	
	

}