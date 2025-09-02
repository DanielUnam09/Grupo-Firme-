/**
 * Estrategia de cobro para el servicio Momazon Prime Video.
 * Aplica las tarifas correspondientes según el tipo de suscripción.
 */
class CobradorMomazon implements EstrategiaCobro {
    /**
     * Calcula el costo de la suscripción a Momazon según el tipo.
     * @param tipo Tipo de suscripción (normal o premium).
     * @param mes Mes actual de la suscripción (no afecta el costo).
     * @return Costo mensual correspondiente.
     */
    public double calcularCosto(TipoSuscripcion tipo, int mes) {
        if (tipo instanceof MomazonNormal) return 110.0;
        if (tipo instanceof MomazonPremium) return 150.0;
        return 0.0;
    }

    /**
     * Procesa el cobro al cliente por el servicio de Momazon.
     * @param cliente Cliente a cobrar.
     * @param servicio Servicio de streaming.
     * @param tipo Tipo de suscripción.
     * @param costo Monto a cobrar.
     * @return true si el cobro fue exitoso, false en caso contrario.
     */
    public boolean procesarCobro(Cliente cliente, ServicioStreaming servicio, TipoSuscripcion tipo, double costo) {
        if (cliente.descontarSaldo(costo)) {
            String mensaje = cliente.getNombre() + " paga $" + costo + " por el servicio de " + 
                           servicio.getNombre() + " versión " + tipo.getNombre();
            Simulacion.escribirTransaccion(mensaje);
            return true;
        }
        return false;
    }
}
