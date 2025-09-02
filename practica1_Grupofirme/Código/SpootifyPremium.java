/**
 * Representa la suscripción premium de Spootify.
 * Implementa la interfaz TipoSuscripcion.
 */
public class SpootifyPremium implements TipoSuscripcion {
    /**
     * Obtiene el nombre de la suscripción.
     * @return Nombre de la suscripción: "premium".
     */
    public String getNombre() { return "premium"; }

    /**
     * Verifica si la suscripción es compatible con el servicio dado.
     * @param nombreServicio Nombre del servicio a verificar.
     * @return true si es Spootify, false en caso contrario.
     */
    public boolean esCompatible(String nombreServicio) { return nombreServicio.equals("Spootify"); }
}
