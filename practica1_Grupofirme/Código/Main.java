/**
 * Clase principal para ejecutar la simulación de servicios de streaming.
 */
public class Main {
    /**
     * Método principal que inicia la simulación.
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        Simulacion simulacion = new Simulacion();
        simulacion.ejecutarSimulacion();
        System.out.println("Simulación finalizada.");
        System.out.println("Resultados de la simulación guardados en 'simulacion_streaming.txt'");
        System.out.println("Saldos de los clientes:");
    }
}