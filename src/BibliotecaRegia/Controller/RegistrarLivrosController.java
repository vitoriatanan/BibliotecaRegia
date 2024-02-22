package BibliotecaRegia.Controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import BibliotecaRegia.Main;
import BibliotecaRegia.Model.DAO.Livro.LivroDAOImpl;
import BibliotecaRegia.Model.Entidade.Livro;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class RegistrarLivrosController implements Initializable {

    @FXML
    private TextField AutorTextField;

    @FXML
    private TextField EditoraTextField;

    @FXML
    private TextField ISBNtexteField;

    @FXML
    private ImageView ImagemLivros;

    @FXML
    private TextField anoTextField;

    @FXML
    private Button bouttonAdicionar;

    @FXML
    private TextField categoriaTextFiel;

    @FXML
    private ImageView logoImage;

    @FXML
    private Label logoImageView;

    @FXML
    private TextField tituloTextField;

    @FXML
    private Label labelMensagem;

    @FXML
    private Button buttonVoltar;

    @FXML
    private ImageView ImagemVoltar;

    @FXML
    private Button buttonAtualizar;

    @FXML
    private Button buttonCancelar;




    @FXML
    void BttCancelar(ActionEvent event) {
            if (event.getSource() == buttonCancelar){
                Main.navegacaoEntreTelas("perfilAdministrador");
            }

    }



    @FXML
    void bttVoltarOnAction(ActionEvent event) {
        if (event.getSource() == buttonVoltar){
            Main.navegacaoEntreTelas("perfilAdministrador");
        }

    }


    @FXML
    void BttAdicionar(ActionEvent event) throws IOException, ClassNotFoundException {

        String titulo = tituloTextField.getText();
        String ISBN = ISBNtexteField.getText();
        String editora = EditoraTextField.getText();
        String categoria = categoriaTextFiel.getText();
        String ano = anoTextField.getText();
        String autor = AutorTextField.getText();


        Livro novoLivro = new Livro(titulo, autor, editora, ISBN, ano, categoria);
        LivroDAOImpl LivroDAO = new LivroDAOImpl();


        //Verifica se algum campo está vazio
        if (titulo.isEmpty() || ISBN.isEmpty() || editora.isEmpty() || categoria.isEmpty() || ano.isEmpty() || autor.isEmpty()) {
            this.labelMensagem.setText("Por favor, preencha todos os campos.");
            return;
        }

        LivroDAOImpl livroDAO = new LivroDAOImpl();
        List<Livro> listaLivro = livroDAO.create(novoLivro);
        if (listaLivro != null) {
            this.labelMensagem.setText("Livro cadastrado");



            limparCampos();
        } else {
            this.labelMensagem.setText("Livro já cadastrado.");
        }
    }




    private void limparCampos() {
        this.tituloTextField.clear();
        this.ISBNtexteField.clear();
        this.AutorTextField.clear();
        this.categoriaTextFiel.clear();
        this.anoTextField.clear();
        this.EditoraTextField.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //carregamento logo
        File logoFile = new File("resources/imagem/logo.png");
        Image logoImageView = new Image(logoFile.toURI().toString());
        logoImage.setImage(logoImageView);
        //carregamento ícone usuario
        File fileLivros = new File("resources/imagem/registrarLivros.png");
        Image iconeLivros = new Image(fileLivros.toURI().toString());
        ImagemLivros.setImage(iconeLivros);
        //Icone de Voltar
        File voltarFile = new File("resources/imagem/voltar.png");
        Image iconeVoltar = new Image(voltarFile.toURI().toString());
        ImagemVoltar.setImage(iconeVoltar);


    }
}








