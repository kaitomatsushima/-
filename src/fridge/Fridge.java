package fridge;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Fridge {
	private List<FoodItem> foodItems;
	private int nextId;

	public Fridge() {
		foodItems = new ArrayList<>();
		nextId = 1;
	}

	public void addFood(String name, Category category, String customCategoryName, LocalDate expireDate) {
		FoodItem foodItem = new FoodItem(nextId, name, category, customCategoryName, LocalDate.now(), expireDate);
		foodItems.add(foodItem);
		nextId++;
	}

	public void showFoods() {
		if (foodItems.isEmpty()) {
			System.out.println("冷蔵庫に食材がありません。");
			return;
		}

		foodItems.sort(
				Comparator.comparing(
						FoodItem::getExpireDate));

		System.out.println("===== 食材一覧 =====");
		for (FoodItem food : foodItems) {
			System.out.println("ID：" + food.getId());
			System.out.println("食品名：" + food.getName());
			System.out.println("カテゴリー：" + food.getCategoryName());
			System.out.println("登録日：" + food.getCreatedDate());
			System.out.println("消費期限：" + food.getExpireDate());
			System.out.println("--------------------");
		}
	}

	public FoodItem findFoodById(int id) {
		for (FoodItem food : foodItems) {
			if (food.getId() == id) {
				return food;
			}
		}
		return null;
	}

	public boolean updateFood(int id, String name, Category category, String customCategoryName, LocalDate expireDate) {
		FoodItem food = findFoodById(id);

		if (food == null) {
			return false;
		}
		food.setName(name);
		food.setCategory(category);
		food.setCustomCategoryName(customCategoryName);
		food.setExpireDate(expireDate);
		return true;
	}

	public boolean deleteFood(int id) {
		FoodItem food = findFoodById(id);

		if (food == null) {
			return false;
		}
		foodItems.remove(food);
		return true;
	}

	public void checkExpireDates() {
		if (foodItems.isEmpty()) {
			System.out.println("冷蔵庫に食材がありません。");
			return;
		}

		System.out.println("===== 消費期限チェック =====");
		LocalDate today = LocalDate.now();
		for (FoodItem food : foodItems) {
			long days = ChronoUnit.DAYS.between(today, food.getExpireDate());
			if (days < 0) {
				System.out.println("【期限切れ】" + food.getName() + " → " + Math.abs(days) + "日経過");
			} else if (days <= 3) {
				System.out.println("【注意】" + food.getName() + " → あと" + days + "日");
			} else {
				System.out.println("【問題なし】" + food.getName() + " → あと" + days + "日");
			}
		}
	}

	public boolean isEmpty() {
		return foodItems.isEmpty();
	}
}