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
        int correcte = -1;
        Combinacio combinacio = new Combinacio(tauler.getLine_size());

        while(correcte < 0){
            combinacio.llegir_comb();
            correcte = combinacio.comprovar_colors(tauler.getColors());

            if (correcte < 0){
                System.out.println("La combinacio no respecta els parametres de color! Torna a ficar-la. \n");
            }
        }
        return combinacio;
    }
}
