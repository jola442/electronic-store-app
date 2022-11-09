public abstract class Product {
    private double price;
    private int stockQuantity;
    private int soldQuantity;
    private int unitsInCart;

    public double getPrice() {
        return price;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public int getUnitsInCart() {
        return unitsInCart;
    }

    public void setUnitsInCart(int unitsInCart) {
        this.unitsInCart = unitsInCart;
    }

    public Product(double price, int quantity){
        this.price = price;
        this.stockQuantity = quantity;
    }

    public double sellUnits(int amount){
        if (stockQuantity >= amount){
            stockQuantity -= amount;
            soldQuantity += amount;
            return amount * price;
        }
        else{
            return 0.0;
        }
    }
}
