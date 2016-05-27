import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import query.BankQuery;
import query.CharacterQuery;
import query.CommerceQuery;
import query.ItemQuery;
import query.MaterialsQuery;
import query.RecipeQuery;
import domain.Bag;
import domain.Inventory;
import domain.Item;
import domain.Price;
import domain.Recipe;

public class WhatINeed {
	private List<Inventory> items;

	WhatINeed() throws JSONException, ParseException,
			UnsupportedEncodingException {
		items = new ArrayList<Inventory>();
		addAllItems(items, MaterialsQuery.accountGet());
		addAllItems(items, BankQuery.get());
		for (String character : CharacterQuery.get()) {
			if (CharacterQuery.get(character) != null
					&& CharacterQuery.get(character).getBags() != null) {
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
			return ((po > 0) ? String.format("%dpo ", po) : "")
					+ ((pa > 0) ? String.format("%dpa ", pa) : "")
					+ ((pc > 0) ? String.format("%dpc", pc) : "");
		}
		return "0pc";
	}

	private void addItems(List<Inventory> items, Inventory item) {
		if (items.contains(item)) {
			items.get(items.indexOf(item))
					.setCount(
							items.get(items.indexOf(item)).getCount()
									+ item.getCount());
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
		Integer somme = 0;
		System.out.println(ItemQuery.get(item.getId()).getName() + " :");
		List<Inventory> whatINeed = new ArrayList<Inventory>();
		runner(whatIWant, new ArrayList<Inventory>(items), whatINeed,
				new ArrayList<Recipe>());
		for (Inventory inventory : whatINeed) {
			Price price = CommerceQuery.getPrice(inventory.getId());
			if (price.getSells() != null) {
				somme += price.getSells().getUnitPrice() * inventory.getCount();
				System.out.println("	"
						+ ItemQuery.get(inventory.getId()).getName()
						+ " : "
						+ inventory.getCount()
						+ " x ("
						+ printPrice(price.getSells().getUnitPrice())
						+ ") = "
						+ printPrice(price.getSells().getUnitPrice()
								* inventory.getCount()));
			} else {
				System.out.println("	"
						+ ItemQuery.get(inventory.getId()).getName() + " : "
						+ inventory.getCount());
			}
		}
		System.out.println("Total : " + printPrice(somme));
		Integer benefV = estimBenefV(item, somme);
		if (benefV != null) {
			System.out.println("Benefice (Vente): " + printPrice(benefV));
		}
		Integer benefVR = estimBenefVR(item, somme);
		if (benefVR != null) {
			System.out.println("Benefice (Vente Rapide): "
					+ printPrice(benefVR));
		}
	}

	private Integer runner(Inventory item, List<Inventory> whatIHave,
			List<Inventory> whatINeed, List<Recipe> recipes) {
		Integer price = null;
		Price getPrice = CommerceQuery.getPrice(item.getId());
		if (getPrice != null && getPrice.getSells() != null) {
			price = CommerceQuery.getPrice(item.getId()).getSells()
					.getUnitPrice()
					* item.getCount();
		}
		if (whatIHave.contains(item)
				&& whatIHave.get(whatIHave.indexOf(item)).getCount() > 0) {
			int count = whatIHave.get(whatIHave.indexOf(item)).getCount()
					- item.getCount();
			if (count >= 0) {
				whatIHave.get(whatIHave.indexOf(item)).setCount(count);
			} else {
				whatIHave.get(whatIHave.indexOf(item)).setCount(0);
				item.setCount(-count);
				price = runner(item, new ArrayList<Inventory>(whatIHave),
						whatINeed, new ArrayList<Recipe>(recipes));
			}
		} else {
			List<Integer> recipesIds = RecipeQuery.searchByItem(item.getId());
			for (Integer id : new ArrayList<Integer>(recipesIds)) {
				if (recipes.contains(RecipeQuery.get(id))) {
					recipesIds.remove(id);
				}
			}
			if (!recipesIds.isEmpty()) {
				Recipe recipe = RecipeQuery.get(recipesIds.get(0));
				List<Inventory> wathINeedTemp = new ArrayList<Inventory>();
				Integer priceTemp = 0;
				for (Inventory ingredient : recipe.getIngredients()) {
					ingredient.setCount((int) (Math.round((new Double(item
							.getCount()) / new Double(recipe
							.getOutputItemCount())) + 0.4) * ingredient
							.getCount()));
					Integer p = runner(ingredient, new ArrayList<Inventory>(
							whatIHave), wathINeedTemp, new ArrayList<Recipe>(
							recipes));
					if (p != null) {
						priceTemp += p;
					}
				}
				if (price != null && price < priceTemp) {
					addItems(whatINeed, item);
					return price;
				} else {
					addAllItems(whatINeed, wathINeedTemp);
					recipes.add(recipe);
					return priceTemp;
				}
			} else {
				addItems(whatINeed, item);
			}
		}
		return price;
	}
}
