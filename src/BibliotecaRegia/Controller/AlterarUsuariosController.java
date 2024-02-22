package BibliotecaRegia.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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

public class AlterarUsuariosController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button alterarBotao;

    @FXML
    private Button cancelarBotao;

    @FXML
    private Label enderecoLabel;

    @FXML
    private TextField enderecoTextField;

    @FXML
    private TextField enderecoTextField2;

    @FXML
    private Label idLabel, labelMensagem, encontrouUsuario;

    @FXML
    private TextField idTextField2;

    @FXML
    private ImageView logoImageView, pesquisarImageView;

    @FXML
    private Label nomeLabel;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField nomeTextField2;

    @FXML
    private Button pesquisarBotao;

    @FXML
    private TextField pesquisarIDTextField;

    @FXML
    private Label telefoneLabel;

    @FXML
    private TextField telefoneTextField;

    @FXML
    private TextField telefoneTextField2;

    @FXML
    void botaoPesquisarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String id = pesquisarIDTextField.getText();

        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        Usuario usuario = usuarioDAO.read(id);

        if (usuario != null) {
            encontrouUsuario.setText("Usuário Encontrado!");

            nomeTextField2.setText(usuario.getNome());
            idTextField2.setText(usuario.getId());
            enderecoTextField2.setText(usuario.getEndereco());
            telefoneTextField2.setText(usuario.getTelefone());

        } else {
            encontrouUsuario.setText("Usuário Não Encontrado!");
            limparTextField2();
        }
    }

    @FXML
    void botaoAlterarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String id = pesquisarIDTextField.getText();

        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        Usuario usuario = usuarioDAO.read(id);

        if (usuario != null) {
            String novoNome = nomeTextField.getText();
            String novoEndereco = enderecoTextField.getText();
            String novoTelelfone = telefoneTextField.getText();

            if (!novoNome.isEmpty()) {
                usuario.setNome(novoNome);
                nomeTextField2.setText(novoNome);
            }
            if (!novoEndereco.isEmpty()) {
                usuario.setEndereco(novoEndereco);
                enderecoTextField2.setText(novoEndereco);
            }
            if (!novoTelelfone.isEmpty()) {
                usuario.setTelefone(novoTelelfone);
                telefoneTextField2.setText(novoTelelfone);
            }

            usuarioDAO.update(id, usuario);

            limparCampos();

            labelMensagem.setText("Usuário atualizado com sucesso!");

        } else {
            labelMensagem.setText("Usuário não encontrado.");
        }
    }

    @FXML
    void botaoCancelarOnAction(ActionEvent event) {
        if (event.getSource() == cancelarBotao) {
            Main.navegacaoEntreTelas("perfilAdministrador");
        }

    }
    private void limparCampos() {
        this.nomeTextField.clear();
        this.enderecoTextField.clear();
        this.telefoneTextField.clear();
    }

    private void limparTextField2() {
        nomeTextField2.clear();
        idTextField2.clear();
        enderecoTextField2.clear();
        telefoneTextField2.clear();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //carregamento logo
        File logoFile = new File("resources/imagem/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

        //carregamento icone pesquisa
        File iconeFile = new File("resources/imagem/pesquisarWhite.png");
        Image iconeImage = new Image(iconeFile.toURI().toString());
        pesquisarImageView.setImage(iconeImage);
    }

}
