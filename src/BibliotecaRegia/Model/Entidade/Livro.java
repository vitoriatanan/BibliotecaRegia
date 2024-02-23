package BibliotecaRegia.Model.Entidade;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Livro implements Serializable {
    private static final long serialVersionUID = 9072900783305973555L;

    private String titulo;
    private String autor;
    private String editora;
    private String isbn;
    private String anoPublicacao;
    private String categoria;
    private boolean livroDisponivel;
    private List<Usuario> usuariosReservados; //lista de reserva de livros


    // Construtor
    public Livro(String titulo, String autor, String editora, String isbn, String anoPublicacao, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.categoria = categoria;
        this.livroDisponivel = true;
        this.usuariosReservados = new ArrayList<>();
    }

    public void marcarComoLivroEmprestado() {
        this.livroDisponivel = false;
    }
    public void marcarComoLivroDevolvido() {
        this.livroDisponivel = true;
    }


    // Métodos Acessores
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public String getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(String anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isLivroDisponivel() {
        return livroDisponivel;
    }

    public void setLivroDisponivel(boolean livroDisponivel) {
        this.livroDisponivel = livroDisponivel;
    }

    public List<Usuario> getUsuariosReservados() {
        return usuariosReservados;
    }

    
    public void adicionarReserva(Usuario usuario) {
        usuariosReservados.add(usuario);
    }
}
