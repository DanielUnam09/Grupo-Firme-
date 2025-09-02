/**
 * Estrategia de cobro para el servicio HVO Max.
 * Aplica las tarifas correspondientes según el mes de suscripción.
 */
public class CobradorHVO implements EstrategiaCobro {
    /**
     * Calcula el costo de la suscripción a HVO Max según el mes.
     * @param tipo Tipo de suscripción (debe ser HVONormal).
     * @param mes Mes actual de la suscripción.
     * @return Costo mensual correspondiente.
     */
    public double calcularCosto(TipoSuscripcion tipo, int mes) {
        if (tipo instanceof HVONormal) {
            return mes <= 3 ? 0.0 : 140.0;
        }
        return 0.0;
    }

    /**
     * Procesa el cobro al cliente por el servicio de HVO Max.
     * @param cliente Cliente a cobrar.
     * @param servicio Servicio de streaming.
     * @param tipo Tipo de suscripción.
     * @param costo Monto a cobrar.
     * @return true si el cobro fue exitoso o es gratis, false en caso contrario.
     */
    public boolean procesarCobro(Cliente cliente, ServicioStreaming servicio, TipoSuscripcion tipo, double costo) {
        if (costo == 0.0 || cliente.descontarSaldo(costo)) {
            String mensaje = cliente.getNombre() + " paga $" + costo + " por el servicio de " + servicio.getNombre();
            Simulacion.escribirTransaccion(mensaje);
            return true;
        }
        return false;
    }
}
