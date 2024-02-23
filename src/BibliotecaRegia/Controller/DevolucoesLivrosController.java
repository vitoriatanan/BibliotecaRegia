package BibliotecaRegia.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import BibliotecaRegia.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import BibliotecaRegia.Model.DAO.Livro.LivroDAOImpl;
import BibliotecaRegia.Model.Entidade.Livro;
import BibliotecaRegia.Model.DAO.Usuario.UsuarioDAOImpl;
import BibliotecaRegia.Model.Entidade.Usuario;
import BibliotecaRegia.Model.Entidade.EmprestimoDevolucao;
import BibliotecaRegia.Model.DAO.EmprestimoDevolucao.EmprestimoDevolucaoDAOImpl;
import java.time.ZoneId;


public class DevolucoesLivrosController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField DataDevoTextField;

    @FXML
    private TextField DataEmpreTextField;

    @FXML
    private TextField IdTextField;

    @FXML
    private TextField LivroTextField;

    @FXML
    private Button buttonAdicionar;

    @FXML
    private Button buttonAtualizar;

    @FXML
    private Button buttonDeletar;

    @FXML
    private Button buttonVoltar;

    @FXML
    private Label labelMensagem;

    @FXML
    private ImageView voltar;

    @FXML
    private ImageView logo;



    @FXML
    void BttAdicionarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String idUsuario = IdTextField.getText();
        String tituloLivro = LivroTextField.getText();
        LocalDate dataDevolucao = LocalDate.parse(DataDevoTextField.getText());
        Date dataDevolucaoDate = Date.from(dataDevolucao.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDate dataEmprestimo = LocalDate.parse(DataEmpreTextField.getText());

        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        LivroDAOImpl livroDAO = new LivroDAOImpl();
        EmprestimoDevolucaoDAOImpl emprestimoDAO = new EmprestimoDevolucaoDAOImpl();

        // Verifica se os campos estão preenchidos
        if (!idUsuario.isEmpty() && !tituloLivro.isEmpty()) {
            // Busca usuário e livro no banco de dados
            Usuario usuario = usuarioDAO.read(idUsuario);
            Livro livro = livroDAO.read(tituloLivro);

            if (usuario != null && livro != null) {
                // Verifica se o livro está emprestado para o usuário
                for (EmprestimoDevolucao emprestimo : usuario.getEmprestimos()) {
                    if (emprestimo.getLivro().equals(livro)) {
                        // Realiza a devolução
                        emprestimoDAO.delete(emprestimo);
                        usuarioDAO.realizarDevolucao(emprestimo, dataDevolucaoDate, usuario);
                        // Exibe mensagem de sucesso
                        labelMensagem.setText("Devolução realizada com sucesso.");
                        return;
                    }
                }
                // Exibe mensagem de erro se o livro não estiver emprestado para o usuário
                labelMensagem.setText("O livro não está emprestado para este usuário.");
            } else {
                // Exibe mensagem de erro se o usuário ou o livro não existirem
                labelMensagem.setText("Usuário ou livro não encontrado.");
            }
        } else {
            // Exibe mensagem de erro se algum campo estiver vazio
            labelMensagem.setText("Por favor, preencha todos os campos.");
        }

    }




    @FXML
    void BttVoltarOnAction(ActionEvent event) {
        Main.navegacaoEntreTelas("login");

    }


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //carregamento logo
        File logoFile = new File("resources/imagem/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logo.setImage(logoImage);

        //Icone de Voltar
        File voltarFile = new File("resources/imagem/voltar.png");
        Image iconeVoltar = new Image(voltarFile.toURI().toString());
        voltar.setImage(iconeVoltar);



    }

}
