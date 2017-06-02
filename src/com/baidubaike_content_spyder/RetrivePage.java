package com.baidubaike_content_spyder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

public class RetrivePage {
	private static HttpClient httpClient = new HttpClient();
	static GetMethod getmethod;

	public static boolean downloadPage(String path) throws HttpException, IOException {
		getmethod = new GetMethod(path);
		// 获得响应状态码
		int statusCode = httpClient.executeMethod(getmethod);
		if (statusCode == HttpStatus.SC_OK) {
			System.out.println("response=" + getmethod.getResponseBodyAsString());
			// 写入本地文件
			FileWriter fwrite = new FileWriter("hello.txt");
			String pageString = getmethod.getResponseBodyAsString();
			getmethod.releaseConnection();
			fwrite.write(pageString, 0, pageString.length());
			fwrite.flush();
			// 关闭文件
			fwrite.close();
			// 释放资源
			return true;
		}
		return false;
	}

	/**
	 * 测试代码
	 */
	public static void main(String[] args) {
		System.out.println("ok");
		// 抓取制指定网页，并将其输出
		try {
			Scanner in = new Scanner(System.in);
			System.out.println("Input the URL of the page you want to get:");
			String path = in.next();
			System.out.println("program start!");
			RetrivePage.downloadPage(path);
			System.out.println("Program end!");
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
