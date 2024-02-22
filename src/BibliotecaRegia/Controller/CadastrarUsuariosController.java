package BibliotecaRegia.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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

public class CadastrarUsuariosController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button botaoCadastrar;

    @FXML
    private Button botaoCancelar;

    @FXML
    private TextField enderecoTextField;

    @FXML
    private Label labelMensagem, exibicaoID;

    @FXML
    private ImageView logoImageView, iconeImageView;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField telefoneTextField;


    /**
     * Método para lidar com o evento de clique no botão "Cadastrar".
     * Este método é chamado quando o botão é clicado.
     *
     * @param event O evento que desencadeou a ação, neste caso, um ActionEvent.
     * @throws IOException Exceção lançada se ocorrer um erro de entrada/saída durante a execução.
     * @throws ClassNotFoundException Exceção lançada se uma classe não puder ser encontrada durante a desserialização.
     */
    @FXML
    void botaoCadastrarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        // Obtém os dados dos campos de entrada
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
            //Exibe o ID gerado
            this.exibicaoID.setText(novoUsuario.getId());
            this.labelMensagem.setText("Usuário cadastrado com sucesso!");

            limparCampos();
        } else {
            this.labelMensagem.setText("Usuário já cadastrado.");
        }
    }

    @FXML
    void botaoCancelarOnAction(ActionEvent event) {
        if (event.getSource() == botaoCancelar) {
            Main.navegacaoEntreTelas("perfilAdministrador");
        }
    }

    /**
     * Método privado para limpar os campos de entrada de texto.
     * Este método limpa os campos nomeTextField, enderecoTextField e telefoneTextField.
     */
    private void limparCampos() {
        this.nomeTextField.clear();
        this.enderecoTextField.clear();
        this.telefoneTextField.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //carregamento logo
        File logoFile = new File("resources/imagem/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

        //carregamento ícone usuário
        File iconeFile = new File("resources/imagem/adicionarUsuario.png");
        Image iconeImage = new Image(iconeFile.toURI().toString());
        iconeImageView.setImage(iconeImage);

    }

}
