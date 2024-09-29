package Model;

import java.util.Calendar;

public class Exame {
    private int idExame;
    private String tipo;
    private Calendar dataSolicitacao;
    private Calendar dataResultaod;
    private String Resultado;

    public Exame(int idExame, String tipo, Calendar dataSolicitacao, Calendar dataResultado, String Resultado) {
    this.idExame = idExame;
    this.tipo = tipo;
    this.dataSolicitacao = dataSolicitacao;
    this.dataResultaod = dataResultado;
    this.Resultado = Resultado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdExame() {
        return idExame;
    }

    public void setIdExame(int idExame) {
        this.idExame = idExame;
    }

    public Calendar getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Calendar dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getResultado() {
        return Resultado;
    }

    public void setResultado(String resultado) {
        Resultado = resultado;
    }

    public Calendar getDataResultaod() {
        return dataResultaod;
    }

    public void setDataResultaod(Calendar dataResultaod) {
        this.dataResultaod = dataResultaod;
    }
}
