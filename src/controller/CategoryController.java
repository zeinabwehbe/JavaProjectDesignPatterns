package controller;

import model.CategoryData;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Controller for managing category-related operations such as adding categories
 * and notifying listeners when the category list is updated.
 */
public class CategoryController {
    // The class uses an ArrayList<CategoryData> to store the categories.
    private final List<CategoryData> categories = new ArrayList<>();  // List to store category data
    private Consumer<List<CategoryData>> categoryUpdateListener;  // Listener to notify when category list is updated

    /**
     * Sets the listener that will be notified when the category list is updated.
     The lambda function is utilized in the setCategoryUpdateListener method. It allows flexibility by letting you define
     the behavior of the categoryUpdateListener using a functional interface (Consumer<List<CategoryData>>).
     This interface is then used in the addCategory method to notify the listener when a category is added.
     The lambda function helps in defining the actions to take when a category update occurs,
     which could be any operation on the category list like updating a view.
     * @param listener The Consumer function that handles category updates.
     */
    public void setCategoryUpdateListener(Consumer<List<CategoryData>> listener) {
        this.categoryUpdateListener = listener;  // Assign the listener to the class variable
    }

    /**
     * Adds a new category to the category list and notifies the listener if set.
     *
     * @param id   The ID of the new category.
     * @param name The name of the new category.
     */
    public void addCategory(String id, String name) {
        categories.add(new CategoryData(id, name));  // Add a new category to the list
        // If a listener is set, notify it with the updated list of categories
        if (categoryUpdateListener != null) {
            categoryUpdateListener.accept(categories);
        }
    }

    /**
     * Retrieves the list of categories.
     *
     * @return A list of CategoryData objects representing all categories.
     */
    public List<CategoryData> getCategories() {
        return categories;  // Return the list of categories
    }
}
