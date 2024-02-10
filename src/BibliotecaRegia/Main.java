package BibliotecaRegia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class Main extends Application {

    private static Scene loginScene;
    private static Scene opcaoCadastroScene, cadastrarUsuarioScene, cadastrarOperadorScene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {

        primaryStage = stage;

        //login
       Parent login = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/login.fxml"));
       loginScene = new Scene(login);

       //opcção de cadastro (usuário/operador)
       Parent opcaoCadastro = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/opcaoCadastro.fxml"));
       opcaoCadastroScene = new Scene(opcaoCadastro);

       //cadastrar usuário
        Parent cadastrarUsuario = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/cadastrarUsuario.fxml"));
        cadastrarUsuarioScene = new Scene(cadastrarUsuario);

        //cadastrar operador
        Parent cadastrarOperador = FXMLLoader.load(getClass().getResource("/BibliotecaRegia/View/cadastrarOperador.fxml"));
        cadastrarOperadorScene = new Scene(cadastrarOperador);


        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(loginScene);
        stage.show();

        /*
        FXMLLoader janela1 = new FXMLLoader(getClass().getResource("/BibliotecaRegia/View/login.fxml"));


        primaryStage = stage;
        primaryStage.setTitle("Testezinho Maroto");
        Parent parentJanela1 = janela1.load();

        sceneJanela1 = new Scene(parentJanela1);

        stage.setScene(sceneJanela1);
        stage.show();
        */
    }


    public static void navegacaoEntreTelas(String nomeTela) {
        switch (nomeTela) {
            case ("opcaoDeCadastro"):
                primaryStage.setScene(opcaoCadastroScene);
                break;
            case ("cadastrarUsuario"):
                primaryStage.setScene(cadastrarUsuarioScene);
                break;
            case ("cadastrarOperador"):
                primaryStage.setScene(cadastrarOperadorScene);
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}