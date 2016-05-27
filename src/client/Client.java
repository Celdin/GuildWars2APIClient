package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Client {
	public static String get(String URL) {
		try {
			URL url = new URL(URL);
			System.getProperties().put("proxySet", "true");
			System.getProperties().put("https.proxyHost",
					"fr-proxy.groupinfra.com");
			System.getProperties().put("https.proxyPort", "3128");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Accept-Language", "fr");
			conn.setRequestProperty(
					"Authorization",
					"Bearer "
							+ "D8883947-9C76-F743-AD83-155421CA4A196F234D25-2183-4E4F-B585-AA5D4249A406");

			int code = conn.getResponseCode();
			if (code != 200) {
				// System.out.println("Failed : HTTP error code : "
				// + conn.getResponseCode());
				// System.out.println("	at :" + URL);
				return "{}";
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			String concat = "";
			while ((output = br.readLine()) != null)
				concat += output;
			conn.disconnect();
			return concat;

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return "{}";
	}

}
