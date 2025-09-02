/**
 * Clase que representa la suscripción de Memeflix para 4 dispositivos.
 * Implementa la interfaz {@link TipoSuscripcion} y valida compatibilidad con el servicio.
 */
public class MemeflixCuatroDispositivos implements TipoSuscripcion {
    /**
     * Obtiene el nombre de la suscripción.
     * @return Nombre de la suscripción: "4 dispositivos".
     */
    @Override
    public String getNombre() {
        return "4 dispositivos";
    }

    /**
     * Verifica si la suscripción es compatible con el servicio dado.
     * @param nombreServicio Nombre del servicio a verificar. No debe ser null.
     * @return true si es Memeflix, false en caso contrario.
     * @throws IllegalArgumentException si el nombre del servicio es null.
     */
    @Override
    public boolean esCompatible(String nombreServicio) {
        if (nombreServicio == null) {
            throw new IllegalArgumentException("El nombre del servicio no puede ser null");
        }
        return nombreServicio.equals("Memeflix");
    }
}

