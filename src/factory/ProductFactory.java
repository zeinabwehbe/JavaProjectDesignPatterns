package factory;

import model.ProductData;
import strategy.PricingStrategy;

import java.sql.Blob;

/**
 * Factory class to create ProductData objects with validation.
 */
public class ProductFactory {
    /**
     * Creates a ProductData instance with proper validation.
     *
     * @param productId       Unique identifier for the product.
     * @param productCategory Category of the product.
     * @param productName     Name of the product.
     * @param productStatus   Status of the product (e.g., "Available", "Out of Stock").
     * @param productImage    Image blob of the product (optional, can be null).
     * @param basePrice       Base price of the product (must be non-negative).
     * @param pricingStrategy Pricing strategy for the product.
     * @return A new ProductData object.
     * @throws IllegalArgumentException If any required parameter is invalid.
     */
    public static ProductData createProduct(
            String productId,
            String productCategory,
            String productName,
            String productStatus,
            Blob productImage,
            Double basePrice,
            PricingStrategy pricingStrategy
    ) {
        // Validate required fields
        if (productId == null || productId.isBlank()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty.");
        }
        if (productCategory == null || productCategory.isBlank()) {
            throw new IllegalArgumentException("Product category cannot be null or empty.");
        }
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or empty.");
        }
        if (productStatus == null || productStatus.isBlank()) {
            throw new IllegalArgumentException("Product status cannot be null or empty.");
        }
        if (basePrice == null || basePrice < 0) {
            throw new IllegalArgumentException("Base price must be non-negative.");
        }
        if (pricingStrategy == null) {
            throw new IllegalArgumentException("Pricing strategy cannot be null.");
        }

        // Create and return the ProductData object
        return new ProductData(productId, productCategory, productName, productStatus, productImage, basePrice, pricingStrategy);
    }
}
