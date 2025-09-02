import java.io.*;
import java.util.*;

public class Simulacion {
    private List<Cliente> clientes;
    private List<ServicioStreaming> servicios;
    private SistemaCobro sistemaCobro;
    private static PrintWriter writer;
    private int mesActual;
    
    public Simulacion() {
        clientes = new ArrayList<>();
        servicios = new ArrayList<>();
        sistemaCobro = new SistemaCobro();
        mesActual = 1;
        
        try {
            writer = new PrintWriter(new FileWriter("simulacion_streaming.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void inicializarClientes() {
        clientes.add(new Cliente("Alicia", 15000));
        clientes.add(new Cliente("Bob", 2400));
        clientes.add(new Cliente("César", 5000));
        clientes.add(new Cliente("Diego", 9000));
        clientes.add(new Cliente("Erika", 10000));
        clientes.add(new Cliente("Fausto", 5000));
    }
    
    public void inicializarServicios() {
        servicios.add(new Memeflix());
        servicios.add(new MomazonPrime());
        servicios.add(new Spootify());
        servicios.add(new ThisneyPlus());
        servicios.add(new HVOMax());
    }
    
    public void ejecutarSimulacion() {
        inicializarClientes();
        inicializarServicios();
        
        escribirTransaccion("=== INICIO DE SIMULACIÓN ===\n");
        
        // Configurar suscripciones iniciales
        configurarSuscripcionesIniciales();
        
        // Ejecutar 12 meses
        for (mesActual = 1; mesActual <= 12; mesActual++) {
            procesarMes(mesActual);
        }
        
        escribirTransaccion("\n=== FIN DE SIMULACIÓN ===");
        writer.close();
    }
    
    private void configurarSuscripcionesIniciales() {
        Cliente alicia = clientes.get(0);
        Cliente bob = clientes.get(1);
        Cliente cesar = clientes.get(2);
        Cliente diego = clientes.get(3);
        Cliente erika = clientes.get(4);
        Cliente fausto = clientes.get(5);
        
        ServicioStreaming memeflix = servicios.get(0);
        ServicioStreaming momazon = servicios.get(1);
        ServicioStreaming spootify = servicios.get(2);
        ServicioStreaming thisney = servicios.get(3);
        ServicioStreaming hvo = servicios.get(4);
        
        // Alicia - todos los servicios con versión más cara
        alicia.suscribirse(memeflix, new MemeflixCuatroDispositivos());
        alicia.suscribirse(momazon, new MomazonPremium());
        alicia.suscribirse(spootify, new SpootifyPremium());
        alicia.suscribirse(thisney, new ThisneyNormal());
        alicia.suscribirse(hvo, new HVONormal());
        
        // Bob - todos los servicios con versión más cara
        bob.suscribirse(memeflix, new MemeflixCuatroDispositivos());
        bob.suscribirse(momazon, new MomazonPremium());
        bob.suscribirse(spootify, new SpootifyPremium());
        bob.suscribirse(thisney, new ThisneyNormal());
        bob.suscribirse(hvo, new HVONormal());
        
        // César - Thisney+ y HVO Max
        cesar.suscribirse(thisney, new ThisneyNormal());
        cesar.suscribirse(hvo, new HVONormal());
        
        // Diego - HVO Max, Momazon premium, Spootify normal
        diego.suscribirse(hvo, new HVONormal());
        diego.suscribirse(momazon, new MomazonPremium());
        diego.suscribirse(spootify, new SpootifyGratis());
        
        // Erika - Memeflix 4 dispositivos, Spootify normal, HVO Max
        erika.suscribirse(memeflix, new MemeflixCuatroDispositivos());
        erika.suscribirse(spootify, new SpootifyGratis());
        erika.suscribirse(hvo, new HVONormal());
        
        // Fausto - Thisney+ y HVO Max
        fausto.suscribirse(thisney, new ThisneyNormal());
        fausto.suscribirse(hvo, new HVONormal());
    }
    
    private void procesarMes(int mes) {
        escribirTransaccion("\n--- MES " + mes + " ---");
        
        // Procesar eventos especiales según el mes
        procesarEventosEspeciales(mes);
        
        // Cobrar mensualidades de todos los servicios
        for (ServicioStreaming servicio : servicios) {
            servicio.cobrarMensualidad(sistemaCobro);
        }
        
        // Mostrar saldos al final del mes
        escribirTransaccion("Saldos al final del mes " + mes + ":");
        for (Cliente cliente : clientes) {
            escribirTransaccion(cliente.getNombre() + ": $" + cliente.getSaldo());
        }
    }
    
    private void procesarEventosEspeciales(int mes) {
        Cliente bob = clientes.get(1);
        Cliente cesar = clientes.get(2);
        Cliente diego = clientes.get(3);
        Cliente erika = clientes.get(4);
        Cliente fausto = clientes.get(5);
        
        ServicioStreaming memeflix = servicios.get(0);
        ServicioStreaming momazon = servicios.get(1);
        ServicioStreaming spootify = servicios.get(2);
        ServicioStreaming thisney = servicios.get(3);
        ServicioStreaming hvo = servicios.get(4);
        
        switch (mes) {
            case 3:
                // Erika cancela HVO Max y contrata Thisney+
                if (erika.getServiciosActivos().contains(hvo)) {
                    erika.cancelarSuscripcion(hvo);
                    erika.suscribirse(thisney, new ThisneyNormal());
                }
                break;
                
            case 4:
                // Bob se da de baja en Thisney+ y HVO Max (después de mes 3)
                // Bob da de baja Memeflix y Momazon (cuarto mes)
                bob.cancelarSuscripcion(thisney);
                bob.cancelarSuscripcion(hvo);
                bob.cancelarSuscripcion(memeflix);
                bob.cancelarSuscripcion(momazon);
                break;
                
            case 6:
                // Diego se suscribe a Thisney+
                diego.suscribirse(thisney, new ThisneyNormal());
                
                // Erika cancela todos sus servicios
                for (ServicioStreaming servicio : new ArrayList<>(erika.getServiciosActivos())) {
                    erika.cancelarSuscripcion(servicio);
                }
                
                // Fausto cancela todas sus suscripciones nuevamente
                for (ServicioStreaming servicio : new ArrayList<>(fausto.getServiciosActivos())) {
                    fausto.cancelarSuscripcion(servicio);
                }
                break;
                
            case 7:
                // César contrata Spootify premium
                cesar.suscribirse(spootify, new SpootifyPremium());
                
                // Diego: suscribe a Memeflix 1 dispositivo, cambia a Spootify premium, cancela Momazon
                diego.suscribirse(memeflix, new MemeflixUnDispositivo());
                if (diego.getServiciosActivos().contains(spootify)) {
                    diego.cancelarSuscripcion(spootify);
                }
                diego.suscribirse(spootify, new SpootifyPremium());
                diego.cancelarSuscripcion(momazon);
                break;
                
            case 5: // Fausto cancela después de 3 meses y contrata Memeflix
                for (ServicioStreaming servicio : new ArrayList<>(fausto.getServiciosActivos())) {
                    fausto.cancelarSuscripcion(servicio);
                }
                fausto.suscribirse(memeflix, new MemeflixUnDispositivo());
                break;
                
            case 8:
                // Fausto contrata nuevamente Thisney+ y HVO Max
                fausto.suscribirse(thisney, new ThisneyNormal());
                fausto.suscribirse(hvo, new HVONormal());
                break;
                
            case 10:
                // Erika contrata Momazon premium, HVO Max y Thisney+ nuevamente
                erika.suscribirse(momazon, new MomazonPremium());
                erika.suscribirse(hvo, new HVONormal());
                erika.suscribirse(thisney, new ThisneyNormal());
                break;
        }
    }
    
    public static void escribirTransaccion(String mensaje) {
        System.out.println(mensaje);
        if (writer != null) {
            writer.println(mensaje);
            writer.flush();
        }
    }
}
