import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class ElectronicStoreView extends Pane {
    private ElectronicStore model;
    private StoreButtonPane buttonPane;
    private ListView<Product> mostPopularListView, storeStockListView,currentCartListView;
    private TextField numSalesField, revenueField, averageRevenueField;
    private Label cartLabel;

    public ElectronicStoreView(ElectronicStore initModel) {
        model = initModel;

        //Initializing labels
        Label summaryLabel = new Label("Store Summary:");
        Label stockLabel = new Label("Store Stock:");
        cartLabel = new Label("Current Cart($0.0)");
        Label popularLabel = new Label("Most Popular Items:");
        Label numSalesLabel = new Label("# Sales:");
        Label revenueLabel = new Label("Revenue:");
        Label averageRevenueLabel = new Label("$ / Sale:");

        //Positioning labels
        summaryLabel.relocate(50,10);
        stockLabel.relocate(300,10);
        cartLabel.relocate(620,10);
        popularLabel.relocate(40,130);
        numSalesLabel.relocate(45,35);
        revenueLabel.relocate(45,65);
        averageRevenueLabel.relocate(45,95);

        //Initializing text fields
        numSalesField = new TextField();
        revenueField = new TextField();
        averageRevenueField = new TextField();

        //Setting the default text of the text fields
        numSalesField.setText("0");
        revenueField.setText("0.00");
        averageRevenueField.setText("N/A");

        //Making the text fields uneditable by the user
        numSalesField.setEditable(false);
        revenueField.setEditable(false);
        averageRevenueField.setEditable(false);


        //Setting the size of the text fields
        numSalesField.setPrefSize(85,10);
        revenueField.setPrefSize(85,10);
        averageRevenueField.setPrefSize(85,10);

        //Positioning the text fields
        numSalesField.relocate(95,30);
        revenueField.relocate(95,60);
        averageRevenueField.relocate(95,90);

        //Initializing button pane
        buttonPane = new StoreButtonPane();

        //Positioning the button pane
        buttonPane.relocate(20,340);

        buttonPane.getAddButton().setDisable(true);
        //buttonPane.getResetButton().setDisable(true);
        buttonPane.getRemoveButton().setDisable(true);
        buttonPane.getCompleteSaleButton().setDisable(true);

        //Initializing the list views
        storeStockListView = new ListView<Product>();
        currentCartListView = new ListView<Product>();
        mostPopularListView = new ListView<Product>();




        //ObservableList <Product> popularItemsObservableList = FXCollections.observableArrayList(model.getProductsList());
        //popularItemsListView.setItems(popularItemsObservableList);

        //Positioning the list views
        storeStockListView.relocate(190,30);
        mostPopularListView.relocate(10,150);
        currentCartListView.relocate(500,30);

        //Setting the size of the list views
        storeStockListView.setPrefSize(300,300);
        mostPopularListView.setPrefSize(170,180);
        currentCartListView.setPrefSize(300,300);

        //Adding all components to the view
        getChildren().addAll(buttonPane,stockLabel,summaryLabel,
                popularLabel, cartLabel, numSalesLabel, revenueLabel, averageRevenueLabel,
                numSalesField, revenueField, averageRevenueField,
                mostPopularListView, currentCartListView, storeStockListView);

        update();
    }


    //Removes all items from the cart
    public void clearCart(){
        for(int i = 0; i < model.getCart().getCurrentCart().length; i++){
            Product product = model.getCart().getCurrentCart()[i];
            if(product != null) {
                product.setUnitsInCart(0);
                model.getCart().getCurrentCart()[i] = null;
            }
        }
        model.getCart().setNumProducts(0);
    }

    public void update(){
        //Updating the values of the text fields
        numSalesField.setText(String.format("%d", model.getNumSales()));
        revenueField.setText(String.format("%.2f", model.getRevenue()));
        if(model.getNumSales() > 0) {
            averageRevenueField.setText(String.format("%.2f", model.getAverageRevenue()));
        }
        else
            averageRevenueField.setText("N/A");

        //Updating the cost of the cart
        cartLabel.setText((String.format("Current Cart($%.2f)", model.getCart().computePrice())));

        ObservableList <Product> storeStockObservableList = FXCollections.observableArrayList(model.getProductsList());
        storeStockListView.setItems(storeStockObservableList);

        //current cart list view from an observable list (list provided by the model)
        ObservableList <Product> currentCartObservableList = FXCollections.observableArrayList(model.getCart().getCartList());
        currentCartListView.setItems(currentCartObservableList);

        ObservableList <Product> mostPopularObservableList = FXCollections.observableArrayList(model.getMostPopularProducts());
        mostPopularListView.setItems(mostPopularObservableList);
        //if there is enough of a product in stock it should be displayed on the store stock list view

        //if there is 1 or more items in the cart, the complete sale button should be enabled
        if(model.getCart().getCartList().length > 0){
            buttonPane.getCompleteSaleButton().setDisable(false);
        }
        //it should be disabled otherwise
        else
            buttonPane.getCompleteSaleButton().setDisable(true);


        //getting the index of the item currently selected in the cart list view
        int index1 = getCurrentCartListView().getSelectionModel().getSelectedIndex();

        //if an item is selected, enable the remove button
        if(index1 > -1){
            buttonPane.getRemoveButton().setDisable(false);
            buttonPane.getAddButton().setDisable(true);
        }
        //otherwise, disable it
        else
            buttonPane.getRemoveButton().setDisable(true);

        //getting the index of the item currently selected in the stock list view
        int index2 = getStoreStockListView().getSelectionModel().getSelectedIndex();

        //if an item is selected, enable the add button
        if(index2 > -1){
            buttonPane.getAddButton().setDisable(false);
            //buttonPane.getRemoveButton().setDisable(true);
        }
        //otherwise disable it
        else
            buttonPane.getAddButton().setDisable(true);
    }


    public void setModel(ElectronicStore model) {
        this.model = model;
    }

    public ListView<Product> getCurrentCartListView() {
        return currentCartListView;
    }

    public ListView<Product> getStoreStockListView() {
        return storeStockListView;
    }

    public StoreButtonPane getButtonPane() {
        return buttonPane;
    }

}