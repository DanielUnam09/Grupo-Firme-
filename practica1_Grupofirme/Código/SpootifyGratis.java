public class SpootifyGratis implements TipoSuscripcion {
    public String getNombre() { return "gratis"; }
    public boolean esCompatible(String nombreServicio) { return nombreServicio.equals("Spootify"); }
}
