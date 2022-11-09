import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Arrays;

public class ElectronicStoreApp extends Application {
    private ElectronicStoreView view;
    private ElectronicStore model;

    public static void main(String[] args) { launch(args); }

    public void start(Stage primaryStage) {
        model = ElectronicStore.createStore1();
        view = new ElectronicStoreView(model);
        primaryStage.setTitle("Electronic Store Application - " + model.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view, 820,410)); // Sets size of window
        primaryStage.show();
        view.update();

        view.getButtonPane().getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleAdd();
            }
        });

        view.getButtonPane().getRemoveButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleRemove();
            }
        });

        view.getButtonPane().getResetButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleReset();
            }
        });

        view.getButtonPane().getCompleteSaleButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                handleCompleteSale();
            }
        });

        view.getStoreStockListView().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.update();
            }
        });

        view.getCurrentCartListView().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.update();
            }
        });
    }

    public void handleAdd(){
        //adds the item to current cart
        Product selected = view.getStoreStockListView().getSelectionModel().getSelectedItem();
        model.getCart().addToCart(selected);

        //adds one to unitsInCart variable
        selected.setUnitsInCart(selected.getUnitsInCart()+1);

        //subtracts one from the stock of that item
        if(selected.getStockQuantity() > 0){
            selected.setStockQuantity(selected.getStockQuantity()-1);
        }

        //removes the product from the list if it is out of stock
        for(int i = 0; i < model.getProductsList().length;i++){
            Product product = model.getProducts()[i];
            if (product.getStockQuantity() == 0){
                model.removeProduct(i);
            }
        }

        view.update();
    }

    public void handleRemove(){
        //when the remove from cart button is pressed

        //removes the selected product from the current cart list
        int index = view.getCurrentCartListView().getSelectionModel().getSelectedIndex();
        model.getCart().removeProduct(index);

        //updates the store stock by adding one to the stock of the item
        Product selected = view.getCurrentCartListView().getSelectionModel().getSelectedItem();
        selected.setStockQuantity(selected.getStockQuantity()+1);

        //subtracts one from the amount of units of the product in the cart
        selected.setUnitsInCart(selected.getUnitsInCart()-1);

        //If the selected product is back in stock
        if(selected.getStockQuantity() == 1 && !Arrays.asList(model.getProducts()).contains(selected)){
            //add the product back at index 0
            model.addProduct(selected,0);
        }


        view.update();
    }

    public void handleReset(){
        //reverts the model to its original state
        model = ElectronicStore.createStore1();
        //sets up the view according to the model
        view.setModel(model);
        view.update();

    }

    public void handleCompleteSale(){
        //increases the sold quantity of each item in the cart
        for(int i = 0; i < model.getCart().getNumProducts(); i++){
            int currentSoldQuantity = model.getCart().getCurrentCart()[i].getSoldQuantity();
            model.getCart().getCurrentCart()[i].setSoldQuantity(currentSoldQuantity+1);
        }

        //increases #sales by 1
        int currentNumSales = model.getNumSales();
        model.setNumSales(currentNumSales+1);

        //increases revenue by the total value of the cart
        double currentRevenue = model.getRevenue();
        model.setRevenue(currentRevenue + model.getCart().computePrice());

        //calculates average revenue (#sales/revenue)
        model.setAverageRevenue(model.getRevenue()/model.getNumSales());


        view.clearCart();

        view.update();
    }

}
