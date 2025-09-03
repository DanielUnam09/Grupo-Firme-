/**
 * Interfaz Subject para el patrón Observer.
 * Permite agregar, remover y notificar observers (clientes) sobre cambios o eventos.
 */
public interface Subject {
    /**
     * Agrega un observer al sujeto.
     * Si el observer ya está registrado, no debe agregarse nuevamente.
     * @param observer Observer a agregar. No debe ser null.
     * @throws IllegalArgumentException si observer es null.
     */
    public void agregarObserver(Observer observer);

    /**
     * Remueve un observer del sujeto.
     * Si el observer no está registrado, no ocurre nada.
     * @param observer Observer a remover. No debe ser null.
     * @throws IllegalArgumentException si observer es null.
     */
    public void removerObserver(Observer observer);

    /**
     * Notifica a todos los observers con un mensaje.
     * @param mensaje Mensaje a enviar a los observers. No debe ser null.
     * @throws IllegalArgumentException si mensaje es null.
     */
    public void notificarObservers(String mensaje);
}
