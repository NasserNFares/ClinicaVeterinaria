package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExameDAO extends DAO {
    private static ExameDAO instance;

    private ExameDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static ExameDAO getInstance() {
        return (instance == null ? (instance = new ExameDAO()) : instance);
    }

    // CRUD
    public Exame create(String tipo, Calendar dataSolicitacao, Calendar dataResultado, String resultado) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO exame (tipo, dataSolicitacao, dataResultado, resultado) VALUES (?, ?, ?, ?)");
            stmt.setString(1, tipo);
            stmt.setTimestamp(2, new java.sql.Timestamp(dataSolicitacao.getTimeInMillis()));
            stmt.setTimestamp(3, new java.sql.Timestamp(dataResultado.getTimeInMillis()));
            stmt.setString(4, resultado);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ExameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("exame", "idExame"));
    }

    private Exame buildObject(ResultSet rs) {
        Exame exame = null;
        try {
            exame = new Exame(
                    rs.getInt("idExame"),
                    rs.getString("tipo"),
                    convertToCalendar(rs.getTimestamp("dataSolicitacao")),
                    convertToCalendar(rs.getTimestamp("dataResultado")),
                    rs.getString("resultado")
            );
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return exame;
    }

    private Calendar convertToCalendar(java.sql.Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        if (timestamp != null) {
            calendar.setTimeInMillis(timestamp.getTime());
        }
        return calendar;
    }

    // Generic Retriever
    public List<Exame> retrieve(String query) {
        List<Exame> exames = new ArrayList<>();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                exames.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return exames;
    }

    // RetrieveAll
    public List<Exame> retrieveAll() {
        return this.retrieve("SELECT * FROM exame");
    }

    // RetrieveLast
    public List<Exame> retrieveLast() {
        return this.retrieve("SELECT * FROM exame WHERE idExame = " + lastId("exame", "idExame"));
    }

    // RetrieveById
    public Exame retrieveById(int idExame) {
        List<Exame> exames = this.retrieve("SELECT * FROM exame WHERE idExame = " + idExame);
        return (exames.isEmpty() ? null : exames.get(0));
    }

    // Update
    public void update(Exame exame) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE exame SET tipo=?, dataSolicitacao=?, dataResultado=?, resultado=? WHERE idExame=?");
            stmt.setString(1, exame.getTipo());
            stmt.setTimestamp(2, new java.sql.Timestamp(exame.getDataSolicitacao().getTimeInMillis()));
            stmt.setTimestamp(3, new java.sql.Timestamp(exame.getDataResultaod().getTimeInMillis()));
            stmt.setString(4, exame.getResultado());
            stmt.setInt(5, exame.getIdExame());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Delete
    public void delete(Exame exame) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM exame WHERE idExame = ?");
            stmt.setInt(1, exame.getIdExame());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
