package BibliotecaRegia.Controller;

import BibliotecaRegia.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    @FXML
    private Button cadastrarButton;

    @FXML
    private Button entrarButton;

    @FXML
    private Label loginMensagemLabel;

    @FXML
    private ImageView marcaImageView;

    @FXML
    private PasswordField senhaPasswordField;

    @FXML
    private TextField usuarioTextField;

    @FXML
    private void bttCadastrarOnAction(ActionEvent event) {
        if (event.getSource() == cadastrarButton) {
            Main.navegacaoEntreTelas("opcaoDeCadastro");
        }

    }

    @FXML
    void bttEntrarOnAction(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {

        File logoFile = new File("resources/imagem/regia_login.jpg");
        Image marcaImage = new Image(logoFile.toURI().toString());
        marcaImageView.setImage(marcaImage);
    }

}
