package ec.edu.monster.ws;

import ec.edu.monster.service.CuentaService;
import ec.edu.monster.DAO.CuentaDAO;
import ec.edu.monster.model.CuentaModel;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;
import java.sql.SQLException;

@Path("cuenta")
@RequestScoped
public class CuentaResource {

    public static class DepositoRequest {
        private String cuenta;
        private double monto;
        private String tipo;
        private String cd;

        public DepositoRequest() {}

        public String getCuenta() {
            return cuenta;
        }

        public void setCuenta(String cuenta) {
            this.cuenta = cuenta;
        }

        public double getMonto() {
            return monto;
        }

        public void setMonto(double monto) {
            this.monto = monto;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public String getCd() {
            return cd;
        }

        public void setCd(String cd) {
            this.cd = cd;
        }
    }

    public static class DepositoResponse {
        private boolean success;
        private double saldo;

        public DepositoResponse(boolean success, double saldo) {
            this.success = success;
            this.saldo = saldo;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public double getSaldo() {
            return saldo;
        }

        public void setSaldo(double saldo) {
            this.saldo = saldo;
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DepositoResponse deposito(DepositoRequest request) throws SQLException {
        CuentaService servicio = new CuentaService();
        boolean success = servicio.actualizarSaldoYRegistrarMovimiento(
            request.getCuenta(), request.getMonto(), request.getTipo(), request.getCd()
        );

        CuentaDAO cuentaDAO = new CuentaDAO();
        CuentaModel cuenta = cuentaDAO.obtenerCuentaPorCodigo(request.getCuenta());
        double saldo = (cuenta != null) ? cuenta.getDecCuenSaldo() : 0.0;

        return new DepositoResponse(success, saldo);
    }
}