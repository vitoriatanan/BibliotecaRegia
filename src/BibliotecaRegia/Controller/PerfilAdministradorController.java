package BibliotecaRegia.Controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import BibliotecaRegia.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PerfilAdministradorController implements Initializable {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem alterarLivroMenuItem;

    @FXML
    private MenuItem alterarMenuItem;

    @FXML
    private MenuItem alterarOperadorMenuItem;

    @FXML
    private Button botaoMulta;

    @FXML
    private Button botaoSair;

    @FXML
    private Button botarReservarLivros;

    @FXML
    private MenuItem cadastrarMenuItem;

    @FXML
    private MenuItem cadastrarOperadorMenuItem;

    @FXML
    private ImageView credencialImageView;

    @FXML
    private MenuItem deletarEmprestimoMenuItem;

    @FXML
    private MenuItem deletarLivroMenuItem;

    @FXML
    private MenuItem deletarMenuItem;

    @FXML
    private MenuItem deletarOperadorMenuItem;

    @FXML
    private ImageView gerenciarOperadorWhite;

    @FXML
    private ImageView gerenciarUsuarioWhite;

    @FXML
    private ImageView iconeUsuarioImageView;

    @FXML
    private ImageView leitorWhite;

    @FXML
    private ImageView livroWhite;

    @FXML
    private MenuButton menuItemOperador;

    @FXML
    private ImageView multaWhite;

    @FXML
    private ImageView pesquisaWhite;

    @FXML
    private Button pesquisarLivrosBotao;

    @FXML
    private MenuItem registrarEmprestimoMenuItem;

    @FXML
    private MenuItem registrarLivroMenuItem;

    @FXML
    private MenuItem renovarEmprestimoMenuItem;

    @FXML
    private ImageView reservaWhite;

    @FXML
    private ImageView sairImageView;

    @FXML
    private Button relatorio;

    @FXML
    private Button devolucao;

    @FXML
    void devolucaoOnAction(ActionEvent event) {
        if (event.getSource() == devolucao) {
            Main.navegacaoEntreTelas("devolucoesLivros");
        }
    }

    @FXML
    void pesquisarLivrosOnAction(ActionEvent event) {
        if (event.getSource() == pesquisarLivrosBotao) {
            Main.navegacaoEntreTelas("telaInicial");
        }
    }


    /* GERENCIAR USUÁRIOS */
    @FXML
    void menuCadastrarUsuarioOnAction(ActionEvent event) {
        if (event.getSource() == cadastrarMenuItem) {
            Main.navegacaoEntreTelas("cadastrarUsuario");
        }
    }

    @FXML
    void menuAlterarUsuarioOnAction(ActionEvent event) {
        if (event.getSource() == alterarMenuItem) {
            Main.navegacaoEntreTelas("alterarUsuario");
        }
    }

    @FXML
    void menuDeletarUsuarioOnAction(ActionEvent event) {
        if (event.getSource() == deletarMenuItem) {
            Main.navegacaoEntreTelas("deletarUsuario");
        }

    }

    /* GERENCIAR OPERADORES */
    @FXML
    void menuCadastrarOperadorOnAction(ActionEvent event) {
        if (event.getSource() == cadastrarOperadorMenuItem) {
            Main.navegacaoEntreTelas("cadastrarOperador");
        }
    }

    @FXML
    void menuAlterarOperadorOnAction(ActionEvent event) {
        if (event.getSource() == alterarOperadorMenuItem) {
            Main.navegacaoEntreTelas("alterarOperador");
        }
    }

    @FXML
    void menuDeletarOperadorOnAction(ActionEvent event) {
        if (event.getSource() == deletarOperadorMenuItem) {
            Main.navegacaoEntreTelas("deletarOperador");
        }

    }


    // EMPRESTIMOS
    @FXML
    void menuRegistrarEmprestimoOnAction(ActionEvent event) {
        if (event.getSource() == registrarEmprestimoMenuItem) {
            Main.navegacaoEntreTelas("registrarEmprestimo");
        }
    }

    @FXML
    void menuRenovarEmprestimoOnAction(ActionEvent event) {
        if (event.getSource() == renovarEmprestimoMenuItem) {
            Main.navegacaoEntreTelas("renovarEmprestimo");
        }
    }


    @FXML
    void menuDeletarEmprestimoOnAction(ActionEvent event) {
        if (event.getSource() == deletarEmprestimoMenuItem) {
            Main.navegacaoEntreTelas("deletarEmprestimo");
        }

    }

    /* GERENCIAR ACERVO */

    @FXML
    void menuAlterarLivroOn(ActionEvent event) {
        if (event.getSource() == alterarLivroMenuItem) {
            Main.navegacaoEntreTelas("alterarLivros");
        }
    }
    @FXML
    void menuDeletarLivroOn(ActionEvent event) {
        if (event.getSource() == deletarLivroMenuItem) {
            Main.navegacaoEntreTelas("deletarLivros");
        }
    }

    @FXML
    void menuRegistrarLivroOnAction(ActionEvent event) {
        if (event.getSource() == registrarLivroMenuItem ) {
            Main.navegacaoEntreTelas("registrarLivro");
        }
    }

    @FXML
    void botaoReservarLivrosOnAction(ActionEvent event) {
        if (event.getSource() == botarReservarLivros) {
            Main.navegacaoEntreTelas("reservarLivro");
        }
    }


    // Opção Multa
    @FXML
    void botaoMultaOnAction(ActionEvent event) {
        if (event.getSource() == botaoMulta) {
            Main.navegacaoEntreTelas("multa");
        }
    }

    //Opção Sair
    @FXML
    void botaoSairOnAction(ActionEvent event) {
        if (event.getSource() == botaoSair) {
            Main.navegacaoEntreTelas("login");
        }
    }

    @FXML
    void bttTelaRelatorioOnAction(ActionEvent event) {
        if (event.getSource() == relatorio) {
            Main.navegacaoEntreTelas("relatorio");

        }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //carregamento credencial
        File credencialFile = new File("resources/imagem/credencial.png");
        Image credencialImage = new Image(credencialFile.toURI().toString());
        credencialImageView.setImage(credencialImage);

        //carregamento ícone sair
        File iconeSairFile = new File("resources/imagem/sair.png");
        Image iconeSairImage = new Image(iconeSairFile.toURI().toString());
        sairImageView.setImage(iconeSairImage);

        //carregamento icone usuário
        File iconeUsuarioFile = new File("resources/imagem/perfil.png");
        Image iconeUsuarioImage = new Image(iconeUsuarioFile.toURI().toString());
        iconeUsuarioImageView.setImage(iconeUsuarioImage);

        //carregamento icone livro
        File livroFile = new File("resources/imagem/livroWhite.png");
        Image livroImage = new Image(livroFile.toURI().toString());
        livroWhite.setImage(livroImage);

        //carregamento icone pesquisa
        File pesquisaFile = new File("resources/imagem/pesquisarWhite.png");
        Image pesquisaImage = new Image(pesquisaFile.toURI().toString());
        pesquisaWhite.setImage(pesquisaImage);

        //carregamento icone leitor
        File leitorFile = new File("resources/imagem/leitorWhite.png");
        Image leitorImage = new Image(leitorFile.toURI().toString());
        leitorWhite.setImage(leitorImage);

        //carregamento icone reserva
        File reservaFile = new File("resources/imagem/reservarWhite.png");
        Image reservaImage = new Image(reservaFile.toURI().toString());
        reservaWhite.setImage(reservaImage);

        //carregamento icone multa
        File multaFile = new File("resources/imagem/banimentoWhite.png");
        Image multaImage = new Image(multaFile.toURI().toString());
        multaWhite.setImage(multaImage);

        //carregamento icone gerenciar usuário
        File gerenciarUsuarioFile = new File("resources/imagem/usuarioWhite.png");
        Image usuarioImage = new Image(gerenciarUsuarioFile.toURI().toString());
        gerenciarUsuarioWhite.setImage(usuarioImage);

        //carregamento icone operador
        File operadorFile = new File("resources/imagem/operadorWhite.png");
        Image operadorImage = new Image(operadorFile.toURI().toString());
        gerenciarOperadorWhite.setImage(operadorImage);
    }
}