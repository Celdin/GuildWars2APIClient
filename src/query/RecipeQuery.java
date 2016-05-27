package query;

import java.util.ArrayList;
import java.util.List;

import lang.Locale;

import org.json.JSONArray;
import org.json.JSONObject;

import client.Client;
import domain.Recipe;

public class RecipeQuery {
	private static final String URL_RECIPE = "recipes/";
	private static final String URL_RECIPE_SEARCH = URL_RECIPE
			+ "search?output=";

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
		String data = Client.get(Locale.BASE_URL + URL_RECIPE + id);
		return new Recipe(new JSONObject(data));
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
