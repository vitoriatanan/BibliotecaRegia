package BibliotecaRegia.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import BibliotecaRegia.FileData.Entidade.Serializador;
import BibliotecaRegia.Main;
import BibliotecaRegia.Model.DAO.Livro.LivroDAOImpl;
import BibliotecaRegia.Model.DAO.Usuario.UsuarioDAOImpl;
import BibliotecaRegia.Model.Entidade.EmprestimoDevolucao;
import BibliotecaRegia.Model.Entidade.Livro;
import BibliotecaRegia.Model.Entidade.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static BibliotecaRegia.FileData.Entidade.Serializador.leituraDados;

public class RegistrarEmprestimosController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button botaoPesquisar;

    @FXML
    private Button botaoRegistrar;

    @FXML
    private Button botaoVoltar;

    @FXML
    private DatePicker dataDevolucaoDatePicker;

    @FXML
    private DatePicker dataEmprestimoDatePicker;

    @FXML
    private Label labelPesquisa, labelMensagem;

    @FXML
    private TextField livroTextField;

    @FXML
    private ImageView logoImageView, leitorImageView, pesquisarImageView;

    @FXML
    private TextField pesquisarIDTextField;


    @FXML
    void botaoPesquisarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String id = pesquisarIDTextField.getText();

        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        Usuario usuario = usuarioDAO.read(id);

        if (usuario != null) {
            if (!usuario.isContaBloqueada()) {
                labelPesquisa.setText("Usuário encontrado. Pode fazer o empréstimo.");
            } else {
                labelPesquisa.setText("CONTA BLOQUEADA! Não pode fazer o empréstimo.");
            }
        } else {
            labelPesquisa.setText("Usuário não encontrado.");
        }
    }

    @FXML
    void botaoRegistrarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        // Obter o usuário atualmente pesquisado e o livro desejado
        String id = pesquisarIDTextField.getText();
        String livro = livroTextField.getText();

        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        Usuario usuario = usuarioDAO.read(id);
        LivroDAOImpl livroDAO = new LivroDAOImpl();

        Livro livroEmprestimo = livroDAO.read(livro);


        // Verificar se o usuário e o livro são válidos
        if (id != null && livroEmprestimo != null) {
            // Realizar o empréstimo
            EmprestimoDevolucao emprestimoDevolucao = usuarioDAO.realizarEmprestimo(livroEmprestimo, usuario);

            // Se o empréstimo for bem-sucedido
            if (emprestimoDevolucao != null) {
                //Obter a data de Empréstimo
                LocalDate dataEmprestimo = emprestimoDevolucao.getDataEmprestimo();

                // Obter a data de devolução do empréstimo
                LocalDate dataDevolucao = emprestimoDevolucao.getDataDevolucaoEsperada();

                // Configurar o DatePicker com a data de empréstimo e devolução
                dataEmprestimoDatePicker.setValue(dataEmprestimo);
                dataDevolucaoDatePicker.setValue(dataDevolucao);

                labelMensagem.setText("Empréstimo realizado com sucesso!");


            } else {
                // Exibir uma mensagem indicando que o empréstimo não pode ser feito
                labelMensagem.setText("O empréstimo não pode ser feito.");
            }
        } else {
            // Exibir uma mensagem indicando que o usuário ou o livro não foi selecionado
            labelMensagem.setText("Selecione um usuário e um livro antes de registrar o empréstimo.");
        }
    }

    @FXML
    void botaoVoltarOnAction(ActionEvent event) {
        if (event.getSource() == botaoVoltar) {
            Main.navegacaoEntreTelas("login");
        }
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //carregamento logo
        File logoFile = new File("resources/imagem/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);

        //carregamento ícone leitor
        File leitorFile = new File("resources/imagem/leitor.png");
        Image leitorImage = new Image(leitorFile.toURI().toString());
        leitorImageView.setImage(leitorImage);

        //carregamento ícone pesquisar
        File pesquisarFile = new File("resources/imagem/pesquisarWhite.png");
        Image pesquisarImage = new Image(pesquisarFile.toURI().toString());
        pesquisarImageView.setImage(pesquisarImage);

    }

}
