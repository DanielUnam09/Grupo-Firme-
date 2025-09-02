import java.util.*;

/**
 * Representa a un cliente que puede suscribirse a servicios de streaming.
 * Implementa el patrón Observer para recibir notificaciones de los servicios.
 */
public class Cliente implements Observer {
    private String nombre;
    private double saldo;
    private Set<ServicioStreaming> serviciosActivos;
    private Map<ServicioStreaming, Integer> mesesSuscrito;

    /**
     * Crea un nuevo cliente con un nombre y un saldo inicial.
     *
     * @param nombre Nombre del cliente.
     * @param saldo  Saldo disponible en la cuenta del cliente.
     */
    public Cliente(String nombre, double saldo) {
        this.nombre = nombre;
        this.saldo = saldo;
        this.serviciosActivos = new HashSet<>();
        this.mesesSuscrito = new HashMap<>();
    }

    /**
     * Método de actualización para recibir mensajes de notificación.
     *
     * @param mensaje Mensaje enviado por el servicio de streaming.
     */
    public void actualizar(String mensaje) {
        Simulacion.escribirTransaccion(mensaje);
    }

    /**
     * Permite al cliente suscribirse a un servicio de streaming.
     *
     * @param servicio Servicio de streaming al que se desea suscribir.
     * @param tipo     Tipo de suscripción a contratar.
     */
    public void suscribirse(ServicioStreaming servicio, TipoSuscripcion tipo) {
        servicio.suscribirCliente(this, tipo);
        serviciosActivos.add(servicio);

        boolean esRegreso = mesesSuscrito.containsKey(servicio);
        String mensajeBienvenida = servicio.getMensajeBienvenida(this, esRegreso);
        actualizar(mensajeBienvenida);
    }

    /**
     * Cancela la suscripción del cliente a un servicio de streaming.
     *
     * @param servicio Servicio de streaming a cancelar.
     */
    public void cancelarSuscripcion(ServicioStreaming servicio) {
        if (serviciosActivos.contains(servicio)) {
            servicio.cancelarCliente(this);
            serviciosActivos.remove(servicio);

            String mensajeDespedida = servicio.getMensajeDespedida(this);
            actualizar(mensajeDespedida);
        }
    }

    /**
     * Cambia el tipo de suscripción de un servicio ya contratado.
     *
     * @param servicio  Servicio de streaming.
     * @param nuevoTipo Nuevo tipo de suscripción.
     */
    public void cambiarTipoSuscripcion(ServicioStreaming servicio, TipoSuscripcion nuevoTipo) {
        if (serviciosActivos.contains(servicio)) {
            servicio.cambiarTipoSuscripcion(this, nuevoTipo);
        }
    }

    /**
     * Descuenta un monto del saldo del cliente si es posible.
     *
     * @param monto Monto a descontar.
     * @return {@code true} si la operación fue exitosa, {@code false} si el saldo es insuficiente.
     */
    public boolean descontarSaldo(double monto) {
        if (saldo >= monto) {
            saldo -= monto;
            return true;
        }
        return false;
    }

    /**
     * Incrementa en uno los meses de suscripción del cliente a un servicio.
     *
     * @param servicio Servicio de streaming al que se le incrementa el contador de meses.
     */
    public void incrementarMesesSuscrito(ServicioStreaming servicio) {
        mesesSuscrito.put(servicio, mesesSuscrito.getOrDefault(servicio, 0) + 1);
    }

    // ========================
    // Getters
    // ========================

    /**
     * Obtiene el nombre del cliente.
     *
     * @return Nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el saldo actual del cliente.
     *
     * @return Saldo disponible.
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Obtiene la cantidad de meses que el cliente ha estado suscrito a un servicio específico.
     *
     * @param servicio Servicio de streaming.
     * @return Número de meses suscrito (0 si nunca ha estado suscrito).
     */
    public int getMesesSuscrito(ServicioStreaming servicio) {
        return mesesSuscrito.getOrDefault(servicio, 0);
    }

    /**
     * Obtiene los servicios de streaming a los que el cliente está suscrito actualmente.
     *
     * @return Conjunto de servicios activos.
     */
    public Set<ServicioStreaming> getServiciosActivos() {
        return serviciosActivos;
    }
}
