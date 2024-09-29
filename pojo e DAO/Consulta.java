package Model;

import java.util.Calendar;

public class Consulta {
    private int id;
    private Calendar dataHora;
    private String sintomas;
    private String diagnostico;

    public Consulta(int id, Calendar dataHora, String sintomas, String diagnostico) {
        this.id = id;
        this.dataHora = dataHora;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
    }

    public int getId() {
        return id;
    }

    public Calendar getDataHora() {
        return dataHora;
    }

    public void setDataHora(Calendar dataHora) {
        this.dataHora = dataHora;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
}

