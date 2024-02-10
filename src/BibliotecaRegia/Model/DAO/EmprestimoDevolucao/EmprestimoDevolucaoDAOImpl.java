package BibliotecaRegia.Model.DAO.EmprestimoDevolucao;

import BibliotecaRegia.FileData.Entidade.Serializador;
import BibliotecaRegia.Model.Entidade.EmprestimoDevolucao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDevolucaoDAOImpl implements EmprestimoDevolucaoDAO {
    private List<EmprestimoDevolucao> emprestimos;

    public EmprestimoDevolucaoDAOImpl() {
        this.emprestimos = new ArrayList<>();
    }


    /**
     * Responsável por adicionar um novo empréstimo ao sistema.
     *
     * Adiciona o empréstimo à lista de empréstimos e salva a
     * lista atualizada de empréstimos no arquivo "empréstimoDevolucao.dat".
     *
     * @param emprestimo    empréstimo a ser adicionado
     * @return              lista de empréstimos
     * */
    @Override
    public List<EmprestimoDevolucao> create(EmprestimoDevolucao emprestimo) throws IOException, ClassNotFoundException {
        List<EmprestimoDevolucao> listaEmprestimos = readEmprestimosDoArquivo();

        for (EmprestimoDevolucao emprestimos : listaEmprestimos) {
            if (emprestimos.getLivro().getTitulo().equals(emprestimos.getLivro().getTitulo())) {
                return null;
            }
        }

        listaEmprestimos.add(emprestimo);
        Serializador.salvarDados("emprestimos.dat", listaEmprestimos);

        return listaEmprestimos;
    }

    /**
     *
     * Lê a lista de empréstimos do arquivo "emprestimoDevolucao.dat", itera
     * sobre ela procurando por um empréstimo específico e salva a lista
     * atualizada no arquivo.
     *
     * @param titulo     Título do livro
     * @return           Empréstimo se encontrado, caso contrário, retorna nulo
     * */
    @Override
    public EmprestimoDevolucao read(String titulo) throws IOException, ClassNotFoundException {

        List<EmprestimoDevolucao> listaEmprestimos = readEmprestimosDoArquivo();

        for (EmprestimoDevolucao emprestimo : listaEmprestimos) {
            if (emprestimo.getLivro().getTitulo().equals(titulo)) {
                return emprestimo;
            }
        }
        return null;
    }

    /**
     * Responsável por atualizar as informações de um empréstimo específico.
     *
     * Lê a lista de empréstimos do arquivo, verifica se a lista contém o
     * empréstimo a ser atualizado, substitui pelo novo empréstimo e salva a
     * lista atualizada no arquivo "emprestimoDevolucao.dat".
     *
     * @param emprestimoAtual       empréstimo realizado recente
     * @param novoEmprestimo        emprestimo a ser atualizadp
     * */
    @Override
    public void update(EmprestimoDevolucao emprestimoAtual, EmprestimoDevolucao novoEmprestimo) throws IOException, ClassNotFoundException {

        /* Faz a leitura do arquivo "emprestimoDevolucao.dat" */
        ArrayList<EmprestimoDevolucao> listaEmprestimos = new ArrayList<EmprestimoDevolucao>();
        listaEmprestimos = (ArrayList<EmprestimoDevolucao>) Serializador.leituraDados("emprestimo.dat");

        for (int i = 0; i < listaEmprestimos.size(); i++) {
            if (listaEmprestimos.get(i).equals(emprestimoAtual)) {
                listaEmprestimos.set(i, novoEmprestimo);
                break;
            }
        }

        emprestimos = listaEmprestimos;
        Serializador.salvarDados("emprestimo.dat", listaEmprestimos);
    }

    /**
     * Responsável por remover um empréstimo específico.
     *
     * Lê a lista de empréstimos do arquivo "emprestimoDevolucao.dat", itera sobre
     * uma cópia da lista de empréstimos, verifica se cada empréstimo na cópia
     * possui as mesmas características do empréstimo a ser removido, remove esse
     * empréstimo da lista e salva a lista atualizada no arquivo.
     *
     * @param emprestimo     empréstimo a ser removido
     * */
    @Override
    public void delete(EmprestimoDevolucao emprestimo) throws IOException, ClassNotFoundException {
        emprestimos.remove(emprestimo);
        Serializador.salvarDados("emprestimoDevolucao.dat", emprestimos);
    }


    // Método auxiliar para ler empréstimos do arquivo de dados
    public List<EmprestimoDevolucao> readEmprestimosDoArquivo() throws IOException, ClassNotFoundException {
        try {
            // Tenta ler os empréstimos do arquivo
            return (List<EmprestimoDevolucao>) Serializador.leituraDados("emprestimoDevolucao.dat");
        } catch (FileNotFoundException e) {
            // Se o arquivo não existir, cria um novo arquivo vazio
            Serializador.salvarDados("emprestimoDevolucao.dat", new ArrayList<>());
            // Retorna uma lista vazia
            return new ArrayList<>();
        }
    }

    public void salvarDevolucoes(List<EmprestimoDevolucao> devolucoes) throws  IOException {
        Serializador.salvarDados("devolucao.dat", devolucoes);
    }
}
