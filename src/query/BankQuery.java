package query;

import java.util.ArrayList;
import java.util.List;

import lang.Locale;

import org.json.JSONArray;
import org.json.JSONObject;

import client.Client;
import domain.Inventory;

public class BankQuery {
	private static final String URL_BANK = "account/bank/";

	public static List<Inventory> get() {
		List<Inventory> bank = new ArrayList<Inventory>();
		String data = Client.get(Locale.BASE_URL + URL_BANK);
		JSONArray json = new JSONArray(data);
		for (int i = 0; i < json.length(); i++) {
			if (json.get(i) instanceof JSONObject) {
				bank.add(new Inventory(json.getJSONObject(i)));
			}
		}
		return bank;
	}
}
