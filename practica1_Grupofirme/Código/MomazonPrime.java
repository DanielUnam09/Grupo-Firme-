public class MomazonPrime extends ServicioStreaming {
    private String[] recomendaciones = {
        "Te recomendamos: The Boys", "Te recomendamos: The Marvelous Mrs. Maisel",
        "Te recomendamos: Jack Ryan", "Te recomendamos: The Grand Tour",
        "Te recomendamos: Fleabag", "Te recomendamos: The Expanse",
        "Te recomendamos: Good Omens", "Te recomendamos: Upload",
        "Te recomendamos: The Wheel of Time", "Te recomendamos: Invincible",
        "Te recomendamos: The Terminal List", "Te recomendamos: Rings of Power"
    };
    
    public MomazonPrime() { super("Momazon Prime Video"); }
    
    public String getRecomendacion(int mes) {
        return recomendaciones[(mes - 1) % recomendaciones.length];
    }
    
    public void cobrarMensualidad(SistemaCobro sistema) {
        sistema.setEstrategia(new CobradorMomazon());
        super.cobrarMensualidad(sistema);
    }
}

