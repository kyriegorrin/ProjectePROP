package Drivers;

import Domini.Jugador;
import Dades.Ranking;

import java.util.Scanner;

public class DriverRanking {
    public static void main (String[] args) {

        String comanda;
        Scanner reader = new Scanner(System.in);
        Jugador[] jugadors = new Jugador[5];
        Ranking ranking = new Ranking();

        //Inicialitza jugadors stub
        for (int i = 0; i < 5; ++i) {
            jugadors[i] = new Jugador("Jugador" + i, i * 100);
        }

        System.out.println("Inserta comanda (escriu help si necessites ajuda):");
        comanda = reader.nextLine();

        while (!comanda.equals("exit")) {
            switch (comanda) {
                case "help":
                    System.out.println("Llista de comandes:");
                    System.out.println("inicialitza -> Inicialitza la classe i llegeix les dades guardades");
                    System.out.println("insertaJugador -> Afegeix un jugador de manera ordenada");
                    System.out.println("clear -> Esborra tot el ranking (arxiu inclos). Necessita inicialitzar despres de cridar aquest metode");
                    System.out.println("toString -> Crea un string que representa el ranking (i en aquest cas el printa)\n");
                    System.out.println("---IMPORTANT: Inicialitza el ranking abans de operar amb ell. " +
                                        "La constructora no te parametres, aixi que es crida per defecte---\n");
                    System.out.println("Disposes de 5 Jugadors stud, amb identificadors del 0 fins al 4, amb puntuacions equivalents a id*100\n");
                    break;

                case "inicialitza":
                    ranking.inicialitza();
                    break;

                case "insertaJugador":
                    System.out.println("Escriu el id del jugador a afegir (0..4)");
                    ranking.insertaJugador(jugadors[reader.nextInt()].toString());
                    break;

                case "clear":
                    ranking.clear();
                    break;

                case "toString":
                    System.out.println(ranking.toString());
                    break;

                default:
                    break;
            }
            System.out.println("Inserta comanda (escriu help si necessites ajuda):");
            comanda = reader.nextLine();
        }
    }
}
