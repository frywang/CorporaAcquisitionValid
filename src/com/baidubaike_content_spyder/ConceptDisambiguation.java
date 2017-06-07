package com.baidubaike_content_spyder;

import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ConceptDisambiguation {

	public void parse_a(Document document,String rooturl,List<String> urls) throws IOException {


		//		System.out.println(document.toString());

		/*提取出href属性里面/item以及后面的字符*/
		Elements a = document.select("div.polysemantList-header-title");

		/*判断是歧义标签是否为空，如果为空则说明是没有歧义项*/
		if(a.isEmpty()){
			urls.add(rooturl);
		}else {
			boolean flag = false;
			Elements briefIntros = document.select("dd.h2");
			for(Element briefIntr:briefIntros){
				String briefIntrod = briefIntros.attr("title");
				if((briefIntrod.indexOf("喜羊羊")!=-1)){
					urls.add(rooturl);
					flag =true;
					break;
				}
			}
			if(!flag){
				Elements rawlinks = document.select("li.item > a");
				for(Element e:rawlinks){
					String title = e.attr("title");
					String url  = "https://baike.baidu.com"+e.attr("href"); 
					if((title.indexOf("喜羊羊")!=-1)){
						urls.add(url);
						break;
					}else{	
						urls.add(rooturl);
					}
				}
			}


		}


	}

}