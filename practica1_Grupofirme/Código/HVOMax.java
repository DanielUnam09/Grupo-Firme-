public class HVOMax extends ServicioStreaming {
    private String[] recomendaciones = {
        "Te recomendamos: Game of Thrones", "Te recomendamos: Succession",
        "Te recomendamos: Euphoria", "Te recomendamos: The White Lotus",
        "Te recomendamos: Barry", "Te recomendamos: Westworld",
        "Te recomendamos: True Detective", "Te recomendamos: The Last of Us",
        "Te recomendamos: House of the Dragon", "Te recomendamos: Mare of Easttown",
        "Te recomendamos: The Sopranos", "Te recomendamos: Yellowjackets"
    };
    
    public HVOMax() { super("HVO Max"); }
    
    public String getRecomendacion(int mes) {
        return recomendaciones[(mes - 1) % recomendaciones.length];
    }
    
    public void cobrarMensualidad(SistemaCobro sistema) {
        sistema.setEstrategia(new CobradorHVO());
        super.cobrarMensualidad(sistema);
    }
}
