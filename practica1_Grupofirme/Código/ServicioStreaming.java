import java.util.*;
abstract class ServicioStreaming implements Subject {
    protected String nombre;
    protected List<Observer> observers;
    protected Map<Cliente, Integer> mesesContratados;
    protected Map<Cliente, TipoSuscripcion> tiposSuscripcion;
    
    public ServicioStreaming(String nombre) {
        this.nombre = nombre;
        this.observers = new ArrayList<>();
        this.mesesContratados = new HashMap<>();
        this.tiposSuscripcion = new HashMap<>();
    }
    
    public void agregarObserver(Observer observer) {
        observers.add(observer);
    }
    
    public void removerObserver(Observer observer) {
        observers.remove(observer);
    }
    
    public void notificarObservers(String mensaje) {
        for (Observer observer : observers) {
            observer.actualizar(mensaje);
        }
    }
    
    public void suscribirCliente(Cliente cliente, TipoSuscripcion tipo) {
        if (!tipo.esCompatible(nombre)) {
            throw new IllegalArgumentException("Tipo de suscripción incompatible");
        }
        
        agregarObserver(cliente);
        mesesContratados.put(cliente, cliente.getMesesSuscrito(this));
        tiposSuscripcion.put(cliente, tipo);
    }
    
    public void cancelarCliente(Cliente cliente) {
        removerObserver(cliente);
        mesesContratados.remove(cliente);
        tiposSuscripcion.remove(cliente);
    }
    
    public void cambiarTipoSuscripcion(Cliente cliente, TipoSuscripcion nuevoTipo) {
        if (tiposSuscripcion.containsKey(cliente) && nuevoTipo.esCompatible(nombre)) {
            tiposSuscripcion.put(cliente, nuevoTipo);
        }
    }
    
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
    
    public String getMensajeBienvenida(Cliente cliente, boolean esRegreso) {
        if (esRegreso) {
            return "Bienvenido de vuelta " + cliente.getNombre() + " a " + nombre;
        } else {
            return "Bienvenido " + cliente.getNombre() + " a " + nombre;
        }
    }
    
    public String getMensajeDespedida(Cliente cliente) {
        return "Lamentamos que dejes el servicio " + cliente.getNombre() + " de " + nombre;
    }
    
    // Métodos abstractos
    public abstract String getRecomendacion(int mes);
    
    // Getters
    public String getNombre() { return nombre; }
    public Map<Cliente, TipoSuscripcion> getTiposSuscripcion() { return tiposSuscripcion; }
}
