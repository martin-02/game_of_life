package dominio;

import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;

/**
* Esta clase es responsable de leer el tablero de un
* fichero en forma de ceros y unos, ir transitando de
* estados e ir mostrando dichos estados.
*/
public class Tablero {

    private static int DIMENSION = 32;
    private int[][] estadoActual;
    private int[][] estadoSiguiente = new int[DIMENSION][DIMENSION];

    /********************************************************
    * Lee el estado inicial de un fichero llamado ‘matriz‘.
    *
    * complejidad espacial: variable
    * complejidad temporal: O(n^2)
    ********************************************************/
    public void leerEstadoActual(){

        try {
            FileReader ficheroLeer = new FileReader("Matriz.txt");
            Scanner fichero = new Scanner(ficheroLeer);

            for (int i = 0; i < DIMENSION; i++) {

                String linea = fichero.nextLine();

                for (int j = 0; j < DIMENSION; j++) {
                    estadoActual [i+1][j+1] = Integer.parseInt(String.valueOf(linea.charAt(j))); //ERROR!!
                }
            }

            for (int i = 1; i < DIMENSION + 1; i++) {

                for (int j = 1; j < DIMENSION + 1; j++) {
                    int vecinasVivas = estadoActual [i - 1][j - 1] + estadoActual [i - 1][j] + estadoActual [i - 1][j + 1] + estadoActual [i][j - 1] + estadoActual [i][j + 1]
                    + estadoActual [i + 1][j - 1] + estadoActual [i + 1][j] + estadoActual [i + 1][j + 1];

                    if (estadoActual [i][j] == 1 && (vecinasVivas == 2 || vecinasVivas == 3)) {
                        estadoSiguiente [i][j] = 1;
                    }
                    else if (estadoActual [i][j] == 0 && vecinasVivas == 3) {
                        estadoSiguiente [i][j] = 1;
                    }
                    else {
                        estadoSiguiente [i][j] = 0;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // La secuencia de ceros y unos del fichero es guardada
    // en ‘estadoActual‘ y, utilizando las reglas del juego
    // de la vida, se insertan los ceros y unos
    // correspondientes en ‘estadoSiguiente‘.
    /********************************************************
    * Genera un estado inicial aleatorio. Para cada celda
    * genera un número aleatorio en el intervalo [0, 1). Si
    * el número es menor que 0,5, entonces la celda está
    * inicialmente viva. En caso contrario, está muerta.
    *
    * complejidad espacial: variable
    * complejidad temporal: O(n^2)
    *******************************************************/
    public void generarEstadoActualPorMontecarlo(){

        for (int i = 0; i < DIMENSION; i++) {

            for (int j = 0; j < DIMENSION; j++) {
                estadoActual [i+1][j+1] = (int) Math.round(Math.random());
            }
        }

        for (int i = 1; i < DIMENSION + 1; i++) {

            for (int j = 1; j < DIMENSION + 1; j++) {

                int vecinasVivas = estadoActual [i - 1][j - 1] + estadoActual [i - 1][j] + estadoActual [i - 1][j + 1] + estadoActual [i][j - 1] + estadoActual [i][j + 1]
                + estadoActual [i + 1][j - 1] + estadoActual [i + 1][j] + estadoActual [i + 1][j + 1];

                if (estadoActual [i][j] == 1 && (vecinasVivas == 2 || vecinasVivas == 3)) {
                    estadoSiguiente [i][j] = 1;
                }
                else if (estadoActual [i][j] == 0 && vecinasVivas == 3) {
                    estadoSiguiente [i][j] = 1;
                }
                else {
                    estadoSiguiente[i][j] = 0;
                }
            }
        }
    }

    // La secuencia de ceros y unos generada es guardada
    // en ‘estadoActual‘ y, utilizando las reglas del juego
    // de la vida, se insertan los ceros y unos
    // correspondientes en ‘estadoSiguiente‘.
    /********************************************************
    * Transita al estado siguiente según las reglas del
    * juego de la vida.

    * complejidad espacial: variable
    * complejidad temporal: O(n^2)
    ********************************************************/
    public void transitarAlEstadoSiguiente(){

        estadoActual = estadoSiguiente;
        estadoSiguiente = new int[DIMENSION + 2][DIMENSION + 2];

        for (int i = 1; i < DIMENSION + 1; i++) {

            for (int j = 1; j < DIMENSION + 1; j++) {

                int vecinasVivas = estadoActual [i - 1][j - 1] + estadoActual [i - 1][j] + estadoActual [i - 1][j + 1] + estadoActual [i][j - 1] + estadoActual [i][j + 1]
                + estadoActual [i + 1][j - 1] + estadoActual [i + 1][j] + estadoActual [i + 1][j + 1];

                if (estadoActual [i][j] == 1 && (vecinasVivas == 2 || vecinasVivas == 3)) {
                    estadoSiguiente [i][j] = 1;
                }
                else if (estadoActual [i][j] == 0 && vecinasVivas == 3) {
                    estadoSiguiente [i][j] = 1;
                }
                else {
                    estadoSiguiente [i][j] = 0;
                }
            }
        }
    }

    // La variable ‘estadoActual‘ pasa a tener el contenido
    // de ‘estadoSiguiente‘ y, éste útimo atributo pasar a
    // reflejar el siguiente estado.
    /*******************************************************
    * Devuelve, en modo texto, el estado actual.
    * @return el estado actual.

    * complejidad espacial: variable
    * complejidad temporal: O(n^2)
    *******************************************************/

    @Override
    public String toString() {

        StringBuilder cadena = new StringBuilder();

        for (int i = 1; i < DIMENSION + 1; i++) {

            for (int j = 1; j < DIMENSION + 1; j++) {

                if (estadoActual [i][j] == 0) {
                    cadena.append(" ");
                }
                else {
                    cadena.append("x");
                }
            }

            cadena.append("\n");
        }

        return cadena.toString();
    }
}