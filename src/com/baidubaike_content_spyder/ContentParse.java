package com.baidubaike_content_spyder;

import java.io.IOException;
import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.text.html.HTML;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public class ContentParse {

	
	public void parse_content(Document document,List<String> contents ) {

		
		/*获得summary*/
		Elements summarys = document.select("div.lemma-summary");
		for(Element sumarry:summarys){
			contents.add(sumarry.text());
			System.out.println("summary###"+sumarry.text());
		}
		
		/*获得infobox里面的内容*/
		Elements infoboxName = document.select("dt.name");
		for(Element e:infoboxName){
			/*获得infobox里面的name，去掉因为格式&nbsp所导致到空格*/
			String rawinfoName = e.toString().replace("&nbsp;","");
			/*通过Jsoup.parse()把处理过的字符串转成Ｄocument，再通过Ｄocument.text()得到处理过的内容*/
			Document d = Jsoup.parse(rawinfoName);
			System.out.print("basicInfo###"+d.text());
			/*获得infobox里面的value*/
			Element value = e.nextElementSibling();
			if(null != value){
				contents.add("basicInfo###"+d.text()+"###::"+value.text());
				System.out.println("###::"+value.text());
			}

		}
		
		

		/*获得所有有用的内容*/
		Elements content = document.select("h2.title-text,h3.title-text,div.para");
		for(Element allInfo:content){
//			String rawincontent = allInfo.toString();
//			System.out.print(allInfo.tag().toString());
		}


			


			/*获得正文内容*/
//			Elements content = allInfo.select("h2.title-text,h3.title-text,div.para");
//				for(Element l:content){
//					String rawincontent = l.toString();
//					System.out.print(rawincontent);
//					
//					Document d = Jsoup.parse(rawincontent);
//					System.out.print("basicInfo###"+d.text());
//			
//					Elements h2 = d.select("h2.title-text");
//					Elements h3 = d.select("h3.title-text");
//					Elements para = d.select("h3.div.para");
//					
//					}
//					
//
//				}
			}

		}

	


		
