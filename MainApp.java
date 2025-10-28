package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.LoginView;
import dao.Database;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Database.init(); // create tables and sample data if not exist
        LoginView loginView = new LoginView(primaryStage);
        primaryStage.setTitle("Banking System");
        primaryStage.setScene(new Scene(loginView.getView(), 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
