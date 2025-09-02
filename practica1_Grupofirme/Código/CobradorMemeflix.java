/**
 * Estrategia de cobro para el servicio Memeflix.
 * Aplica las tarifas correspondientes según el tipo de suscripción.
 */
public class CobradorMemeflix implements EstrategiaCobro {
    /**
     * Calcula el costo de la suscripción a Memeflix según el tipo.
     * @param tipo Tipo de suscripción (1, 2 o 4 dispositivos).
     * @param mes Mes actual de la suscripción (no afecta el costo).
     * @return Costo mensual correspondiente.
     */
    public double calcularCosto(TipoSuscripcion tipo, int mes) {
        if (tipo instanceof MemeflixUnDispositivo) return 120.0;
        if (tipo instanceof MemeflixDosDispositivos) return 170.0;
        if (tipo instanceof MemeflixCuatroDispositivos) return 200.0;
        return 0.0;
    }

    /**
     * Procesa el cobro al cliente por el servicio de Memeflix.
     * @param cliente Cliente a cobrar.
     * @param servicio Servicio de streaming.
     * @param tipo Tipo de suscripción.
     * @param costo Monto a cobrar.
     * @return true si el cobro fue exitoso, false en caso contrario.
     */
    public boolean procesarCobro(Cliente cliente, ServicioStreaming servicio, TipoSuscripcion tipo, double costo) {
        if (cliente.descontarSaldo(costo)) {
            String mensaje = cliente.getNombre() + " paga $" + costo + " por el servicio de " + 
                           servicio.getNombre() + " para " + tipo.getNombre();
            Simulacion.escribirTransaccion(mensaje);
            return true;
        }
        return false;
    }
}
