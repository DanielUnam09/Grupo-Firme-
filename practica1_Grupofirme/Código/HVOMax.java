/**
 * Representa el servicio de streaming HVO Max.
 * Extiende la clase abstracta ServicioStreaming.
 */
public class HVOMax extends ServicioStreaming {
    /**
     * Lista de recomendaciones que HVO Max puede ofrecer mes a mes.
     */
    private String[] recomendaciones = {
        "Te recomendamos: Game of Thrones", "Te recomendamos: Succession",
        "Te recomendamos: Euphoria", "Te recomendamos: The White Lotus",
        "Te recomendamos: Barry", "Te recomendamos: Westworld",
        "Te recomendamos: True Detective", "Te recomendamos: The Last of Us",
        "Te recomendamos: House of the Dragon", "Te recomendamos: Mare of Easttown",
        "Te recomendamos: The Sopranos", "Te recomendamos: Yellowjackets"
    };

    /**
     * Constructor de HVOMax.
     * Inicializa el servicio con el nombre "HVO Max".
     */
    public HVOMax() { super("HVO Max"); }

    /**
     * Devuelve una recomendación para el mes dado.
     * @param mes Número de mes para la recomendación.
     * @return Recomendación correspondiente al mes.
     */
    public String getRecomendacion(int mes) {
        return recomendaciones[(mes - 1) % recomendaciones.length];
    }

    /**
     * Realiza el cobro mensual usando la estrategia de cobro de HVO Max.
     * @param sistema Sistema de cobro a utilizar.
     */
    public void cobrarMensualidad(SistemaCobro sistema) {
        sistema.setEstrategia(new CobradorHVO());
        super.cobrarMensualidad(sistema);
    }
}
