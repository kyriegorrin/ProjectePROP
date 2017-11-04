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
    public int fesJugada(){
        int combinacio;
        //TODO: potser necessitem control de la entrada
        System.out.println("Inserta una combinacio (format: 1234):");
        Scanner reader = new Scanner(System.in);
        combinacio = reader.nextInt();

        return combinacio;
    }
}
