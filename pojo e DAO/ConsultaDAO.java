package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultaDAO extends DAO {
    private static ConsultaDAO instance;

    private ConsultaDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static ConsultaDAO getInstance() {
        return (instance == null ? (instance = new ConsultaDAO()) : instance);
    }

    // CRUD
    public Consulta create(Calendar dataHora, String sintomas, String diagnostico) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO consulta (dataHora, sintomas, diagnostico) VALUES (?, ?, ?)");
            stmt.setString(1, new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dataHora.getTime()));
            stmt.setString(2, sintomas);
            stmt.setString(3, diagnostico);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("consulta", "id"));
    }

    private Consulta buildObject(ResultSet rs) {
        Consulta consulta = null;
        try {
            Calendar dataHora = Calendar.getInstance();
            dataHora.setTime(rs.getTimestamp("dataHora"));
            consulta = new Consulta(rs.getInt("id"), dataHora, rs.getString("sintomas"), rs.getString("diagnostico"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consulta;
    }

    // Generic Retriever
    public List<Consulta> retrieve(String query) {
        List<Consulta> consultas = new ArrayList<>();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                consultas.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consultas;
    }

    // RetrieveAll
    public List<Consulta> retrieveAll() {
        return this.retrieve("SELECT * FROM consulta");
    }

    // RetrieveById
    public Consulta retrieveById(int id) {
        List<Consulta> consultas = this.retrieve("SELECT * FROM consulta WHERE id = " + id);
        return (consultas.isEmpty() ? null : consultas.get(0));
    }

    // Update
    public void update(Consulta consulta) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE consulta SET dataHora=?, sintomas=?, diagnostico=? WHERE id=?");
            stmt.setString(1, new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(consulta.getDataHora().getTime()));
            stmt.setString(2, consulta.getSintomas());
            stmt.setString(3, consulta.getDiagnostico());
            stmt.setInt(4, consulta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Delete
    public void delete(Consulta consulta) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM consulta WHERE id = ?");
            stmt.setInt(1, consulta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }


}
