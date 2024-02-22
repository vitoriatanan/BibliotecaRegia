package BibliotecaRegia.Controller;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import BibliotecaRegia.Main;
import BibliotecaRegia.Model.DAO.EmprestimoDevolucao.EmprestimoDevolucaoDAOImpl;
import BibliotecaRegia.Model.DAO.Usuario.UsuarioDAOImpl;
import BibliotecaRegia.Model.Entidade.EmprestimoDevolucao;
import BibliotecaRegia.Model.Entidade.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RenovacaoEmprestimoController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button boatoRenovar;

    @FXML
    private Button botaoVoltar;

    @FXML
    private TextField devolucaoTextField;

    @FXML
    private TextField devolucaoTextField2;

    @FXML
    private TextField emprestimoTextField;

    @FXML
    private TextField emprestimoTextField2;

    @FXML
    private Label encontrouEmprestimo;

    @FXML
    private Label encontrouUsuario;

    @FXML
    private Label enderecoLabel1;

    @FXML
    private Label enderecoLabel11;

    @FXML
    private Label idLabel1;

    @FXML
    private Label idLabel11;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField idTextField2;

    @FXML
    private Label labelMensagem;

    @FXML
    private ImageView logoImageView;

    @FXML
    private Label nomeLabel1;

    @FXML
    private Label nomeLabel11;

    @FXML
    private Button pesquisarBotao;

    @FXML
    private ImageView pesquisarImageView;

    @FXML
    private TextField pesquisarTextField;

    @FXML
    private Label telefoneLabel1;

    @FXML
    private Label telefoneLabel11;

    @FXML
    private TextField tituloTextField;

    @FXML
    private TextField tituloTextField2;

    @FXML
    void botaoPesquisarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String titulo = pesquisarTextField.getText();

        EmprestimoDevolucaoDAOImpl emprestimoDAO = new EmprestimoDevolucaoDAOImpl();
        EmprestimoDevolucao emprestimo = emprestimoDAO.read(titulo);

        if (emprestimo != null) {
            tituloTextField.setText(emprestimo.getLivro().getTitulo());
            idTextField.setText(emprestimo.getUsuario().getId());

            //convertendo de LocalDate para String
            String dataEmprestimo = emprestimo.getDataEmprestimo().toString();
            String dataDevolucao = emprestimo.getDataDevolucaoEsperada().toString();

            emprestimoTextField.setText(dataEmprestimo);
            devolucaoTextField.setText(dataDevolucao);

            encontrouEmprestimo.setText("Empréstimo encontrado");
        } else {
            encontrouEmprestimo.setText("Empréstimo não encontrado");

        }
    }

    @FXML
    void botaoRenovarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String titulo = pesquisarTextField.getText();

        EmprestimoDevolucaoDAOImpl emprestimoDAO = new EmprestimoDevolucaoDAOImpl();
        EmprestimoDevolucao emprestimo = emprestimoDAO.read(titulo);

        if (emprestimo != null) {
            UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
            boolean renovacaoSucesso = usuarioDAO.renovarEmprestimo(emprestimo.getUsuario(), emprestimo);

            if (renovacaoSucesso) {
                // Atualização bem-sucedida, exiba as informações atualizadas
                tituloTextField2.setText(emprestimo.getLivro().getTitulo());
                idTextField2.setText(emprestimo.getUsuario().getId());

                //Renova para mais 14 dias
                LocalDate dataEmprestimoRenovada = emprestimo.getDataEmprestimo().plusDays(14);
                //converte de LocalDate para String
                String novaDataEmprestimo = dataEmprestimoRenovada.toString();
                String novaDataDevolucao = emprestimo.getDataDevolucaoEsperada().toString();

                emprestimoTextField2.setText(novaDataEmprestimo);
                devolucaoTextField2.setText(novaDataDevolucao);

                labelMensagem.setText("Renovação realizada!");
            } else {
                labelMensagem.setText("Renovação não realizada: conta bloqueada ou limite de renovações atingido");
            }
        } else {
            labelMensagem.setText("Empréstimo não encontrado");
        }
    }

    @FXML
    void botaoVoltarOnAction(ActionEvent event) {
        if (event.getSource() == botaoVoltar) {
            Main.navegacaoEntreTelas("perfilAdministrador");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //carregamento logo
        File logoFile = new File("resources/imagem/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

        File lupaFile = new File("resources/imagem/pesquisarWhite.png");
        Image lupaImage = new Image(lupaFile.toURI().toString());
        pesquisarImageView.setImage(lupaImage);
    }

}
