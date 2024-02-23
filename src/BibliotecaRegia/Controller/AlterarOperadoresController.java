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

public class AlterarOperadoresController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button alterarBotao;

    @FXML
    private Button cancelarBotao;

    @FXML
    private TextField cargoTextField;

    @FXML
    private Label encontrouUsuario;

    @FXML
    private Label enderecoLabel;

    @FXML
    private Label idLabel;

    @FXML
    private TextField idTextField2;

    @FXML
    private Label labelMensagem;

    @FXML
    private ImageView logoImageView;

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
    private ImageView pesquisarImageView;

    @FXML
    private TextField senhaTextField;

    @FXML
    private TextField senhaTextField2;

    @FXML
    private Label telefoneLabel;

    @FXML
    void botaoAlterarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String id = pesquisarIDTextField.getText();

        BibliotecarioDAOImpl bibliotecarioDAO = new BibliotecarioDAOImpl();
        Bibliotecario bibliotecario = bibliotecarioDAO.read(id);

        AdministradorDAOImpl administradorDAO = new AdministradorDAOImpl();
        Administrador administrador = administradorDAO.read(id);

        if (bibliotecario != null) {
            String novoNome = nomeTextField.getText();
            String novaSenha = senhaTextField.getText();

            if (!novoNome.isEmpty()) {
                bibliotecario.setNome(novoNome);
                nomeTextField2.setText(novoNome);
            }
            if (!novaSenha.isEmpty()) {
                bibliotecario.setSenhaAcesso(novaSenha);
                senhaTextField2.setText(novaSenha);
            }

            bibliotecarioDAO.update(id, bibliotecario);

            limparCampos();

            labelMensagem.setText("Bibliotecário atualizado com sucesso!");

        } else if (administrador != null){
            String novoNome = nomeTextField.getText();
            String novaSenha = senhaTextField.getText();

            if (!novoNome.isEmpty()) {
                administrador.setNome(novoNome);
                nomeTextField2.setText(novoNome);
            }
            if (!novaSenha.isEmpty()) {
                administrador.setSenhaAcesso(novaSenha);
                senhaTextField2.setText(novaSenha);
            }

            administradorDAO.update(id, administrador);

            limparCampos();

            labelMensagem.setText("Administrador atualizado com sucesso!");
        } else {
            labelMensagem.setText("Operador não encontrado.");

        }


    }

    @FXML
    void botaoCancelarOnAction(ActionEvent event) {
        if (event.getSource() == cancelarBotao) {
            Main.navegacaoEntreTelas("perfilAdministrador");
        }
    }

    @FXML
    void botaoPesquisarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String id = pesquisarIDTextField.getText();

        BibliotecarioDAOImpl bibliotecarioDAO = new BibliotecarioDAOImpl();
        Bibliotecario bibliotecario = bibliotecarioDAO.read(id);

        AdministradorDAOImpl administradorDAO = new AdministradorDAOImpl();
        Administrador administrador = administradorDAO.read(id);

        if (bibliotecario != null) {
            labelMensagem.setText("Bibliotecário Encontrado.");

            nomeTextField2.setText(bibliotecario.getNome());
            idTextField2.setText(bibliotecario.getId());
            cargoTextField.setText(bibliotecario.getCargo());
            senhaTextField2.setText(bibliotecario.getSenhaAcesso());
        } else if (administrador != null) {
            labelMensagem.setText("Administrador Encontrado.");
            nomeTextField2.setText(administrador.getNome());
            idTextField2.setText(administrador.getId());
            cargoTextField.setText(administrador.getCargo());
            senhaTextField2.setText(administrador.getSenhaAcesso());
        } else {
            labelMensagem.setText("Operador Não Encontrado.");
            limparTextField2();
        }

    }

    private void limparCampos() {
        this.nomeTextField.clear();
        this.senhaTextField.clear();
    }

    private void limparTextField2() {
        nomeTextField2.clear();
        idTextField2.clear();
        senhaTextField2.clear();

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
