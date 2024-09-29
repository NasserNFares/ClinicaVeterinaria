package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TratamentoDAO extends DAO {
    private static TratamentoDAO instance;

    private TratamentoDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static TratamentoDAO getInstance() {
        return (instance == null ? (instance = new TratamentoDAO()) : instance);
    }

    // CRUD
    public Tratamento create(int idAnimal, Calendar dataIncio, Calendar dataFim, String descricao) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO tratamento (idAnimal, dataInicio, dataFim, descricao) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, idAnimal);
            stmt.setTimestamp(2, new java.sql.Timestamp(dataIncio.getTimeInMillis()));
            stmt.setTimestamp(3, new java.sql.Timestamp(dataFim.getTimeInMillis()));
            stmt.setString(4, descricao);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(TratamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("tratamento", "idTratamento"));
    }

    private Tratamento buildObject(ResultSet rs) {
        Tratamento tratamento = null;
        try {
            tratamento = new Tratamento(
                    rs.getInt("idAnimal"),
                    convertToCalendar(rs.getTimestamp("dataInicio")),
                    convertToCalendar(rs.getTimestamp("dataFim")),
                    rs.getString("descricao")
            );
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return tratamento;
    }

    private Calendar convertToCalendar(java.sql.Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        if (timestamp != null) {
            calendar.setTimeInMillis(timestamp.getTime());
        }
        return calendar;
    }

    // Generic Retriever
    public List<Tratamento> retrieve(String query) {
        List<Tratamento> tratamentos = new ArrayList<>();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                tratamentos.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return tratamentos;
    }

    // RetrieveAll
    public List<Tratamento> retrieveAll() {
        return this.retrieve("SELECT * FROM tratamento");
    }

    // RetrieveLast
    public List<Tratamento> retrieveLast() {
        return this.retrieve("SELECT * FROM tratamento WHERE idTratamento = " + lastId("tratamento", "idTratamento"));
    }

    // RetrieveById
    public Tratamento retrieveById(int idTratamento) {
        List<Tratamento> tratamentos = this.retrieve("SELECT * FROM tratamento WHERE idTratamento = " + idTratamento);
        return (tratamentos.isEmpty() ? null : tratamentos.get(0));
    }

    // Update
    public void update(Tratamento tratamento) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE tratamento SET idAnimal=?, dataInicio=?, dataFim=?, descricao=? WHERE idTratamento=?");
            stmt.setInt(1, tratamento.getIdAnimal());
            stmt.setTimestamp(2, new java.sql.Timestamp(tratamento.getDataIncio().getTimeInMillis()));
            stmt.setTimestamp(3, new java.sql.Timestamp(tratamento.getDataFim().getTimeInMillis()));
            stmt.setString(4, tratamento.getDescricao());
            stmt.setInt(5, tratamento.getIdAnimal());  // Supondo que você tenha um campo idTratamento
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Delete
    public void delete(Tratamento tratamento) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM tratamento WHERE idTratamento = ?");
            stmt.setInt(1, tratamento.getIdAnimal());  // Supondo que você tenha um campo idTratamento
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}

