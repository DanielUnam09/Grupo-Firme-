public class Main {
    public static void main(String[] args) {
        Simulacion simulacion = new Simulacion();
        simulacion.ejecutarSimulacion();
        System.out.println("Simulación finalizada.");
        System.out.println("Resultados de la simulación guardados en 'simulacion_streaming.txt'");
        System.out.println("Saldos de los clientes:");
    }
}