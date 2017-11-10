package Domini;

import java.util.Scanner;

public class HBreaker extends Jugador {

    public HBreaker() {
        super();
    }

    public HBreaker(String nom, int puntuacio){
        super(nom, puntuacio);
    }

    @Override
    public Combinacio fesJugada(Tauler tauler){
        Combinacio combinacio = new Combinacio(tauler.getLine_size());
        combinacio.llegir_comb();
        return combinacio;
    }
}
