package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnimalDAO extends DAO {
    private static AnimalDAO instance;

    private AnimalDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static AnimalDAO getInstance() {
        return (instance == null ? (instance = new AnimalDAO()) : instance);
    }

    // CRUD
    public Animal create(String nome, int dataNascimento, String raca, int idEspecie, int idCliente) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO animal (nome, dataNascimento, raca, idEspecie, idCliente) VALUES (?,?,?,?,?)");
            stmt.setString(1, nome);
            stmt.setInt(2, dataNascimento);
            stmt.setString(3, raca);
            stmt.setInt(4, idEspecie);
            stmt.setInt(5, idCliente);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("animal", "id"));
    }

    private Animal buildObject(ResultSet rs) {
        Animal animal = null;
        try {
            animal = new Animal(rs.getInt("id"), rs.getString("nome"), rs.getInt("dataNascimento"), rs.getString("raca"));
            animal.setIdEspecie(rs.getInt("idEspecie"));  // Mudado para int
            animal.setIdCliente(rs.getInt("idCliente"));  // Mudado para int
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return animal;
    }

    // Generic Retriever
    public List<Animal> retrieve(String query) {
        List<Animal> animals = new ArrayList<>();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                animals.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return animals;
    }

    // RetrieveAll
    public List<Animal> retrieveAll() {
        return this.retrieve("SELECT * FROM animal");
    }

    // RetrieveLast
    public List<Animal> retrieveLast() {
        return this.retrieve("SELECT * FROM animal WHERE id = " + lastId("animal", "id"));
    }

    // RetrieveById
    public Animal retrieveById(int id) {
        List<Animal> animals = this.retrieve("SELECT * FROM animal WHERE id = " + id);
        return (animals.isEmpty() ? null : animals.get(0));
    }
    //retrievebyidcliente
    public List retrieveByIdCliente(int id) {
        return this.retrieve("SELECT * FROM animal WHERE IdCliente = " + id);

    }

    // RetrieveBySimilarName
    public List<Animal> retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM animal WHERE nome LIKE '%" + nome + "%'");
    }

    // Update
    public void update(Animal animal) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE animal SET nome=?, dataNascimento=?, raca=?, idEspecie=?, idCliente=? WHERE id=?");
            stmt.setString(1, animal.getNome());
            stmt.setInt(2, animal.getDataNascimento());
            stmt.setString(3, animal.getRaca());
            stmt.setInt(4, animal.getIdEspecie()); // Mudado para int
            stmt.setInt(5, animal.getIdCliente()); // Mudado para int
            stmt.setInt(6, animal.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Delete
    public void delete(Animal animal) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM animal WHERE id = ?");
            stmt.setInt(1, animal.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
