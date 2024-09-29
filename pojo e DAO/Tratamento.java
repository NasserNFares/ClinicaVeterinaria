package Model;

import java.util.Calendar;

public class Tratamento {
    private int idAnimal;
    private Calendar dataIncio;
    private Calendar dataFim;
    private String descricao;

    public Tratamento(int id, Calendar dataIncio, Calendar dataFim, String descricao) {
        this.idAnimal = idAnimal;
        this.dataIncio = dataIncio;
        this.dataFim = dataFim;
        this.descricao = descricao;
    }

    public Calendar getDataIncio() {
        return dataIncio;
    }

    public void setDataIncio(Calendar dataIncio) {
        this.dataIncio = dataIncio;
    }

    public Calendar getDataFim() {
        return dataFim;
    }

    public void setDataFim(Calendar dataFim) {
        this.dataFim = dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }
}
