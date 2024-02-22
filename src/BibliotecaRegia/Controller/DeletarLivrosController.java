package BibliotecaRegia.Controller;

import BibliotecaRegia.Main;
import BibliotecaRegia.Model.DAO.Livro.LivroDAOImpl;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeletarLivrosController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label ISBNTextField;

    @FXML
    private Label TituloTextField;

    @FXML
    private Label anoTextField;

    @FXML
    private Label autorTextField;

    @FXML
    private Button cancelarBotao;

    @FXML
    private Label categoriaTextField;

    @FXML
    private Button deletarBotao;

    @FXML
    private ImageView deletarLivroImageView;

    @FXML
    private Label editoraTextField;


    @FXML
    private Label labelMensagem;


    @FXML
    private ImageView logoImageView;

    @FXML
    private Button pesquisarBotao;

    @FXML
    private ImageView pesquisarImageView;

    @FXML
    private TextField pesquisarTextField;

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
    void botaoCancelarOnAction(ActionEvent event) {
        if (event.getSource() == cancelarBotao){
            Main.navegacaoEntreTelas("perfilAdministrador");
        }

    }

    @FXML
    void botaoDeletarOnAction(ActionEvent event) throws IOException, ClassNotFoundException{
        String pesquisar = pesquisarTextField.getText();

        LivroDAOImpl livroDAO = new LivroDAOImpl();
        Livro livro = livroDAO.read(pesquisar);

        if (livro != null) {
            // Deleta o usuário
            livroDAO.delete(livro);

            // Limpa os campos de texto
            resultadoTitulo.setText("");
            resultadoISBN.setText("");
            resultadoEditora.setText("");
            resultadoCategoria.setText("");
            resultadoAno.setText("");
            resultadoAutor.setText("");

            labelMensagem.setText("Livro deletado!");
        } else {
            labelMensagem.setText("Livro não encontrado!");

        }
    }



    @FXML
    void botaoPesquisarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String pesquisar = pesquisarTextField.getText();

        LivroDAOImpl livroDAO = new LivroDAOImpl();
        Livro livro = livroDAO.read(pesquisar);

        if (livro != null) {
            labelMensagem.setText("Livro encontrado!");

            resultadoTitulo.setText(livro.getTitulo());
            resultadoISBN.setText(livro.getIsbn());
            resultadoEditora.setText(livro.getEditora());
            resultadoCategoria.setText(livro.getCategoria());
            resultadoAno.setText(livro.getAnoPublicacao());
            resultadoAutor.setText(livro.getAutor());

        } else {
            labelMensagem.setText("Livro não encontrado!");
            limparCampos();
        }
    }

    private void limparCampos() {
        resultadoTitulo.clear();
        resultadoISBN.clear();
        resultadoEditora.clear();
        resultadoCategoria.clear();
        resultadoAno.clear();
        resultadoAutor.clear();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //carregamento logo
        File logoFile = new File("resources/imagem/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

        //carregamento ícone pesquisar
        File pesquisarFile = new File("resources/imagem/pesquisarWhite.png");
        Image pesquisarImage = new Image(pesquisarFile.toURI().toString());
        pesquisarImageView.setImage(pesquisarImage);

        //carregamento ícone deletar usuários
        File deletarUsuarioFile = new File("resources/imagem/removerUsuario.png");
        Image deletarUsuario = new Image(deletarUsuarioFile.toURI().toString());
        deletarLivroImageView.setImage(deletarUsuario);


    }

}





