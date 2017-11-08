package Drivers;

import Domini.Jugador;
import Dades.Ranking;

public class DriverRanking {
    public static void main (String[] args){
        //Inicialitza jugadors stud
        Jugador [] jugadors = new Jugador[5];
        for(int i = 0; i < 5; ++i){
            jugadors[i] = new Jugador("Jugador" + i, i*100);
        }


    }
}
