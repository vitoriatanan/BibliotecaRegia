package BibliotecaRegia.Controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import BibliotecaRegia.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PerfilAdministradorController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button gerenciarUsuariosBotao;

    @FXML
    private ImageView credencialImageView, livroImageView, pesquisarImageView, sairImageView, perfilImageView;

    @FXML
    void gerenciarAcervoOnAction(ActionEvent event) {

    }

    @FXML
    void pesquisarLivrosOnAction(ActionEvent event) {

    }

    @FXML
    void gerenciarUsuariosOnAction(ActionEvent event) {
        if (event.getSource() == gerenciarUsuariosBotao) {
            Main.navegacaoEntreTelas("gerenciarUsuarios");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //carregamento credencial
        File credencialFile = new File("resources/imagem/credencial.png");
        Image credencialImage = new Image(credencialFile.toURI().toString());
        credencialImageView.setImage(credencialImage);

        //carregamento ícone perfil
        File iconePerfilFile = new File("resources/imagem/usuarioPerfil.png");
        Image iconePerfilImage = new Image(iconePerfilFile.toURI().toString());
        perfilImageView.setImage(iconePerfilImage);

        //carregamento ícone gerenciar acervo
        File iconeLivroFile = new File("resources/imagem/livroWhite.png");
        Image iconeLivroImage = new Image(iconeLivroFile.toURI().toString());
        livroImageView.setImage(iconeLivroImage);

        //carregamento ícone pesquisar livros
        File iconePesquisarFile = new File("resources/imagem/pesquisarWhite.png");
        Image iconePesquisarImage = new Image(iconePesquisarFile.toURI().toString());
        pesquisarImageView.setImage(iconePesquisarImage);

        //carregamento ícone sair
        File iconeSairFile = new File("resources/imagem/sair.png");
        Image iconeSairImage = new Image(iconeSairFile.toURI().toString());
        sairImageView.setImage(iconeSairImage);

    }

}
