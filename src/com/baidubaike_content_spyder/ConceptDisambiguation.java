package com.baidubaike_content_spyder;

import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ConceptDisambiguation {

	public void parse_a(Document document, String rooturl, List<String> urls, List<String> questionUrls)
			throws IOException {
		/*查找概念简介*/
		Elements summarys = document.select("div.lemma-summary");
		/*如果没有概念简介，则加入有问题的url列表*/
		if(summarys.isEmpty()){
			questionUrls.add(rooturl);
		}else{
			/*如果有概念简介，则判断概念简介是否包含查找要素*/
			Element summry1 = summarys.first();
			String summary1 = summry1.text().toString();
			/*如果概念简介包含查找要素，则加入可用url列表*/
//			if ((summary1.indexOf("羊羊") != -1)|(summary1.indexOf("狼") != -1)) {
//			if (summary1.indexOf("熊") != -1) {
			if ((summary1.indexOf("西游") != -1)|(summary1.indexOf("哪吒") != -1)) {
				urls.add(rooturl);
			} else {
				/*查看概念的多个义项*/
				Elements a = document.select("div.polysemantList-header-title");
				
				/*如果概念没有多个义项，说明概念url有问题*/
				if (a.isEmpty()) {
					questionUrls.add(rooturl);
				} else {
					/*如果概念多个义项中有符合条件的类型，则加入可用url列表*/
					boolean flag1 = false;
					Elements rawlinks = document.select("li.item > a");
					for (Element e : rawlinks) {
						String title = e.attr("title");
						String url = "https://baike.baidu.com" + e.attr("href");
//						if ((title.indexOf("羊") != -1)) {
//						if ((title.indexOf("熊") != -1)) {
						if ((summary1.indexOf("西游") != -1)|(summary1.indexOf("哪吒") != -1)) {						
							urls.add(url);
							flag1 = true;
							break;
						}
					}
					/*如果概念多个义项都没有符合条件的类型，则加入问题url列表*/
					if (!flag1) {
						questionUrls.add(rooturl);
					}
				}

		}
		
		
		}
	}

}













//		System.out.println(document.toString());

/*提取出href属性里面/item以及后面的字符*/
//Elements a = document.select("div.polysemantList-header-title");

/*判断歧义标签是否为空，如果为空则说明是没有歧义项*/
//if(a.isEmpty()){
//	Elements summarys = document.select("div.lemma-summary");
//	Element summry1 = summarys.first();
//	String summary1 = summry1.text().toString();
//	
//	if(summary1.indexOf("羊")!=-1){
//		urls.add(rooturl);
//	}else{
//		questionUrls.add(rooturl);
//	}
//}else {
//	/*有歧义项的情况下判断第一个义项是否准确*/
//	boolean flag = false;
//	Elements briefIntros = document.select("dd.h2");
//	for(Element briefIntr:briefIntros){
//		String briefIntrod = briefIntros.attr("title");
//		if((briefIntrod.indexOf("羊")!=-1)){
//			urls.add(rooturl);
//			flag =true;
//			break;
//		}
//	}
//	if(!flag){
//		boolean flag1 = false;
//		Elements rawlinks = document.select("li.item > a");
//		for(Element e:rawlinks){
//			String title = e.attr("title");
//			String url  = "https://baike.baidu.com"+e.attr("href"); 
//			if((title.indexOf("羊")!=-1)){
//				urls.add(url);
//				flag1 = true;
//				break;
//			}
//		}
//		if(!flag1){	
//				urls.add(rooturl);
//		}
//	}
//
//
//}


