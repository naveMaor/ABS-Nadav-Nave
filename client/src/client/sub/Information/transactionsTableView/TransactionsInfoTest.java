package client.sub.Information.transactionsTableView;

import client.sub.Information.MainInformationTest;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TransactionsInfoTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello There in FXML");

        Parent load = FXMLLoader.load(getClass().getResource("transactions.fxml"));
        Scene scene = new Scene(load, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(TransactionsInfoTest.class);
    }}
