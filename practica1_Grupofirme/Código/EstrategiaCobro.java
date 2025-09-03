/**
 * Interfaz para definir estrategias de cobro en servicios de streaming (patrón Strategy).
 */
public interface EstrategiaCobro {
    /**
     * Calcula el costo de la suscripción según el tipo y el mes.
     * @param tipo Tipo de suscripción.
     * @param mes Mes actual de la suscripción.
     * @return Costo calculado para el cobro.
     */
    double calcularCosto(TipoSuscripcion tipo, int mes);

    /**
     * Procesa el cobro al cliente por el servicio y tipo de suscripción.
     * @param cliente Cliente a cobrar.
     * @param servicio Servicio de streaming.
     * @param tipo Tipo de suscripción.
     * @param costo Monto a cobrar.
     * @return true si el cobro fue exitoso, false en caso contrario.
     */
    public boolean procesarCobro(Cliente cliente, ServicioStreaming servicio, TipoSuscripcion tipo, double costo);
}
