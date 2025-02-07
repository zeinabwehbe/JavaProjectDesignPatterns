package strategy;

/**
 * RegularPriceStrategy implements the PricingStrategy interface and calculates the regular price
 * of a product without applying any discount.
 */
public class RegularPriceStrategy implements PricingStrategy {

    /**
     * Calculates the regular price of a product, which is the base price without any modifications.
     *
     * @param basePrice The original price of the product.
     * @return The regular price (same as the base price).
     */
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice; // No discount is applied, return the base price as is.
    }
}
