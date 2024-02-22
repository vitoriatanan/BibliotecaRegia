package BibliotecaRegia.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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

public class DeletarEmprestimoController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelarBotao;

    @FXML
    private Label cargoLabel;

    @FXML
    private Button deletarBotao;

    @FXML
    private ImageView deletarOperadorImageView;

    @FXML
    private TextField devolucaoTextField;

    @FXML
    private TextField emprestimoTextField;

    @FXML
    private Label encontrouUsuario;

    @FXML
    private Label idLabel;

    @FXML
    private TextField idTextField;

    @FXML
    private Label labelMensagem;

    @FXML
    private ImageView logoImageView, leitorImageView;

    @FXML
    private ImageView menosImageView;

    @FXML
    private Label nomeLabel;

    @FXML
    private Button pesquisarBotao;

    @FXML
    private ImageView pesquisarImageView;

    @FXML
    private TextField pesquisarTextField;

    @FXML
    private Label senhaLabel;

    @FXML
    private TextField tituloTextField;



    @FXML
    void botaoCancelarOnAction(ActionEvent event) {
        if (event.getSource() == cancelarBotao) {
            Main.navegacaoEntreTelas("perfilAdministrador");
        }
    }

    @FXML
    void botaoDeletarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String titulo = pesquisarTextField.getText();

        EmprestimoDevolucaoDAOImpl emprestimoDAO = new EmprestimoDevolucaoDAOImpl();
        EmprestimoDevolucao emprestimo = emprestimoDAO.read(titulo);

        if (emprestimo != null) {
            emprestimoDAO.delete(emprestimo);
            limparCampos();

            labelMensagem.setText("Empréstimo Deletado!");
        }


    }

    @FXML
    void botaoPesquisarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String titulo = pesquisarTextField.getText();

        EmprestimoDevolucaoDAOImpl emprestimoDAO = new EmprestimoDevolucaoDAOImpl();
        EmprestimoDevolucao emprestimo = emprestimoDAO.read(titulo);

        if (emprestimo != null) {
            encontrouUsuario.setText("Empréstimo Encontrado");
            tituloTextField.setText(emprestimo.getLivro().getTitulo());
            idTextField.setText(emprestimo.getUsuario().getId());

            //converte de LocalDate para String
            String dataEmprestimo = emprestimo.getDataEmprestimo().toString();
            String dataDevolucao = emprestimo.getDataDevolucaoEsperada().toString();

            emprestimoTextField.setText(dataEmprestimo);
            devolucaoTextField.setText(dataDevolucao);
        } else {
            encontrouUsuario.setText("Empréstimo não encontrado");
        }


    }

    private void limparCampos() {
        tituloTextField.clear();
        idTextField.clear();
        emprestimoTextField.clear();
        devolucaoTextField.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //carregamento logo
        File logoFile = new File("resources/imagem/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

        //carregamento ícone leitor
        File leitorFile = new File("resources/imagem/leitor.png");
        Image leitorImage = new Image(leitorFile.toURI().toString());
        leitorImageView.setImage(leitorImage);

        //carregamento ícone sinal subtração
        File menosFile = new File("resources/imagem/menosPequeno.png");
        Image menosImage = new Image(menosFile.toURI().toString());
        menosImageView.setImage(menosImage);

        //carregamento pesquisa
        File lupaFile = new File("resources/imagem/pesquisarWhite.png");
        Image pesquisa = new Image(lupaFile.toURI().toString());
        pesquisarImageView.setImage(pesquisa);
    }

}
