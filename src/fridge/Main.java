package fridge;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Fridge fridge = new Fridge();

		System.out.println("======================");
		System.out.println("     うちの冷蔵庫");
		System.out.println("======================");

		while (true) {
			System.out.println("===== メニュー =====");
			System.out.println("1. 食材を登録する");
			System.out.println("2. 食材を表示する");
			System.out.println("3. 食材を変更する");
			System.out.println("4. 食材を削除する");
			System.out.println("0. 終了");
			System.out.print("番号を選択してください：");

			int choice;
			try {
				choice = scanner.nextInt();
				scanner.nextLine();
			} catch (Exception e) {
				System.out.println("数字を入力してください。");
				scanner.nextLine();
				continue;
			}
			switch (choice) {
			case 1:
				registerFood(scanner, fridge);
				break;
			case 2:
				fridge.showFoods();
				break;
			case 3:
				updateFood(scanner, fridge);
				break;
			case 4:
				deleteFood(scanner, fridge);
				break;
			case 0:
				System.out.println("アプリを終了します。");
				scanner.close();
				return;
			default:
				System.out.println("0～4の番号を入力してください。");
			}
		}
	}

	private static void registerFood(
			Scanner scanner,
			Fridge fridge) {
		System.out.println("===== 食材登録 =====");
		String name = Input.inputName(scanner);
		Category category = Category.selectCategory(scanner);
		String customCategoryName = null;
		LocalDate expireDate;

		if (category == Category.OTHER) {
			customCategoryName = Input.inputCustomCategoryName(scanner);
			expireDate = Input.inputDate(scanner);
		} else {
			expireDate = LocalDate.now().plusDays(category.getDefaultExpireDays());
		}

		System.out.println("===== 登録内容 =====");
		System.out.println("食品名：" + name);

		if (category == Category.OTHER) {
			System.out.println("カテゴリー：" + customCategoryName);

		} else {
			System.out.println("カテゴリー：" + category.getDisplayName());
		}

		System.out.println("消費期限：" + expireDate);

		boolean confirm = Input.confirm(scanner, "この内容で登録しますか？");

		if (!confirm) {
			System.out.println("登録をキャンセルしました。");
			return;
		}

		fridge.addFood(name, category, customCategoryName, expireDate);
		System.out.println("食材を登録しました。");
	}

	private static void updateFood(
			Scanner scanner,
			Fridge fridge) {

		if (fridge.isEmpty()) {
			System.out.println("更新できる食材がありません。");
			return;
		}

		System.out.println("===== 食材変更 =====");
		fridge.showFoods();

		int id = Input.inputId(scanner, "変更する食材のIDを入力してください：");
		FoodItem food = fridge.findFoodById(id);

		if (food == null) {
			System.out.println("指定されたIDの食材がありません。");
			return;
		}

		String name = Input.inputName(scanner);
		Category category = Category.selectCategory(scanner);
		String customCategoryName = null;
		LocalDate expireDate;

		if (category == Category.OTHER) {
			customCategoryName = Input.inputCustomCategoryName(scanner);
			expireDate = Input.inputDate(scanner);
		} else {
			expireDate = LocalDate.now().plusDays(category.getDefaultExpireDays());
		}

		fridge.updateFood(id, name, category, customCategoryName, expireDate);
		System.out.println("食材を変更しました。");
	}

	private static void deleteFood(
			Scanner scanner,
			Fridge fridge) {

		if (fridge.isEmpty()) {
			System.out.println("削除できる食材がありません。");
			return;
		}

		System.out.println("===== 食材削除 =====");
		fridge.showFoods();
		int id = Input.inputId(scanner, "削除する食材のIDを入力してください：");
		FoodItem food = fridge.findFoodById(id);

		if (food == null) {
			System.out.println("指定されたIDの食材がありません。");
			return;
		}

		boolean confirm = Input.confirm(scanner, food.getName() + "を削除しますか？");

		if (!confirm) {
			System.out.println("削除をキャンセルしました。");
			return;
		}

		fridge.deleteFood(id);
		System.out.println("食材を削除しました。");
	}
}