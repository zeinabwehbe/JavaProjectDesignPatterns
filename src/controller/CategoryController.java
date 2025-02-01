package controller;

import model.CategoryData;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CategoryController {
    private final List<CategoryData> categories = new ArrayList<>();
    private Consumer<List<CategoryData>> categoryUpdateListener;

    public void setCategoryUpdateListener(Consumer<List<CategoryData>> listener) {
        this.categoryUpdateListener = listener;
    }

    public void addCategory(String id, String name) {
        categories.add(new CategoryData(id, name));
        if (categoryUpdateListener != null) {
            categoryUpdateListener.accept(categories);
        }
    }

    public List<CategoryData> getCategories() {
        return categories;
    }
}
