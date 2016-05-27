package query;

import java.util.ArrayList;
import java.util.List;

import lang.Locale;

import org.json.JSONArray;
import org.json.JSONObject;

import client.Client;
import domain.Item;

public class ItemQuery {
	private static final String URL_ITEM = "items/";

	public static List<Integer> get() {
		String data = Client.get(Locale.BASE_URL + URL_ITEM);
		JSONArray json = new JSONArray(data);
		List<Integer> items = new ArrayList<Integer>();
		for (int i = 0; i < json.length(); i++) {
			items.add(json.getInt(i));
		}
		return items;
	}

	public static Item get(Integer id) {
		String data = Client.get(Locale.BASE_URL + URL_ITEM + id);
		return new Item(new JSONObject(data));
	}

	public static Integer search(String name) {
		List<Integer> ids = get();
		for (int id = 0; id < ids.size(); id++) {
			if (get(id).getName() != null && get(id).getName().equals(name)) {
				return id;
			}
		}
		return null;
	}
}
