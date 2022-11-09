public class ToasterOven extends KitchenAppliance {
    private int width;
    private boolean convection;

    public ToasterOven(double price, int quantity, int wattage, String color, String brand, int width, boolean convection) {
        super(price, quantity, wattage, color, brand);
        this.width = width;
        this.convection = convection;
    }


    public String toString(){
        if (convection)
            return String.format("%d inch %s Toaster with convection (%s, %d watts) ($%.2f each, %d in stock, %d sold)",
                    width, getBrand(), getColor(), getWattage(), getPrice(), getStockQuantity(), getSoldQuantity());

        else
            return String.format("%d inch %s Toaster (%s, %d watts) ($%.2f each, %d in stock, %d sold)",
                    width, getBrand(), getColor(), getWattage(), getPrice(), getStockQuantity(), getSoldQuantity());
    }
}

