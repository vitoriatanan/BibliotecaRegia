package BibliotecaRegia.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Janela2 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btVoltar;

    @FXML
    void clickVoltar(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert btVoltar != null : "fx:id=\"btVoltar\" was not injected: check your FXML file 'Janela2.fxml'.";

    }

}
