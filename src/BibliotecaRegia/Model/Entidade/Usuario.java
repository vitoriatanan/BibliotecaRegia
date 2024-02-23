package BibliotecaRegia.Model.Entidade;



import java.util.ArrayList;
import java.util.List;

public class Usuario extends Pessoa {
    private static final long serialVersionUID = 9072900783305973555L;
    private String id;
    private String endereco;
    private String telefone;
    private List<EmprestimoDevolucao> emprestimos;
    private boolean contaBloqueada;
    private double multa;
    private long diasAtraso;

    public Usuario(String nome, String endereco, String telefone) {
        super(nome);
        this.endereco = endereco;
        this.telefone = telefone;
        this.emprestimos = new ArrayList<>();
        this.contaBloqueada = false;
        this.multa = 0;
        this.diasAtraso = 0;
    }

    public long getDiasAtraso() {
        return diasAtraso;
    }

    public void setDiasAtraso(long diasAtraso) {
        this.diasAtraso = diasAtraso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<EmprestimoDevolucao> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<EmprestimoDevolucao> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public boolean isContaBloqueada() {
        return contaBloqueada;
    }

    public void setContaBloqueada(boolean contaBloqueada) {
        this.contaBloqueada = contaBloqueada;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }



}
