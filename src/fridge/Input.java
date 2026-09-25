package fridge;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Input {
	public static String inputName(Scanner scanner) {
		while (true) {
			System.out.print("食品名を入力してください：");
			String name = scanner.nextLine();
			if (!name.trim().isEmpty()) {
				return name;
			}
			System.out.println("食品名を入力してください。");
		}
	}

	public static String inputCustomCategoryName(
			Scanner scanner) {

		while (true) {
			System.out.print("カテゴリー名を入力してください：");
			String name = scanner.nextLine();
			if (!name.trim().isEmpty()) {
				return name;
			}
			System.out.println("カテゴリー名を入力してください。");
		}
	}

	public static LocalDate inputDate(Scanner scanner) {
		while (true) {
			System.out.print("消費期限を入力してください（yyyy-MM-dd）：");
			String input = scanner.nextLine();

			try {
				LocalDate date = LocalDate.parse(input);
				if (date.isBefore(LocalDate.now())) {
					System.out.println("今日より前の日付は入力できません。");
					continue;
				}
				return date;
			} catch (DateTimeParseException e) {
				System.out.println("日付の形式が正しくありません。");
				System.out.println("例：2026-09-25");
			}
		}
	}

	public static int inputId(
			Scanner scanner,
			String message) {
		while (true) {
			System.out.print(message);
			try {
				int id = scanner.nextInt();
				scanner.nextLine();
				if (id <= 0) {
					System.out.println("1以上の数字を入力してください。");
					continue;
				}
				return id;
			} catch (Exception e) {
				System.out.println("数字を入力してください。");
				scanner.nextLine();
			}
		}
	}

	public static boolean confirm(
			Scanner scanner,
			String message) {
		while (true) {
			System.out.print(message + "（y/n）：");
			String answer = scanner.nextLine();
			if (answer.equalsIgnoreCase("y")) {
				return true;
			}
			if (answer.equalsIgnoreCase("n")) {
				return false;
			}
			System.out.println("y または n を入力してください。");
		}
	}
}