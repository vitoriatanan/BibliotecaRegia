package BibliotecaRegia.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BibliotecaRegia.Main;
import BibliotecaRegia.Model.DAO.EmprestimoDevolucao.EmprestimoDevolucaoDAOImpl;
import BibliotecaRegia.Model.DAO.Livro.LivroDAOImpl;
import BibliotecaRegia.Model.DAO.Usuario.UsuarioDAOImpl;
import BibliotecaRegia.Model.Entidade.EmprestimoDevolucao;
import BibliotecaRegia.Model.Entidade.Livro;
import BibliotecaRegia.Model.Entidade.Reserva;
import BibliotecaRegia.Model.Entidade.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ReservarLivrosController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button botaoReservar;

    @FXML
    private Button botaoVoltar;

    @FXML
    private Label encontrouLivro;

    @FXML
    private TextField idTextEmprestimo, idTextField;

    @FXML
    private TextField idTextReserva;

    @FXML
    private Label labelMensagem;

    @FXML
    private ImageView logoImageView, pesquisaImageView;

    @FXML
    private TextField pesquisarTextField;

    @FXML
    private TextField tituloTextEmprestimo;

    @FXML
    private TextField tituloTextReserva;

    @FXML
    void botaoPesquisarOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        String titulo = pesquisarTextField.getText();

        EmprestimoDevolucaoDAOImpl emprestimoDAO = new EmprestimoDevolucaoDAOImpl();
        EmprestimoDevolucao emprestimo = emprestimoDAO.read(titulo);

        if (emprestimo != null) {
            encontrouLivro.setText("Empréstimo Encontrado");
            idTextEmprestimo.setText(emprestimo.getUsuario().getId());
            tituloTextEmprestimo.setText(emprestimo.getLivro().getTitulo());
        } else {
            encontrouLivro.setText("Empréstimo não encontrado");
        }
    }

    @FXML
    void botaoReservarOnAction(ActionEvent event) throws IOException, ClassNotFoundException{
        String titulo = pesquisarTextField.getText();
        String id = idTextField.getText();

        LivroDAOImpl livroDAO = new LivroDAOImpl();
        Livro livro = livroDAO.read(titulo);

        if (livro != null) {
            UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
            Usuario usuario = usuarioDAO.read(id);

            if (usuario != null) {
                Reserva reserva = new Reserva(livro, usuario);
                boolean reservaFeita = usuarioDAO.realizarReserva(livro, usuario);

                if (reservaFeita) {
                    tituloTextReserva.setText(livro.getTitulo());
                    idTextReserva.setText(usuario.getId());
                    labelMensagem.setText("Reserva realizada com sucesso");
                } else {
                    labelMensagem.setText("A reserva não pode ser feita!");
                }
            } else {
                labelMensagem.setText("Usuário não encontrado");
            }
        } else {
            labelMensagem.setText("Livro não encontrado");
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

        //carregamento icone pesquisa
        File iconeFile = new File("resources/imagem/pesquisarWhite.png");
        Image iconeImage = new Image(iconeFile.toURI().toString());
        pesquisaImageView.setImage(iconeImage);
    }

}
