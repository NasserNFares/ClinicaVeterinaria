package Model;

public class Animal {
    private int id;
    private String nome;
    private int dataNascimento;
    private String raca;
    private int IdEspecie;
    private int IdCliente;

    public int getIdEspecie() {
        return IdEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        IdEspecie = idEspecie;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }

    public Animal(int id, String nome, int dataNascimento, String raca) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.raca = raca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(int dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String toString() {
        return "Model.Animal{" + "nome:" + nome + '}';
    }
}
