package model;

import java.util.Arrays;
import java.util.List;
/*
* public class CategoryData
{
    private String categoryId;
    private String categoryName;

    public CategoryData(String categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
*/
/**
 * CategoryData class represents a product category.
 */
public class CategoryData {
    private final String categoryId;
    private final String categoryName;

    /**
     * Constructs a CategoryData instance with specified category ID and name.
     *
     * @param categoryId   The unique identifier for the category.
     * @param categoryName The name of the category.
     */
    public CategoryData(String categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    /**
     * Gets the category ID.
     *
     * @return The category ID.
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * Gets the category name.
     *
     * @return The category name.
     */
    public String getCategoryName() {
        return categoryName;
    }
}
