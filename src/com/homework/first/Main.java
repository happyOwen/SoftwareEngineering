package com.homework.first;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	private static boolean IS_COUNTCHAR = false;
	private static boolean IS_COUNTWORD = false;
	private static boolean IS_COUNTLINE = false;
	private static boolean IS_RECURSION = false;
	private static boolean IS_COUNTDIFFLINE = false;
	//利用ArrayList存储符合条件的文件的绝对路径
	private static ArrayList<String> fileList = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException,ArrayIndexOutOfBoundsException {
		//默认最后一个参数为路径名
		String path = args[args.length - 1];

		CountUtils cUtils = new CountUtils();
		//判断需要执行的功能
		for(int i = 0; i < args.length - 1; i++) {
			if(args[i].equals("-c")) {
				IS_COUNTCHAR = true;
			}
			if(args[i].equals("-w")) {
				IS_COUNTWORD = true;
			}
			if(args[i].equals("-l")) {
				IS_COUNTLINE = true;
			}
			if(args[i].equals("-s")) {
				IS_RECURSION = true;
			}
			if(args[i].equals("-a")) {
				IS_COUNTDIFFLINE = true;
			}
		}
		//获取目录名
		String paths[] = path.split("\\\\");
		StringBuilder sb = new StringBuilder();		
		for(int i=0;i<paths.length-1;i++) {
			if(i==paths.length-2) {
				sb.append(paths[i]);
			}else {
				sb.append(paths[i]+"\\");
			}
		}
		
		String dirName = sb.toString();
		File file = new File(dirName);
		if(!file.isDirectory()) {
			System.out.println("路径错误！");
		}
		String fileName =paths[paths.length-1];
		//对文件名通配符处理
		fileName = fileName.replaceAll("\\*", "\\.+").replaceAll("\\?", "\\.");
		
		//若IS_RECURSION，则使用递归获取文件名(包括子目录)，否则只获取当前目录下符合条件的文件名
		if(IS_RECURSION) {
			cUtils.getRecursionFiles(dirName, fileName);
		}else {
			cUtils.getFiles(dirName, fileName);
		}
		fileList = cUtils.fileList;
		
		//遍历fileList，对每一个文件使用选择的功能
		for(String item : fileList) {
			System.out.println("文件路径为："+item);
			if(IS_COUNTCHAR) {
				cUtils.countChar(item);
			}
			if(IS_COUNTWORD) {
				cUtils.countWord(item);
			}
			if(IS_COUNTLINE) {
				cUtils.countLine(item);
			}
			if(IS_COUNTDIFFLINE) {
				cUtils.countDiffLine(item);
			}
		}
	}
	

}
