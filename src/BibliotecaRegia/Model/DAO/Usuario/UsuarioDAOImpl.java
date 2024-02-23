package BibliotecaRegia.Model.DAO.Usuario;


import BibliotecaRegia.FileData.Entidade.Serializador;
import BibliotecaRegia.Model.DAO.EmprestimoDevolucao.EmprestimoDevolucaoDAOImpl;
import BibliotecaRegia.Model.DAO.Livro.LivroDAOImpl;
import BibliotecaRegia.Model.Entidade.EmprestimoDevolucao;
import BibliotecaRegia.Model.Entidade.Livro;
import BibliotecaRegia.Model.Entidade.Reserva;
import BibliotecaRegia.Model.Entidade.Usuario;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class UsuarioDAOImpl implements UsuarioDAO {
    private List<Usuario> usuarios;
    private LivroDAOImpl livroDAO;

    public UsuarioDAOImpl() {
        this.usuarios = new ArrayList<>();
        this.livroDAO = new LivroDAOImpl();
    }

    /**
     * Responsável por adicionar um novo usuário ao sistema.
     *
     * Gera um novo id para o usuário, faz a leitura do arquivo
     * usuario.dat, itera sobre a lista verificando se existe
     * um usuário com o mesmo id, adiciona o usuário à lista de
     * uauários e salva a lista atualizada no arquivo.
     *
     * @param usuario    usuario a ser adicionado
     * @return           nulo se já houver um usuário com o mesmo id, caso
     *                   contrário, a lista de usuarios.
     */
    @Override
    public List<Usuario> create(Usuario usuario) throws IOException, ClassNotFoundException {
        usuario.setId(gerarIdUsuario());

        // Lê os usuários existentes do arquivo de dados
        List<Usuario> listaUsuarios = readUsuariosDoArquivo();

        // Verifica se já existe um usuário com o mesmo ID
        for (Usuario user : listaUsuarios) {
            if (user.getId().equals(usuario.getId())) {
                return null; // Já existe um usuário com o mesmo ID
            }
        }

        // Adiciona o novo usuário à lista
        listaUsuarios.add(usuario);

        // Salva a lista atualizada no arquivo de dados
        Serializador.salvarDados("usuario.dat", listaUsuarios);

        return listaUsuarios;
    }

    /**
     * Responsável por procurar um usuário com um id específico
     * na lista de usuários.
     *
     * Faz a leitura do arquivo "usuario.dat", itera sobre a lista
     * e verifica se cada usuário possui o id.
     *
     * @param id id do usuario a ser procurado
     * @return usuário que possui o id ou nulo se o usuário não foi encontrado
     */
    @Override
    public Usuario read(String id) throws IOException, ClassNotFoundException {
        List<Usuario> listaUsuarios = readUsuariosDoArquivo();

        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Responsável por atualizar informações de um usuário no sistema.
     * <p>
     * Faz a leitura do arquivo para obter a lista atual de usuários,
     * remove o usuário antigo, adiciona o novo usuário à lista e salva
     * no arquivo "usuario.dat".
     *
     * @param id            id do usuário
     * @param novoUsuario   dados do usuário a serem atualizadas
     */
    @Override
    public void update(String id, Usuario novoUsuario) throws IOException, ClassNotFoundException {
        // Lê os usuários existentes do arquivo de dados
        List<Usuario> listaUsuarios = readUsuariosDoArquivo();

        // Procura o usuário antigo usando o método read
        Usuario usuarioAntigo =  read(id);

        if (usuarioAntigo != null) {
            listaUsuarios.remove(usuarioAntigo);
            listaUsuarios.add(novoUsuario);
            Serializador.salvarDados("usuario.dat", listaUsuarios);
        }
    }


    /**
     * Responsável por remover um usuário do sistema.
     *
     * Faz a leitura do arquivo para obter a lista atual de usuários,
     * cria uma cópia dessa lista para iterar sobre a copia, verifica
     * o usuario a ser removido, remove o usuário e salva a lista
     * modificada no arquivo "usuario.dat".
     *
     * @param usuario usuário a ser removido.
     * @return lista atualizada.
     */
    @Override
    public List<Usuario> delete(Usuario usuario) throws IOException, ClassNotFoundException {
        List<Usuario> listaUsuarios = readUsuariosDoArquivo();
        Usuario removerUsuario = read(usuario.getId());

        if (removerUsuario != null) {
            listaUsuarios.remove(removerUsuario);
            Serializador.salvarDados("usuario.dat", listaUsuarios);
            return listaUsuarios;
        }
        return null;
    }





    /**
     * Método que cria id aleatórios para o usuário
     *
     * @return id do usuário
     */
    public String gerarIdUsuario() {
        Random rand = new Random();
        String novoID;

        // Loop para gerar um novo ID e verificar se já existe na lista de usuários
        do {
            int numeroAleatorio = rand.nextInt(1000);
            novoID = "u" + numeroAleatorio;
        } while (existeId(novoID));

        return novoID;
    }

    /**
     * Método auxiliar para verificar a existência de um ID na lista de usuários
     *
     * @param id id a ser verificado
     * @return true se já existir um id e false caso contrário
     */
    private boolean existeId(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return true; // ID encontrado, já existe
            }
        }
        return false; // ID não encontrado, é único
    }

    /**
     * Permite que o usuário realize pesquisa de livros
     *
     * @param tipo  Corresponde a categoria para pesquisa (autor, título, isbn ou categoria)
     * @param busca Corresponde a busca desejada
     * @return Resultado da pesquisa
     */
    public boolean pesquisarLivros(String tipo, String busca) {
        return livroDAO.pesquisarLivro(tipo, busca);
    }

    /**
     * Realiza o Empréstimo de livros por 14 dias se a conta
     * do usuário não estiver bloqueada, adiciona os empréstimos
     * na lista de empréstimos do usuário e salva o empréstimo
     * no arquivo "emprestimoDevolucao.dat".
     *
     * @param livro        Livro a ser emprestado
     * @param usuario      Usuário a fazer empréstimo
     * @return             Registro de empréstimo se for bem-sucedido, ou nulo
     * se a conta estiver bloqueada ou livro não disponível
     */
    public EmprestimoDevolucao realizarEmprestimo(Livro livro, Usuario usuario) throws IOException, ClassNotFoundException {
        if (!usuario.isContaBloqueada() && livro.isLivroDisponivel()) {

            // Lê os empréstimos existentes do arquivo de dados
            List<EmprestimoDevolucao> listaEmprestimos = new EmprestimoDevolucaoDAOImpl().readEmprestimosDoArquivo();

            // Verifica se já existe um empréstimo para o mesmo livro e usuário
            for (EmprestimoDevolucao emprestimo : listaEmprestimos) {
                if (emprestimo.getLivro().getTitulo().equals(livro.getTitulo()) && emprestimo.getUsuario().getId().equals(usuario.getId())) {
                    return null; // Já existe um empréstimo para o mesmo livro e usuário
                }
            }

            /* Cria um novo empréstimo */
            LocalDate dataEmprestimo = LocalDate.now();
            LocalDate dataDevolucaoEsperada = dataEmprestimo.plus(14, ChronoUnit.DAYS);
            EmprestimoDevolucao emprestimo = new EmprestimoDevolucao(usuario, livro, dataEmprestimo, dataDevolucaoEsperada);


            /* Adiciona os empréstimos na lista de empréstimos do usuário */
            usuario.getEmprestimos().add(emprestimo);

            /* Salva a lista atualizada no arquivo de empréstimos */
            new EmprestimoDevolucaoDAOImpl().create(emprestimo);

            /* Marca o livro como emprestado */
            livro.marcarComoLivroEmprestado();

            return emprestimo;
        } else {
            /* A conta está bloqueada ou o livro não está disponível para empréstimo */
            return null;
        }
    }


    /**
     * Responsável por fazer a devolução de um empréstimo.
     *
     * Verifica se o empréstimo está na lista de empréstimo do usuário, adiciona
     * o empréstimo à lista de devoluções, salva a lista de devoluções no arquivo
     * "emprestimoDevolucao.dat", verifica as datas e calcula a multa caso haja atraso
     *
     * @param emprestimo            Empréstimo do livro
     * @param dataDevolucaoReal     Data atual de devolução do livro
     * @param usuario               Usuário a fazer a devolução
     * */
    public void realizarDevolucao(EmprestimoDevolucao emprestimo, Date dataDevolucaoReal, Usuario usuario) throws IOException, ClassNotFoundException {
        // Lê os empréstimos existentes do arquivo
        List<EmprestimoDevolucao> listaEmprestimos = new EmprestimoDevolucaoDAOImpl().readEmprestimosDoArquivo();

        if (listaEmprestimos.contains(emprestimo)) {
            emprestimo.setDataDevolucaoReal(dataDevolucaoReal);
            emprestimo.getLivro().marcarComoLivroDevolvido();

            // Adiciona o empréstimo à lista de devoluções
            emprestimo.getListaDevolucoes().add(emprestimo);

            // Salva a lista atualizada no arquivo
            new EmprestimoDevolucaoDAOImpl().salvarDevolucoes(emprestimo.getListaDevolucoes());

            LocalDate dataDevolucaoEsperada = emprestimo.getDataDevolucaoEsperada();
            if (dataDevolucaoReal.after(Date.from(dataDevolucaoEsperada.atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
                /* Calcula a multa */
                long diasAtraso = ChronoUnit.DAYS.between(dataDevolucaoEsperada, LocalDate.now());
                double multa = diasAtraso * 2;
                /* Atualiza o estado da conta do usuário com a multa */
                usuario.setMulta(usuario.getMulta() + multa);
            }
        }

        /* Atualiza conta do usuário para bloqueada, caso haja multa */
        if (usuario.getMulta() > 0) {
            usuario.setContaBloqueada(true);
        }
    }

    /**
     * Realiza a reserva de um livro
     *
     * @param livro       Livro a ser reservado
     * @param usuario     Usuário que está fazendo a reserva
     * @return            true se a reserva for bem-sucedida, false caso contrário
     * */
    public boolean realizarReserva(Livro livro, Usuario usuario) throws IOException, ClassNotFoundException {
        // Lê os empréstimos existentes do arquivo
        List<EmprestimoDevolucao> listaEmprestimos = new EmprestimoDevolucaoDAOImpl().readEmprestimosDoArquivo();

        for (EmprestimoDevolucao emprestimo : listaEmprestimos) {
            if (emprestimo.getLivro().equals(livro)) {
                livro.adicionarReserva(usuario);

                Serializador.salvarDados("livro.dat", livro.getUsuariosReservados());

                return true;
            }
        }
        return false;
    }


    /**
     * Renova o empréstimo de livros para mais 14 dias se a conta do usuário
     * não estiver bloqueada.
     *
     * Lê a lista de empréstimos do arquivo "emprestimoDevolucao.dat", atualiza
     * o empréstimo desejado e, em seguida, salvando a lista atualizada de volta
     * no arquivo.
     *
     * @param usuario         Usuário que deseja renovar o empréstimo
     * @param emprestimo      Empréstimo a ser renovado
     * @return                true se a renovação for bem-sucedida, false caso contrário, ou
     *                        conta bloqueada
     * */
    public boolean renovarEmprestimo(Usuario usuario, EmprestimoDevolucao emprestimo) throws IOException, ClassNotFoundException {

        // Lê os empréstimos existentes do arquivo
        List<EmprestimoDevolucao> listaEmprestimos = new EmprestimoDevolucaoDAOImpl().readEmprestimosDoArquivo();

        for (EmprestimoDevolucao emp : listaEmprestimos) {
            if (emp.equals(emprestimo)) {
                // Verifica se o usuário tem permissão para renovar
                if (!usuario.isContaBloqueada() && emp.getRenovacoes() < 1) {

                    // Verifica se o livro ja foi reservado por outros usuários
                    List<Livro> listaLivrosReservados = new LivroDAOImpl().readLivrosDoArquivo();
                    for (Livro livro : listaLivrosReservados) {
                        if (livro.getTitulo().equals(emp.getLivro().getTitulo())) {
                            return false; // Não pode fazer renovação, pois o livro já foi reservado
                        }
                    }

                    //Atualiza as informações de renovação
                    LocalDate dataAtual = LocalDate.now();
                    LocalDate novaDataRenovacaoEsperada = dataAtual.plus(14, ChronoUnit.DAYS);

                    emp.setDataDevolucaoEsperada(novaDataRenovacaoEsperada);
                    emp.contagemRenovacoes();

                    // Salva a lista atualizada no arquivo
                    new EmprestimoDevolucaoDAOImpl().update(emprestimo, emp);

                    return true; // Renovação bem sucedida
                }
            }
        }
        return false; // Emprestimo não encontrado
    }

    /**
     * Método para ler a lista de usuários do arquivo "usuario.dat".
     *
     * @return Lista de usuários lida do arquivo.
     * @throws IOException            Se ocorrer um erro durante a leitura do arquivo.
     * @throws ClassNotFoundException Se a classe não for encontrada durante a desserialização.
     */
    // Método auxiliar para ler usuários do arquivo de dados
    private List<Usuario> readUsuariosDoArquivo() throws IOException, ClassNotFoundException {
        try {
            // Tenta ler os usuários do arquivo
            return (List<Usuario>) Serializador.leituraDados("usuario.dat");
        } catch (FileNotFoundException e) {
            // Se o arquivo não existir, cria um novo arquivo vazio
            Serializador.salvarDados("usuario.dat", new ArrayList<>());
            // Retorna uma lista vazia
            return new ArrayList<>();
        }
    }
}
