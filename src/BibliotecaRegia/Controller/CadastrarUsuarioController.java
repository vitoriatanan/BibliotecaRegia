package BibliotecaRegia.Controller;

import BibliotecaRegia.Model.Entidade.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastrarUsuarioController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button adicionarBotao;

    @FXML
    private TextField enderecoText;

    @FXML
    private Label labelError;

    @FXML
    private TextField nomeText;

    @FXML
    private PasswordField senhaText;

    @FXML
    private TextField telefoneText;

    @FXML
    void bttCadastrarOnAction(ActionEvent event) { //createDAO do Usuario
       try {
           Usuario novoUsuario = new Usuario(nomeText.getText(), enderecoText.getText(), telefoneText.getText());
           System.out.println(novoUsuario.getNome());

           this.clearAll();
       }catch (Exception e) {
           this.labelError.setText("Campos inválidos!");
       }
    }

    @FXML
    void nome(ActionEvent event) {

    }

    private void clearAll() {
        this.nomeText.clear();
        this.enderecoText.clear();
        this.telefoneText.clear();
        this.senhaText.clear();
    }

    @FXML
    void initialize() {
        assert adicionarBotao != null : "fx:id=\"adicionarBotao\" was not injected: check your FXML file 'cadastrarUsuario.fxml'.";
        assert enderecoText != null : "fx:id=\"enderecoText\" was not injected: check your FXML file 'cadastrarUsuario.fxml'.";
        assert nomeText != null : "fx:id=\"nomeText\" was not injected: check your FXML file 'cadastrarUsuario.fxml'.";
        assert senhaText != null : "fx:id=\"senhaText\" was not injected: check your FXML file 'cadastrarUsuario.fxml'.";
        assert telefoneText != null : "fx:id=\"telefoneText\" was not injected: check your FXML file 'cadastrarUsuario.fxml'.";

    }

}
