package BibliotecaRegia.Controller;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import BibliotecaRegia.Main;
import BibliotecaRegia.Model.DAO.Usuario.UsuarioDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import BibliotecaRegia.Model.DAO.Livro.LivroDAOImpl;
import BibliotecaRegia.Model.Entidade.Livro;
import BibliotecaRegia.Model.DAO.Usuario.UsuarioDAOImpl;
import BibliotecaRegia.Model.Entidade.Usuario;
import BibliotecaRegia.Model.Entidade.EmprestimoDevolucao;
import BibliotecaRegia.Model.DAO.EmprestimoDevolucao.EmprestimoDevolucaoDAOImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import BibliotecaRegia.Model.Entidade.Relatorio;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileWriter;




public class RelatorioController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField atrasadosTextField;

    @FXML
    private ImageView voltarImagem;

    @FXML
    private Button buttonVoltar;

    @FXML
    private TextField emprestadosTextField;

    @FXML
    private TextField historico;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField livro1TextField;

    @FXML
    private TextField livro2TextField;

    @FXML
    private TextField livrosPopulares;

    @FXML
    private TextField numeroLivros;

    @FXML
    private TextField reservadosTextField;

    @FXML
    private TextField LivrosPopulares;

    @FXML
    private Label labelMensagem;

    @FXML
    private Button buttonGerarrelatorio;

    @FXML
    private TextField tituloTextField;

    @FXML
    private ImageView logo;


    @FXML
    public void gerarRelatorioOnAction(ActionEvent event) throws IOException, ClassNotFoundException {
        Relatorio relatorio = new Relatorio();
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        List<Usuario> usuarios = new ArrayList<>();
        List<Livro> livros = new ArrayList<>();
        LivroDAOImpl livroDAO = new LivroDAOImpl();
        String id = idTextField.getText();
        String titulo = tituloTextField.getText();
        Usuario usuario = usuarioDAO.read(id);
        EmprestimoDevolucaoDAOImpl EmprestimoDevolucaoDAO = new EmprestimoDevolucaoDAOImpl();
        EmprestimoDevolucao emprestimoDevolucao = EmprestimoDevolucaoDAO.read(titulo);







        int atrasados = Relatorio.quantidadeLivrosAtrasados(usuarios);
        int emprestados = emprestimoDevolucao.getUsuario().getEmprestimos().size();
        int reservados = Relatorio.quantidadeLivrosReservados(livros);
        int emprestimosID = emprestimoDevolucao.getUsuario().getEmprestimos().size();
        String livro = Relatorio.livrosMaisPopulares(usuarios).toString();









        // Verifique se o usuário não é nulo antes de prosseguir
        atrasadosTextField.setText(String.valueOf(atrasados));
        emprestadosTextField.setText(String.valueOf(emprestados));
        reservadosTextField.setText(String.valueOf(reservados));
        LivrosPopulares.setText(livro);
        livro1TextField.setText(String.valueOf(emprestimosID));

    }

    @FXML
    public void VoltarOnAction(ActionEvent event){
        Main.navegacaoEntreTelas("perfilAdministrador");
    }


    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
        //carregamento logo
        File logoFile = new File("resources/imagem/logo.png");
        Image marcaImage = new Image(logoFile.toURI().toString());
        logo.setImage(marcaImage);

        //carregamento voltar
        File voltarFile = new File("resources/imagem/voltar.png");
        Image iconeVoltar = new Image(voltarFile.toURI().toString());
        voltarImagem.setImage(iconeVoltar);


    }
}

