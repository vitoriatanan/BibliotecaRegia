package BibliotecaRegia.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BibliotecaRegia.Main;
import BibliotecaRegia.Model.DAO.Usuario.UsuarioDAOImpl;
import BibliotecaRegia.Model.Entidade.Livro;
import BibliotecaRegia.Model.Entidade.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import BibliotecaRegia.Model.DAO.Livro.LivroDAOImpl;
import java.util.List;


public class TelaInicialController implements Initializable {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ISBNPesquisa;

    @FXML
    private ListView<?> ListLivros;

    @FXML
    private ImageView LupaPesquisar;

    @FXML
    private Button Onclick;

    @FXML
    private TextField PesquisarLivros;

    @FXML
    private TextField anoPesquisa;

    @FXML
    private TextField autorPesquisa;

    @FXML
    private Button buttonPesquisar;

    @FXML
    private TextField categoriaPesquisa;

    @FXML
    private TextField editoraPesquisa;

    @FXML
    private Label loginMensagemLabel;

    @FXML
    private ImageView logo;

    @FXML
    private ImageView mudarDeTela;

    @FXML
    private TextField tituloPesquisa;

    @FXML
    private Label labelMensagem;


    @FXML
    void ListOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String pesquisar = PesquisarLivros.getText();

        LivroDAOImpl livroDAO = new LivroDAOImpl();
        Livro livro = livroDAO.read(pesquisar);

        if (livro != null) {
            labelMensagem.setText("Livro encontrado!");

            tituloPesquisa.setText(livro.getTitulo());
            ISBNPesquisa.setText(livro.getIsbn());
            editoraPesquisa.setText(livro.getEditora());
            categoriaPesquisa.setText(livro.getCategoria());
            anoPesquisa.setText(livro.getAnoPublicacao());
            autorPesquisa.setText(livro.getAutor());

        } else {
            labelMensagem.setText("Livro não encontrado!");
            limparCampos();
        }
    }

    private void limparCampos() {
        this.tituloPesquisa.clear();
        this.ISBNPesquisa.clear();
        this.editoraPesquisa.clear();
        this.categoriaPesquisa.clear();
        this.anoPesquisa.clear();
        this.autorPesquisa.clear();
    }

    @FXML
    void bttmudarParaLogin(ActionEvent event) {
        if (event.getSource() == Onclick) {

            Main.navegacaoEntreTelas("login");

        }

    }








    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //carregamento ícone usuario
        File iconemudarDeTela = new File("resources/imagem/usuario.png");
        Image mudarImage = new Image(iconemudarDeTela.toURI().toString());
        mudarDeTela.setImage(mudarImage);

        //carregamento logo
        File logoFile = new File("resources/imagem/logo.png");
        Image marcaImage = new Image(logoFile.toURI().toString());
        logo.setImage(marcaImage);

        //carregamento pesquisa
        File lupaFile = new File("resources/imagem/pesquisarWhite.png");
        Image pesquisarImage = new Image(lupaFile.toURI().toString());
        LupaPesquisar.setImage(pesquisarImage);

    }
}

