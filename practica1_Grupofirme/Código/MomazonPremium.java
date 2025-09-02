public class MomazonPremium implements TipoSuscripcion {
    public String getNombre() { return "premium"; }
    public boolean esCompatible(String nombreServicio) { return nombreServicio.equals("Momazon Prime Video"); }
}
