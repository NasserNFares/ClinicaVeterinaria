package Model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;

    private List<Animal> animais;

    public Cliente(int id, String nome, String endereco, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.animais = new ArrayList<Animal>();
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void addAnimal(Animal animal) {
        if (!animal.getNome().isBlank()) {

            animais.add(animal);
        }
    }

    public List<Animal> getAnimais() {
        List <Animal> copia = new ArrayList<Animal>(animais);

        return copia;
    }
    public String toString() {
        String desc = "cliente{" +
                "nome: " + nome + ", " +
                "endereco: " + endereco + ", " +
                "email: " + email + ", " +
                "telefone: " + telefone + '}';
        String strAnimais = animais.toString();
        return desc + "\n" + strAnimais;
    }


}
