package query;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import client.Client;
import domain.Item;
import lang.Locale;

public class ItemQuery {
	private static final String URL_ITEM = "items";

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
		String data = Client.get(Locale.BASE_URL + URL_ITEM + "/" + id);
		return new Item(new JSONObject(data));
	}

	public static List<Item> get(List<Integer> ids) {
		String data = Client.get(Locale.BASE_URL + URL_ITEM + "?ids=" + StringUtils.join(ids, ','));
		List<Item> items = new ArrayList<>();
		if (data.charAt(0) == '[') {
			JSONArray array = new JSONArray(data);
			int i = 0;
			Item item = new Item(array.getJSONObject(i));
			for (Integer id : ids) {
				if (item.getId().equals(id)) {
					items.add(item);
					if (i < array.length() - 1) {
						i++;
						item = new Item(array.getJSONObject(i));
					}
				} else {
					items.add(null);
				}
			}
		}
		return items;
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
