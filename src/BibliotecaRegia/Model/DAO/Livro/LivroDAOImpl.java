package BibliotecaRegia.Model.DAO.Livro;

import BibliotecaRegia.FileData.Entidade.Serializador;
import BibliotecaRegia.Model.Entidade.Livro;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LivroDAOImpl implements BibliotecaRegia.Model.DAO.Livro.LivroDAO {
    private List<Livro> livros;

    public LivroDAOImpl() {
        this.livros = new ArrayList<>();
    }

    /**
     * Responsável por adicionar um novo livro ao sistema.
     *
     * Adiciona o livro à lista de livross e salva a lista atualizada
     * de livros no arquivo "livro.dat".
     *
     * @param livro     livro a ser adicionado
     * @return          lista de livros
     * */
    @Override
    public List<Livro> create(Livro livro) throws IOException, ClassNotFoundException {
        List<Livro> listaLivros = readLivrosDoArquivo();

        // Verifica se já existe um usuário com o mesmo ID
        for (Livro liv : listaLivros) {
            if (liv.getTitulo().equals(liv.getTitulo())) {
                return null; // Já existe um usuário com o mesmo ID
            }
        }

        // Adiciona o novo usuário à lista
        listaLivros.add(livro);

        // Salva a lista atualizada no arquivo de dados
        Serializador.salvarDados("livro.dat", listaLivros);

        return listaLivros;
    }

    /**
     * Responsável por procurar um livro com um id específico
     * na lista de livros.
     *
     * Faz a leitura do arquivo "livro.dat", itera sobre a lista
     * e verifica se cada livro possui o titulo.
     *
     * @param titulo    titulo do usuario a ser procurado
     * @return          livro que possui o titulo ou nulo se o livro não foi encontrado
     * */
    @Override
    public Livro read(String titulo) throws IOException, ClassNotFoundException {

        /* Faz a leitura do arquivo "livro.dat" */
        List<Livro> listaLivro = readLivrosDoArquivo();

        for (Livro livro : listaLivro) {
            if (livro.getTitulo().equals(titulo)) {
                return livro;
            }
        }
        return null;
    }


    /**
     * Responsável por atualizar informações de um livro no sistema.
     *
     * Faz a leitura do arquivo para obter a lista atual de livros,
     * remove o livro antigo, adiciona o novo livro à lista e salva
     * no arquivo "livro.dat".
     *
     * @param titulo          título do livro
     * @param novoLivro       dados do livro a serem atualizados
     * */
    @Override
    public void update(String titulo, Livro novoLivro) throws IOException, ClassNotFoundException {
        List<Livro> listaLivros = readLivrosDoArquivo();

        // Procura o usuário antigo usando o método read
        Livro livroAntigo =  read(titulo);

        if (livroAntigo != null) {
            listaLivros.remove(livroAntigo);
            listaLivros.add(novoLivro);
            Serializador.salvarDados("livro.dat", listaLivros);
        }
    }


    /**
     * Responsável por remover um livro do sistema.
     *
     * Faz a leitura do arquivo para obter a lista atual de livros,
     * cria uma cópia dessa lista para iterar sobre a copia, verifica
     * o livro a ser removido, remove o livro e salva a lista
     * modificada no arquivo "livro.dat".
     *
     * @param livro     livro a ser removido.
     * @return          lista atualizada.
     * */
    @Override
    public List<Livro> delete(Livro livro) throws IOException, ClassNotFoundException {

        List<Livro> listaLivros = readLivrosDoArquivo();
        Livro removerLivro = read(livro.getTitulo());

        if (removerLivro != null) {
            listaLivros.remove(removerLivro);
            Serializador.salvarDados("livro.dat", listaLivros);
            return listaLivros;
        }
        return null;
    }

    public boolean pesquisarLivro(String tipo, String busca) {
        for (Livro livro : livros) {
            if (Objects.equals(tipo, "autor") && livro.getAutor().contains(busca)) {
                return true;
            } else if (Objects.equals(tipo, "titulo") && livro.getTitulo().contains(busca)) {
                return true;
            } else if (Objects.equals(tipo, "categoria") && livro.getCategoria().contains(busca)) {
                return true;
            } else if (Objects.equals(tipo, "isbn") && livro.getIsbn().contains(busca)) {
                return true;
            } else if (Objects.equals(tipo, "editora") && livro.getEditora().contains(busca)) {
                return true;
            }
        }
        return false;
    }

    // Método auxiliar para ler empréstimos do arquivo de dados
    public List<Livro> readLivrosDoArquivo() throws IOException, ClassNotFoundException {
        try {
            // Tenta ler os empréstimos do arquivo
            return (List<Livro>) Serializador.leituraDados("livro.dat");
        } catch (FileNotFoundException e) {
            // Se o arquivo não existir, cria um novo arquivo vazio
            Serializador.salvarDados("livro.dat", new ArrayList<>());
            // Retorna uma lista vazia
            return new ArrayList<>();
        }
    }

    public List<Livro> getLivros() {
        return livros;
    }
}
