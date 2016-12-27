package com.productMonitor.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileOperate {

	/**
	 * 缓冲输入文件
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static String readFile(String filename) throws IOException {
		// Reading input by lines
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String s;
		StringBuilder sb = new StringBuilder();
		while ((s = br.readLine()) != null)
			sb.append(s + "\n");
		br.close();
		return sb.toString();
	}

	public static void saveFile(File file, String content) {
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			// if file doesnt exists, then create it

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	/**
	 * 获取某个文件夹下的所有txt文件
	 * 
	 * @param file
	 * @return
	 */
	public static List<String> getFileList(File file) {

		List<String> result = new ArrayList<String>();

		if (!file.isDirectory()) {
			System.out.println(file.getAbsolutePath());
			result.add(file.getAbsolutePath());
		} else {
			File[] directoryList = file.listFiles(new FileFilter() {
				public boolean accept(File file) {
					if (file.isFile() && file.getName().indexOf("txt") > -1) {
						return true;
					} else {
						return false;
					}
				}
			});
			for (int i = 0; i < directoryList.length; i++) {
				result.add(directoryList[i].getPath());
			}
		}

		return result;
	}

	/**
	 * 获取txt数据文件中的URL
	 * 
	 * @throws Exception
	 */
	public static void getURL() throws Exception {
		String FILE_IN = "E:\\1xy\\zhangqi\\out";
		File f = new File(FILE_IN);
		List<String> list = new ArrayList<String>();
		list = getFileList(f);
		File file = new File("E:\\1xy\\zhangqi\\tb_productURL.txt");
		if (!file.exists()) {
			file.createNewFile();
		}

		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(
				file), "utf-8");
		BufferedWriter writer = new BufferedWriter(osw);
		int lineCount = 1;

		System.out.println(list.size());

		for (String l : list) {
			BufferedReader br = new BufferedReader(new FileReader(new File(l)));
			while (true) {

				String line = br.readLine();
				// 到达文件末尾
				if (line == null) {
					break;
				}
				String[] tmps = line.split("\\s\\|\\|\\s");
				String website;
				if (tmps[5].contains("taobao")) {
					website = "TaoBao";
				} else
					website = "Tmall";
				try {

					writer.write(tmps[5] + "\t" + website + "\t0");
					writer.write("\r\n");
					writer.flush();
					lineCount++;
					continue;

				} catch (IOException e) {
					e.printStackTrace();

				}

			}
		}
		writer.close();
		System.out.println(lineCount);
	}

	/**
	 * 获取txt数据文件中的URL
	 * 
	 * @throws Exception
	 */
	public static void gatherData(String folder, String outFile)
			throws Exception {
		String FILE_IN = folder;
		File f = new File(FILE_IN);
		List<String> list = new ArrayList<String>();
		list = getFileList(f);
		File file = new File(outFile);
		if (!file.exists()) {
			file.createNewFile();
		}

		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(
				file), "utf-8");
		BufferedWriter writer = new BufferedWriter(osw);
		int lineCount = 1;

		System.out.println(list.size());

		for (String l : list) {
			BufferedReader br = new BufferedReader(new FileReader(new File(l)));
			while (true) {

				String line = br.readLine();
				// 到达文件末尾
				if (line == null) {
					break;
				}
				try {
					writer.write(line);
					writer.write("\r\n");
					writer.flush();
					lineCount++;
					continue;

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		writer.close();
		System.out.println(lineCount);
	}

}
