package Domini;

import java.util.Scanner;

public class HMaker extends Jugador{

    public HMaker() {
        super();
    }

    public HMaker(String nom, int puntuacio) {
        super(nom, puntuacio);
    }


    public Combinacio triaCombinacio(int tamany, int colors){
        int correcte = -1;
        Combinacio combinacio = new Combinacio(tamany);

        while(correcte < 0){
            System.out.println("Rang de colors: 0.."+ (colors-1));
            combinacio = new Combinacio(tamany);
            combinacio.llegir_comb();
            correcte = combinacio.comprovar_colors(colors);

            if (correcte < 0){
                System.out.println("La combinacio no respecta els parametres! Torna a ficar-la. \n");
            }
        }
        return combinacio;
    }
}
