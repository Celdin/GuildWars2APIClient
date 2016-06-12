package query;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import client.Client;
import domain.Character;
import lang.Locale;

public class CharacterQuery {
	private final static String URL_CHARACTER = "characters";

	public static List<String> get() {
		String data = Client.get(Locale.BASE_URL + URL_CHARACTER);
		JSONArray json = new JSONArray(data);
		List<String> characters = new ArrayList<String>();
		for (int i = 0; i < json.length(); i++) {
			characters.add(json.getString(i));
		}
		return characters;
	}

	public static Character get(String id) throws JSONException, ParseException, UnsupportedEncodingException {
		String url_id = URLEncoder.encode(id, "UTF-8");
		if (url_id.contains("+")) {
			url_id = StringUtils.join(URLEncoder.encode(id, "UTF-8").split("\\+"), "%20");
		}
		String data = Client.get(Locale.BASE_URL + URL_CHARACTER + "/" + url_id);
		return new Character(new JSONObject(data));
	}
}
