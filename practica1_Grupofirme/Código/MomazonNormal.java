public class MomazonNormal implements TipoSuscripcion {
    public String getNombre() { return "normal"; }
    public boolean esCompatible(String nombreServicio) { return nombreServicio.equals("Momazon Prime Video"); }
}
