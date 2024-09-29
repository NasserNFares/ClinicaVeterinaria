package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VeterinarioDAO extends DAO {
    private static VeterinarioDAO instance;

    private VeterinarioDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static VeterinarioDAO getInstance() {
        return (instance == null ? (instance = new VeterinarioDAO()) : instance);
    }

    // CRUD
    public Veterinario create(String nome, String crmv, String especialidade) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO veterinario (nome, crmv, especialidade) VALUES (?, ?, ?)");
            stmt.setString(1, nome);
            stmt.setString(2, crmv);
            stmt.setString(3, especialidade);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("veterinario", "idVeterinario"));
    }

    private Veterinario buildObject(ResultSet rs) {
        Veterinario veterinario = null;
        try {
            veterinario = new Veterinario(
                    rs.getInt("idVeterinario"),
                    rs.getString("nome"),
                    rs.getString("crmv"),
                    rs.getString("especialidade")
            );
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return veterinario;
    }

    // Generic Retriever
    public List<Veterinario> retrieve(String query) {
        List<Veterinario> veterinarios = new ArrayList<>();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                veterinarios.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return veterinarios;
    }

    // RetrieveAll
    public List<Veterinario> retrieveAll() {
        return this.retrieve("SELECT * FROM veterinario");
    }

    // RetrieveLast
    public List<Veterinario> retrieveLast() {
        return this.retrieve("SELECT * FROM veterinario WHERE idVeterinario = " + lastId("veterinario", "idVeterinario"));
    }

    // RetrieveById
    public Veterinario retrieveById(int idVeterinario) {
        List<Veterinario> veterinarios = this.retrieve("SELECT * FROM veterinario WHERE idVeterinario = " + idVeterinario);
        return (veterinarios.isEmpty() ? null : veterinarios.get(0));
    }

    // Update
    public void update(Veterinario veterinario) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE veterinario SET nome=?, crmv=?, especialidade=? WHERE idVeterinario=?");
            stmt.setString(1, veterinario.getNome());
            stmt.setString(2, veterinario.getCrmv());
            stmt.setString(3, veterinario.getEspecialidade());
            stmt.setInt(4, veterinario.getIdVeterinario());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Delete
    public void delete(Veterinario veterinario) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM veterinario WHERE idVeterinario = ?");
            stmt.setInt(1, veterinario.getIdVeterinario());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}

