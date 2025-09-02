/**
 * Interfaz Observer para el patrón Observer.
 * Define el método para recibir notificaciones de cambios o eventos.
 */
public interface Observer {
    /**
     * Método llamado cuando el sujeto notifica un cambio o evento.
     * @param mensaje Mensaje de notificación enviado por el sujeto.
     */
    public void actualizar(String mensaje);
}
