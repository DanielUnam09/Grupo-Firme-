public class CobradorSpootify implements EstrategiaCobro {
    public double calcularCosto(TipoSuscripcion tipo, int mes) {
        if (tipo instanceof SpootifyGratis) return 0.0;
        if (tipo instanceof SpootifyPremium) return 80.0;
        return 0.0;
    }
    
    public boolean procesarCobro(Cliente cliente, ServicioStreaming servicio, TipoSuscripcion tipo, double costo) {
        if (costo == 0.0 || cliente.descontarSaldo(costo)) {
            String mensaje = cliente.getNombre() + " paga $" + costo + " por el servicio de " + 
                           servicio.getNombre() + " versión " + tipo.getNombre();
            Simulacion.escribirTransaccion(mensaje);
            return true;
        }
        return false;
    }
}
