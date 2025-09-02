public class CobradorThisney implements EstrategiaCobro {
    public double calcularCosto(TipoSuscripcion tipo, int mes) {
        if (tipo instanceof ThisneyNormal) {
            return mes <= 3 ? 130.0 : 160.0;
        }
        return 0.0;
    }
    
    public boolean procesarCobro(Cliente cliente, ServicioStreaming servicio, TipoSuscripcion tipo, double costo) {
        if (cliente.descontarSaldo(costo)) {
            String mensaje = cliente.getNombre() + " paga $" + costo + " por el servicio de " + servicio.getNombre();
            Simulacion.escribirTransaccion(mensaje);
            return true;
        }
        return false;
    }
}
