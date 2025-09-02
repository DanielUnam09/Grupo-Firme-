/**
 * Estrategia de cobro para el servicio Spootify.
 * Aplica las tarifas correspondientes según el tipo de suscripción.
 */
public class CobradorSpootify implements EstrategiaCobro {
    /**
     * Calcula el costo de la suscripción a Spootify según el tipo.
     * @param tipo Tipo de suscripción (gratis o premium).
     * @param mes Mes actual de la suscripción (no afecta el costo).
     * @return Costo mensual correspondiente.
     */
    public double calcularCosto(TipoSuscripcion tipo, int mes) {
        if (tipo instanceof SpootifyGratis) return 0.0;
        if (tipo instanceof SpootifyPremium) return 80.0;
        return 0.0;
    }

    /**
     * Procesa el cobro al cliente por el servicio de Spootify.
     * @param cliente Cliente a cobrar.
     * @param servicio Servicio de streaming.
     * @param tipo Tipo de suscripción.
     * @param costo Monto a cobrar.
     * @return true si el cobro fue exitoso o es gratis, false en caso contrario.
     */
    public boolean procesarCobro(Cliente cliente, ServicioStreaming servicio, TipoSuscripcion tipo, double costo) {
        if (costo == 0.0 || cliente.descontarSaldo(costo)) {
            String mensaje = cliente.getNombre() + " paga $" + costo + " por el servicio de " + 
                           servicio.getNombre() + " versión " + tipo.getNombre();
            Simulacion.escribirTransaccion(mensaje);
            return true;
        }
        return false;
    }
}
