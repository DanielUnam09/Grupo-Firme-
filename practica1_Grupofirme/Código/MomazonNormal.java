/**
 * Representa la suscripción normal de Momazon Prime Video.
 * Implementa la interfaz TipoSuscripcion.
 */
public class MomazonNormal implements TipoSuscripcion {
    /**
     * Obtiene el nombre de la suscripción.
     * @return Nombre de la suscripción: "normal".
     */
    public String getNombre() { return "normal"; }

    /**
     * Verifica si la suscripción es compatible con el servicio dado.
     * @param nombreServicio Nombre del servicio a verificar.
     * @return true si es Momazon Prime Video, false en caso contrario.
     */
    public boolean esCompatible(String nombreServicio) { return nombreServicio.equals("Momazon Prime Video"); }
}
