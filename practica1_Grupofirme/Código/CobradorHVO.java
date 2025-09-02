public class CobradorHVO implements EstrategiaCobro {
    public double calcularCosto(TipoSuscripcion tipo, int mes) {
        if (tipo instanceof HVONormal) {
            return mes <= 3 ? 0.0 : 140.0;
        }
        return 0.0;
    }
    
    public boolean procesarCobro(Cliente cliente, ServicioStreaming servicio, TipoSuscripcion tipo, double costo) {
        if (costo == 0.0 || cliente.descontarSaldo(costo)) {
            String mensaje = cliente.getNombre() + " paga $" + costo + " por el servicio de " + servicio.getNombre();
            Simulacion.escribirTransaccion(mensaje);
            return true;
        }
        return false;
    }
}
