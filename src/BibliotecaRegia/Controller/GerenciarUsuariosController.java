package BibliotecaRegia.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import BibliotecaRegia.Model.DAO.Usuario.UsuarioDAOImpl;
import BibliotecaRegia.Model.Entidade.Usuario;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class GerenciarUsuariosController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cadastrarBotao;

    @FXML
    private Button cancelarBotao;

    @FXML
    private TextField enderecoTextField;

    @FXML
    private Label labelMensagem;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField telefoneTextField;

    @FXML
    private ImageView logoImageView, usuariosImageView;

    @FXML
    private TextField nomeTextField2, idTextField;

    @FXML
    private TextField enderecoTextField2;


    @FXML
    private TextField telefoneTextField2;
    @FXML
    private TextField exibicaoID;








    @FXML
    void botaoCadastrarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String nome = nomeTextField.getText();
        String endereco = enderecoTextField.getText();
        String telefone = telefoneTextField.getText();

        Usuario novoUsuario = new Usuario(nome, endereco, telefone);
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();


        //Verifica se algum campo está vazio
        if (nome.isEmpty() || endereco.isEmpty() || telefone.isEmpty()) {
            this.labelMensagem.setText("Por favor, preencha todos os campos.");
            return;
        }


        List<Usuario> listaUsuarios = usuarioDAO.create(novoUsuario);
        if (listaUsuarios != null) {

            this.exibicaoID.setText(novoUsuario.getId());
            // Exibir dados do usuário nas labels
            this.idTextField.setText(novoUsuario.getId());
            this.nomeTextField2.setText(novoUsuario.getNome());
            this.enderecoTextField2.setText(novoUsuario.getEndereco());
            this.telefoneTextField2.setText(novoUsuario.getTelefone());

            this.labelMensagem.setText("Usuário cadastrado com sucesso!");
        } else {
            this.labelMensagem.setText("Usuário já cadastrado.");
        }


    }

    @FXML
    void botaoCancelarOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)   {
        //carregamento logo
        File logoFile = new File("resources/imagem/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

        /*
        File usuariosFile = new File("resources/imagem/usuarios.png");
        Image usuariosImage = new Image(usuariosFile.toURI().toString());
        usuariosImageView.setImage(usuariosImage);*/


    }

}
