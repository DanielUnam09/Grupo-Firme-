/**
 * Representa el servicio de streaming Momazon Prime Video.
 * Extiende la clase abstracta ServicioStreaming y gestiona recomendaciones y cobros mensuales.
 */
public class MomazonPrime extends ServicioStreaming {
    /**
     * Lista de recomendaciones que Momazon Prime Video puede ofrecer mes a mes.
     */
    private String[] recomendaciones = {
        "Te recomendamos: The Boys", "Te recomendamos: The Marvelous Mrs. Maisel",
        "Te recomendamos: Jack Ryan", "Te recomendamos: The Grand Tour",
        "Te recomendamos: Fleabag", "Te recomendamos: The Expanse",
        "Te recomendamos: Good Omens", "Te recomendamos: Upload",
        "Te recomendamos: The Wheel of Time", "Te recomendamos: Invincible",
        "Te recomendamos: The Terminal List", "Te recomendamos: Rings of Power"
    };
    
    /**
     * Constructor de MomazonPrime.
     * Inicializa el servicio con el nombre "Momazon Prime Video".
     */
    public MomazonPrime() { super("Momazon Prime Video"); }
    
    /**
     * Devuelve una recomendación para el mes dado.
     * @param mes Número de mes para la recomendación.
     * @return Recomendación correspondiente al mes.
     */
    public String getRecomendacion(int mes) {
        return recomendaciones[(mes - 1) % recomendaciones.length];
    }
    
    /**
     * Realiza el cobro mensual usando la estrategia de cobro de Momazon Prime Video.
     * @param sistema Sistema de cobro a utilizar.
     */
    public void cobrarMensualidad(SistemaCobro sistema) {
        sistema.setEstrategia(new CobradorMomazon());
        super.cobrarMensualidad(sistema);
    }
}

