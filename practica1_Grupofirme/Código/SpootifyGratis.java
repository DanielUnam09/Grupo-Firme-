/**
 * Representa la suscripción gratuita de Spootify.
 * Implementa la interfaz TipoSuscripcion.
 */
public class SpootifyGratis implements TipoSuscripcion {
    /**
     * Obtiene el nombre de la suscripción.
     * @return Nombre de la suscripción: "gratis".
     */
    public String getNombre() { return "gratis"; }

    /**
     * Verifica si la suscripción es compatible con el servicio dado.
     * @param nombreServicio Nombre del servicio a verificar.
     * @return true si es Spootify, false en caso contrario.
     */
    public boolean esCompatible(String nombreServicio) { return nombreServicio.equals("Spootify"); }
}
