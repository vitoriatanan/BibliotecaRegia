package BibliotecaRegia.Model.DAO.Administrador;


import BibliotecaRegia.FileData.Entidade.Serializador;
import BibliotecaRegia.Model.Entidade.*;
import BibliotecaRegia.Model.DAO.Livro.LivroDAOImpl;
import BibliotecaRegia.Model.DAO.Usuario.UsuarioDAOImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class AdministradorDAOImpl implements AdministradorDAO {
    private List<Administrador> adms;
    private LivroDAOImpl livroDAO;
    private UsuarioDAOImpl usuarioDAO;

    public AdministradorDAOImpl() {
        this.adms = new ArrayList<>();
        this.livroDAO = new LivroDAOImpl();
        this.usuarioDAO = new UsuarioDAOImpl();

        //Inicialização de um administrador padrão no sistema
        Administrador administradorPadrao = new Administrador("", "Administrador", "admim");
        administradorPadrao.setId("admim");
        this.adms.add(administradorPadrao);
        try {
            Serializador.salvarDados("administrador.dat", this.adms);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Responsável por adicionar um novo administrador ao sistema.
     * <p>
     * Gera um novo id para o administrador, adiciona o administrador à
     * lista de adms e salva a lista atualizada de administradores
     * no arquivo "administrador.dat".
     *
     * @param administrador       usuario a ser adicionado
     * @return                    lista de usuarios
     */
    @Override
    public List<Administrador> create(Administrador administrador) throws IOException, ClassNotFoundException {
        administrador.setId(gerarIdAdministrador());

        List<Administrador> listaAdministrador = readAdministradorDoArquivo();

        for (Administrador adm : listaAdministrador) {
            if (adm.getId().equals(adm.getId())) {
                return null;
            }
        }

        listaAdministrador.add(administrador);
        Serializador.salvarDados("administrador.dat", listaAdministrador);
        return listaAdministrador;
    }


    /**
     * Responsável por procurar um administrador com um id específico
     * na lista de administradores.
     *
     * Faz a leitura do arquivo "administrador.dat", itera sobre a lista
     * e verifica se cada administrador possui o id.
     *
     * @param id       id do administrador a ser procurado
     * @return         administrador que possui o id ou nulo se o administrador não foi encontrado
     */
    @Override
    public Administrador read(String id) throws IOException, ClassNotFoundException {
        List<Administrador> listaAdministrador = readAdministradorDoArquivo();

        for (Administrador administrador : listaAdministrador) {
            if (administrador.getId().equals(id)) {
                return administrador;
            }
        }
        return null;
    }

    /**
     * Responsável por atualizar informações de um administrador no sistema.
     *
     * Faz a leitura do arquivo para obter a lista atual de administradores,
     * remove o administrador antigo, adiciona o novo administrador à lista
     * e salva no arquivo "administrador.dat".
     *
     * @param id           id do usuário
     * @param novoAdm      dados do administrador a serem atualizadas
     */
    @Override
    public void update(String id, Administrador novoAdm) throws IOException, ClassNotFoundException {
       List<Administrador> listaAdministrador = readAdministradorDoArquivo();

       Administrador admAntigo = read(id);

       if (admAntigo != null) {
           listaAdministrador.remove(admAntigo);
           listaAdministrador.add(novoAdm);
           Serializador.salvarDados("administrador.dat", listaAdministrador);
       }
    }

    /**
     * Responsável por remover um administrador do sistema.
     *
     * Faz a leitura do arquivo para obter a lista atual de administradores,
     * cria uma cópia dessa lista para iterar sobre a copia, verifica
     * o administrador a ser removido, remove o administrador e salva a lista
     * modificada no arquivo "administrador.dat".
     *
     * @param administrador      administrador a ser removido.
     * @return                   lista atualizada.
     */
    @Override
    public List<Administrador> delete(Administrador administrador) throws IOException, ClassNotFoundException {
        List<Administrador> listaAdministrador = readAdministradorDoArquivo();
        Administrador removerAdm = read(administrador.getId());

        if (removerAdm != null) {
            listaAdministrador.remove(removerAdm);
            Serializador.salvarDados("administrador.dat", listaAdministrador);
            return listaAdministrador;
        }
        return null;
    }

    /**
     * Método que cria id aleatórios para o administrador
     *
     * @return   id do administrador
     */
    public String gerarIdAdministrador() {
        Random rand = new Random();
        String novoID;

        // Loop para gerar um novo ID e verificar se já existe na lista de adms
        do {
            int numeroAleatorio = rand.nextInt(1000);
            novoID = "u" + numeroAleatorio;
        } while (existeId(novoID));

        return novoID;
    }

    /**
     * Método auxiliar para verificar a existência de um ID na lista de adms
     *
     * @param id        id a ser verificado
     * @return          true se já existir um id e false caso contrário
     */
    private boolean existeId(String id) {
        for (Administrador administrador : adms) {
            if (administrador.getId().equals(id)) {
                return true; // ID encontrado, já existe
            }
        }
        return false; // ID não encontrado, é único
    }

    /**
     * Permite que o administrador realize pesquisa de livros
     *
     * @param tipo      Corresponde a categoria para pesquisa (autor, título, isbn ou categoria)
     * @param busca     Corresponde a busca desejada
     * */
    public boolean pesquisar(String tipo, String busca) {
        return livroDAO.pesquisarLivro(tipo, busca);
    }


    /**
     * Registra novo livro na biblioteca
     *
     * @param livro    Livro a ser registrado
     * @return         Lista de livros após o registro
     * */
    public List<Livro> registrarLivros(Livro livro) throws IOException, ClassNotFoundException {
        List<Livro> listaLivrosRegistrados = new LivroDAOImpl().readLivrosDoArquivo();

        // Verifica se o livro já foi registrado
        for (Livro liv : listaLivrosRegistrados) {
            if (liv.getTitulo().equals(livro.getTitulo())) {
                return null; // Livro já foi registrado
            }
        }

        // Registra o livro
        livroDAO.create(livro);
        return livroDAO.getLivros();
    }


    /**
     * Permite que o administrador tenha acesso aos empréstimos
     *
     * @param livro      Livro a ser emprestado
     * @param usuario    Usuario que fará o empréstimo
     * */
    public EmprestimoDevolucao emprestimos(Livro livro, Usuario usuario) throws IOException, ClassNotFoundException {
        EmprestimoDevolucao emprestimo = usuarioDAO.realizarEmprestimo(livro, usuario);
        return emprestimo;
    }


    /**
     * Realiza a devolução do livro e marca o livro como disponível
     *
     * @param emprestimo           Empréstimo realizado pelo usuário
     * @param dataDevolucaoReal    Data de devolução do livro
     * */
    public void devolucao(EmprestimoDevolucao emprestimo, Date dataDevolucaoReal, Usuario usuario) throws IOException, ClassNotFoundException {
        usuarioDAO.realizarDevolucao(emprestimo, dataDevolucaoReal, usuario);
    }


    /**
     * Permite ao administrador acessar as reservas dos livros
     *
     * @param livro       Livro a ser reservado
     * @param usuario     Usuário que está fazendo a reserva
     * @return            true se a reserva for bem-sucedida, false caso contrário
     * */

    public boolean reserva(Livro livro, Usuario usuario) throws IOException, ClassNotFoundException {
        return usuarioDAO.realizarReserva(livro, usuario);
    }

    
    /**
     * Permite ao administrador acessar as renovações de empréstimos
     *
     * @param usuario         Usuário que deseja renovar o empréstimo
     * @param emprestimo      Empréstimo que deve ser renovado
     * @return                true se a renovação for bem-sucedida, false caso contrário
     * */
    public boolean renovacao(Usuario usuario, EmprestimoDevolucao emprestimo) throws IOException, ClassNotFoundException {
        return usuarioDAO.renovarEmprestimo(usuario, emprestimo);
    }

    // Método auxiliar para ler administradores do arquivo de dados
    private List<Administrador> readAdministradorDoArquivo() throws IOException, ClassNotFoundException {
        try {
            // Tenta ler os usuários do arquivo
            return (List<Administrador>) Serializador.leituraDados("administrador.dat");
        } catch (FileNotFoundException e) {
            // Se o arquivo não existir, cria um novo arquivo vazio
            Serializador.salvarDados("administrador.dat", new ArrayList<>());
            // Retorna uma lista vazia
            return new ArrayList<>();
        }
    }
}
