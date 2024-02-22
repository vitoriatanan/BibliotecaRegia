package BibliotecaRegia.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BibliotecaRegia.Main;
import BibliotecaRegia.Model.DAO.Usuario.UsuarioDAOImpl;
import BibliotecaRegia.Model.Entidade.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MultaController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button botaoVoltar;

    @FXML
    private Label encontrouUsuario;

    @FXML
    private Label labelMensagem;

    @FXML
    private ImageView logoImageView;

    @FXML
    private Button pesquisarBotao;

    @FXML
    private TextField pesquisarIDTextField, atrasoTextField, multaTextField;

    @FXML
    private ImageView pesquisarImageView, iconeImageView, usuarioImageView;

    @FXML
    void botaoPesquisarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String id = pesquisarIDTextField.getText();

        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        Usuario usuario = usuarioDAO.read(id);

        if (usuario != null) {
            encontrouUsuario.setText("Usuário Encontrado");

            //convertendo de long para String
           long diasAtrasoLong = usuario.getDiasAtraso();
           String diasAtraso = String.valueOf(diasAtrasoLong);

           //convertendo de double para String
           double diasMultaDouble = usuario.getMulta();
           String diasMulta = String.valueOf(diasMultaDouble);

           atrasoTextField.setText(diasAtraso);
           multaTextField.setText(diasMulta);
        } else {
            encontrouUsuario.setText("Usuário não encontrado");
        }
    }

    @FXML
    void botaoVoltarOnAction(ActionEvent event) {
        if (event.getSource() == botaoVoltar) {
            Main.navegacaoEntreTelas("perfilAdministrador");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //carregamento logo
        File logoFile = new File("resources/imagem/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

        //carregamento ícone usuário
        File iconeUsuario = new File("resources/imagem/usuarioMulta.png");
        Image usuarioImage = new Image(iconeUsuario.toURI().toString());
        usuarioImageView.setImage(usuarioImage);

        //carregamento ícone banimento
        File iconeFile = new File("resources/imagem/banimento.png");
        Image iconeImage = new Image(iconeFile.toURI().toString());
        iconeImageView.setImage(iconeImage);

        //carregamento ícone de pesquisa
        File iconePesquisa = new File("resources/imagem/pesquisarWhite.png");
        Image pesquisaImage = new Image(iconePesquisa.toURI().toString());
        pesquisarImageView.setImage(pesquisaImage);
    }
}
