/**
 * Clase que representa el sistema de cobro para los servicios de streaming.
 * Permite cambiar la estrategia de cobro y procesar pagos según el tipo de suscripción y mes.
 */
public class SistemaCobro {
    private EstrategiaCobro estrategia;

    /**
     * Establece la estrategia de cobro a utilizar.
     * @param estrategia Estrategia de cobro.
     */
    public void setEstrategia(EstrategiaCobro estrategia) {
        this.estrategia = estrategia;
    }

    /**
     * Realiza el cobro al cliente según la estrategia, tipo de suscripción y mes.
     * @param cliente Cliente a cobrar.
     * @param servicio Servicio de streaming.
     * @param tipo Tipo de suscripción.
     * @param mes Mes actual de la suscripción.
     * @return true si el cobro fue exitoso, false en caso contrario.
     */
    public boolean cobrar(Cliente cliente, ServicioStreaming servicio, TipoSuscripcion tipo, int mes) {
        if (estrategia == null) return false;

        double costo = estrategia.calcularCosto(tipo, mes);
        return estrategia.procesarCobro(cliente, servicio, tipo, costo);
    }
}
