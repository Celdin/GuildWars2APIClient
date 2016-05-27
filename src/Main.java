import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import query.AccountQuery;
import query.AchievementQuery;
import query.CharacterQuery;
import domain.Daily;

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
		// if (args.length > 0) {
		// try {
		// Integer id = Integer.parseInt(args[0]);
		// WhatINeed need = new WhatINeed();
		// Item item = new Item();
		// item.setId(id);
		// need.run(item);
		// } catch (NumberFormatException e) {
		// System.out.println("Usage : java -jar ");
		// }
		// } else {
		// System.out.println("Usage : java -jar ");
		// }
		Map<Integer, Map<String, List<Daily>>> levels = new HashMap<Integer, Map<String, List<Daily>>>();
		for (String nom : CharacterQuery.get()) {
			Integer level = CharacterQuery.get(nom).getLevel();
			if (!levels.containsKey(level)) {
				Map<String, List<Daily>> dailys = AchievementQuery
						.getDalys(true);
				for (String type : dailys.keySet()) {
					for (Daily daily : new ArrayList<Daily>(dailys.get(type))) {
						if (daily.getLevel().getMax() < level
								|| daily.getLevel().getMin() > level
								|| !daily.getRequiredAccess().contains(
										AccountQuery.get().getAccess())) {
							dailys.get(type).remove(daily);
						}
					}
				}
				levels.put(level, dailys);
			}
		}
		System.out.println("pve :");
		for (Object o : levels.keySet()) {
			Integer lvl = (Integer) o;
			System.out.println("	lvl " + lvl + " :");
			for (Daily daily : levels.get(lvl).get("pve")) {
				System.out.println("		"
						+ AchievementQuery.get(daily.getId()).getName());
			}
		}
		Map<String, List<Daily>> map = levels.get(levels.keySet().toArray()[0]);
		map.remove("pve");
		for (Object o : map.keySet()) {
			String type = (String) o;
			if (!type.equals("special") || !map.get(type).isEmpty()) {
				System.out.println(type + " :");
				for (Daily daily : map.get(type)) {
					System.out.println("	"
							+ AchievementQuery.get(daily.getId()).getName());
				}
			}
		}
	}
}
