package com.baidubaike_allhtml_spyder;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ConnectNet {
	
	public static Document getDom(String url) throws IOException {
		try {
			Document document = Jsoup.connect(url).get();
			return document;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	

}