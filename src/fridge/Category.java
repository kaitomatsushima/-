package fridge;

import java.util.Scanner;

public enum Category {
	MEAT("肉", 3), FISH("魚", 2), VEGETABLE("野菜", 5), DAIRY("乳製品", 7), OTHER("その他", 0);

	private String displayName;
	private int defaultExpireDays;

	Category(String displayName, int defaultExpireDays) {
		this.displayName = displayName;
		this.defaultExpireDays = defaultExpireDays;
	}

	public String getDisplayName() {
		return displayName;
	}

	public int getDefaultExpireDays() {
		return defaultExpireDays;
	}

	public static Category selectCategory(Scanner scanner) {

		while (true) {

			System.out.println("===== カテゴリー選択 =====");
			System.out.println("1. 肉");
			System.out.println("2. 魚");
			System.out.println("3. 野菜");
			System.out.println("4. 乳製品");
			System.out.println("5. その他");
			System.out.print("選択：");

			String input = scanner.nextLine();

			switch (input) {

			case "1":
				return MEAT;

			case "2":
				return FISH;

			case "3":
				return VEGETABLE;

			case "4":
				return DAIRY;

			case "5":
				return OTHER;

			default:
				System.out.println(
						"1～5の番号を入力してください。");
			}
		}
	}
}