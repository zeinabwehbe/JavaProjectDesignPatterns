package strategy;

/**
 * PricingStrategy interface defines the method for calculating the price of a product.
 * Any pricing strategy (e.g., discount or regular price) should implement this interface.
 */
public interface PricingStrategy {

    /**
     * Calculates the price of a product based on the base price.
     *
     * @param basePrice The original price of the product.
     * @return The calculated price after applying the strategy (e.g., discount).
     */
    double calculatePrice(double basePrice);
}
