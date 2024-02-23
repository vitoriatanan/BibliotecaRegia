package BibliotecaRegia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class Main extends Application {

    private static Scene  loginScene, perfilBibliotecarioScene, perfilAdministradorScene, telaInicialScene;

    private static Scene cadastrarUsuarioScene, alterarUsuariosScene, deletarUsuariosScene;
    private static Scene cadastrarOperadoresScene, alterarOperadoresScene, deletarOperadoresScene;
    private static Scene registrarEmprestimosScene, renovarEmprestimosScene, deletarEmprestimosScene;
    private static Scene registrarLivrosScene, reservarLivrosScene, deletarLivrosScene, alterarLivrosScene, devolucoesLivrosScene;
    private static Scene multasScene, relatorioScene;

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {

        primaryStage = stage;

        // Relatórios
        Parent relatorio = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/relatorio.fxml"));
        relatorioScene = new Scene(relatorio);

        //Devoluções Empréstimos
        Parent devolucoesLivros = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/devolucoesLivros.fxml"));
        devolucoesLivrosScene =  new Scene(devolucoesLivros);
        //alterar Livros
        Parent alterarLivros = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/alterarLivros.fxml"));
        alterarLivrosScene = new Scene(alterarLivros);

        //deletarLivros
        Parent deletarLivros = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/deletarLivros.fxml"));
        deletarLivrosScene = new Scene(deletarLivros);

        //TelaInicial
        Parent telaInicial = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/telaInicial.fxml"));
        telaInicialScene = new Scene(telaInicial);

        //login
        Parent login = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/login.fxml"));
        loginScene = new Scene(login);

        //perfil bibliotecario
        Parent perfilBibliotecario = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/perfilBibliotecario.fxml"));
        perfilBibliotecarioScene = new Scene(perfilBibliotecario);

        //perfil administrador
        Parent perfilAdministrador = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/perfilAdministrador.fxml"));
        perfilAdministradorScene = new Scene(perfilAdministrador);


        /*    ===== CONTROLE DE USUÁRIOS =====     */
        //cadastrar usuários
        Parent cadastrarUsuarios = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/cadastrarUsuarios.fxml"));
        cadastrarUsuarioScene = new Scene(cadastrarUsuarios);

        //alterar usuários
        Parent alterarUsuarios =  FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/alterarUsuarios.fxml"));
        alterarUsuariosScene = new Scene(alterarUsuarios);

        //deletar usuários
        Parent deletarUsuarios = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/deletarUsuarios.fxml"));
        deletarUsuariosScene = new Scene(deletarUsuarios);


        /*      ===== CONTROLE DE OPERADORES =====     */
        //cadastrar operadores
        Parent cadastrarOperadores = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/cadastrarOperadores.fxml"));
        cadastrarOperadoresScene = new Scene(cadastrarOperadores);

        //alterar operadores
        Parent alterarOperadores = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/alterarOperadores.fxml"));
        alterarOperadoresScene = new Scene(alterarOperadores);

        //deletar operadores
        Parent deletarOperadores = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/deletarOperadores.fxml"));
        deletarOperadoresScene = new Scene(deletarOperadores);


        /*    ===== EMPRÉSTIMOS =====  */
        //registrar empréstimos
        Parent registrarEmprestimos = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/registrarEmprestimo.fxml"));
        registrarEmprestimosScene = new Scene(registrarEmprestimos);

        //renovar empréstimos
        Parent renovarEmprestimos = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/renovarEmprestimo.fxml"));
        renovarEmprestimosScene = new Scene(renovarEmprestimos);

        //deletar emrpéstimos
        Parent deletarEmprestimos = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/deletarEmprestimos.fxml"));
        deletarEmprestimosScene = new Scene(deletarEmprestimos);


        /* ===== GERENCIAR ACERVO ===== */
        //registrar livros
        Parent registrarLivros = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/registrarLivros.fxml"));
        registrarLivrosScene = new Scene(registrarLivros);

        //reservar livros
        Parent reservarLivros = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/reservarLivros.fxml"));
        reservarLivrosScene = new Scene(reservarLivros);


        //multas
        Parent multas = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/multa.fxml"));
        multasScene = new Scene(multas);

        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(telaInicialScene);
        stage.show();
    }


    public static void navegacaoEntreTelas(String nomeTela) {
        switch (nomeTela) {

            case ("alterarLivros"):
                primaryStage.setScene(alterarLivrosScene);
                break;

            case ("deletarLivros"):
                primaryStage.setScene(deletarLivrosScene);
                break;

            case("telaInicial"):
                primaryStage.setScene(telaInicialScene);
                break;

            case ("login"):
                primaryStage.setScene(loginScene);
                break;
            case ("perfilBibliotecario"):
                primaryStage.setScene(perfilBibliotecarioScene);
                break;
            case ("perfilAdministrador"):
                primaryStage.setScene(perfilAdministradorScene);
                break;
            case ("cadastrarUsuario"):
                primaryStage.setScene(cadastrarUsuarioScene);
                break;
            case ("alterarUsuario"):
                primaryStage.setScene(alterarUsuariosScene);
                break;
            case ("deletarUsuario"):
                primaryStage.setScene(deletarUsuariosScene);
                break;
            case ("cadastrarOperador"):
                primaryStage.setScene(cadastrarOperadoresScene);
                break;
            case ("alterarOperador"):
                primaryStage.setScene(alterarOperadoresScene);
                break;
            case ("deletarOperador"):
                primaryStage.setScene(deletarOperadoresScene);
                break;
            case ("devolucoesLivros"):
                primaryStage.setScene(devolucoesLivrosScene);
                break;
            case ("registrarEmprestimo"):
                primaryStage.setScene(registrarEmprestimosScene);
                break;
            case ("renovarEmprestimo"):
                primaryStage.setScene(renovarEmprestimosScene);
                break;
            case ("deletarEmprestimo"):
                primaryStage.setScene(deletarEmprestimosScene);
                break;
            case ("registrarLivro"):
                primaryStage.setScene(registrarLivrosScene);
                break;
            case ("reservarLivro"):
                primaryStage.setScene(reservarLivrosScene);
                break;
            case ("multa"):
                primaryStage.setScene(multasScene);
                break;
            case ("relatorio"):
                primaryStage.setScene(relatorioScene);
                break;

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}