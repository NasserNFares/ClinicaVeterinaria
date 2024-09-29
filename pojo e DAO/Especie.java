package Model;

public class Especie {
    private int id;
    private String nome;

    public Especie(int id, String nome) {
         this.id = id;
         this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public int getId(){
        return id;
    }
}
