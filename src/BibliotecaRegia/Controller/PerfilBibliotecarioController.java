package BibliotecaRegia.Controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import BibliotecaRegia.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
public class PerfilBibliotecarioController implements Initializable {

    @FXML
    private MenuItem alterarLivros;

    @FXML
    private Button botaoSair;

    @FXML
    private MenuItem cadastrarLivros;

    @FXML
    private ImageView credencialImageView;

    @FXML
    private MenuItem deletarLivros;

    @FXML
    private Button devolucao;

    @FXML
    private Button emprestimos;

    @FXML
    private MenuButton gerenciarAcervo;

    @FXML
    private ImageView livroImageView;

    @FXML
    private ImageView perfilImageView;

    @FXML
    private ImageView pesquisarImageView;

    @FXML
    private Button pesquisarLivros;

    @FXML
    private ImageView sairImageView;

    @FXML
    void CadastrarLivrosOnAction(ActionEvent event) {
        Main.navegacaoEntreTelas("cadastrarLivros");

    }

    @FXML
    void alterarLivrosOnAction(ActionEvent event) {
        Main.navegacaoEntreTelas("alterarLivros");

    }
    @FXML
    void deletarLivrosOnAction(ActionEvent event) {
        Main.navegacaoEntreTelas("deletarLivros");

    }

    @FXML
    void devolucaoOnAction(ActionEvent event) {
        Main.navegacaoEntreTelas("devolucoesLivros");

    }

    @FXML
    void emprestimoOnAction(ActionEvent event) {
        Main.navegacaoEntreTelas("registrarEmprestimos");

    }

    @FXML
    void pesquisarOnAction(ActionEvent event) {
        Main.navegacaoEntreTelas("telaInicial");

    }




    @FXML
    void bttSair(ActionEvent event) {
        if (event.getSource() == botaoSair) {
            Main.navegacaoEntreTelas("login");

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
