package query;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import client.Client;
import domain.Recipe;
import lang.Locale;

public class RecipeQuery {
	private static final String URL_RECIPE = "recipes";
	private static final String URL_RECIPE_SEARCH = URL_RECIPE + "/search?output=";

	public static List<Integer> get() {
		String data = Client.get(Locale.BASE_URL + URL_RECIPE);
		JSONArray json = new JSONArray(data);
		List<Integer> recipes = new ArrayList<Integer>();
		for (int i = 0; i < json.length(); i++) {
			recipes.add(json.getInt(i));
		}
		return recipes;
	}

	public static Recipe get(Integer id) {
		String data = Client.get(Locale.BASE_URL + URL_RECIPE + "/" + id);
		return new Recipe(new JSONObject(data));
	}

	public static List<Recipe> get(List<Integer> ids) {
		String data = Client.get(Locale.BASE_URL + URL_RECIPE + "?ids=" + StringUtils.join(ids, ','));
		List<Recipe> recipes = new ArrayList<>();
		if (data.charAt(0) == '[') {
			JSONArray array = new JSONArray(data);
			int i = 0;
			Recipe recipe = new Recipe(array.getJSONObject(i));
			for (Integer id : ids) {
				if (recipe.getId().equals(id)) {
					recipes.add(recipe);
					if (i < array.length() - 1) {
						i++;
						recipe = new Recipe(array.getJSONObject(i));
					}
				} else {
					recipes.add(null);
				}
			}
		}
		return recipes;
	}

	public static List<Integer> searchByItem(Integer id) {
		String data = Client.get(Locale.BASE_URL + URL_RECIPE_SEARCH + id);
		JSONArray json = new JSONArray(data);
		List<Integer> recipes = new ArrayList<Integer>();
		for (int i = 0; i < json.length(); i++) {
			recipes.add(json.getInt(i));
		}
		return recipes;
	}
}
