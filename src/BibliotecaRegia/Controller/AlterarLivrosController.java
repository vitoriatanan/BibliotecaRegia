package BibliotecaRegia.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import BibliotecaRegia.Main;
import BibliotecaRegia.Model.DAO.Livro.LivroDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import BibliotecaRegia.Model.Entidade.Livro;
import javafx.scene.image.ImageView;

public class AlterarLivrosController implements Initializable {

    @FXML
    private ImageView logoImageView;

    @FXML
    private ImageView pesquisarImageView;

    @FXML
    private TextField pesquisarTextField;

    @FXML
    private Button buttonPesquisar;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ISBNTextField;

    @FXML
    private Button alterarLivro;

    @FXML
    private TextField anoTextField;

    @FXML
    private TextField autorTextField;

    @FXML
    private TextField editoraTextField;

    @FXML
    private Button cancelarAlterar;

    @FXML
    private TextField categoriaTextField;

    @FXML
    private Label labelAno;

    @FXML
    private Label labelAutor;

    @FXML
    private Label labelCategoria;

    @FXML
    private Label labelEditora;

    @FXML
    private Label labelISBN;

    @FXML
    private Label labelTitulo;

    @FXML
    private TextField resultadoAno;

    @FXML
    private TextField resultadoAutor;

    @FXML
    private TextField resultadoCategoria;

    @FXML
    private TextField resultadoEditora;

    @FXML
    private TextField resultadoISBN;

    @FXML
    private TextField resultadoTitulo;

    @FXML
    private TextField tituloTextField;

    @FXML
    private Label labelMensagem;

    @FXML
    void BttPesquisarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String pesquisa = pesquisarTextField.getText();

        LivroDAOImpl livroDAO = new LivroDAOImpl();
        Livro livro = livroDAO.read(pesquisa);

        if (livro != null) {
            labelMensagem.setText("Livro Encontrado!");

            resultadoTitulo.setText(livro.getTitulo());
            resultadoISBN.setText(livro.getIsbn());
            resultadoEditora.setText(livro.getEditora());
            resultadoCategoria.setText(livro.getCategoria());
            resultadoAno.setText(livro.getAnoPublicacao());
            resultadoAutor.setText(livro.getAutor());

        } else {
            labelMensagem.setText("Livro não Encontrado!");
            limparResultados();

        }

    }

    @FXML
    void BttAlterarLivroOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String pesquisar = resultadoTitulo.getText();

        LivroDAOImpl livroDAO = new LivroDAOImpl();
        Livro livro = livroDAO.read(pesquisar);

        if (livro!= null) {
            String novoTitulo = tituloTextField.getText();
            String novoISBN = ISBNTextField.getText();
            String novoEditora = editoraTextField.getText();
            String novoCategoria = categoriaTextField.getText();
            String novoAno = anoTextField.getText();
            String novoAutor = autorTextField.getText();

            if (!novoTitulo.isEmpty()) {
                livro.setTitulo(novoTitulo);
                resultadoTitulo.setText(novoTitulo);
            }
            if (!novoISBN.isEmpty()) {
                livro.setIsbn(novoISBN);
                resultadoISBN.setText(novoISBN);
            }
            if (!novoEditora.isEmpty()){
                livro.setEditora(novoEditora);
                resultadoEditora.setText(novoEditora);
            }
            if (!novoEditora.isEmpty()) {
                livro.setCategoria(novoCategoria);
                resultadoCategoria.setText(novoCategoria);
            }
            if (!novoAno.isEmpty()) {
                livro.setAnoPublicacao(novoAno);
                resultadoAno.setText(novoAno);
            }
            if (!novoAutor.isEmpty()) {
                livro.setAutor(novoAutor);
                resultadoAutor.setText(novoAutor);
            }


            labelMensagem.setText("Livro atualizado com sucesso!");

        } else {
            labelMensagem.setText("Livro não encontrado.");
        }
        livroDAO.update(pesquisar, livro);
        limparCampos();

    }

    private void limparCampos() {
        this.tituloTextField.clear();
        this.ISBNTextField.clear();
        this.editoraTextField.clear();
        this.categoriaTextField.clear();
        this.anoTextField.clear();
        this.autorTextField.clear();

    }

    private void limparResultados() {
        resultadoTitulo.clear();
        resultadoISBN.clear();
        resultadoEditora.clear();
        resultadoCategoria.clear();
        resultadoAno.clear();
        resultadoAutor.clear();


    }




    @FXML
    void BttCancelarOnAction(ActionEvent event) {
        if (event.getSource() == cancelarAlterar);
        Main.navegacaoEntreTelas("login");

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //carregamento logo
        File logoFile = new File("resources/imagem/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

        //carregamento icone pesquisa
        File iconeFile = new File("resources/imagem/pesquisar.png");
        Image iconeImage = new Image(iconeFile.toURI().toString());
        pesquisarImageView.setImage(iconeImage);
    }

}