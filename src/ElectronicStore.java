import java.util.Scanner;

public class ElectronicStore {
    private final int MAX_PRODUCTS = 10;
    private String name;
    private double revenue;
    private Product[] products;
    private int productCount;
    private ShoppingCart cart;
    private Product[] mostPopularProducts;
    private int numSales;
    private double averageRevenue;
    //inventory contains the products the store sells, regardless of whether they are in stock
    private Product[] inventory;
    //inventoryCount contains the number of different products a store has
    private int inventoryCount;


    public ElectronicStore(String name) {
        this.name = name;
        products = new Product[MAX_PRODUCTS];
        inventory = new Product[MAX_PRODUCTS];
        numSales = 0;
        revenue = 0;
        averageRevenue = 0;
        cart = new ShoppingCart(this);
        mostPopularProducts = new Product[3];

    }

    public boolean addProduct(Product p) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                products[i] = p;
                inventory[i] = p;
                productCount++;
                inventoryCount++;
                return true;
            }
        }
        return false;
    }

    public void addProduct(Product p, int index) {

        //shifts everything to the right
        for (int i = productCount; i > index; i--) {
            products[i] = products[i-1];;
        }
        //overwrites the value of products[index] (the previous value of products[index] has already been copied)
        products[index] = p;
        productCount++;

    }

    public void removeProduct(int index) {
        //if the index is valid
        if (index >= 0 && index < productCount) {
            //set the object to null
            products[index] = null;
            //shift everything to the left and pushes the null value to the end of the list
            for (int i = index + 1; i < productCount; i++) {
                Product temp = products[i - 1];
                products[i - 1] = products[i];
                products[i] = temp;
            }
            productCount--;
        }

    }

    public void sellProducts(int item, int amount) {
        if (!(item > MAX_PRODUCTS - 1 || item < 0 || amount < 0 || products[item] == null)) {
            revenue += products[item].sellUnits(amount);
        }
    }


    public void sellProducts() {
        int desiredIndex = 0;
        int desiredQuantity = 0;
        Scanner in = new Scanner(System.in);
        printStock();
        System.out.println("Select the index of the product you want to buy");
        desiredIndex = in.nextInt();

        System.out.println("How many do you want to buy?");
        desiredQuantity = in.nextInt();

        if (!(desiredIndex > MAX_PRODUCTS - 1 || desiredIndex < 0 || desiredQuantity < 0 || products[desiredIndex] == null)) {
            revenue += products[desiredIndex].sellUnits(desiredQuantity);
        }

    }


    public Product[] getMostPopularProducts() {
        Product[] popularList = getInventoryList();

        //Bubble Sort Algorithm
        boolean flag = true;
        while(flag) {
            //sets the flag to false so that the program will end when
            //the condition in the if statement is not satisfied
            //at this point, the list is sorted
            flag = false;
            //looping up to productsCount-1 because i+1 will be accessed
            for (int i = 0; i < inventoryCount-1; i++) {
                //if the current product is less than the next, swap them
                if (popularList[i].getSoldQuantity() < popularList[i + 1].getSoldQuantity()) {
                    Product temp = popularList[i];
                    popularList[i] = popularList[i + 1];
                    popularList[i + 1] = temp;
                    //once swapped, set the flag to true
                    flag = true;
                }
            }
        }
        mostPopularProducts[0] = popularList[0];
        mostPopularProducts[1] = popularList[1];
        mostPopularProducts[2] = popularList[2];


        return mostPopularProducts;
    }

    public static ElectronicStore createStore1(){
        ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
        Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
        Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
        Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
        Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
        Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", 15.5, false);
        Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", 23, true);
        ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", 8, false);
        ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", 12, true);
        store1.addProduct(d1);
        store1.addProduct(d2);
        store1.addProduct(l1);
        store1.addProduct(l2);
        store1.addProduct(f1);
        store1.addProduct(f2);
        store1.addProduct(t1);
        store1.addProduct(t2);
        return store1;
    }

    public double getRevenue() {
        return revenue;
    }

    public void printStock() {
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null)
                System.out.println(i + ". " + products[i]);
        }
    }


    public String getName() {
        return name;
    }

    public Product[] getProducts() {
        return products;
    }

    public int getNumSales() {
        return numSales;
    }

    public double getAverageRevenue() {
        return averageRevenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public void setAverageRevenue(double averageRevenue) {
        this.averageRevenue = averageRevenue;
    }

    public void setNumSales(int numSales) {
        this.numSales = numSales;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public Product[] getProductsList() {
        Product[] nonNullProducts = new Product[productCount];
        for (int i = 0; i < productCount; i++) {
            if (products[i] != null) {
                nonNullProducts[i] = products[i];
            }
        }
        return nonNullProducts;
    }

    public Product[] getInventoryList() {
        Product[] nonNullInventory = new Product[inventoryCount];
        for (int i = 0; i < inventoryCount; i++) {
            if (inventory[i] != null) {
                nonNullInventory[i] = inventory[i];
            }
        }
        return nonNullInventory;
    }

}
