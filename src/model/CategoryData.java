package model;

import java.util.Arrays;
import java.util.List;

public class CategoryData {
    private String categoryId;
    private String categoryName;

    public CategoryData(String categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public CategoryData() {}

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public static List<CategoryData> getStaticCategories() {
        return Arrays.asList(
                new CategoryData("1", "Electronics"),
                new CategoryData("2", "Books"),
                new CategoryData("3", "Clothing"),
                new CategoryData("4", "Home & Kitchen")
        );
    }
}
