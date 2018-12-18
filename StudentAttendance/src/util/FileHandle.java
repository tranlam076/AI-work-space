package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import model.Mark;

public class FileHandle {

	private static FileHandle instance;

	private FileHandle() {

	}

	public static FileHandle getInstance() {
		if (instance == null) {
			instance = new FileHandle();
		}
		return instance;
	}

	public void writeFile(String filePath, String content) {
		try {
			FileWriter fileWriter = new FileWriter(filePath);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(content);
			bufferedWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String readFile(String filePath) {
		StringBuffer text = new StringBuffer();
		try {
			FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				text.append(line + "\n");
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text.toString();
	}
	
	public List<Mark> getMarksData(String dataPath) {
		Gson gson = new Gson();
		String content = readFile(dataPath);
		Mark[] marks = gson.fromJson(content, Mark[].class);
		return Arrays.asList(marks);
	}
}
