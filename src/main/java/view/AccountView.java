package view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.SavingsAccount;
import controller.AccountController;

public class AccountView {
    private VBox root;
    private AccountController controller = new AccountController();

    public AccountView(Stage primaryStage){
        root = new VBox(10);
        root.setPadding(new Insets(20));
        Label title = new Label("Account Dashboard");
        TextField accNo = new TextField();
        accNo.setPromptText("Account number");
        TextField amt = new TextField();
        amt.setPromptText("Amount");
        Button depositBtn = new Button("Deposit");
        depositBtn.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amt.getText());
                SavingsAccount sa = new SavingsAccount(accNo.getText(), 0.0);
                controller.deposit(sa, amount);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "New balance: " + sa.getBalance());
                a.showAndWait();
            } catch(Exception ex) { ex.printStackTrace(); }
        });
        root.getChildren().addAll(title, accNo, amt, depositBtn);
    }

    public Parent getView(){ return root; }
}
