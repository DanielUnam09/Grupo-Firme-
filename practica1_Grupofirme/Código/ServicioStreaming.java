import java.util.*;

/**
 * Clase abstracta que representa un servicio de streaming.
 * Implementa el patrón Observer y define la lógica común para todos los servicios.
 */
public abstract class ServicioStreaming implements Subject {
    protected String nombre;
    protected List<Observer> observers;
    protected List<Cliente> clientesActivos;
    protected List<TipoSuscripcion> tiposSuscripcion;
    
    /**
     * Constructor de ServicioStreaming.
     * @param nombre Nombre del servicio de streaming.
     */
    public ServicioStreaming(String nombre) {
        this.nombre = nombre;
        this.observers = new ArrayList<>();
        this.clientesActivos = new ArrayList<>();
        this.tiposSuscripcion = new ArrayList<>();
    }
    
    /**
     * Agrega un observer (cliente) al servicio.
     * @param observer Observer a agregar.
     */
    public void agregarObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
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
        clientesActivos.add(cliente);
        tiposSuscripcion.add(tipo);
    }
    
    /**
     * Cancela la suscripción de un cliente al servicio.
     * @param cliente Cliente a cancelar.
     */
    public void cancelarCliente(Cliente cliente) {
        int index = clientesActivos.indexOf(cliente);
        if (index != -1) {
            removerObserver(cliente);
            clientesActivos.remove(index);
            tiposSuscripcion.remove(index);
        }
    }
    
    /**
     * Cambia el tipo de suscripción de un cliente.
     * @param cliente Cliente a modificar.
     * @param nuevoTipo Nuevo tipo de suscripción.
     */
    public void cambiarTipoSuscripcion(Cliente cliente, TipoSuscripcion nuevoTipo) {
        int index = clientesActivos.indexOf(cliente);
        if (index != -1 && nuevoTipo.esCompatible(nombre)) {
            tiposSuscripcion.set(index, nuevoTipo);
        }
    }
    
    /**
     * Obtiene el tipo de suscripción de un cliente.
     * @param cliente Cliente a consultar.
     * @return Tipo de suscripción o null si no está suscrito.
     */
    public TipoSuscripcion getTipoSuscripcion(Cliente cliente) {
        int index = clientesActivos.indexOf(cliente);
        return index != -1 ? tiposSuscripcion.get(index) : null;
    }
    
    /**
     * Realiza el cobro mensual a todos los clientes suscritos.
     * Si el cliente no puede pagar, se le elimina del servicio.
     * @param sistema Sistema de cobro a utilizar.
     */
    public void cobrarMensualidad(SistemaCobro sistema) {
        List<Integer> indicesParaEliminar = new ArrayList<>();
        
        // Iterar usando índices para evitar problemas de concurrencia
        for (int i = 0; i < clientesActivos.size(); i++) {
            Cliente cliente = clientesActivos.get(i);
            TipoSuscripcion tipo = tiposSuscripcion.get(i);
            
            // CORRECCIÓN CRÍTICA: Calcular el mes que se va a cobrar (actual + 1)
            // pero usar los meses actuales para el cálculo del precio
            int mesesHistoricos = cliente.getMesesSuscrito(this);
            int mesQueSeCobrara = mesesHistoricos + 1;
            
            if (sistema.cobrar(cliente, this, tipo, mesQueSeCobrara)) {
                // Pago exitoso - AHORA sí incrementar el contador
                cliente.incrementarMesesSuscrito(this);
                
                // Notificar progreso y recomendación con el mes actualizado
                String mensajeProgreso = cliente.getNombre() + ", llevas " + mesQueSeCobrara + 
                                       " meses usando " + nombre;
                cliente.actualizar(mensajeProgreso);
                
                String recomendacion = getRecomendacion(mesQueSeCobrara);
                cliente.actualizar(recomendacion);
            } else {
                // No pudo pagar, marcar para eliminación
                indicesParaEliminar.add(i);
                String mensaje = cliente.getNombre() + " no pudo pagar " + nombre + 
                               " y perdió la suscripción";
                Simulacion.escribirTransaccion(mensaje);
                
                String mensajeDespedida = getMensajeDespedida(cliente);
                cliente.actualizar(mensajeDespedida);
            }
        }
        
        // Eliminar clientes que no pudieron pagar (de mayor a menor índice)
        for (int i = indicesParaEliminar.size() - 1; i >= 0; i--) {
            int index = indicesParaEliminar.get(i);
            Cliente cliente = clientesActivos.get(index);
            
            removerObserver(cliente);
            clientesActivos.remove(index);
            tiposSuscripcion.remove(index);
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
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la lista de clientes activos.
     * @return Lista de clientes suscritos.
     */
    public List<Cliente> getClientesActivos() {
        return clientesActivos;
    }
}