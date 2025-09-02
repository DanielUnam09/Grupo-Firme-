/**
 * Interfaz para los diferentes tipos de suscripción de los servicios de streaming.
 */
public interface TipoSuscripcion {
    /**
     * Obtiene el nombre del tipo de suscripción.
     * @return Nombre de la suscripción.
     */
    String getNombre();

    /**
     * Verifica si el tipo de suscripción es compatible con el servicio dado.
     * @param nombreServicio Nombre del servicio a verificar.
     * @return true si es compatible, false en caso contrario.
     */
    boolean esCompatible(String nombreServicio);
}
