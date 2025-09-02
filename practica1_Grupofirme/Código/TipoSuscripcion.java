public interface TipoSuscripcion {
    String getNombre();
    boolean esCompatible(String nombreServicio);
}
