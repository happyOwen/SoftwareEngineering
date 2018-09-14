package test;

import java.io.IOException;

import org.junit.Test;

import com.homework.first.CountUtils;
import com.homework.first.Main;

public class CountTest {

	CountUtils cUtils = new CountUtils();
	Main m = new Main();
	@Test
	public void testMainReCursion() throws IOException {
		Main.main(new String[]{"-s","-c","-w","-l","-a","D:\\workspace\\*.txt"});
	}
	@Test
	public void testMain() throws IOException{
		Main.main(new String[] {"-c","-w","-l","-a","D:\\workspace\\*.txt"});
	}
	@Test
	public void testMainException() throws IOException {
		Main.main(new String[]{"-c","-w","-l","-a","D:\\workspac\\*.txt"});
	}
	@Test
	public void testCountChar() {
		cUtils.countChar("D://Test.java");	
	}
	@Test
	public void testCountCharException() {
		cUtils.countChar("E://Test.java");	
	}

	@Test
	public void testCountWord() {
		cUtils.countWord("D://Test.java");
	}
	@Test
	public void testCountWordException() {
		cUtils.countWord("E://Test.java");
	}

	@Test
	public void testCountLine() {
		cUtils.countLine("D://Test.java");
	}
	@Test
	public void testCountLineException() {
		cUtils.countLine("E://Test.java");
	}
	@Test
	public void testCountDiffLine() {
		cUtils.countDiffLine("D://Test.java");
	}
	@Test
	public void testCountDiffLineException() {
		cUtils.countDiffLine("E://Test.java");
	}
	@Test
	public void testGetRecursionFiles() {
		String path = "E:\\";
		String fileName = "*.txt";
		fileName = fileName.replaceAll("\\*", "\\.+").replaceAll("\\?", "\\.");
		cUtils.getRecursionFiles(path, fileName);
	}
	@Test
	public void testGetFiles() {
		String path = "D:\\recurse";
		String fileName = "*.txt";
		fileName = fileName.replaceAll("\\*", "\\.+").replaceAll("\\?", "\\.");
		cUtils.getFiles(path, fileName);
		for(String list:cUtils.fileList) {
			System.out.println(list);
		}
	}
}
