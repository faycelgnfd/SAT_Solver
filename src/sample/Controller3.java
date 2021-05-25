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

public class Controller3 implements Initializable { //controller de algo A*

    @javafx.fxml.FXML
    private TextField fich;
    @javafx.fxml.FXML
    private TextArea fcont;
    @javafx.fxml.FXML
    private ChoiceBox<String> methods;
    private String[] ListMethods = {"Heuristique 1", "Heuristique 2"};

    public void initialize(URL arg0, ResourceBundle arg1) {  //affiche les 2 methodes pour l'algo A*
        methods.getItems().addAll(ListMethods);
            }

    public void FileFct(ActionEvent actionEvent) { //affiche le nom et le contenu du fichier
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


