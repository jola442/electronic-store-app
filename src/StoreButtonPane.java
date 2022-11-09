import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class StoreButtonPane extends Pane {
    private Button resetButton, addButton, removeButton, completeSaleButton;

    public StoreButtonPane(){
        resetButton = new Button("Reset Store");
        addButton = new Button("Add to Cart");
        removeButton = new Button("Remove from Cart");
        completeSaleButton = new Button("Complete Sale");

        //Setting the size of the buttons
        resetButton.setPrefSize(150,50);
        addButton.setPrefSize(150,50);
        removeButton.setPrefSize(150,50);
        completeSaleButton.setPrefSize(150,50);

        //Setting the location of the buttons
        resetButton.relocate(0,0);
        addButton.relocate(250,0);
        removeButton.relocate(480,0);
        completeSaleButton.relocate(630,0);


        //Adding the buttons to the store button pane
        getChildren().addAll(resetButton,addButton,removeButton,completeSaleButton);

    }
    public Button getResetButton() {
        return resetButton;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getRemoveButton(){
        return removeButton;
    }

    public Button getCompleteSaleButton() {
        return completeSaleButton;
    }
}
