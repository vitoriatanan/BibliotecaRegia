package BibliotecaRegia.Controller;

import BibliotecaRegia.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class OpcaoCadastroController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cadastrarOperadorButton;

    @FXML
    private Button cadastrarUsuarioButton;

    @FXML
    private ImageView credencialOperador;

    @FXML
    private ImageView credencialUsuario;

    @FXML
    private ImageView logo;

    @FXML
    void cadastrarOperadorOnAction(ActionEvent event) {
        if (event.getSource() == cadastrarOperadorButton) {
            Main.navegacaoEntreTelas("cadastrarOperador");
        }

    }

    @FXML
    void cadastrarUsuarioOnAction(ActionEvent event) {
        if (event.getSource() == cadastrarUsuarioButton) {
            Main.navegacaoEntreTelas("cadastrarUsuario");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Carrega a imagem da logo
         File logoFile = new File("imagens/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logo.setImage(logoImage);

        // Carrega a imagem da credencial do usuário
        File credencialUsuarioFile = new File("imagens/credencialCadastrar.png");
        Image credencialUsuarioImage = new Image(credencialUsuarioFile.toURI().toString());
        credencialUsuario.setImage(credencialUsuarioImage);

        // Carrega a imagem da credencial do operador
        File credencialOperadorFile = new File("imagens/credencialCadastrar.png");
        Image credencialOperadorImage = new Image(credencialOperadorFile.toURI().toString());
        credencialOperador.setImage(credencialOperadorImage);

    }

}
