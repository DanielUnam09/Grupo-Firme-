public interface EstrategiaCobro {
    double calcularCosto(TipoSuscripcion tipo, int mes);
    boolean procesarCobro(Cliente cliente, ServicioStreaming servicio, TipoSuscripcion tipo, double costo);
}
