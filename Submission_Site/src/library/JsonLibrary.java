package library;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonLibrary {
	Gson gson;

	public JsonLibrary() {
		super();
	}

	public Object jsonToObject(String jsonString, Object obj) {
//		String jsonStr = "{  'id': 10, 'name': 'Ram', 'address': {'city': 'Varanasi','zip':"
//				+ " 221001 },'mobileNums': [ 111111, 222222 ]}";
		gson = new Gson();
//ex:	Person person = gson.fromJson(jsonStr, Person.class);
		return null;
	}

	public String objectToJson(String jsonString, Object obj) {
//ex:	gson.toJson(obj)
		return null;
	}

	public String arrayObjToJson(String jsonString, Object obj) {
//ex:	List<City> cities = new ArrayList<>();
		gson = new GsonBuilder().create();
//ex:	JsonArray jarray = gson.toJsonTree(cities).getAsJsonArray();
//		JsonObject jsonObject = new JsonObject();
//ex:	jsonObject.add("cities", jarray);
		/*
		 * add more element: ex: JsonElement jelement = gson.toJsonTree(person); ex:
		 * jsonObject.add("person", jelement);
		 */
//ex:	jsonObject.toString();
		return null;
	}

	public String jsonToArrayObj(String jsonString, Object obj) {
//ex:	String jarrayStr = "{cities:[{\"id\":1,\"name\":\"Bratislava\",\"population\":432000,\"person\":{\"id\":10,\"name\":\"Ram\",\"address\":{\"city\":\"Varanasi\",\"zip\":\"221001\"},\"mobileNums\":[111111,222222]}},{\"id\":2,\"name\":\"Budapest\",\"population\":1759000},{\"id\":3,\"name\":\"HaNoi\",\"population\":1370096}]}";
//		City[] citiesTest = gson.fromJson(jarrayStr, City[].class);
//or:	Cities citiesTest = gson.fromJson(jarrayStr, Cities.class);
//ex:	System.out.println("checking " + citiesTest.cities.length);
		return null;
	}
}
