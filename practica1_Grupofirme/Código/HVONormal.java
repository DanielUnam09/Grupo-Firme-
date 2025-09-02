public class HVONormal implements TipoSuscripcion {
    public String getNombre() { return "normal"; }
    public boolean esCompatible(String nombreServicio) { return nombreServicio.equals("HVO Max"); }
}
