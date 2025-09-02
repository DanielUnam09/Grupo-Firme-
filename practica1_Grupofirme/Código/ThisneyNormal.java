/**
 * Representa la suscripción normal de Thisney+.
 * Implementa la interfaz TipoSuscripcion.
 */
public class ThisneyNormal implements TipoSuscripcion {
    /**
     * Obtiene el nombre de la suscripción.
     * @return Nombre de la suscripción: "normal".
     */
    public String getNombre() { return "normal"; }

    /**
     * Verifica si la suscripción es compatible con el servicio dado.
     * @param nombreServicio Nombre del servicio a verificar.
     * @return true si es Thisney+, false en caso contrario.
     */
    public boolean esCompatible(String nombreServicio) { return nombreServicio.equals("Thisney+"); }
}
