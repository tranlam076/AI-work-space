package data;

import static business.Constants.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataUtils {

	public static String contentOf(String filePath) throws IOException {
		StringBuilder content = new StringBuilder();
		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF8"));) {
			String line;
			while ((line = in.readLine()) != null) {
				content.append(line);
			}
		}
		return content.toString();
	}

	public static JSONArray getListTable() {
		JSONArray arr = new JSONArray();
		File testDirectory = new File(DATA_TABLE);
		File[] files = testDirectory.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				String name = pathname.getName().toLowerCase();
				return name.endsWith(".json") && pathname.isFile();
			}
		});
		for (File file : files) {
			arr.put(file.getName().split("\\.")[0]);
		}
		return arr;
	}

	public static JSONArray getListField(String tableName) throws IOException {
		JSONArray arr = new JSONArray(contentOf(filePath(tableName)));
		JSONArray fields = new JSONArray();
		Iterator<String> keys = arr.getJSONObject(0).keys();
		while (keys.hasNext()) {
			fields.put(keys.next());
		}
		return fields;
	}

	public static void save(JSONArray arr, String filePath) throws IOException {
		try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filePath),
				StandardCharsets.UTF_8)) {
			writer.write(arr.toString());
		}
	}

	public static boolean match(JSONObject obj, JSONObject condition) {
		for (String key : condition.keySet()) {
			if (!condition.getString(key).equals(obj.getString(key))) {
				return false;
			}
		}
		return true;
	}

	public static JSONArray getList(JSONObject condition, String tableName) throws IOException {
		JSONArray list = new JSONArray();
		JSONArray arr = new JSONArray(contentOf(filePath(tableName)));
		for (int i = 0; i < arr.length(); i++) {
			JSONObject obj = arr.getJSONObject(i);
			if (match(obj, condition)) {
				list.put(obj);
			}
		}
		return list;
	}

	public static JSONObject getOne(JSONObject condition, String tableName) throws IOException {
		ArrayList<JSONObject> list = new ArrayList<>();
		JSONArray arr = new JSONArray(contentOf(filePath(tableName)));
		for (int i = 0; i < arr.length(); i++) {
			JSONObject obj = arr.getJSONObject(i);
			if (match(obj, condition)) {
				return obj;
			}
		}
		return null;
	}

	public static JSONArray getAll(String tableName) throws IOException {
		return new JSONArray(contentOf(filePath(tableName)));
	}

	public static void edit(JSONObject condition, JSONObject newObj, String tableName) throws IOException {
		JSONArray arr = new JSONArray(contentOf(filePath(tableName)));
		for (int i = 0; i < arr.length(); i++) {
			JSONObject obj = arr.getJSONObject(i);
			if (match(obj, condition)) {
				arr.put(i, newObj);
			}
		}
		save(arr, filePath(tableName));
	}

	public static void delete(JSONObject condition, String tableName) throws IOException {
		JSONArray arr = new JSONArray(contentOf(filePath(tableName)));
		for (int i = 0; i < arr.length(); i++) {
			JSONObject obj = arr.getJSONObject(i);
			if (match(obj, condition)) {
				arr.remove(i);
			}
		}
		save(arr, filePath(tableName));
	}

	public static void insert(JSONObject newObj, String tableName) throws IOException {
		JSONArray arr = new JSONArray(contentOf(filePath(tableName)));
		arr.put(newObj);
		save(arr, filePath(tableName));
	}

	public static String filePath(String tableName) {
		return DATA_TABLE + tableName + JSON;
	}

}
