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
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private ImageView sairImageView, leitorWhite, devolucaoWhite;

    @FXML
    private MenuItem registrarEmprestimoMenuItem, renovarEmprestimoMenuItem, deletarEmprestimoMenuItem;

    @FXML
    void CadastrarLivrosOnAction(ActionEvent event) {
        if (event.getSource() == cadastrarLivros ) {
            Main.navegacaoEntreTelas("registrarLivro");
        }
    }


    @FXML
    void alterarLivrosOnAction(ActionEvent event) {
        if (event.getSource() == alterarLivros) {
            Main.navegacaoEntreTelas("alterarLivros");
        }
    }

    @FXML
    void deletarLivrosOnAction(ActionEvent event) {
        if (event.getSource() == deletarLivros) {
            Main.navegacaoEntreTelas("deletarLivros");
        }
    }

    @FXML
    void devolucaoOnAction(ActionEvent event) {
        if (event.getSource() == devolucao) {
            Main.navegacaoEntreTelas("devolucoesLivros");
        }
    }

    @FXML
    void menuDeletarEmprestimoOnAction(ActionEvent event) {
        if (event.getSource() == deletarEmprestimoMenuItem) {
            Main.navegacaoEntreTelas("deletarEmprestimo");
        }
    }

    @FXML
    void menuRegistrarEmprestimoOnAction(ActionEvent event) {
        if (event.getSource() == registrarEmprestimoMenuItem) {
            Main.navegacaoEntreTelas("registrarEmprestimo");
        }
    }

    @FXML
    void menuRenovarEmprestimoOnAction(ActionEvent event) {
        if (event.getSource() == renovarEmprestimoMenuItem) {
            Main.navegacaoEntreTelas("login");
        }
    }

    @FXML
    void pesquisarOnAction(ActionEvent event) {
        if (event.getSource() == pesquisarLivros) {
            Main.navegacaoEntreTelas("telaInicial");
        }
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
        File iconePerfilFile = new File("resources/imagem/perfil.png");
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

        //carregamento icone emprestimo

        //carregamento icone devolução
        File devolucaoFile = new File("resources/imagem/devolucaoWhite.png");
        Image devolucaoImage = new Image(devolucaoFile.toURI().toString());
        devolucaoWhite.setImage(devolucaoImage);

        //carregamento icone leitor
        File leitorFile = new File("resources/imagem/leitorWhite.png");
        Image leitorImage = new Image(leitorFile.toURI().toString());
        leitorWhite.setImage(leitorImage);

    }

}
