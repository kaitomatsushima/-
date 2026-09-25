package fridge;

import java.time.LocalDate;

public class FoodItem {
	private int id;
	private String name;
	private Category category;
	private String customCategoryName;
	private LocalDate createdDate;
	private LocalDate expireDate;

	public FoodItem(int id, String name, Category category, String customCategoryName, LocalDate createdDate,
			LocalDate expireDate) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.customCategoryName = customCategoryName;
		this.createdDate = createdDate;
		this.expireDate = expireDate;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Category getCategory() {
		return category;
	}

	public String getCustomCategoryName() {
		return customCategoryName;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public LocalDate getExpireDate() {
		return expireDate;
	}

	public String getCategoryName() {

		if (category == Category.OTHER) {
			return customCategoryName;
		}

		return category.getDisplayName();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setCustomCategoryName(String customCategoryName) {
		this.customCategoryName = customCategoryName;
	}

	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}
}