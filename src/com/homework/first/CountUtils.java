package com.homework.first;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.StyledEditorKit.BoldAction;

public class CountUtils {
	//利用BufferedReader整行读取统计字符数
	public int countChar(String path) {
		File file = new File(path);
		BufferedReader br = null;
		String line;
		int charNum = 0;
		try {
			br = new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null){
				char[] ch = line.toCharArray();
				for(int i=0; i < ch.length; i++) {
					if(!Character.isWhitespace(ch[i])) {
						charNum++;
					}
				}
			}
			System.out.println("the charCount is: " + charNum);
			br.close();
		} catch (Exception e) {
			System.out.println(path + "文件名错误");
		}	
		return charNum;
	}
	/*
	 * 统计英文单词数
	 * 先用BufferedReader整行读取，然后添加到StringBuffer中，
	 * 将非英文字符替换为空格，再根据空格分割
	 */
	public int countWord(String path) {
		BufferedReader br = null;
		String line;
		String[] strings;
		StringBuffer sbf = new StringBuffer();
		int wordNum = 0;
		String reg = "\\s+";
		try {
			br = new BufferedReader(new FileReader(path));
			while((line = br.readLine()) != null){
				sbf.append(line);								
			}
			String s = sbf.toString().replaceAll("[^a-zA-Z]", " ");			
			strings = s.split(reg);
			wordNum = strings.length;
			System.out.println("the wordCount is: " + wordNum);
			br.close();
		} catch (IOException e) {
			System.out.println(path + "文件名错误");
		}			
		return wordNum;
	}
	//统计总行数
	public int countLine(String path) {
		BufferedReader br = null;
		String line;
		int lineNum = 0;
		try {
			br = new BufferedReader(new FileReader(path));
			while((line = br.readLine()) != null){
				lineNum++;				
			}
			System.out.println("the lineCount is: " + lineNum);
			br.close();
		} catch (IOException e) {
			System.out.println(path + "文件名错误");
		}			
		return lineNum;
	}
	//统计注释行、空行、代码行
	public void countDiffLine(String path) {
		int annotationLineNum = 0;
		int codeLineNum = 0;
		int nullLineNum = 0;
		String line;
		BufferedReader br = null;
		// 注释匹配器(匹配单行、多行、文档注释)
        Pattern annotationLinePattern = Pattern.compile("(//)|(/\\*)|(^\\s*\\*)|(^\\s*\\*+/)");    
		try {
			br = new BufferedReader(new FileReader(path));
			while((line = br.readLine()) != null){
				if(annotationLinePattern.matcher(line).find()) {//注释行		
					annotationLineNum++;
				}else if (line.matches("\\s*\\p{Graph}?\\s*")) {//空行
					nullLineNum++;
				}else {					
					codeLineNum++;
				}				
			}
			System.out.println("the nullLineNum is: " + nullLineNum);
			System.out.println("the annotationLineNum is: " + annotationLineNum);
			System.out.println("the codeLineNum is: " + codeLineNum);
			br.close();
		} catch (IOException e) {
			System.out.println(path + "文件名错误");
		}	
	}
	//用ArrayList存储符合条件的文件的绝对路径
	public ArrayList<String> fileList = new ArrayList<String>();
	//递归获取当前目录及其子目录下符合条件的文件，并将绝对路径添加到ArrayList
	public void getRecursionFiles(String dirName,String fileName) {
		File file = new File(dirName);
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			if(files!=null) {
				for(File file1 : files) {
					if(file1.isDirectory()) {					
						getRecursionFiles(dirName+"\\"+file1.getName(),fileName);
					}else {
						Pattern pattern = Pattern.compile(fileName);
						Matcher m = pattern.matcher(file1.getName());
						if(m.matches()) {
							fileList.add(dirName+"\\"+file1.getName());
						}
					}
				}
			}
		}
	}
	//仅获取当前目录下符合条件的文件，并将其绝对路径添加到ArrayList
	public void getFiles(String dirName,String fileName) {
		File file = new File(dirName);
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			if(files != null) {
				for(File file1 : files) {
					if(!file1.isDirectory()) {
						Pattern pattern = Pattern.compile(fileName);
						Matcher m = pattern.matcher(file1.getName());
						if(m.matches()) {
							fileList.add(dirName+"\\"+file1.getName());
						}
					}
				}
			}
			
		}
	}
}