package ec.edu.monster.ws;

import ec.edu.monster.model.MovimientoModel;
import ec.edu.monster.service.MovimientoService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("movimiento")
@RequestScoped
public class MovimientoResource {

    public static class MovimientoRequest {
        private String numcuenta;

        public MovimientoRequest() {}

        // Getters and setters
        public String getNumcuenta() {
            return numcuenta;
        }

        public void setNumcuenta(String numcuenta) {
            this.numcuenta = numcuenta;
        }
    }
   
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovimientoModel> getMovimientos(MovimientoRequest request) throws SQLException {
        MovimientoService servicio = new MovimientoService();
        return servicio.ObtenerMovimiento(request.getNumcuenta());
    }
}