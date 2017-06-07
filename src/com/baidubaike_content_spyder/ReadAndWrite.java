package com.baidubaike_content_spyder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWrite {

	public  static List<String> readFile(String filepath) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath),"utf-8"));
		List<String> strlist = new ArrayList<String>();
		String s;
		//s = br.readLine();
		while ((s = br.readLine())!=null){
			strlist.add(s);
		}
		br.close();
		return strlist;
	}
	
}
