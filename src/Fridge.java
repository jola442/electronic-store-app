public class Fridge extends KitchenAppliance{
    private double cubicFeet;
    private boolean hasFreezer;

    public Fridge(double price, int quantity, int wattage, String color, String brand, double cubicFeet, boolean freezer){
        super(price, quantity, wattage, color, brand);
        this.cubicFeet = cubicFeet;
        hasFreezer = freezer;
    }

    public String toString() {
        if(hasFreezer)
            return String.format("%.1f cu. ft. %s Fridge with Freezer (%s, %d watts) ($%.2f each, %d in stock, %d sold)",
                    cubicFeet, getBrand(), getColor(), getWattage(), getPrice(), getStockQuantity(), getSoldQuantity());
        else
            return String.format("%.1f cu. ft. %s Fridge (%s, %d watts) ($%.2f each, %d in stock, %d sold)",
                cubicFeet, getBrand(), getColor(), getWattage(), getPrice(), getStockQuantity(), getSoldQuantity());

    }
}
