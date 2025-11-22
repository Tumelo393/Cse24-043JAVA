package view;

import controller.LoginController;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

public class LoginView {
    private VBox root;
    private LoginController controller = new LoginController();

    public LoginView(Stage primaryStage){
        root = new VBox(10);
        root.setPadding(new Insets(20));
        TextField userField = new TextField();
        userField.setPromptText("Username");
        PasswordField passField = new PasswordField();
        passField.setPromptText("Password");
        Button btn = new Button("Login");
        Label msg = new Label();
        btn.setOnAction(e -> {
            boolean ok = controller.authenticate(userField.getText(), passField.getText());
            if(ok){
                msg.setText("Login successful");
                AccountView av = new AccountView(primaryStage);
                primaryStage.getScene().setRoot(av.getView());
            } else {
                msg.setText("Login failed, check username or password");
            }
        });
        HBox hb = new HBox(10, btn, msg);
        root.getChildren().addAll(new Label("Banking System Login"), userField, passField, hb);
    }

    public Parent getView(){ return root; }
}
