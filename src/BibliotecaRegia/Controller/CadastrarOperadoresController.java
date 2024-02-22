package BibliotecaRegia.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import BibliotecaRegia.Main;
import BibliotecaRegia.Model.DAO.Administrador.AdministradorDAOImpl;
import BibliotecaRegia.Model.DAO.Bibliotecario.BibliotecarioDAOImpl;
import BibliotecaRegia.Model.DAO.Usuario.UsuarioDAOImpl;
import BibliotecaRegia.Model.Entidade.Administrador;
import BibliotecaRegia.Model.Entidade.Bibliotecario;
import BibliotecaRegia.Model.Entidade.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CadastrarOperadoresController implements Initializable {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button botaoCadastrar;

    @FXML
    private Button botaoCancelar;



    @FXML
    private Label exibicaoID;

    @FXML
    private ImageView iconeImageView;

    @FXML
    private Label labelMensagem;

    @FXML
    private ImageView logoImageView, operadorImageView, adicaoImageView;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField senhaTextField;

    @FXML
    private ChoiceBox<String> cargoChoiceBox;


    @FXML
    private TextField cargoTextField;



    @FXML
    void botaoCadastrarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {

        String nome = nomeTextField.getText();
        String senha = senhaTextField.getText();
        String cargoSelecionado = cargoChoiceBox.getValue();

        if (nome.isEmpty() || senha.isEmpty() || cargoSelecionado == null) {
            labelMensagem.setText("Preencha todos os campos.");
            return;
        }

        if (cargoSelecionado.equals("Bibliotecário")) {
            BibliotecarioDAOImpl bibliotecarioDAO = new BibliotecarioDAOImpl();
            Bibliotecario novoBibliotecario = new Bibliotecario(nome, cargoSelecionado, senha);
            List<Bibliotecario> bibliotecarios = bibliotecarioDAO.create(novoBibliotecario);

            if (bibliotecarios != null) {
                //Exibe o ID gerado
                this.exibicaoID.setText(novoBibliotecario.getId());
                this.labelMensagem.setText("Bibliotecário cadastrado com sucesso!");

                System.out.println(novoBibliotecario.getNome());
                System.out.println(novoBibliotecario.getId());
                System.out.println(novoBibliotecario.getCargo());
                System.out.println(novoBibliotecario.getSenhaAcesso());
                limparCampos();
            } else {
                this.labelMensagem.setText("Bibliotecário já cadastrado.");
            }

        } else if (cargoSelecionado.equals("Administrador")) {
            AdministradorDAOImpl administradorDAO = new AdministradorDAOImpl();
            Administrador novoAdministrador = new Administrador(nome, "Administrador", senha);
            List<Administrador> administradores = administradorDAO.create(novoAdministrador);

            if (administradores != null) {
                this.exibicaoID.setText(novoAdministrador.getId());
                this.labelMensagem.setText("Administrador cadastrado com sucesso");
                limparCampos();
            } else {
                this.labelMensagem.setText("Administrador já cadastrado.");
            }
        }

    }

    @FXML
    void botaoCancelarOnAction(ActionEvent event) {
        if (event.getSource() == botaoCancelar) {
            Main.navegacaoEntreTelas("perfilAdministrador");
        }
    }

    private void limparCampos() {
        this.nomeTextField.clear();
        this.senhaTextField.clear();
        //cargoChoiceBox.getSelectionModel().clearSelection();
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
        operadorImageView.setImage(operadorImage);

        //carregamento ícone sinal de adição
        File adicaoFile = new File("resources/imagem/maisPequeno.png");
        Image adicaoImage = new Image(adicaoFile.toURI().toString());
        adicaoImageView.setImage(adicaoImage);


        cargoChoiceBox.setItems(FXCollections.observableArrayList("Administrador", "Bibliotecário"));

    }
}
