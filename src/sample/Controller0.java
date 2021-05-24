package sample;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;
import java.io.Serializable;

public class Controller0 implements Serializable {
    @javafx.fxml.FXML
    @FXML
    private BorderPane mainPane;


    public void handleBtn1(javafx.event.ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("screen1");
        mainPane.setCenter(view);
    }


    public void handleBtn3(javafx.event.ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("screen3");
        mainPane.setCenter(view);
    }

    public void handleBtn2(javafx.event.ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("screen2");
        mainPane.setCenter(view);
    }
}
