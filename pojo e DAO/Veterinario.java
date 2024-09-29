package Model;

public class Veterinario {
    private int idVeterinario;
    private String nome;
    private String crmv;
    private String especialidade;

    public Veterinario(int idVeterinario, String nome, String crmv, String especialidade) {
        this.idVeterinario = idVeterinario;
        this.nome = nome;
        this.crmv = crmv;
        this.especialidade = especialidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String toString() {
        return "Veterinario{" +
                "idVeterinario=" + idVeterinario +
                ", nome='" + nome + '\'' +
                ", crmv='" + crmv + '\'' +
                ", especialidade='" + especialidade + '\'' +
                '}';
    }
}
