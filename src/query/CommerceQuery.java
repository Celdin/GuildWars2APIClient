package query;

import lang.Locale;

import org.json.JSONObject;

import client.Client;
import domain.Price;

public class CommerceQuery {
	private static final String URL_COMMERCE = "commerce/";
	private static final String URL_PRICE = URL_COMMERCE + "prices/";

	public static Price getPrice(Integer id) {
		return new Price(new JSONObject(Client.get(Locale.BASE_URL + URL_PRICE
				+ id)));
	}
}
