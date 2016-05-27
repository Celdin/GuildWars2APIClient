import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import org.json.JSONException;

import domain.Item;

public class Main {

	public static void main(String[] args) throws JSONException,
			ParseException, UnsupportedEncodingException {
		// Map<String, List<Daily>> dailys = AchievementQuery.getDalys(true);
		// for (String type : dailys.keySet()) {
		// System.out.println(type + " :");
		// for (Daily daily : dailys.get(type)) {
		// if (daily.getLevel().getMax() >= 80
		// && daily.getRequiredAccess().contains(
		// AccountQuery.get().getAccess())) {
		// System.out.println("	"
		// + AchievementQuery.get(daily.getId()).getName());
		// }
		// }
		// }
		// System.out.println(AccountQuery.get());
		// System.out.println(CharacterQuery.get("C%C3%A9ledine"));
		// System.out.println(ItemQuery.search("Bifrost"));
		if (args.length > 0) {
			try {
				Integer id = Integer.parseInt(args[0]);
				WhatINeed need = new WhatINeed();
				Item item = new Item();
				item.setId(id);
				need.run(item);
			} catch (NumberFormatException e) {
				System.out.println("Usage : java -jar ");
			}
		} else {
			System.out.println("Usage : java -jar ");
		}
	}
}
