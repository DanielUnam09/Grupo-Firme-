/**
 * Representa la suscripción premium de Momazon Prime Video.
 * Implementa la interfaz TipoSuscripcion.
 */
public class MomazonPremium implements TipoSuscripcion {
    /**
     * Obtiene el nombre de la suscripción.
     * @return Nombre de la suscripción: "premium".
     */
    public String getNombre() { return "premium"; }

    /**
     * Verifica si la suscripción es compatible con el servicio dado.
     * @param nombreServicio Nombre del servicio a verificar.
     * @return true si es Momazon Prime Video, false en caso contrario.
     */
    public boolean esCompatible(String nombreServicio) { return nombreServicio.equals("Momazon Prime Video"); }
}
