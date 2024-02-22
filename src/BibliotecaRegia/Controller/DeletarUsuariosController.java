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

public class DeletarUsuariosController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelarBotao;

    @FXML
    private Button deletarBotao;

    @FXML
    private Label enderecoLabel;

    @FXML
    private TextField enderecoTextField;

    @FXML
    private TextField enderecoTextField2;

    @FXML
    private Label idLabel;

    @FXML
    private TextField idTextField2;

    @FXML
    private ImageView logoImageView, pesquisarImageView, deletarUsuarioImageView;

    @FXML
    private Label nomeLabel, encontrouUsuario, labelMensagem;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField nomeTextField2;

    @FXML
    private Button pesquisarBotao;

    @FXML
    private TextField pesquisarTextField;

    @FXML
    private Label telefoneLabel;

    @FXML
    private TextField telefoneTextField;

    @FXML
    private TextField telefoneTextField2;


    @FXML
    void botaoCancelarOnAction(ActionEvent event) {
        if (event.getSource() == cancelarBotao) {
            Main.navegacaoEntreTelas("perfilAdministrador");
        }
    }

    @FXML
    void botaoDeletarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String id = pesquisarTextField.getText();

        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        Usuario usuario = usuarioDAO.read(id);

        if (usuario != null) {
            // Deleta o usuário
            usuarioDAO.delete(usuario);

            // Limpa os campos de texto
            nomeTextField2.setText("");
            idTextField2.setText("");
            enderecoTextField2.setText("");
            telefoneTextField2.setText("");

            labelMensagem.setText("Usuário Deletado!");
        } else {
            encontrouUsuario.setText("Usuário Não Encontrado!");
        }
    }

    @FXML
    void botaoPesquisarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String id = pesquisarTextField.getText();

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
            limparCampos();
        }
    }

    private void limparCampos() {
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

        //carregamento ícone pesquisar
        File pesquisarFile = new File("resources/imagem/pesquisarWhite.png");
        Image pesquisarImage = new Image(pesquisarFile.toURI().toString());
        pesquisarImageView.setImage(pesquisarImage);

        //carregamento ícone deletar usuários
        File deletarUsuarioFile = new File("resources/imagem/removerUsuario.png");
        Image deletarUsuario = new Image(deletarUsuarioFile.toURI().toString());
        deletarUsuarioImageView.setImage(deletarUsuario);


    }

}
