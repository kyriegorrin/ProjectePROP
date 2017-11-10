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
        System.out.println("Rang de colors: 0.."+ (colors-1));
        Combinacio combinacio = new Combinacio(tamany);
        combinacio.llegir_comb();
        return combinacio;
    }
}
