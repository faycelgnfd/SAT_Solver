package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller1 implements Initializable {   //controller de la recherche en largeur d'abord

    @javafx.fxml.FXML
    private TextField fich;
    @javafx.fxml.FXML
    private TextArea fcont;
    private ChoiceBox<String> methods;
    private String[] ListMethods = {"1", "2"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void FileFct(ActionEvent actionEvent) { //afficher le nom et le contenu du fichier
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("cnf files", "*.cnf"));
        File f = fc.showOpenDialog(null);
        if (f != null) {
            fcont.setText("");
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


