public class Memeflix extends ServicioStreaming {
    private String[] recomendaciones = {
        "Te recomendamos: Stranger Things", "Te recomendamos: The Crown", 
        "Te recomendamos: Ozark", "Te recomendamos: Black Mirror",
        "Te recomendamos: House of Cards", "Te recomendamos: Narcos",
        "Te recomendaciones: The Witcher", "Te recomendamos: Dark",
        "Te recomendamos: Money Heist", "Te recomendamos: Bridgerton",
        "Te recomendamos: Squid Game", "Te recomendamos: Wednesday"
    };
    
    public Memeflix() { super("Memeflix"); }
    
    public String getRecomendacion(int mes) {
        return recomendaciones[(mes - 1) % recomendaciones.length];
    }
    
    public void cobrarMensualidad(SistemaCobro sistema) {
        sistema.setEstrategia(new CobradorMemeflix());
        super.cobrarMensualidad(sistema);
    }
}
