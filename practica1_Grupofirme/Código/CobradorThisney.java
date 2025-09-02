/**
 * Estrategia de cobro para el servicio Thisney+.
 * Aplica las tarifas correspondientes según el mes de suscripción.
 */
public class CobradorThisney implements EstrategiaCobro {
    /**
     * Calcula el costo de la suscripción a Thisney+ según el mes.
     * @param tipo Tipo de suscripción (debe ser ThisneyNormal).
     * @param mes Mes actual de la suscripción.
     * @return Costo mensual correspondiente.
     */
    public double calcularCosto(TipoSuscripcion tipo, int mes) {
        if (tipo instanceof ThisneyNormal) {
            return mes <= 3 ? 130.0 : 160.0;
        }
        return 0.0;
    }

    /**
     * Procesa el cobro al cliente por el servicio de Thisney+.
     * @param cliente Cliente a cobrar.
     * @param servicio Servicio de streaming.
     * @param tipo Tipo de suscripción.
     * @param costo Monto a cobrar.
     * @return true si el cobro fue exitoso, false en caso contrario.
     */
    public boolean procesarCobro(Cliente cliente, ServicioStreaming servicio, TipoSuscripcion tipo, double costo) {
        if (cliente.descontarSaldo(costo)) {
            String mensaje = cliente.getNombre() + " paga $" + costo + " por el servicio de " + servicio.getNombre();
            Simulacion.escribirTransaccion(mensaje);
            return true;
        }
        return false;
    }
}
