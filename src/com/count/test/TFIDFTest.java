package com.count.test;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class TFIDFTest {

	/**
	 * @param args
	 *            来源于http://blog.csdn.net/qy20115549/article/details/54173243
	 * 
	 * 
	 *            TFIDF的主要思想 程序使用 程序结果
	 * 
	 *            TFIDF的主要思想
	 * 
	 *            TFIDF的主要思想是：如果某个词或短语在一篇文章中出现的频率TF高，并且在其他文章中很少出现，
	 *            则认为此词或者短语具有很好的类别区分能力，适合用来分类。 TFIDF实际上是：TF * IDF，TF词频(Term
	 *            Frequency)，IDF逆向文件频率(Inverse Document
	 *            Frequency)。TF表示词条在文档d中出现的频率。
	 *            IDF的主要思想是：如果包含词条t的文档越少，也就是n越小，IDF越大
	 *            ，则说明词条t具有很好的类别区分能力。如果某一类文档C中包含词条t的文档数为m
	 *            ，而其它类包含t的文档总数为k，显然所有包含t的文档数n
	 *            =m+k，当m大的时候，n也大，按照IDF公式得到的IDF的值会小，就说明该词条t类别区分能力不强。
	 *            但是实际上，如果一个词条在一个类的文档中频繁出现
	 *            ，则说明该词条能够很好代表这个类的文本的特征，这样的词条应该给它们赋予较高的权重
	 *            ，并选来作为该类文本的特征词以区别与其它类文档。这就是IDF的不足之处. 在一份给定的文件里，词频（term
	 *            frequency，TF）指的是某一个给定的词语在该文件中出现的频率。这个数字是对词数(term
	 *            count)的归一化，以防止它偏向长的文件
	 *            。（同一个词语在长文件里可能会比短文件有更高的词数，而不管该词语重要与否。）【来自百度百科】
	 * 
	 *            以下程序可实现TF-IDF。其核心程序来自于这篇博客：http://blog.csdn.net/endless_yy/
	 *            article/details/12745405 程序使用
	 * 
	 *            在此基础上，本人修改了其分词部分，因为我的分词是单独写的。同时，本人自己加了权值的排序，以及输入文件，输出文件。
	 * 
	 *            程序的输入是分词之后文档的目录。
	 */

	private static ArrayList<String> FileList = new ArrayList<String>(); // the
																			// list
																			// of
																			// file

	// 获取文件名
	public static String getFileNameWithSuffix(String pathandname) {
		int start = pathandname.lastIndexOf("\\");
		if (start != -1) {
			return pathandname.substring(start + 1);
		} else {
			return null;
		}
	}

	// get list of file for the directory, including sub-directory of it
	public static List<String> readDirs(String filepath)
			throws FileNotFoundException, IOException {
		try {
			File file = new File(filepath);
			if (!file.isDirectory()) {
				System.out.println("输入的[]");
				System.out.println("filepath:" + file.getAbsolutePath());
			} else {
				String[] flist = file.list();
				for (int i = 0; i < flist.length; i++) {
					File newfile = new File(filepath + "\\" + flist[i]);
					if (!newfile.isDirectory()) {
						FileList.add(newfile.getAbsolutePath());
					} else if (newfile.isDirectory()) // if file is a directory,
														// call ReadDirs
					{
						readDirs(filepath + "\\" + flist[i]);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return FileList;
	}

	// read file
	public static String readFile(String file) throws FileNotFoundException,
			IOException {
		StringBuffer strSb = new StringBuffer(); // String is constant，
													// StringBuffer can be
													// changed.
		InputStreamReader inStrR = new InputStreamReader(new FileInputStream(
				file), "gbk"); // byte streams to character streams
		BufferedReader br = new BufferedReader(inStrR);
		String line = br.readLine();
		while (line != null) {
			strSb.append(line).append("\r\n");
			line = br.readLine();
		}

		return strSb.toString();
	}

	// word segmentation
	public static ArrayList<String> cutWords(String file) throws IOException {
		ArrayList<String> words = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(new File(file)), "utf-8"));
		String s = null;
		while ((s = reader.readLine()) != null) {
			String cutWordResult[] = s.split(" ");
			for (int i = 0; i < cutWordResult.length; i++) {
				words.add(cutWordResult[i]);
			}

		}
		reader.close();
		return words;
	}

	// term frequency in a file, times for each word
	public static HashMap<String, Integer> normalTF(ArrayList<String> cutwords) {
		HashMap<String, Integer> resTF = new HashMap<String, Integer>();

		for (String word : cutwords) {
			if (resTF.get(word) == null) {
				resTF.put(word, 1);
			} else {
				resTF.put(word, resTF.get(word) + 1);
			}
		}
		return resTF;
	}

	// term frequency in a file, frequency of each word
	public static HashMap<String, Float> tf(ArrayList<String> cutwords) {
		HashMap<String, Float> resTF = new HashMap<String, Float>();

		int wordLen = cutwords.size();
		HashMap<String, Integer> intTF = TFIDFTest.normalTF(cutwords);

		Iterator iter = intTF.entrySet().iterator(); // iterator for that get
														// from TF
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			resTF.put(entry.getKey().toString(),
					Float.parseFloat(entry.getValue().toString()) / wordLen);
			// System.out.println(entry.getKey().toString() + " = "+
			// Float.parseFloat(entry.getValue().toString()) / wordLen);
		}
		return resTF;
	}

	// tf times for file
	public static HashMap<String, HashMap<String, Integer>> normalTFAllFiles(
			String dirc) throws IOException {
		HashMap<String, HashMap<String, Integer>> allNormalTF = new HashMap<String, HashMap<String, Integer>>();
		List<String> filelist = TFIDFTest.readDirs(dirc);
		for (String file : filelist) {
			HashMap<String, Integer> dict = new HashMap<String, Integer>();
			ArrayList<String> cutwords = TFIDFTest.cutWords(file); // get cut
																	// word
																	// for one
																	// file
			dict = TFIDFTest.normalTF(cutwords);
			allNormalTF.put(file, dict);
		}
		return allNormalTF;
	}

	// tf for all file
	public static HashMap<String, HashMap<String, Float>> tfAllFiles(String dirc)
			throws IOException {
		HashMap<String, HashMap<String, Float>> allTF = new HashMap<String, HashMap<String, Float>>();
		List<String> filelist = TFIDFTest.readDirs(dirc);

		for (String file : filelist) {
			HashMap<String, Float> dict = new HashMap<String, Float>();
			ArrayList<String> cutwords = TFIDFTest.cutWords(file); // get cut
																	// words
																	// for one
																	// file

			dict = TFIDFTest.tf(cutwords);
			allTF.put(file, dict);
		}
		return allTF;
	}

	public static HashMap<String, Float> idf(
			HashMap<String, HashMap<String, Float>> all_tf) throws IOException {
		HashMap<String, Float> resIdf = new HashMap<String, Float>();
		HashMap<String, Integer> dict = new HashMap<String, Integer>();
		int docNum = FileList.size();

		for (int i = 0; i < docNum; i++) {
			HashMap<String, Float> temp = all_tf.get(FileList.get(i));
			Iterator iter = temp.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String word = entry.getKey().toString();
				if (dict.get(word) == null) {
					dict.put(word, 1);
				} else {
					dict.put(word, dict.get(word) + 1);
				}
			}
		}
		System.out.println("IDF for every word is:");
		Iterator iter_dict = dict.entrySet().iterator();
		while (iter_dict.hasNext()) {
			Map.Entry entry = (Map.Entry) iter_dict.next();
			float value = (float) Math.log(docNum
					/ Float.parseFloat(entry.getValue().toString()));
			resIdf.put(entry.getKey().toString(), value);
			// 这里输入的是key值和value值,每个词对应的idf
			// System.out.println(entry.getKey().toString() + " == " + value);
		}
		return resIdf;
	}

	public static void tf_idf(HashMap<String, HashMap<String, Float>> all_tf,
			HashMap<String, Float> idfs, String putpath) throws IOException {
		HashMap<String, HashMap<String, Float>> resTfIdf = new HashMap<String, HashMap<String, Float>>();

		int docNum = FileList.size();
		for (int i = 0; i < docNum; i++) {
			String filepath = FileList.get(i);
			HashMap<String, Float> tfidf = new HashMap<String, Float>();
			HashMap<String, Float> temp = all_tf.get(filepath);
			Iterator iter = temp.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String word = entry.getKey().toString();
				Float value = (float) Float.parseFloat(entry.getValue()
						.toString()) * idfs.get(word);
				tfidf.put(word, value);
			}
			resTfIdf.put(filepath, tfidf);
		}
		DisTfIdf(resTfIdf, putpath);
	}

	// 排序算法
	public static void Rank(HashMap<String, Float> wordmap, String filename)
			throws IOException {
		BufferedWriter Writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(new File(filename)), "utf-8"));
		List<String> wordgaopindipin = new ArrayList<String>();
		List<Map.Entry<String, Float>> list = new ArrayList<Map.Entry<String, Float>>(
				wordmap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Float>>() {
			// 降序排序
			public int compare(Entry<String, Float> o1, Entry<String, Float> o2) {
				// return o1.getValue().compareTo(o2.getValue());
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		// 排序靠前的60个词及权值
		if (list.size() > 60) {
			for (int i = 0; i < 59; i++) {
				// 写入文件
				wordgaopindipin.add(list.get(i).getKey());
				Writer.append(list.get(i).getKey() + " "
						+ list.get(i).getValue() + "\r\n");
			}
		} else {
			for (int i = 0; i < list.size(); i++) {
				// 写入文件
				System.out.println(i);
				wordgaopindipin.add(list.get(i).getKey());
				Writer.append(list.get(i).getKey() + " "
						+ list.get(i).getValue() + "\r\n");
			}
		}

		Writer.close();
	}

	public static void DisTfIdf(HashMap<String, HashMap<String, Float>> tfidf,
			String outpath) throws IOException {
		Iterator iter1 = tfidf.entrySet().iterator();
		while (iter1.hasNext()) {
			Map.Entry entrys = (Map.Entry) iter1.next();
			System.out.println("FileName: "
					+ getFileNameWithSuffix(entrys.getKey().toString()));
			HashMap<String, Float> temp = (HashMap<String, Float>) entrys
					.getValue();
			// 将排序结果输入到文本
			Rank(temp, outpath
					+ getFileNameWithSuffix(entrys.getKey().toString()));
			// 这里使用排序输出
		}
	}

	public static void main(String[] args) throws IOException {
		// 输入目录及输出目录

		String inputpath = "F:\\QianYang\\Test\\";
		String outpath = "F:\\QianYang\\Test1\\";
		HashMap<String, HashMap<String, Float>> all_tf = tfAllFiles(inputpath);
		System.out.println();
		HashMap<String, Float> idfs = idf(all_tf);
		// System.out.println();
		tf_idf(all_tf, idfs, outpath);
	}
}
