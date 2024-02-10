package BibliotecaRegia.Model.Entidade;



import java.io.Serializable;

public class Pessoa implements Serializable {
    private String nome;

    // Construtor
    public Pessoa(String nome) {
        this.nome = nome;
    }

    // Métodos Acessores
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
