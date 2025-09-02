/**
 * Representa la suscripción normal de HVO Max.
 * Implementa la interfaz TipoSuscripcion.
 */
public class HVONormal implements TipoSuscripcion {
    /**
     * Obtiene el nombre de la suscripción.
     * @return Nombre de la suscripción: "normal".
     */
    public String getNombre() { return "normal"; }

    /**
     * Verifica si la suscripción es compatible con el servicio dado.
     * @param nombreServicio Nombre del servicio a verificar.
     * @return true si es HVO Max, false en caso contrario.
     */
    public boolean esCompatible(String nombreServicio) { return nombreServicio.equals("HVO Max"); }
}
