public class MemeflixDosDispositivos implements TipoSuscripcion {
    public String getNombre() { return "2 dispositivos"; }
    public boolean esCompatible(String nombreServicio) { return nombreServicio.equals("Memeflix"); }
}
