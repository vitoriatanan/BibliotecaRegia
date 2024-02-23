package BibliotecaRegia.Controller;

import BibliotecaRegia.Main;
import BibliotecaRegia.Model.DAO.Administrador.AdministradorDAOImpl;
import BibliotecaRegia.Model.DAO.Bibliotecario.BibliotecarioDAOImpl;
import BibliotecaRegia.Model.Entidade.Administrador;
import BibliotecaRegia.Model.Entidade.Bibliotecario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    @FXML
    private Button cancelarButton;

    @FXML
    private Button entrarButton;

    @FXML
    private Label erroLabel;

    @FXML
    private ImageView marcaImageView;

    @FXML
    private ImageView usuarioImageView;

    @FXML
    private ImageView cadeadoImageView;

    @FXML
    private PasswordField senhaPasswordField;

    @FXML
    private TextField idTextField;

    @FXML
    private void bttCancelarOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }

    /*
     * Valida a entrada de dados nas caixas de texto.
     * Faz a busca do id e senha do operador, se estiver
     * correto, troca de tela, caso contrário exibe uma
     * mensagem de erro.
     * */
    @FXML
    void bttEntrarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        if (event.getSource() == entrarButton) {
            String id = idTextField.getText();
            String senha = senhaPasswordField.getText();

            BibliotecarioDAOImpl bibliotecarioDAO = new BibliotecarioDAOImpl();
            Bibliotecario bibliotecario = bibliotecarioDAO.read(id);

            AdministradorDAOImpl administradorDAO = new AdministradorDAOImpl();
            Administrador administrador = administradorDAO.read(id);

            if (bibliotecario != null && bibliotecario.getSenhaAcesso().equals(senha)) {
                Main.navegacaoEntreTelas("perfilBibliotecario");
            } else if (administrador != null && administrador.getSenhaAcesso().equals(senha)) {
                Main.navegacaoEntreTelas("perfilAdministrador");
            } else {
                this.erroLabel.setText("ID ou senha incorretos");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        //carregamento logo
        File logoFile = new File("resources/imagem/regia_login.jpg");
        Image marcaImage = new Image(logoFile.toURI().toString());
        marcaImageView.setImage(marcaImage);

        //carregamento ícone usuario
        File iconeUsuario = new File("resources/imagem/usuario.png");
        Image usuarioImage = new Image(iconeUsuario.toURI().toString());
        usuarioImageView.setImage(usuarioImage);

        //carregamento ícone cadeado
        File iconeCadeado = new File("resources/imagem/cadeado.png");
        Image cadeadoImage = new Image(iconeCadeado.toURI().toString());
        cadeadoImageView.setImage(cadeadoImage);
    }

}
