package strategy;

public class DiscountPriceStrategy implements PricingStrategy {
    private double discount;

    public DiscountPriceStrategy(double discount) {
        this.discount = discount;
    }

    @Override
    public double calculatePrice(double basePrice) {
        return basePrice - (basePrice * discount);
    }
}
