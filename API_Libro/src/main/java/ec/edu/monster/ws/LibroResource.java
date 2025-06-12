package ec.edu.monster.ws;

import ec.edu.monster.model.LibroModel;
import ec.edu.monster.service.LibroService;
import jakarta.ws.rs.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("libro")
@RequestScoped
public class LibroResource {
    private final LibroService libroService = new LibroService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LibroModel> getLibros() throws SQLException {
        return libroService.obtenerLibros();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public LibroModel getLibroPorId(@PathParam("id") int id) throws SQLException {
        return libroService.obtenerLibroPorId(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void registrarLibro(LibroModel libro) throws SQLException {
        libroService.registrarLibro(libro);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizarLibro(LibroModel libro) throws SQLException {
        libroService.actualizarLibro(libro);
    }

    @DELETE
    @Path("/{id}")
    public void eliminarLibro(@PathParam("id") int id) throws SQLException {
        libroService.eliminarLibro(id);
    }
}