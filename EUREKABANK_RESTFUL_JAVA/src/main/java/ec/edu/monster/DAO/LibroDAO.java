<<<<<<< HEAD
package ec.edu.monster.DAO;

import ec.edu.monster.model.LibroModel;
import ec.edu.monster.DBB.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    // Método para obtener todos los libros
    public List<LibroModel> obtenerLibros() throws SQLException {
        String sql = "SELECT ID_LIBRO, TITULO_LIBRO, AUTOR_LIBRO FROM libro";
        List<LibroModel> libros = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                LibroModel libro = new LibroModel();
                libro.setID_LIBRO(resultSet.getInt("ID_LIBRO"));
                libro.setTITULO_LIBRO(resultSet.getString("TITULO_LIBRO"));
                libro.setAUTOR_LIBRO(resultSet.getString("AUTOR_LIBRO"));
                libros.add(libro);
            }
        }
        return libros;
    }

    // Método para obtener un libro por ID
    public LibroModel obtenerLibroPorId(int id) throws SQLException {
        String sql = "SELECT ID_LIBRO, TITULO_LIBRO, AUTOR_LIBRO FROM libro WHERE ID_LIBRO = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                LibroModel libro = new LibroModel();
                libro.setID_LIBRO(resultSet.getInt("ID_LIBRO"));
                libro.setTITULO_LIBRO(resultSet.getString("TITULO_LIBRO"));
                libro.setAUTOR_LIBRO(resultSet.getString("AUTOR_LIBRO"));
                return libro;
            }
        }
        return null;
    }

    // Método para registrar un libro
    public void registrarLibro(LibroModel libro) throws SQLException {
        String sql = "INSERT INTO libro (ID_LIBRO, TITULO_LIBRO, AUTOR_LIBRO) VALUES (?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, libro.getID_LIBRO());
            statement.setString(2, libro.getTITULO_LIBRO());
            statement.setString(3, libro.getAUTOR_LIBRO());
            statement.executeUpdate();
        }
    }

    // Método para actualizar un libro
    public void actualizarLibro(LibroModel libro) throws SQLException {
        String sql = "UPDATE libro SET TITULO_LIBRO = ?, AUTOR_LIBRO = ? WHERE ID_LIBRO = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, libro.getTITULO_LIBRO());
            statement.setString(2, libro.getAUTOR_LIBRO());
            statement.setInt(3, libro.getID_LIBRO());
            statement.executeUpdate();
        }
    }

    // Método para eliminar un libro
    public void eliminarLibro(int id) throws SQLException {
        String sql = "DELETE FROM libro WHERE ID_LIBRO = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}

=======
package ec.edu.monster.DAO;

import ec.edu.monster.model.LibroModel;
import ec.edu.monster.DBB.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    // Método para obtener todos los libros
    public List<LibroModel> obtenerLibros() throws SQLException {
        String sql = "SELECT ID_LIBRO, TITULO_LIBRO, AUTOR_LIBRO FROM libro";
        List<LibroModel> libros = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                LibroModel libro = new LibroModel();
                libro.setID_LIBRO(resultSet.getInt("ID_LIBRO"));
                libro.setTITULO_LIBRO(resultSet.getString("TITULO_LIBRO"));
                libro.setAUTOR_LIBRO(resultSet.getString("AUTOR_LIBRO"));
                libros.add(libro);
            }
        }
        return libros;
    }

    // Método para obtener un libro por ID
    public LibroModel obtenerLibroPorId(int id) throws SQLException {
        String sql = "SELECT ID_LIBRO, TITULO_LIBRO, AUTOR_LIBRO FROM libro WHERE ID_LIBRO = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                LibroModel libro = new LibroModel();
                libro.setID_LIBRO(resultSet.getInt("ID_LIBRO"));
                libro.setTITULO_LIBRO(resultSet.getString("TITULO_LIBRO"));
                libro.setAUTOR_LIBRO(resultSet.getString("AUTOR_LIBRO"));
                return libro;
            }
        }
        return null;
    }

    // Método para registrar un libro
    public void registrarLibro(LibroModel libro) throws SQLException {
        String sql = "INSERT INTO libro (ID_LIBRO, TITULO_LIBRO, AUTOR_LIBRO) VALUES (?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, libro.getID_LIBRO());
            statement.setString(2, libro.getTITULO_LIBRO());
            statement.setString(3, libro.getAUTOR_LIBRO());
            statement.executeUpdate();
        }
    }

    // Método para actualizar un libro
    public void actualizarLibro(LibroModel libro) throws SQLException {
        String sql = "UPDATE libro SET TITULO_LIBRO = ?, AUTOR_LIBRO = ? WHERE ID_LIBRO = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, libro.getTITULO_LIBRO());
            statement.setString(2, libro.getAUTOR_LIBRO());
            statement.setInt(3, libro.getID_LIBRO());
            statement.executeUpdate();
        }
    }

    // Método para eliminar un libro
    public void eliminarLibro(int id) throws SQLException {
        String sql = "DELETE FROM libro WHERE ID_LIBRO = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}

alguien que haga commit del DAO
>>>>>>> e7b4f0671c5d1c3a95c46e687188dce668850472
