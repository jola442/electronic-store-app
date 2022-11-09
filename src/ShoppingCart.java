public class ShoppingCart {
    private final int CAPACITY;
    private Product[] currentCart;
    private int numProducts;

    public ShoppingCart(ElectronicStore initStore) {
        CAPACITY = 100;
        currentCart = new Product[CAPACITY];
    }

    public boolean addToCart(Product p) {
        for (int i = 0; i < currentCart.length; i++) {
            if (currentCart[i] == null) {
                currentCart[i] = p;
                numProducts++;
                return true;
            }
        }
        return false;
    }

    public void removeProduct(int index){
        if(index >= 0 && index < numProducts){
            currentCart[index] = null;
            for(int i = index + 1; i < numProducts; i++){
                Product temp = currentCart[i-1];
                currentCart[i-1] = currentCart[i];
                currentCart[i] = temp;
            }
            numProducts--;
        }
    }

    public Product[] getCartList() {
        Product[] nonNullProducts = new Product[numProducts];
        for (int i = 0; i < numProducts; i++) {
            if (currentCart[i] != null) {
                nonNullProducts[i] = currentCart[i];
            }
        }
        return nonNullProducts;

    }

    public double computePrice() {
        double price = 0;
        if (getCartList().length > 0) {

            for (int i = 0; i < numProducts; i++) {
                price += getCartList()[i].getPrice();
            }

        }
        return price;
    }

    public int getNumProducts() {
        return numProducts;
    }

    public Product[] getCurrentCart() {
        return currentCart;
    }


    public void setNumProducts(int numProducts) {
        this.numProducts = numProducts;
    }
}
