public class MemeflixUnDispositivo implements TipoSuscripcion {
    public String getNombre() { return "1 dispositivo"; }
    public boolean esCompatible(String nombreServicio) { return nombreServicio.equals("Memeflix"); }
}
