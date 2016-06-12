import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import domain.Bag;
import domain.Inventory;
import domain.Item;
import domain.Price;
import domain.Recipe;
import query.BankQuery;
import query.CharacterQuery;
import query.CommerceQuery;
import query.ItemQuery;
import query.MaterialsQuery;
import query.RecipeQuery;

public class WhatINeed {
	private List<Inventory> items;
	private Map<Integer, Integer> prices;
	private Map<Integer, Recipe> recipes;
	private Map<Integer, Integer> translate;

	WhatINeed() throws JSONException, ParseException, UnsupportedEncodingException {
		items = new ArrayList<Inventory>();
		prices = new HashMap<Integer, Integer>();
		prices.put(19750, 16); // Morceau de charbon
		prices.put(19924, 48); // Morceau de primordium
		prices.put(46747, 150); // Réactif thermocatalytique
		prices.put(12156, 8); // Cruche d'eau
		// biffrost
		prices.put(74378, 11432); // Essence d'arc-en-ciel
		prices.put(77139, 11432); // Expertise en fabrication de bâtons
		prices.put(77190, 0); // Essence de mysticisme ancien
		prices.put(74909, 0); // Outil de sculpteur

		recipes = new HashMap<Integer, Recipe>();
		translate = new HashMap<>();
		// biffrost
		translate.put(76891, 76027); // Esprit du bâton perfectionné -> Bâton
										// perfectionné
		translate.put(75535, 73431); // Esprit du prototype de la Légende ->
										// Prototype de la Légende
		// ad infinitum
		translate.put(70642, 73251); // Esprit de la Limite supérieure -> Limite
										// supérieure
		translate.put(75066, 73624); // Esprit de Limite finie -> Limite finie

		addAllItems(items, MaterialsQuery.accountGet());
		addAllItems(items, BankQuery.get());
		for (String character : CharacterQuery.get()) {
			if (CharacterQuery.get(character) != null && CharacterQuery.get(character).getBags() != null) {
				for (Bag bag : CharacterQuery.get(character).getBags()) {
					addAllItems(items, bag.getInventories());
				}
			}
		}
	}

	private Integer estimBenefV(Item item, int price) {
		Price cQP = CommerceQuery.getPrice(item.getId());
		if (cQP.getBuys() != null && cQP.getSells().getUnitPrice() != null) {
			Integer p = cQP.getSells().getUnitPrice();
			int tax5 = p * 5 / 100;
			int tax10 = p * 10 / 100;
			p = p - tax5 - tax10;
			return p - price;
		}
		return null;
	}

	private Integer estimBenefVR(Item item, int price) {
		Price cQP = CommerceQuery.getPrice(item.getId());
		if (cQP.getBuys() != null && cQP.getBuys().getUnitPrice() != null) {
			Integer p = cQP.getBuys().getUnitPrice();
			int tax5 = p * 5 / 100;
			int tax10 = p * 10 / 100;
			p = p - tax5 - tax10;
			return p - price;
		}
		return null;
	}

	private String printPrice(int price) {
		if (price > 0) {
			int pc = price % 100;
			int pa = (price / 100) % 100;
			int po = price / 10000;
			return ((po > 0) ? String.format("%dpo ", po) : "") + ((pa > 0) ? String.format("%dpa ", pa) : "")
					+ ((pc > 0) ? String.format("%dpc", pc) : "");
		}
		return "0pc";
	}

	private void addItems(List<Inventory> items, Inventory item) {
		if (items.contains(item)) {
			items.get(items.indexOf(item)).setCount(items.get(items.indexOf(item)).getCount() + item.getCount());
		} else {
			items.add(item);
		}
	}

	private void addAllItems(List<Inventory> target, List<Inventory> source) {
		for (Inventory item : source) {
			addItems(target, item);
		}
	}

	public void run(Item item) {
		Inventory whatIWant = new Inventory();
		whatIWant.setId(item.getId());
		whatIWant.setCount(1);
		if (items.contains(whatIWant)) {
			items.get(items.indexOf(whatIWant)).setCount(0);
		}
		Integer somme = 0;
		System.out.println(ItemQuery.get(item.getId()).getName() + " :");
		List<Inventory> whatINeed = new ArrayList<Inventory>();
		runner(whatIWant, new ArrayList<Inventory>(items), whatINeed, new ArrayList<Recipe>());

		List<Integer> ids = new ArrayList<Integer>();
		for (Inventory inventory : whatINeed) {
			ids.add(inventory.getId());
		}
		List<Item> items = ItemQuery.get(ids);
		List<Price> prices = CommerceQuery.getPrice(ids);

		for (int i = 0; i < ids.size(); i++) {

			Integer prix = null;
			if (this.prices.containsKey(ids.get(i))) {
				prix = this.prices.get(ids.get(i));
			} else if (!prices.isEmpty() && prices.get(i) != null && prices.get(i).getSells() != null) {
				prix = prices.get(i).getSells().getUnitPrice();
			}
			if (prix != null) {
				somme += prix * whatINeed.get(i).getCount();
				System.out.println("	" + items.get(i).getName() + " : " + whatINeed.get(i).getCount() + " x ("
						+ printPrice(prix) + ") = " + printPrice(prix * whatINeed.get(i).getCount()));
			} else {
				System.out.println("	" + items.get(i).getName() + " : " + whatINeed.get(i).getCount());
			}
		}
		System.out.println("Total : " +

		printPrice(somme));
		Integer benefV = estimBenefV(item, somme);
		if (benefV != null) {
			System.out.println("Benefice (Vente): " + printPrice(benefV));
		}
		Integer benefVR = estimBenefVR(item, somme);
		if (benefVR != null) {
			System.out.println("Benefice (Vente Rapide): " + printPrice(benefVR));
		}
	}

	private Integer runner(Inventory item, List<Inventory> whatIHave, List<Inventory> whatINeed,
			List<Recipe> recipesUsed) {
		if (translate.containsKey(item.getId())) {
			item.setId(translate.get(item.getId()));
		}
		Integer price = null;
		Price getPrice;
		if (prices.containsKey(item.getId())) {
			price = prices.get(item.getId());
		} else {
			getPrice = CommerceQuery.getPrice(item.getId());
			if (getPrice != null && getPrice.getSells() != null) {
				price = getPrice.getSells().getUnitPrice() * item.getCount();
				prices.put(item.getId(), getPrice.getSells().getUnitPrice());
			} else {
				prices.put(item.getId(), null);
			}
		}
		if (whatIHave.contains(item) && whatIHave.get(whatIHave.indexOf(item)).getCount() > 0) {
			int count = whatIHave.get(whatIHave.indexOf(item)).getCount() - item.getCount();
			if (count >= 0) {
				whatIHave.get(whatIHave.indexOf(item)).setCount(count);
				return 0;
			} else {
				whatIHave.get(whatIHave.indexOf(item)).setCount(0);
				item.setCount(-count);
				price = runner(item, new ArrayList<Inventory>(whatIHave), whatINeed,
						new ArrayList<Recipe>(recipesUsed));
			}
		} else if (whatINeed.contains(item)) {
			addItems(whatINeed, item);
			return price;
		} else {
			Recipe recipe = null;
			if (recipes.containsKey(item.getId())) {
				recipe = recipes.get(item.getId());
			} else {
				List<Recipe> r = RecipeQuery.get(RecipeQuery.searchByItem(item.getId()));
				r.removeAll(recipesUsed);
				if (!r.isEmpty()) {
					recipe = r.get(0);
					recipes.put(item.getId(), recipe);
				} else {
					recipes.put(item.getId(), null);
				}
			}
			if (recipe != null) {
				List<Inventory> wathINeedTemp = new ArrayList<Inventory>();
				Integer priceTemp = 0;
				for (Inventory ingredient : recipe.getIngredients()) {
					ingredient = ingredient.clone();
					ingredient
							.setCount(
									(int) (Math
											.round((new Double(item.getCount())
													/ new Double(recipe.getOutputItemCount())) + 0.4)
											* ingredient.getCount()));
					Integer p = runner(ingredient, new ArrayList<Inventory>(whatIHave), wathINeedTemp,
							new ArrayList<Recipe>(recipesUsed));
					if (p != null) {
						priceTemp += p;
					}
				}
				if (price != null && price < priceTemp) {
					addItems(whatINeed, item);
					return price;
				} else {
					addAllItems(whatINeed, wathINeedTemp);
					recipesUsed.add(recipe);
					return priceTemp;
				}
			} else {
				addItems(whatINeed, item);
				return price;
			}
		}
		return price;
	}
}
