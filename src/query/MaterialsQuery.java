package query;

import java.util.ArrayList;
import java.util.List;

import lang.Locale;

import org.json.JSONArray;
import org.json.JSONObject;

import client.Client;
import domain.Inventory;

public class MaterialsQuery {
	private static final String URL_MATERIAL = "account/materials/";

	public static List<Inventory> accountGet() {
		List<Inventory> materials = new ArrayList<Inventory>();
		String data = Client.get(Locale.BASE_URL + URL_MATERIAL);
		JSONArray json = new JSONArray(data);
		for (int i = 0; i < json.length(); i++) {
			if (json.getJSONObject(i) instanceof JSONObject) {
				materials.add(new Inventory(json.getJSONObject(i)));
			}
		}
		return materials;
	}
}
