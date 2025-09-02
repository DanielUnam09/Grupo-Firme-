import java.util.*;
/**
 * Clase abstracta que representa un servicio de streaming.
 * Implementa el patrón Observer y define la lógica común para todos los servicios.
 */
public abstract class ServicioStreaming implements Subject {
    protected String nombre;
    protected List<Observer> observers;
    protected Map<Cliente, Integer> mesesContratados;
    protected Map<Cliente, TipoSuscripcion> tiposSuscripcion;
    
    /**
     * Constructor de ServicioStreaming.
     * @param nombre Nombre del servicio de streaming.
     */
    public ServicioStreaming(String nombre) {
        this.nombre = nombre;
        this.observers = new ArrayList<>();
        this.mesesContratados = new HashMap<>();
        this.tiposSuscripcion = new HashMap<>();
    }
    
    /**
     * Agrega un observer (cliente) al servicio.
     * @param observer Observer a agregar.
     */
    public void agregarObserver(Observer observer) {
        observers.add(observer);
    }
    
    /**
     * Remueve un observer (cliente) del servicio.
     * @param observer Observer a remover.
     */
    public void removerObserver(Observer observer) {
        observers.remove(observer);
    }
    
    /**
     * Notifica a todos los observers con un mensaje.
     * @param mensaje Mensaje a enviar.
     */
    public void notificarObservers(String mensaje) {
        for (Observer observer : observers) {
            observer.actualizar(mensaje);
        }
    }
    
    /**
     * Suscribe un cliente al servicio con un tipo de suscripción.
     * @param cliente Cliente a suscribir.
     * @param tipo Tipo de suscripción.
     */
    public void suscribirCliente(Cliente cliente, TipoSuscripcion tipo) {
        if (!tipo.esCompatible(nombre)) {
            throw new IllegalArgumentException("Tipo de suscripción incompatible");
        }
        
        agregarObserver(cliente);
        mesesContratados.put(cliente, cliente.getMesesSuscrito(this));
        tiposSuscripcion.put(cliente, tipo);
    }
    
    /**
     * Cancela la suscripción de un cliente al servicio.
     * @param cliente Cliente a cancelar.
     */
    public void cancelarCliente(Cliente cliente) {
        removerObserver(cliente);
        mesesContratados.remove(cliente);
        tiposSuscripcion.remove(cliente);
    }
    
    /**
     * Cambia el tipo de suscripción de un cliente.
     * @param cliente Cliente a modificar.
     * @param nuevoTipo Nuevo tipo de suscripción.
     */
    public void cambiarTipoSuscripcion(Cliente cliente, TipoSuscripcion nuevoTipo) {
        if (tiposSuscripcion.containsKey(cliente) && nuevoTipo.esCompatible(nombre)) {
            tiposSuscripcion.put(cliente, nuevoTipo);
        }
    }
    
    /**
     * Realiza el cobro mensual a todos los clientes suscritos.
     * Si el cliente no puede pagar, se le elimina del servicio.
     * @param sistema Sistema de cobro a utilizar.
     */
    public void cobrarMensualidad(SistemaCobro sistema) {
        List<Cliente> clientesParaEliminar = new ArrayList<>();
        
        for (Map.Entry<Cliente, TipoSuscripcion> entry : tiposSuscripcion.entrySet()) {
            Cliente cliente = entry.getKey();
            TipoSuscripcion tipo = entry.getValue();
            int mes = mesesContratados.get(cliente) + 1;
            
            if (sistema.cobrar(cliente, this, tipo, mes)) {
                // Pago exitoso
                mesesContratados.put(cliente, mes);
                cliente.incrementarMesesSuscrito(this);
                
                // Notificar progreso y recomendación
                String mensajeProgreso = cliente.getNombre() + ", llevas " + mes + 
                                       " meses usando " + nombre;
                cliente.actualizar(mensajeProgreso);
                
                String recomendacion = getRecomendacion(mes);
                cliente.actualizar(recomendacion);
            } else {
                // No pudo pagar, pierde suscripción
                clientesParaEliminar.add(cliente);
                String mensaje = cliente.getNombre() + " no pudo pagar " + nombre + 
                               " y perdió la suscripción";
                Simulacion.escribirTransaccion(mensaje);
                
                String mensajeDespedida = getMensajeDespedida(cliente);
                cliente.actualizar(mensajeDespedida);
            }
        }
        
        // Eliminar clientes que no pudieron pagar
        for (Cliente cliente : clientesParaEliminar) {
            cancelarCliente(cliente);
            cliente.getServiciosActivos().remove(this);
        }
    }
    
    /**
     * Obtiene el mensaje de bienvenida para el cliente.
     * @param cliente Cliente que recibe el mensaje.
     * @param esRegreso Indica si es una re-suscripción.
     * @return Mensaje de bienvenida personalizado.
     */
    public String getMensajeBienvenida(Cliente cliente, boolean esRegreso) {
        if (esRegreso) {
            return "Bienvenido de vuelta " + cliente.getNombre() + " a " + nombre;
        } else {
            return "Bienvenido " + cliente.getNombre() + " a " + nombre;
        }
    }
    
    /**
     * Obtiene el mensaje de despedida para el cliente que cancela o pierde la suscripción.
     * @param cliente Cliente que recibe el mensaje.
     * @return Mensaje de despedida personalizado.
     */
    public String getMensajeDespedida(Cliente cliente) {
        return "Lamentamos que dejes el servicio " + cliente.getNombre() + " de " + nombre;
    }
    
    // Métodos abstractos
    /**
     * Método abstracto para obtener una recomendación según el mes.
     * @param mes Número de mes.
     * @return Recomendación personalizada.
     */
    public abstract String getRecomendacion(int mes);
    
    // Getters
    /**
     * Obtiene el nombre del servicio de streaming.
     * @return Nombre del servicio.
     */
    public String getNombre() { return nombre; }

    /**
     * Obtiene el mapa de tipos de suscripción por cliente.
     * @return Mapa de clientes y sus tipos de suscripción.
     */
    public Map<Cliente, TipoSuscripcion> getTiposSuscripcion() { return tiposSuscripcion; }
}
