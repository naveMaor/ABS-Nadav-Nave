package client.main;

import client.sub.main.CustomerMainBodyController;
import com.sun.deploy.cache.JarSigningData;
import common.LoginController;
import data.File.XmlFile;
import engine.Engine;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import okhttp3.*;
import util.Constants;
import util.HttpClientUtil;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ClientMainController {
    private Stage primaryStage;
    @FXML private BorderPane root;
    @FXML private AnchorPane loginComponent;
    @FXML private LoginController loginComponentController;
    @FXML private ScrollPane customerMainBody;
    @FXML private CustomerMainBodyController customerMainBodyController;
    //Engine engine = Engine.getInstance();
    private Engine engine = new Engine();


    public enum MessageType{Error,Successfully,Information};

    private StringProperty currentUserName = new SimpleStringProperty();


    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


    public void NewLoanButton1(ActionEvent actionEvent) {
        //noinspection ConstantConditions
        String finalUrl = HttpUrl
                .parse(Constants.LOGIN_PAGE) //todo: change that constant
                .newBuilder()
                .build()
                .toString();


        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select words file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml files", "*.xml"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        //Scene scene = new Scene(load, 800, 600);
        Stage errorWindow;
        if (selectedFile == null) {
            return;
        }
        try {
            XmlFile.createInputObjectFromFile(selectedFile);
            engine.CheckInvalidFile(XmlFile.getInputObject());
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
            return;

        } catch (JAXBException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "file is corrupted");
            alert.showAndWait();
            e.printStackTrace();
            return;
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
            return;
        }

        engine.buildDataFromDescriptor(currentUserName.get());
    }


    @FXML
    private Label welcomeLable;
    @FXML
    private Button NewLoan;


    Node header ;
    Node clientDesktop ;
    Node login ;


    @FXML
    public void initialize() {
        header = root.getTop();
        clientDesktop = root.getCenter();
        login = root.getBottom();
        welcomeLable.setAlignment(Pos.CENTER);
        welcomeLable.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
        root.setCenter(null);
        root.setTop(null);
        loginComponentController.setMainController(this);
        welcomeLable.textProperty().bind(Bindings.concat("Welcome ",currentUserName));




        //customerMainBody.setFitToWidth(true); // tried to set the node to middle of the screen
        //CustomerMainBody.setFitToHeight(true);
    }

    public void updateUserName(String userName) {
        currentUserName.set(userName);
    }

    public void switchToClientDesktop(){
        root.setBottom(null);
        root.setCenter(clientDesktop);
        root.setTop(header);
    }


    public void NewLoanButton(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select xml file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml files", "*.xml"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile == null) {
            return;
        }
        String absolutePath = selectedFile.getAbsolutePath();
        createFileRequest(absolutePath);
    }


    public void createFileRequest(String absolutePath){
        File f = new File(absolutePath);
        RequestBody body =
                new MultipartBody.Builder()
                        .addFormDataPart("file1", f.getName(), RequestBody.create(f, MediaType.parse("text/xml")))
                        //.addFormDataPart("key1", "value1") // you can add multiple, different parts as needed
                        .build();

        String finalUrl = HttpUrl
                .parse(Constants.NEW_LOAN_FROM_FILE)
                .newBuilder()
                .build()
                .toString();

        Request request = new Request.Builder()
                .url(finalUrl)
                .post(body)
                .build();

        HttpClientUtil.runAsync(request, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Platform.runLater(() ->
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR,"Unknown Error");
                    alert.showAndWait();
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.code() != 200){
                    Platform.runLater(() ->
                    {
                        try {
                            Alert alert = new Alert(Alert.AlertType.ERROR,response.body().string());
                            alert.showAndWait();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                } else{
                    Platform.runLater(() ->
                    {
                        try {
                            //Notifications.create().title("Success").text(response.body().string()).hideAfter(Duration.seconds(5)).position(Pos.CENTER).show();
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,response.body().string());
                            alert.showAndWait();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
                //   return false;
            }
        });
}
}