package query;

import java.text.ParseException;

import lang.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import client.Client;
import domain.Account;

public class AccountQuery {
	private final static String URL_ACCOUNT = "account/";

	public static Account get() throws JSONException, ParseException {
		String data = Client.get(Locale.BASE_URL + URL_ACCOUNT);
		return new Account(new JSONObject(data));
	}
}
