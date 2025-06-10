package ec.edu.monster.DAO;

import ec.edu.monster.DBB.DBConnection;
import ec.edu.monster.model.LibroModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    // Método para obtener libros por título
    public List<LibroModel> obtenerLibrosPorTitulo(String titulo) throws SQLException {
        String sql = "SELECT ID_LIBRO, TITULO_LIBRO, AUTOR_LIBRO " +
                "FROM libro " +
                "WHERE TITULO_LIBRO LIKE ? " +
                "ORDER BY TITULO_LIBRO ASC";

        List<LibroModel> libros = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + titulo + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                LibroModel libro = new LibroModel();
                libro.setIdLibro(resultSet.getInt("ID_LIBRO"));
                libro.setTituloLibro(resultSet.getString("TITULO_LIBRO"));
                libro.setAutorLibro(resultSet.getString("AUTOR_LIBRO"));
                libros.add(libro);
            }
        }
        return libros;
    }

    // Método para registrar un nuevo libro
    public void registrarLibro(LibroModel libro) throws SQLException {
        String sql = "INSERT INTO libro (TITULO_LIBRO, AUTOR_LIBRO) VALUES (?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, libro.getTituloLibro());
            statement.setString(2, libro.getAutorLibro());
            statement.executeUpdate();
        }
    }

    // Método para obtener todos los libros
    public List<LibroModel> obtenerTodosLosLibros() throws SQLException {
        String sql = "SELECT ID_LIBRO, TITULO_LIBRO, AUTOR_LIBRO FROM libro ORDER BY TITULO_LIBRO ASC";
        List<LibroModel> libros = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                LibroModel libro = new LibroModel();
                libro.setIdLibro(resultSet.getInt("ID_LIBRO")); // Cambiado setID_LIBRO a setIdLibro
                libro.setTituloLibro(resultSet.getString("TITULO_LIBRO"));
                libro.setAutorLibro(resultSet.getString("AUTOR_LIBRO"));
                libros.add(libro);
            }
        }
        return libros;
    }

    // Método para obtener un libro por ID
    public LibroModel obtenerLibroPorId(int idLibro) throws SQLException {
        String sql = "SELECT ID_LIBRO, TITULO_LIBRO, AUTOR_LIBRO FROM libro WHERE ID_LIBRO = ?";
        LibroModel libro = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idLibro);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                libro = new LibroModel();
                libro.setIdLibro(resultSet.getInt("ID_LIBRO"));
                libro.setTituloLibro(resultSet.getString("TITULO_LIBRO"));
                libro.setAutorLibro(resultSet.getString("AUTOR_LIBRO"));
            }
        }
        return libro;
    }
}
