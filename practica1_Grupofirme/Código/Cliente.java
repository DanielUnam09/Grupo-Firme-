import java.util.*;

public class Cliente implements Observer {
    private String nombre;
    private double saldo;
    private Set<ServicioStreaming> serviciosActivos;
    private Map<ServicioStreaming, Integer> mesesSuscrito;
    
    public Cliente(String nombre, double saldo) {
        this.nombre = nombre;
        this.saldo = saldo;
        this.serviciosActivos = new HashSet<>();
        this.mesesSuscrito = new HashMap<>();
    }
    
    public void actualizar(String mensaje) {
        Simulacion.escribirTransaccion(mensaje);
    }
    
    public void suscribirse(ServicioStreaming servicio, TipoSuscripcion tipo) {
        servicio.suscribirCliente(this, tipo);
        serviciosActivos.add(servicio);
        
        boolean esRegreso = mesesSuscrito.containsKey(servicio);
        String mensajeBienvenida = servicio.getMensajeBienvenida(this, esRegreso);
        actualizar(mensajeBienvenida);
    }
    
    public void cancelarSuscripcion(ServicioStreaming servicio) {
        if (serviciosActivos.contains(servicio)) {
            servicio.cancelarCliente(this);
            serviciosActivos.remove(servicio);
            
            String mensajeDespedida = servicio.getMensajeDespedida(this);
            actualizar(mensajeDespedida);
        }
    }
    
    public void cambiarTipoSuscripcion(ServicioStreaming servicio, TipoSuscripcion nuevoTipo) {
        if (serviciosActivos.contains(servicio)) {
            servicio.cambiarTipoSuscripcion(this, nuevoTipo);
        }
    }
    
    public boolean descontarSaldo(double monto) {
        if (saldo >= monto) {
            saldo -= monto;
            return true;
        }
        return false;
    }
    
    public void incrementarMesesSuscrito(ServicioStreaming servicio) {
        mesesSuscrito.put(servicio, mesesSuscrito.getOrDefault(servicio, 0) + 1);
    }
    
    // Getters
    public String getNombre() { return nombre; }
    public double getSaldo() { return saldo; }
    public int getMesesSuscrito(ServicioStreaming servicio) {
        return mesesSuscrito.getOrDefault(servicio, 0);
    }
    public Set<ServicioStreaming> getServiciosActivos() { return serviciosActivos; }
}
