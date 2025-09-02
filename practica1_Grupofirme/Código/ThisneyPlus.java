/**
 * Representa el servicio de streaming Thisney+.
 * Extiende la clase abstracta ServicioStreaming.
 */
public class ThisneyPlus extends ServicioStreaming {
    /**
     * Lista de recomendaciones que Thisney+ puede ofrecer mes a mes.
     */
    private String[] recomendaciones = {
        "Te recomendamos: The Mandalorian", "Te recomendamos: WandaVision",
        "Te recomendamos: Loki", "Te recomendamos: The Falcon and Winter Soldier",
        "Te recomendamos: Hawkeye", "Te recomendamos: Moon Knight",
        "Te recomendamos: She-Hulk", "Te recomendamos: Ms. Marvel",
        "Te recomendamos: Obi-Wan Kenobi", "Te recomendamos: Andor",
        "Te recomendamos: The Book of Boba Fett", "Te recomendamos: What If...?"
    };

    /**
     * Constructor de ThisneyPlus.
     * Inicializa el servicio con el nombre "Thisney+".
     */
    public ThisneyPlus() { super("Thisney+"); }

    /**
     * Devuelve una recomendación para el mes dado.
     * @param mes Número de mes para la recomendación.
     * @return Recomendación correspondiente al mes.
     */
    public String getRecomendacion(int mes) {
        return recomendaciones[(mes - 1) % recomendaciones.length];
    }

    /**
     * Realiza el cobro mensual usando la estrategia de cobro de Thisney+.
     * @param sistema Sistema de cobro a utilizar.
     */
    public void cobrarMensualidad(SistemaCobro sistema) {
        sistema.setEstrategia(new CobradorThisney());
        super.cobrarMensualidad(sistema);
    }
}

