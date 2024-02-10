package BibliotecaRegia.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import BibliotecaRegia.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class TelaInicialController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label loginMensagemLabel;

    @FXML
    private ImageView mudarDeTela;

    @FXML
    private TextField usuarioTextField;

    @FXML
    private void mudarDeTela(ActionEvent event) {
        if (event.getSource() == mudarDeTela) {
            Main.navegacaoEntreTelas("login");
        }

    }

    @FXML
    void initialize() {
        assert loginMensagemLabel != null : "fx:id=\"loginMensagemLabel\" was not injected: check your FXML file 'telaInicial.fxml'.";
        assert mudarDeTela != null : "fx:id=\"mudarDeTela\" was not injected: check your FXML file 'telaInicial.fxml'.";
        assert usuarioTextField != null : "fx:id=\"usuarioTextField\" was not injected: check your FXML file 'telaInicial.fxml'.";

    }

}
