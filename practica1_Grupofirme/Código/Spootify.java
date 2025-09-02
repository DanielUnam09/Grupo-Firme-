/**
 * Representa el servicio de streaming Spootify.
 * Extiende la clase abstracta ServicioStreaming y gestiona recomendaciones y cobros mensuales.
 */
public class Spootify extends ServicioStreaming {
    /**
     * Lista de recomendaciones que Spootify puede ofrecer mes a mes.
     */
    private String[] recomendaciones = {
        "Te recomendamos: Pop Hits", "Te recomendamos: Rock Classics",
        "Te recomendamos: Jazz Essentials", "Te recomendamos: Electronic Beats",
        "Te recomendamos: Hip Hop Central", "Te recomendamos: Indie Mix",
        "Te recomendamos: Classical Masterpieces", "Te recomendamos: Country Roads",
        "Te recomendamos: Latin Vibes", "Te recomendamos: R&B Soul",
        "Te recomendamos: Alternative Rock", "Te recomendamos: Reggaeton Hits"
    };
    
    /**
     * Constructor de Spootify.
     * Inicializa el servicio con el nombre "Spootify".
     */
    public Spootify() { super("Spootify"); }
    
    /**
     * Devuelve una recomendación para el mes dado.
     * @param mes Número de mes para la recomendación.
     * @return Recomendación correspondiente al mes.
     */
    public String getRecomendacion(int mes) {
        return recomendaciones[(mes - 1) % recomendaciones.length];
    }
    
    /**
     * Realiza el cobro mensual usando la estrategia de cobro de Spootify.
     * @param sistema Sistema de cobro a utilizar.
     */
    public void cobrarMensualidad(SistemaCobro sistema) {
        sistema.setEstrategia(new CobradorSpootify());
        super.cobrarMensualidad(sistema);
    }
}
