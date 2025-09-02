public class Spootify extends ServicioStreaming {
    private String[] recomendaciones = {
        "Te recomendamos: Pop Hits", "Te recomendamos: Rock Classics",
        "Te recomendamos: Jazz Essentials", "Te recomendamos: Electronic Beats",
        "Te recomendamos: Hip Hop Central", "Te recomendamos: Indie Mix",
        "Te recomendamos: Classical Masterpieces", "Te recomendamos: Country Roads",
        "Te recomendamos: Latin Vibes", "Te recomendamos: R&B Soul",
        "Te recomendamos: Alternative Rock", "Te recomendamos: Reggaeton Hits"
    };
    
    public Spootify() { super("Spootify"); }
    
    public String getRecomendacion(int mes) {
        return recomendaciones[(mes - 1) % recomendaciones.length];
    }
    
    public void cobrarMensualidad(SistemaCobro sistema) {
        sistema.setEstrategia(new CobradorSpootify());
        super.cobrarMensualidad(sistema);
    }
}
