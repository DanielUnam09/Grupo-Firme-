public class SpootifyPremium implements TipoSuscripcion {
    public String getNombre() { return "premium"; }
    public boolean esCompatible(String nombreServicio) { return nombreServicio.equals("Spootify"); }
}
