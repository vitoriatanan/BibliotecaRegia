package BibliotecaRegia.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BibliotecaRegia.Main;
import BibliotecaRegia.Model.DAO.Administrador.AdministradorDAOImpl;
import BibliotecaRegia.Model.DAO.Bibliotecario.BibliotecarioDAOImpl;
import BibliotecaRegia.Model.DAO.Usuario.UsuarioDAOImpl;
import BibliotecaRegia.Model.Entidade.Administrador;
import BibliotecaRegia.Model.Entidade.Bibliotecario;
import BibliotecaRegia.Model.Entidade.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DeletarOperadoresController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelarBotao;

    @FXML
    private Button deletarBotao;

    @FXML
    private ImageView deletarOperadorImageView;

    @FXML
    private Label encontrouUsuario;

    @FXML
    private Label senhaLabel;

    @FXML
    private TextField cargoTextField2;

    @FXML
    private Label idLabel;

    @FXML
    private TextField idTextField2;

    @FXML
    private Label labelMensagem;

    @FXML
    private ImageView logoImageView;

    @FXML
    private ImageView menosImageView;

    @FXML
    private Label nomeLabel;

    @FXML
    private TextField nomeTextField2;

    @FXML
    private Button pesquisarBotao;

    @FXML
    private ImageView pesquisarImageView;

    @FXML
    private TextField pesquisarTextField;

    @FXML
    private Label cargoLabel;

    @FXML
    private TextField senhaTextField2;

    @FXML
    void botaoCancelarOnAction(ActionEvent event) {
        if (event.getSource() == cancelarBotao) {
            Main.navegacaoEntreTelas("perfilAdministrador");
        }
    }

    @FXML
    void botaoDeletarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String id = pesquisarTextField.getText();

        BibliotecarioDAOImpl bibliotecarioDAO = new BibliotecarioDAOImpl();
        Bibliotecario bibliotecario = bibliotecarioDAO.read(id);

        AdministradorDAOImpl administradorDAO = new AdministradorDAOImpl();
        Administrador administrador = administradorDAO.read(id);

        if (bibliotecario != null) {
            bibliotecarioDAO.delete(bibliotecario);
            limparCampos();

            labelMensagem.setText("Bibliotecário Deletado!");



        } else if (administrador != null) {
            administradorDAO.delete(administrador);

            limparCampos();

            labelMensagem.setText("Administrador Deletado!");


        } else {
            encontrouUsuario.setText("Operador Não Encontrado!");
            limparCampos();
        }
    }

    @FXML
    void botaoPesquisarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String id = pesquisarTextField.getText();

        BibliotecarioDAOImpl bibliotecarioDAO = new BibliotecarioDAOImpl();
        Bibliotecario bibliotecario = bibliotecarioDAO.read(id);

        AdministradorDAOImpl administradorDAO = new AdministradorDAOImpl();
        Administrador administrador = administradorDAO.read(id);

        if (bibliotecario != null) {
            encontrouUsuario.setText("Bibliotecário Encontrado!");

            nomeTextField2.setText(bibliotecario.getNome());
            idTextField2.setText(bibliotecario.getId());
            cargoTextField2.setText(bibliotecario.getCargo());
            senhaTextField2.setText(bibliotecario.getSenhaAcesso());

        } else if (administrador != null) {
            encontrouUsuario.setText("Administrador Encontrado!");

            nomeTextField2.setText(administrador.getNome());
            idTextField2.setText(administrador.getId());
            cargoTextField2.setText(administrador.getCargo());
            senhaTextField2.setText(administrador.getSenhaAcesso());
        } else {
            encontrouUsuario.setText("Operador Não Encontrado!");
            limparCampos();
        }
    }

    private void limparCampos() {
        nomeTextField2.clear();
        idTextField2.clear();
        cargoTextField2.clear();
        senhaTextField2.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //carregamento logo
        File logoFile = new File("resources/imagem/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

        //carregamento ícone operador
        File operadorFile = new File("resources/imagem/operador.png");
        Image operadorImage = new Image(operadorFile.toURI().toString());
        deletarOperadorImageView.setImage(operadorImage);

        //carregamento ícone sinal subtração
        File menosFile = new File("resources/imagem/menosPequeno.png");
        Image menosImage = new Image(menosFile.toURI().toString());
        menosImageView.setImage(menosImage);
    }
}