public class ThisneyPlus extends ServicioStreaming {
    private String[] recomendaciones = {
        "Te recomendamos: The Mandalorian", "Te recomendamos: WandaVision",
        "Te recomendamos: Loki", "Te recomendamos: The Falcon and Winter Soldier",
        "Te recomendamos: Hawkeye", "Te recomendamos: Moon Knight",
        "Te recomendamos: She-Hulk", "Te recomendamos: Ms. Marvel",
        "Te recomendamos: Obi-Wan Kenobi", "Te recomendamos: Andor",
        "Te recomendamos: The Book of Boba Fett", "Te recomendamos: What If...?"
    };
    
    public ThisneyPlus() { super("Thisney+"); }
    
    public String getRecomendacion(int mes) {
        return recomendaciones[(mes - 1) % recomendaciones.length];
    }
    
    public void cobrarMensualidad(SistemaCobro sistema) {
        sistema.setEstrategia(new CobradorThisney());
        super.cobrarMensualidad(sistema);
    }
}

