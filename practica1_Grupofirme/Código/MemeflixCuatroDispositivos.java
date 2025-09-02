public class MemeflixCuatroDispositivos implements TipoSuscripcion {
    public String getNombre() { return "4 dispositivos"; }
    public boolean esCompatible(String nombreServicio) { return nombreServicio.equals("Memeflix"); }
}

