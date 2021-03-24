package aplicacion;

import dominio.Tablero;
import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;

public class Principal {
    public static void main(String[] args) {
        try {

            Tablero tablero = new Tablero();
            System.out.println("SIMULACION CON TABLERO LEIDO");
            Tablero.leerEstadoActual(); //ERROR!!
            System.out.println(tablero);

            for(int i = 0; i <= 5; i++) {

                TimeUnit.SECONDS.sleep(1);
                Tablero.transitarAlEstadoSiguiente();
                System.out.println(tablero);
            }

            System.out.println("SIMULACIÓN CON TABLERO GENERADO MEDIANTE MONTECARLO");
            
            Tablero.generarEstadoActualPorMontecarlo();
            System.out.println(tablero);

            for(int i = 0; i <= 15; i++) {

                TimeUnit.SECONDS.sleep(1);
                Tablero.transitarAlEstadoSiguiente();
                System.out.println(tablero);
            }
        }

        catch(InterruptedException e) {
            System.out.println(e);
        }
    }
}
