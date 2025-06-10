package ec.edu.monster.service;

import ec.edu.monster.DAO.CuentaDAO;
import ec.edu.monster.DAO.MovimientoDAO;
import ec.edu.monster.model.CuentaModel;
import ec.edu.monster.model.MovimientoModel;
import java.sql.SQLException;
import java.util.List;

public class CuentaService {

    private MovimientoDAO movimientoDAO = new MovimientoDAO();
    private CuentaDAO cuentaDAO = new CuentaDAO();

    public boolean actualizarSaldoYRegistrarMovimiento(String codigoCuenta, double valorMovimiento, String tipo, String cuentaDest) {
        try {
            // Verificar que la cuenta origen exista y esté activa
            CuentaModel cuentaOrigen = cuentaDAO.obtenerCuentaPorCodigo(codigoCuenta);
            if (cuentaOrigen == null || !cuentaOrigen.getVchCuenEstado().equals("ACTIVO")) {
                throw new IllegalArgumentException("Cuenta origen no encontrada o no está activa: " + codigoCuenta);
            }

            // Validar saldo para retiros y transferencias
            if (tipo.equalsIgnoreCase("RET") || tipo.equalsIgnoreCase("TRA")) {
                if (cuentaOrigen.getDecCuenSaldo() < valorMovimiento) {
                    throw new IllegalArgumentException("Saldo insuficiente para la operación. Saldo actual: " + cuentaOrigen.getDecCuenSaldo());
                }
            }

            String codTipo = "";
            // Determinar el tipo de movimiento
            if (tipo.equalsIgnoreCase("RET")) {
                codTipo = "004"; // Retiro
                if (!cuentaDAO.actualizarSaldoCuenta(codigoCuenta, -valorMovimiento)) {
                    return false;
                }
            } else if (tipo.equalsIgnoreCase("DEP")) {
                codTipo = "003"; // Depósito
                if (!cuentaDAO.actualizarSaldoCuenta(codigoCuenta, valorMovimiento)) {
                    return false;
                }
            } else if (tipo.equalsIgnoreCase("TRA")) {
                // Validar cuenta destino
                if (cuentaDest == null || cuentaDest.isEmpty()) {
                    throw new IllegalArgumentException("Cuenta destino es obligatoria para transferencias.");
                }
                CuentaModel cuentaDestino = cuentaDAO.obtenerCuentaPorCodigo(cuentaDest);
                if (cuentaDestino == null || !cuentaDestino.getVchCuenEstado().equals("ACTIVO")) {
                    throw new IllegalArgumentException("Cuenta destino no encontrada o no está activa: " + cuentaDest);
                }

                // Validar saldo para transferencia
                if (cuentaOrigen.getDecCuenSaldo() < valorMovimiento) {
                    throw new IllegalArgumentException("Saldo insuficiente en la cuenta origen para la transferencia. Saldo actual: " + cuentaOrigen.getDecCuenSaldo());
                }

                // Restar de la cuenta origen
                if (!cuentaDAO.actualizarSaldoCuenta(codigoCuenta, -valorMovimiento)) {
                    return false;
                }

                // Sumar a la cuenta destino
                if (!cuentaDAO.actualizarSaldoCuenta(cuentaDest, valorMovimiento)) {
                    // Rollback si falla la cuenta destino
                    cuentaDAO.actualizarSaldoCuenta(codigoCuenta, valorMovimiento);
                    return false;
                }
                codTipo = "009"; // Salida por transferencia
            } else {
                throw new IllegalArgumentException("Tipo de movimiento no soportado: " + tipo);
            }

            // Registrar el movimiento de la cuenta origen
            int numeroMovimiento = obtenerSiguienteNumeroMovimiento(codigoCuenta);
            MovimientoModel movimientoOrigen = new MovimientoModel();
            movimientoOrigen.setCodigoCuenta(codigoCuenta);
            movimientoOrigen.setNumeroMovimiento(numeroMovimiento);
            movimientoOrigen.setFechaMovimiento(new java.sql.Date(System.currentTimeMillis()));
            movimientoOrigen.setCodigoEmpleado("0001");
            movimientoOrigen.setCodigoTipoMovimiento(codTipo);
            movimientoOrigen.setImporteMovimiento(valorMovimiento);
            movimientoDAO.registrarMovimiento(movimientoOrigen);

            // Registrar el movimiento de la cuenta destino en caso de transferencia
            if (tipo.equalsIgnoreCase("TRA")) {
                int numeroMovimientoDest = obtenerSiguienteNumeroMovimiento(cuentaDest);
                MovimientoModel movimientoDest = new MovimientoModel();
                movimientoDest.setCodigoCuenta(cuentaDest);
                movimientoDest.setNumeroMovimiento(numeroMovimientoDest);
                movimientoDest.setFechaMovimiento(new java.sql.Date(System.currentTimeMillis()));
                movimientoDest.setCodigoEmpleado("0001");
                movimientoDest.setCodigoTipoMovimiento("008"); // Ingreso por transferencia
                movimientoDest.setImporteMovimiento(valorMovimiento);
                movimientoDAO.registrarMovimiento(movimientoDest);
            }

            return true;
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    private int obtenerSiguienteNumeroMovimiento(String codigoCuenta) throws SQLException {
        List<MovimientoModel> movimientos = movimientoDAO.obtenerMovimientosPorCuenta(codigoCuenta);
        int numeroMovimiento = 1;
        for (MovimientoModel movimiento : movimientos) {
            if (movimiento.getNumeroMovimiento() >= numeroMovimiento) {
                numeroMovimiento = movimiento.getNumeroMovimiento() + 1;
            }
        }
        return numeroMovimiento;
    }
}