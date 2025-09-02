class CobradorMomazon implements EstrategiaCobro {
    public double calcularCosto(TipoSuscripcion tipo, int mes) {
        if (tipo instanceof MomazonNormal) return 110.0;
        if (tipo instanceof MomazonPremium) return 150.0;
        return 0.0;
    }
    
    public boolean procesarCobro(Cliente cliente, ServicioStreaming servicio, TipoSuscripcion tipo, double costo) {
        if (cliente.descontarSaldo(costo)) {
            String mensaje = cliente.getNombre() + " paga $" + costo + " por el servicio de " + 
                           servicio.getNombre() + " versión " + tipo.getNombre();
            Simulacion.escribirTransaccion(mensaje);
            return true;
        }
        return false;
    }
}
