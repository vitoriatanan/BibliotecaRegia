package BibliotecaRegia.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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

public class GerenciarUsuariosController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button adicionarBotao;

    @FXML
    private Button atualizarBotao;

    @FXML
    private Button deletarBotao;

    @FXML
    private TextField enderecoTextField;

    @FXML
    private Label exibicaoID, labelMensagem;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField telefoneTextField;

    @FXML
    private ImageView logoImageView, usuariosImageView;


    @FXML
    void botaoAdicionarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
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
            this.labelMensagem.setText("Usuário cadastrado com sucesso!");
        } else {
            this.labelMensagem.setText("Usuário já cadastrado.");
        }
    }

    @FXML
    void botaoAtualizarOnAction(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //carregamento logo
        File logoFile = new File("resources/imagem/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

        //carregamento ícone usuários
        File usuariosFile = new File("resources/imagem/usuarios.png");
        Image usuariosImage = new Image(usuariosFile.toURI().toString());
        usuariosImageView.setImage(usuariosImage);

    }

}
