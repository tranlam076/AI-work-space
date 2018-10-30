package gsonTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

@WebServlet("/testGson")
public class testGson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public testGson() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");

		ServletOutputStream out = response.getOutputStream();

		Gson gson;
//--------------------------create object from jsonString----------------------------//
		String jsonStr = "{  'id': 10, 'name': 'Ram', 'address': {'city': 'Varanasi','zip':"
				+ " 221001 },'mobileNums': [ 111111, 222222 ]}";
		gson = new Gson();
		Person person = gson.fromJson(jsonStr, Person.class);
		System.out.println(person.getName());
		System.out.println(person.getAddress().getCity());
		long mobNums[] = person.getMobileNums();
		System.out.println(mobNums[0]);

//--------------------------convert object to json-----------------------------------//
		/**
		 * must created the object following (person, address)
		 */
//		System.out.println(gson.toJson(person));
//		out.println(gson.toJson(person));

//--------------------------convert array to json------------------------------------//
		List<City> cities = new ArrayList<>();
		cities.add(new City(1L, "Bratislava", 432000, person));
		cities.add(new City(2L, "Budapest", 1759000));
		cities.add(new City(3L, "HaNoi", 1370096));

		gson = new GsonBuilder().create();
		JsonArray jarray = gson.toJsonTree(cities).getAsJsonArray();
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("cities", jarray);
//        String output = jsonObject.toString();
//        out.print(output);
		out.println("\n");

//-----------------------------convert json to array-----------------------------------//
		String jarrayStr = "{cities:[{\"id\":1,\"name\":\"Bratislava\",\"population\":432000,\"person\":{\"id\":10,\"name\":\"Ram\",\"address\":{\"city\":\"Varanasi\",\"zip\":\"221001\"},\"mobileNums\":[111111,222222]}},{\"id\":2,\"name\":\"Budapest\",\"population\":1759000},{\"id\":3,\"name\":\"HaNoi\",\"population\":1370096}]}";
//		Type listType = new TypeToken<ArrayList<City>>(){}.getType();
//		City[] citiesTest = gson.fromJson(jarrayStr, City[].class);
		Cities citiesTest = gson.fromJson(jarrayStr, Cities.class);
		System.out.println("checking " + citiesTest.cities.length);
		
		
//-----------------------------adding more json----------------------------------------//
		JsonElement jelement = gson.toJsonTree(person);
		jsonObject.add("person", jelement);
		String output = jsonObject.toString();
		System.out.println(output);
		out.print(output);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
