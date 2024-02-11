package BibliotecaRegia.Model.DAO.Bibliotecario;



import BibliotecaRegia.FileData.Entidade.Serializador;
import BibliotecaRegia.Model.Entidade.Bibliotecario;
import BibliotecaRegia.Model.Entidade.EmprestimoDevolucao;
import BibliotecaRegia.Model.Entidade.Livro;
import BibliotecaRegia.Model.Entidade.Usuario;
import BibliotecaRegia.Model.DAO.Livro.LivroDAOImpl;
import BibliotecaRegia.Model.DAO.Usuario.UsuarioDAOImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BibliotecarioDAOImpl implements BibliotecarioDAO {
    private List<Bibliotecario> bibliotecarios;
    private LivroDAOImpl livroDAO;
    private UsuarioDAOImpl usuarioDAO;

    public BibliotecarioDAOImpl() {
        this.bibliotecarios = new ArrayList<>();
        this.livroDAO = new LivroDAOImpl();
        this.usuarioDAO = new UsuarioDAOImpl();

        //Inicialização de um bibliotecário padrão no sistema
        Bibliotecario bibliotecarioPadrao = new Bibliotecario("", "Bibliotecário", "bibli");
        bibliotecarioPadrao.setId("bibli");
        this.bibliotecarios.add(bibliotecarioPadrao);
        try {
            Serializador.salvarDados("bibliotecario.dat", this.bibliotecarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Responsável por adicionar um novo bibliotecário ao sistema.
     *
     * Gera um novo id para o bibliotecário, adiciona o bibliotecário à
     * lista de bibliotecários e salva a lista atualizada de usuários
     * no arquivo "bibliotecario.dat".
     *
     * @param bibliotecario     bibliotecário a ser adicionado
     * @return                  lista de bibliotecarios
     * */
    @Override
    public List<Bibliotecario> create(Bibliotecario bibliotecario) throws IOException, ClassNotFoundException {
        bibliotecario.setId(gerarIdBibliotecario());

        List<Bibliotecario> listaBibliotecarios = readBibliotecariosDoArquivo();

        for (Bibliotecario bibl : listaBibliotecarios) {
            if (bibl.getId().equals(bibl.getId())) {
                return null;
            }
        }

        listaBibliotecarios.add(bibliotecario);
        Serializador.salvarDados("bibliotecario.dat", listaBibliotecarios);
        return listaBibliotecarios;
    }


    /**
     * Responsável por procurar um bibliotecário com um id específico
     * na lista de bibliotecários.
     *
     * Faz a leitura do arquivo "bibliotecario.dat", itera sobre a lista
     * e verifica se cada bibliotecário possui o id.
     *
     * @param id    id do bibliotecário a ser procurado
     * @return      bibliotecário que possui o id ou nulo se o bibliotecário não foi encontrado
     * */
    @Override
    public Bibliotecario read(String id) throws IOException, ClassNotFoundException {
        List<Bibliotecario> listaBibliotecarios = readBibliotecariosDoArquivo();

        for (Bibliotecario bibliotecario : listaBibliotecarios) {
            if (bibliotecario.getId().equals(id)) {
                return bibliotecario;
            }
        }
        return null;
    }

    /**
     * Responsável por atualizar informações de um bibliotecario no sistema.
     *
     * Faz a leitura do arquivo para obter a lista atual de bibliotecarios,
     * remove o bibliotecário antigo, adiciona o novo bibliotecário à lista
     * e salva  no arquivo "bibliotecario.dat".
     *
     * @param id                    id do bibliotecário
     * @param novoBibliotecario     dados do bibliotecário a serem atualizadas
     * */
    @Override
    public void update(String id, Bibliotecario novoBibliotecario) throws IOException, ClassNotFoundException {
        List<Bibliotecario> listaBibliotecarios = readBibliotecariosDoArquivo();

        Bibliotecario bibliotecarioAntigo = read(id);

        if (bibliotecarioAntigo != null) {
            listaBibliotecarios.remove(bibliotecarioAntigo);
            listaBibliotecarios.add(novoBibliotecario);
            Serializador.salvarDados("bibliotecario.dat", listaBibliotecarios);
        }
    }

    /**
     * Responsável por remover um bibliotecário do sistema.
     *
     * Verifica o bibliotecário a ser removido, remove o bibliotecário e salva a lista
     * modificada no arquivo "bibliotecario.dat".
     *
     * @param bibliotecario     bibliotecario a ser removido.
     * @return                  lista atualizada.
     * */
    @Override
    public List<Bibliotecario> delete(Bibliotecario bibliotecario) throws IOException, ClassNotFoundException {
        List<Bibliotecario> listaBibliotecarios = readBibliotecariosDoArquivo();
        Bibliotecario removerBibliotecario = read(bibliotecario.getId());

        if (removerBibliotecario != null) {
            listaBibliotecarios.remove(removerBibliotecario);
            Serializador.salvarDados("bibliotecario.dat", listaBibliotecarios);
            return listaBibliotecarios;
        }
        return null;
    }


    /**
     * Método que cria id aleatórios para o bibliotecário
     *
     * @return   id do bibliotecário
     * */
    public String gerarIdBibliotecario() {
        Random rand = new Random();
        String novoID;

        // Loop para gerar um novo ID e verificar se já existe na lista de bibliotecários
        do {
            int numeroAleatorio = rand.nextInt(1000);
            novoID = "b" + numeroAleatorio;
        } while (existeId(novoID));

        return novoID;
    }

    /**
     * Método auxiliar para verificar a existência de um ID na lista de bibliotecários
     *
     * @param id     id a ser verificado
     * @return       true se já existir um id e false caso contrário
     * */
    private boolean existeId(String id) {
        for (Bibliotecario bibliotecario : bibliotecarios) {
            if (bibliotecario.getId().equals(id)) {
                return true; // ID encontrado, já existe
            }
        }
        return false; // ID não encontrado, é único
    }

    /**
     * Permite que o biliotecário realize pesquisa de livros
     *
     * @param tipo      Corresponde a categoria para pesquisa (autor, título, isbn ou categoria)
     * @param busca     Corresponde a busca desejada
     * @return          Resultado da pesquisa
     * */
    public boolean pesquisarLivros(String tipo, String busca) {
        return livroDAO.pesquisarLivro(tipo, busca);
    }



    /**
     * Registra novo livro na biblioteca
     *
     * @param livro    Livro a ser registrado
     * @return         Lista de livros após o registro
     * */
    public List<Livro> registrarLivros(Livro livro) throws IOException, ClassNotFoundException {
        livroDAO.create(livro);
        return livroDAO.getLivros();
    }

    /**
     * Permite que o bibliotecário tenha acesso aos empréstimos
     * @param livro      Livro a ser emprestado
     * @param usuario    Usuario que fará o empréstimo
     * */
    public EmprestimoDevolucao realizarEmprestimo(Livro livro, Usuario usuario) throws IOException, ClassNotFoundException {
        EmprestimoDevolucao emprestimo = usuarioDAO.realizarEmprestimo(livro, usuario);
        return emprestimo;
    }


    /**
     * Realiza a devolução do livro e marca o livro como disponível
     *
     * @param emprestimo           Empréstimo realizado pelo usuário
     * @param dataDevolucaoReal    Data de devolução do livro
     * */

    public void realizarDevolucao(EmprestimoDevolucao emprestimo, Date dataDevolucaoReal, Usuario usuario) throws IOException, ClassNotFoundException {
        usuarioDAO.realizarDevolucao(emprestimo, dataDevolucaoReal, usuario);
    }

    // Método auxiliar para ler usuários do arquivo de dados
    private List<Bibliotecario> readBibliotecariosDoArquivo() throws IOException, ClassNotFoundException {
        try {
            // Tenta ler os usuários do arquivo
            return (List<Bibliotecario>) Serializador.leituraDados("bibliotecario.dat");
        } catch (FileNotFoundException e) {
            // Se o arquivo não existir, cria um novo arquivo vazio
            Serializador.salvarDados("bibliotecario.dat", new ArrayList<>());
            // Retorna uma lista vazia
            return new ArrayList<>();
        }
    }

}
