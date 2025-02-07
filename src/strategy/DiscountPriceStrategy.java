package strategy;

/**
 * DiscountPriceStrategy applies a discount to the base price of a product.
 * Implements the PricingStrategy interface.
 */
public class DiscountPriceStrategy implements PricingStrategy {
    private final double discount;

    /**
     * Constructs a DiscountPriceStrategy with the specified discount rate.
     *
     * @param discount The discount rate to be applied (e.g., 0.1 for 10%).
     */
    public DiscountPriceStrategy(double discount) {
        this.discount = discount;
    }

    /**
     * Calculates the price after applying the discount to the base price.
     *
     * @param basePrice The original price of the product before the discount.
     * @return The price after applying the discount.
     */
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice - (basePrice * discount);
    }
}
