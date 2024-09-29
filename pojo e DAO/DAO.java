package Model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAO {
    public static final String DBURL = "jdbc:sqlite:vet2024.db";

    private static Connection con;
    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    // Connect to SQLite
    public static Connection getConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(DBURL);
                if (con != null) {
                    DatabaseMetaData meta = con.getMetaData();
                    System.out.println("Conexão estabelecida com sucesso!"); // Verificação da conexão

                }
            } catch (SQLException e) {
                System.err.println("Exception: " + e.getMessage());
            }
        }
        return con;
    }

    protected ResultSet getResultSet(String query) {
        Statement s;
        ResultSet rs = null;
        try {
            s = (Statement) con.createStatement();
            rs = s.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return rs;
    }

    protected int executeUpdate(PreparedStatement queryStatement) throws SQLException {
        int update;
        update = queryStatement.executeUpdate();
        return update;
    }

    protected int lastId(String tableName, String primaryKey) {
        Statement s;
        int lastId = -1;
        try {
            s = (Statement) con.createStatement();
            ResultSet rs = s.executeQuery("SELECT MAX(" + primaryKey + ") AS id FROM " + tableName);
            if (rs.next()) {
                lastId = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return lastId;
    }

    public static void terminar() {
        try {
            (DAO.getConnection()).close();
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    // Create table SQLite
    protected final boolean createTable() {
        try {
            PreparedStatement stmt;
            // Tabela cliente:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS cliente( \n"
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                    + "nome VARCHAR, \n"
                    + "endereco VARCHAR, \n"
                    + "email VARCHAR, \n"
                    + "telefone VARCHAR); \n");
            executeUpdate(stmt);

            // Tabela animal:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS animal( \n"
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                    + "nome VARCHAR, \n"
                    + "dataNascimento INTEGER, \n"
                    + "raca VARCHAR, \n"
                    + "IdEspecie INTEGER, \n"
                    + "IdCliente INTEGER); \n");
            executeUpdate(stmt);

            // Tabela especie:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS especie( \n"
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                    + "nome VARCHAR); \n");
            executeUpdate(stmt);

            // Tabela vet:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS Veterinario( \n"
                    + "idVeterinario INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                    + "nome VARCHAR, \n"
                    + "crmv VARCHAR, \n"
                    + "especialidade VARCHAR); \n");
            executeUpdate(stmt);

            // Tabela consulta:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS consulta( \n"
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                    + "data DATE, \n"
                    + "horario VARCHAR, \n"
                    + "comentario VARCHAR, \n"
                    + "id_animal INTEGER, \n"
                    + "id_vet INTEGER, \n"
                    + "id_tratamento INTEGER, \n"
                    + "terminado INTEGER); \n");
            executeUpdate(stmt);

            // Tabela exame:
            stmt = DAO.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS exame( \n"
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                    + "tipo VARCHAR, \n"
                    + "dataSolicitacao DATE, \n"
                    + "dataResultado DATE, \n"
                    + "resultado VARCHAR, \n"
                    + "id_consulta INTEGER); \n");
            executeUpdate(stmt);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


}
