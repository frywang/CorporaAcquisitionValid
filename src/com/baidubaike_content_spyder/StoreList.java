package com.baidubaike_content_spyder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StoreList {
	
	
	public void store_contents(List<String> contents,File file) throws IOException {
		FileWriter fileWriter = new FileWriter(file);
		for(int i=0;i<contents.size();i++) {
			fileWriter.write(contents.get(i));
			/*换行和分隔*/
			fileWriter.write("\r\n");
			fileWriter.flush();
		}
		
	}
}