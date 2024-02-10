package BibliotecaRegia.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastrarOperadorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cadastrarBotao;

    @FXML
    private MenuButton cargoMenu;

    @FXML
    private TextField nomeText;

    @FXML
    private PasswordField senhaText;

    @FXML
    void botaoCadastrarOnAction(ActionEvent event) {
        //Bibliotecario novoBibliotecario = new Bibliotecario(nomeText.getText(), cargoMenu.get)
    }

    @FXML
    void initialize() {
        assert cadastrarBotao != null : "fx:id=\"cadastrarBotao\" was not injected: check your FXML file 'cadastrarOperador.fxml'.";
        assert cargoMenu != null : "fx:id=\"cargoMenu\" was not injected: check your FXML file 'cadastrarOperador.fxml'.";
        assert nomeText != null : "fx:id=\"nomeText\" was not injected: check your FXML file 'cadastrarOperador.fxml'.";
        assert senhaText != null : "fx:id=\"senhaText\" was not injected: check your FXML file 'cadastrarOperador.fxml'.";

    }

}
