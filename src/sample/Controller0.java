package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller0 implements Serializable { //controller du l'interface qui gere les interfaces

    @javafx.fxml.FXML
    Tab tab1;

    @javafx.fxml.FXML
    Tab tab2;

    public void initialize(URL location, ResourceBundle resources)
    {

        FXMLLoader loader = new FXMLLoader();
        try
        {
            AnchorPane anch1 = loader.load(getClass().getResource("screen1.fxml"));
            tab1.setContent(anch1);
        }
        catch(IOException iex)
        {
            System.out.println("File not found");
        }
        loader = new FXMLLoader();
        try
        {
            AnchorPane anch2 = loader.load(getClass().getResource("screen2.fxml"));
            tab2.setContent(anch2);
        }
        catch(IOException iex)
        {
            System.out.println("File not found");
        }

    }


}
