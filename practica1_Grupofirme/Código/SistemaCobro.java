public class SistemaCobro {
    private EstrategiaCobro estrategia;
    
    public void setEstrategia(EstrategiaCobro estrategia) {
        this.estrategia = estrategia;
    }
    
    public boolean cobrar(Cliente cliente, ServicioStreaming servicio, TipoSuscripcion tipo, int mes) {
        if (estrategia == null) return false;
        
        double costo = estrategia.calcularCosto(tipo, mes);
        return estrategia.procesarCobro(cliente, servicio, tipo, costo);
    }
}
