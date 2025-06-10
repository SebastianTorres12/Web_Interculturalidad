package ec.edu.monster.service;

import ec.edu.monster.DAO.LibroDAO;
import ec.edu.monster.model.LibroModel;
import java.sql.SQLException;
import java.util.List;

public class LibroService {
    LibroDAO DAO = new LibroDAO();
    public List<LibroModel> obtenerLibros() throws SQLException{
        return DAO.obtenerLibros();
    }         

    public LibroModel obtenerLibroPorId(int id) throws SQLException {
        return DAO.obtenerLibroPorId(id);
    }

    public void registrarLibro(LibroModel libro) throws SQLException {
        DAO.registrarLibro(libro);
    }

    public void actualizarLibro(LibroModel libro) throws SQLException {
        DAO.actualizarLibro(libro);
    }

    public void eliminarLibro(int id) throws SQLException {
        DAO.eliminarLibro(id);
    }
}