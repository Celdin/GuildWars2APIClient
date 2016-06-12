import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import org.json.JSONException;

import domain.Item;

public class Main {

	public static void main(String[] args) throws JSONException, ParseException, UnsupportedEncodingException {
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
		// Map<Integer, Map<String, List<Daily>>> levels = new HashMap<Integer,
		// Map<String, List<Daily>>>();
		// for (String nom : CharacterQuery.get()) {
		// Integer level = CharacterQuery.get(nom).getLevel();
		// if (!levels.containsKey(level) && level != null) {
		// Map<String, List<Daily>> dailys = AchievementQuery.getDalys(true);
		// for (String type : dailys.keySet()) {
		// for (Daily daily : new ArrayList<Daily>(dailys.get(type))) {
		// if (daily.getLevel().getMax() < level || daily.getLevel().getMin() >
		// level
		// ||
		// !daily.getRequiredAccess().contains(AccountQuery.get().getAccess()))
		// {
		// dailys.get(type).remove(daily);
		// }
		// }
		// }
		// levels.put(level, dailys);
		// }
		// }
		// System.out.println("pve :");
		// for (Object o : levels.keySet()) {
		// Integer lvl = (Integer) o;
		// System.out.println(" lvl " + lvl + " :");
		// for (Daily daily : levels.get(lvl).get("pve")) {
		// System.out.println(" " +
		// AchievementQuery.get(daily.getId()).getName());
		// }
		// }
		// Map<String, List<Daily>> map =
		// levels.get(levels.keySet().toArray()[0]);
		// map.remove("pve");
		// for (Object o : map.keySet()) {
		// String type = (String) o;
		// if (!type.equals("special") || !map.get(type).isEmpty()) {
		// System.out.println(type + " :");
		// for (Daily daily : map.get(type)) {
		// System.out.println(" " +
		// AchievementQuery.get(daily.getId()).getName());
		// }
		// }
		// }
	}
}
