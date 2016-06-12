package query;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import client.Client;
import domain.Price;
import lang.Locale;

public class CommerceQuery {
	private static final String URL_COMMERCE = "commerce/";
	private static final String URL_PRICE = URL_COMMERCE + "prices";

	public static Price getPrice(Integer id) {
		return new Price(new JSONObject(Client.get(Locale.BASE_URL + URL_PRICE + "/" + id)));
	}

	public static List<Price> getPrice(List<Integer> ids) {
		List<Price> prices = new ArrayList<>();
		String data = Client.get(Locale.BASE_URL + URL_PRICE + "?ids=" + StringUtils.join(ids, ','));
		if (data.charAt(0) == '[') {
			JSONArray array = new JSONArray(data);
			int i = 0;
			Price price = new Price(array.getJSONObject(i));
			for (Integer id : ids) {
				if (price.getId().equals(id)) {
					prices.add(price);
					if (i < array.length() - 1) {
						i++;
						price = new Price(array.getJSONObject(i));
					}
				} else {
					prices.add(null);
				}
			}
		}
		return prices;
	}
}
