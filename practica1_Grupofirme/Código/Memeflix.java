/**
 * Representa el servicio de streaming Memeflix.
 * Extiende la clase abstracta ServicioStreaming.
 */
public class Memeflix extends ServicioStreaming {
    /**
     * Lista de recomendaciones que Memeflix puede ofrecer mes a mes.
     */
    private String[] recomendaciones = {
        "Te recomendamos: Stranger Things", "Te recomendamos: The Crown", 
        "Te recomendamos: Ozark", "Te recomendamos: Black Mirror",
        "Te recomendamos: House of Cards", "Te recomendamos: Narcos",
        "Te recomendaciones: The Witcher", "Te recomendamos: Dark",
        "Te recomendamos: Money Heist", "Te recomendamos: Bridgerton",
        "Te recomendamos: Squid Game", "Te recomendamos: Wednesday"
    };

    /**
     * Constructor de Memeflix.
     * Inicializa el servicio con el nombre "Memeflix".
     */
    public Memeflix() { super("Memeflix"); }

    /**
     * Devuelve una recomendación para el mes dado.
     * @param mes Número de mes para la recomendación.
     * @return Recomendación correspondiente al mes.
     */
    public String getRecomendacion(int mes) {
        return recomendaciones[(mes - 1) % recomendaciones.length];
    }

    /**
     * Realiza el cobro mensual usando la estrategia de cobro de Memeflix.
     * @param sistema Sistema de cobro a utilizar.
     */
    public void cobrarMensualidad(SistemaCobro sistema) {
        sistema.setEstrategia(new CobradorMemeflix());
        super.cobrarMensualidad(sistema);
    }
}
