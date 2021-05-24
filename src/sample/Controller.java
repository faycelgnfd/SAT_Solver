package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    @javafx.fxml.FXML
    @FXML
    private TextField fich;
    @javafx.fxml.FXML
    private TextArea fcont;
    private ChoiceBox<String> methods;
    private String[] ListMethods = {"1", "2"};

    public void initialize(URL arg0, ResourceBundle arg1) {
        if (methods !=null) methods.getItems().addAll(ListMethods);
    }

    public void FileFct(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("cnf files", "*.cnf"));
        File f = fc.showOpenDialog(null);
        if (f != null) {
            fich.setText(f.getName());
            try (Scanner input = new Scanner(f)) {
                while (input.hasNextLine()) {
                    fcont.appendText(input.nextLine()+"\n");

                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }

    }
}


