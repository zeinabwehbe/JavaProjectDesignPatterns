package controller;

import model.CategoryData;
import view.CategoryView;

import java.util.List;

public class CategoryController {
    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields
    //~ ----------------------------------------------------------------------------------------------------------------
    private final List<CategoryData> categories;
    private CategoryView categoryView;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors
    //~ ----------------------------------------------------------------------------------------------------------------

    public CategoryController() {
        this.categories = CategoryData.getStaticCategories();
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods
    //~ ----------------------------------------------------------------------------------------------------------------

    public void setCategoryView(CategoryView categoryView) {
        this.categoryView = categoryView;
    }

    public void addCategory(String categoryId, String categoryName) {
        CategoryData category = new CategoryData(categoryId, categoryName);
        categories.add(category);
        if (categoryView != null) {
            categoryView.updateDropdown();
        }
    }

    public List<CategoryData> getCategories() {
        return categories;
    }
}
